package com.wy.newblog.test.threadUtils;

/**
 * @author wy
 * @Description CountDownLatch允许一个或多个线程等待其他线程完成操作。
 * 假如有这样一个需求：我们需要解析一个Excel里多个sheet的数据，此时可以考虑使用多
 * 线程，每个线程解析一个sheet里的数据，等到所有的sheet都解析完之后，程序需要提示解析完
 * 成。在这个需求中，要实现主线程等待所有线程完成sheet的解析操作，最简单的做法是使用
 * join()方法
 * @createTime 2019/04/28
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread parser2 = new Thread(() -> {
            System.out.println("parser2 finish");
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all parser finish");
    }
}
