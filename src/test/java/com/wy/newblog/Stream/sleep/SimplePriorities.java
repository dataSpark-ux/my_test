package com.wy.newblog.Stream.sleep;

import org.apache.catalina.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @suther: wy
 * @Date: 2018/10/14 17
 * @Description: 线程的优先级
 */
public class SimplePriorities implements Runnable {


    private int countDown = 5;

    private volatile double d; //No optimization

    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "SimplePriorities{" +
                "countDown=" + countDown +
                ", d=" + d +
                ", priority=" + priority +
                '}';
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();

                }
            }
            System.err.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }
}
