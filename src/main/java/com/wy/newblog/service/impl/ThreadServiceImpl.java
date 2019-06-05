package com.wy.newblog.service.impl;

import com.wy.newblog.common.utils.ApplicationContextProvider;
import com.wy.newblog.common.Result;
import com.wy.newblog.entity.OrderEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.repository.OrderRepository;
import com.wy.newblog.service.IThreadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author wy
 * @Date: 2018/10/24 21
 * @Description:
 */
@Service
public class ThreadServiceImpl implements IThreadService {
    private static final int threadNum = 10;
    /**
     * 利用它可以实现类似计数器的功能。比如有一个任务A，
     * 它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
     * 参数count为计数值
     */
    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    @Resource
    private OrderServiceImpl orderService;

    @Resource(name = "consumerQueueThreadPool")
    private ExecutorService exec;

    @Resource
    private OrderRepository orderRepository;

    @Override
    public Result threadOrderTest() {
        orderService.productionDelayMessage();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new DelayMessage()).start();

            ////将count值减1
            cdl.countDown();
        }
        return null;
    }

    @Override
    public Result countDownLatch() {
        PageRequest pageable = PageRequest.of(0, 50);
        Page<OrderEntity> all = orderRepository.findAll(pageable);
        CopyOnWriteArrayList<Page<OrderEntity>> pages = new CopyOnWriteArrayList<>();
        pages.add(all);
        CountDownLatch c = new CountDownLatch(all.getTotalPages()-1);
        for (int i = 1; i < all.getTotalPages(); i++) {
            PageRequest page = PageRequest.of(i, 50);
            exec.execute(() -> {
                Page<OrderEntity> orders = orderRepository.findAll(page);
                pages.add(orders);
                c.countDown();
            });

        }
        try {
            c.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(pages.size());
        return new Result(ResultCode.OK, pages);
    }

    @Override
    public Result pageAll() {
        PageRequest pageable = PageRequest.of(0, 50);
        List<Page<OrderEntity>> list = new ArrayList<>();
        Page<OrderEntity> all = orderRepository.findAll(pageable);
        list.add(all);
        for (int i = 1; i < all.getTotalPages(); i++) {
            PageRequest page = PageRequest.of(i, 50);
            Page<OrderEntity> orders = orderRepository.findAll(page);
            list.add(orders);
        }
        System.err.println(list.size());
        return new Result(ResultCode.OK,list);
    }

    static class DelayMessage implements Runnable {

        public OrderServiceImpl order;

        public DelayMessage() {
            order = ApplicationContextProvider.getBean(OrderServiceImpl.class);
        }

        @Override
        public void run() {
            try {
                //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
                cdl.await();
            } catch (InterruptedException e) {
                // TODO: 2018/10/24
                e.printStackTrace();
            }

//            order.consumerDelayMessage();
        }
    }

}
