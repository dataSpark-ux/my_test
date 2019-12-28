package com.wy.newblog.thread.test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 随机将客户添加到队列中
 * @createTime 2019/04/14
 */
public class CustomerGenerator implements Runnable {

    private CustomerLine customers;

    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.err.println("CustomerGenerator Interrupted");
        }
        System.err.println("CustomerGenerator terminating");
    }
}
