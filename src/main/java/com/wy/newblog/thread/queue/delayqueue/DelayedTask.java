package com.wy.newblog.thread.queue.delayqueue;

import com.sun.org.apache.xpath.internal.Arg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description delayQueue 是一个无界的BlockingQueue,用于放置实现了Delayed接口的对象，
 * 其中的对象只有在到期时才能从队列中取出，这种队列是有序，即队头对象的延期到期的时间最长、
 * 如果没有任何延迟 到期，那么就不会有任何头元素，并且poll()将返回null(正因为这样，你不能将null放进这种队列里)
 *
 * 下面时一个示例，其中delayed对象本身就是任务，而DelayedTaskConsumer将最紧急的任务（到期时间最长的任务）从队列里取出，
 * 然后运行它，注意，这样的DelayQueue就成为了优先级的一种变体
 * @createTime 2019/04/10
 */
public class DelayedTask implements Runnable, Delayed {

    private static int counter = 0;

    private final int id = counter++;

    private final int delta;

    private final long trigger;

    private static List<DelayedTask> sequence = new ArrayList<>();


    public DelayedTask(int delayInMilliseconds) {
        this.delta = delayInMilliseconds;
        trigger = System.nanoTime()+TimeUnit.NANOSECONDS.convert(delta,TimeUnit.MILLISECONDS);
        sequence.add(this);
    }


    @Override
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if (trigger < that.trigger) {
            return -1;
        }
        if (trigger > that.trigger) {

            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.err.println(this + "");
    }

    @Override
    public String toString() {
        return "DelayedTask{" +
                "id=" + id +
                ", delta=" + delta +
                ", trigger=" + trigger +
                '}';
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return  unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    static class EndSentinel extends DelayedTask {

        private ExecutorService exec;

        public EndSentinel(int delayInMilliseconds, ExecutorService e) {
            super(delayInMilliseconds);
            exec = e;
        }

        @Override
        public void run() {
            for (DelayedTask task : sequence) {
                System.err.println(task.summary() + "");
            }
            System.out.println();
            System.out.println(this+"calling shutdownNow");
            exec.shutdownNow();
        }
    }


}
