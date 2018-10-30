package com.wy.newblog.service.impl;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author wy
 * @Date: 2018/10/24 21
 * @Description:
 */
public class DelayMessageThread implements Runnable {
    private static final int threadNum = 10;

    private static CountDownLatch cdl = new CountDownLatch(threadNum);
    @Resource
    private OrderServiceImpl order;
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
