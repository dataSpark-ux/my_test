package com.wy.newblog.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description
 * @createTime 2019/03/24
 */
public class CountDownLathTest {

    static CountDownLatch countDownLatch = new CountDownLatch(10);

    static class Boss extends Thread {
        @Override
        public void run() {
            System.out.println("Boss在会议室等待，总共有" + countDownLatch.getCount() + "个人开会...");

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.err.println("所有人已到期，开会吧");
        }
    }

    static class Staff extends Thread{

        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + "，到达会议室....");
            //员工到达会议室 count - 1
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Boss线程启动
        new Boss().start();

        for(int i = 0 ; i < 10; i++){
            Thread.sleep(5000);
            new Staff().start();
        }
    }
}
