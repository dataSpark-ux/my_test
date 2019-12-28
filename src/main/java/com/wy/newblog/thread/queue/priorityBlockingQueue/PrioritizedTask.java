package com.wy.newblog.thread.queue.priorityBlockingQueue;


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description prioritizedQueue是一个很基础的优先级队列，它具有可阻塞的读取操作，
 * 下面是一个示例，其中优先级队列中的对象是按照优先级顺序从队列中出现的任务
 * prioritizedTask被赋予了一个优先级数字，以此来提供这种顺序
 * @createTime 2019/04/11
 */
public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {

    private Random random = new Random(47);

    public static int counter = 0;

    private final int id = counter++;

    private final int priority;

    protected static List<PrioritizedTask> sequence = new ArrayList<>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PrioritizedTask o) {

        return priority < o.priority ? 1 : (priority > o.priority ? -1 : 0);
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("this:  " + this);
    }

    @Override
    public String toString() {
        return "PrioritizedTask{" +
                "random=" + random +
                ", id=" + id +
                ", priority=" + priority +
                '}';
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    static class EndSentinel extends PrioritizedTask {

        private ExecutorService exec;

        public EndSentinel(ExecutorService e) {
            // 该计划中的最低优先级
            super(-1);
            exec = e;

        }

        @Override
        public void run() {
            int count = 0;
            for (PrioritizedTask prioritizedTask : sequence) {
                System.out.println(prioritizedTask.summary());
                if (++count % 5 == 0) {
                    System.out.println("yesOrNo");
                }
            }
            System.err.println("yesOrNo");
            System.out.println(this + "Calling shutdownNow()");
            exec.shutdownNow();
        }
    }

    static class PrioritizedTaskProducer implements Runnable {

        private Random random = new Random(47);

        private Queue<Runnable> queue;

        private ExecutorService exec;

        public PrioritizedTaskProducer(Queue<Runnable> queue, ExecutorService exec) {
            this.queue = queue;
            // 用于endSentinel
            this.exec = exec;
        }

        @Override
        public void run() {
            try {
                //未绑定队列永远不会以随机优先级快速填充它
                for (int i = 0; i < 20; i++) {
                    TimeUnit.MILLISECONDS.sleep(250);
                    queue.add(new PrioritizedTask(random.nextInt(10)));
                    Thread.yield();
                }
                // 在最高优先级的工作中流淌
                for (int i = 0; i < 10; i++) {
                    queue.add(new PrioritizedTask(i));
                }
                // 哨兵停止所有任务
                queue.add(new EndSentinel((exec)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成 prioritizedTaskProducer");
        }
    }

    static class PrioritizedTaskConsumer implements Runnable {

        private PriorityBlockingQueue<Runnable> queue;


        public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            try {
                while (!Thread.interrupted()) {
                    //使用当前线程来运行任务
                    queue.take().run();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成  PrioritizedTaskConsumer");
        }
    }

}
