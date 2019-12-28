package com.wy.newblog.thread.queue.priorityBlockingQueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/11
 */
public class PriorityBlockingQueueDemoMain {
    public static void main(String[] args) {
        Random random = new Random(47);
        System.err.println(random.nextInt(80));
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<>();

        exec.execute(new PrioritizedTask.PrioritizedTaskProducer(queue, exec));
        exec.execute(new PrioritizedTask.PrioritizedTaskConsumer(queue));

    }
}
