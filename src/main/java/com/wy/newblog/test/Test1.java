package com.wy.newblog.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @Description 原子测试  i++
 * @createTime 2019/04/21
 */
public class Test1 {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int i = 0;

    public static void main(String[] args) {
        final Test1 t = new Test1();
        List<Thread> ts = new ArrayList<>(600);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread tt = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        t.count();
                        t.safeCount();
                    }
                }
            });
            ts.add(tt);
        }

        for (Thread thread : ts) {
            thread.start();
        }

        for (Thread thread : ts) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("count===" + t.i);
        System.out.println("saftCount===" + t.atomicInteger.get());
        System.out.println("耗时====》》" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 使用cas实现线程安全计数器
     */
    private void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i, ++i);
            if (suc) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
