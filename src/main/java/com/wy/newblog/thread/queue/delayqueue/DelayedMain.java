package com.wy.newblog.thread.queue.delayqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class DelayedMain {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(random.nextInt(5000)));

        }
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayedTaskConsumer(queue));

    }
}
