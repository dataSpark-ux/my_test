package com.wy.newblog.service.impl;

import com.wy.newblog.common.utils.ApplicationContextProvider;
import com.wy.newblog.core.Result;
import com.wy.newblog.service.IOrderService;
import com.wy.newblog.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author wy
 * @Date: 2018/10/24 21
 * @Description:
 */
@Service
public class ThreadServiceImpl implements IThreadService {
    private static final int threadNum = 10;

    private static CountDownLatch cdl = new CountDownLatch(threadNum);

    @Resource
    private OrderServiceImpl orderService;
    @Override
    public Result threadOrderTest() {
        orderService.productionDelayMessage();
        for (int i = 0; i < threadNum; i++) {
            new Thread(new DelayMessage()).start();
            cdl.countDown();
        }
        return null;
    }

    static class DelayMessage implements Runnable {

        public OrderServiceImpl order;

        public DelayMessage() {
            order = ApplicationContextProvider.getBean(OrderServiceImpl.class);
        }

        @Override
        public void run() {
            try {
                cdl.await();
            } catch (InterruptedException e) {
                // TODO: 2018/10/24
                e.printStackTrace();
            }

//            order.consumerDelayMessage();
        }
    }

}
