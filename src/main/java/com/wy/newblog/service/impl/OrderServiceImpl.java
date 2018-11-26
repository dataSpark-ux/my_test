package com.wy.newblog.service.impl;

import com.wy.newblog.base.BaseServiceImpl;
import com.wy.newblog.common.CommonConst;
import com.wy.newblog.common.lock.RedisCase;
import com.wy.newblog.common.utils.RedisKeyUtils;
import com.wy.newblog.common.Result;
import com.wy.newblog.entity.OrderEntity;
import com.wy.newblog.entity.enums.IsPay;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.entity.enums.Status;
import com.wy.newblog.rabbitmq.DelaySender;
import com.wy.newblog.repository.OrderRepository;
import com.wy.newblog.service.IOrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Date: 2018/10/23 23
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {


    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DelaySender delaySender;
    @Resource
    private OrderRepository orderRepository;


    @Override
    public Result sendOrderRedisTest1(OrderEntity order) {
        order.setOrderNo(CommonConst.ID);
        order.setIsPay(IsPay.NO_PAY);
        order.setStatus(Status.NORMAL);
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.SECOND, 100);
        int second3later = (int) (cal1.getTimeInMillis() / 1000);
        redisTemplate.opsForZSet().add(RedisKeyUtils.USER_ORDERID, order.getId() + "", second3later);
        OrderEntity orderEntity = orderRepository.save(order);
        logger.info(LocalDateTime.now() + "【redis生成了一个订单任务】===》》订单ID为：[{}]", order.getId());
        sendOrderRedisTest2();
        return new Result(ResultCode.OK, orderEntity.getId().toString());
    }

    @Override
    public void sendOrderRedisTest2() {
        while (true) {
            Set<ZSetOperations.TypedTuple> items = redisTemplate.opsForZSet().rangeWithScores(RedisKeyUtils.USER_ORDERID, 0, 1);
            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待的任务");
                try {
                    Thread.sleep(900);
                    break;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            double score = ((ZSetOperations.TypedTuple) items.toArray()[0]).getScore();

            Calendar cal = Calendar.getInstance();

            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
            if (nowSecond >= score) {
                String orderId = (String) ((ZSetOperations.TypedTuple) items.toArray()[0]).getValue();
                Long orderId1 = redisTemplate.opsForZSet().remove(RedisKeyUtils.USER_ORDERID, String.valueOf(orderId));
                if (orderId1 != null && orderId1 > 0) {
                    logger.info(LocalDateTime.now() + "【redis过期了一个订单任务】===》》订单ID为：[{}]", orderId);
                    Optional<OrderEntity> orderEntity = orderRepository.findById(Long.valueOf(orderId));
                    OrderEntity order = orderEntity.get();
                    if (order.getIsPay().equals(IsPay.NO_PAY)) {
                        order.setIsPay(IsPay.REFUNDED);
                    }
                    orderRepository.save(order);
                }
            }
        }
    }

    @Override
    public Result sendOrderRedisTest3(Long orderId) throws Exception {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        OrderEntity orderEntity = order.orElseThrow(() -> new Exception("订单不存在"));
        orderEntity.setIsPay(IsPay.YES_PAY);
        OrderEntity or = orderRepository.save(orderEntity);
        Long count = redisTemplate.opsForZSet().remove(RedisKeyUtils.USER_ORDERID, orderId.toString());
        if (count == null && count < 0) {
            throw new Exception("消费订单失败");
        }
        logger.info(LocalDateTime.now() + "【redis消费了一个订单任务】===》》订单ID为：[{}]", orderId);
        return new Result(ResultCode.OK, or.getId().toString());
    }

    @Override
    public Result lockRedisTest() {
        //定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(0, 10,
                1, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.err.println("================");
                RedisCase lock = new RedisCase(redisTemplate,CommonConst.LOCK);
                lock.lock();
                //模拟业务执行15秒
                lock.sleepBySencond(15);

                lock.unlock();

            });
        }
        while (pool.getPoolSize() != 0) {

        }
        return new Result(ResultCode.OK);

    }

    @Override
    public Result sendOrderRabbitmqTest2(OrderEntity order) {
        order.setOrderNo(CommonConst.ID);
        order.setIsPay(IsPay.NO_PAY);
        order.setStatus(Status.INVALID);
        OrderEntity or = orderRepository.save(order);
        delaySender.sendDelay(or.getId());
        return new Result(ResultCode.OK, or.getId().toString());
    }

    @Override
    public Result payOrderTest1(Long orderId) {
        Optional<OrderEntity> orderOptional = orderRepository.findById(orderId);
        OrderEntity order = orderOptional.get();
        order.setIsPay(IsPay.YES_PAY);
        OrderEntity orderEntity = orderRepository.save(order);
        return new Result(ResultCode.OK, orderEntity.getId().toString());
    }

    /**
     * 生产者,生成5个订单放进去
     */
    public void productionDelayMessage() {
        for (int i = 0; i < 5; i++) {
            //延迟3秒
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 10);
            int second3later = (int) (cal1.getTimeInMillis() / 1000);
            redisTemplate.opsForZSet().add("OrderId", "OID00000001" + i, second3later);
            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);
        }
    }

    public void consumerDelayMessage() {
        while (true) {
            Set<ZSetOperations.TypedTuple> items = redisTemplate.opsForZSet().rangeWithScores("OrderId", 0, 1);

            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待的任务");
                try {
                    Thread.sleep(500);
                    break;
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                continue;
            }
            double score = ((ZSetOperations.TypedTuple) items.toArray()[0]).getScore();

            Calendar cal = Calendar.getInstance();

            int nowSecond = (int) (cal.getTimeInMillis() / 1000);
            if (nowSecond >= score) {
                String orderId = (String) ((ZSetOperations.TypedTuple) items.toArray()[0]).getValue();
                Long num = redisTemplate.opsForZSet().remove("OrderId", orderId);
                //注意，此处不做处理并发情况下会出现多出现多个线程同时消费一个资源的情况
                if (num != null && num > 0) {
                    System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
                }
            }
        }
    }
}
