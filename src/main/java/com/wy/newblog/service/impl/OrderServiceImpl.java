package com.wy.newblog.service.impl;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author wy
 * @Date: 2018/10/23 23
 * @Description:
 */
@Service
public class OrderServiceImpl implements IOrderService{



    @Resource
    private  RedisTemplate redisTemplate;

    @Override
    public Result sendOrderTest1() {
        productionDelayMessage();
        consumerDelayMessage();
        return new Result(ResultCode.OK);
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
                Long orderId1 = redisTemplate.opsForZSet().remove("OrderId", orderId);
                if (orderId1 != null && orderId1 > 0) {
                    System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
                }
            }
        }
    }
}
