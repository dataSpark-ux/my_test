package com.wy.newblog.thread.queue.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> queue;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                queue.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
