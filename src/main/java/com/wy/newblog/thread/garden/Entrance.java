package com.wy.newblog.thread.garden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 公园入口
 * @createTime 2019/03/25
 */
public class Entrance implements Runnable {

    private static Count count = new Count();

    private static List<Entrance> entrances = new ArrayList<>();

    private int number;
    /**
     * 不需要同步来阅读
     */
    private final int id;

    private static volatile boolean canceled = false;

    /**
     * 易失性场上的原子操作
     */
    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
        this.id = id;
        // 将此任务保留在列表中，还可以防止死亡任务的垃圾收集
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }

            System.err.println( Thread.currentThread().getName()+"=="+this + "Total: " + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.err.println("sleep interrupted");
            }
        }
        System.out.println("stopping" + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance" + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.err.println("一些未终止的任务");
        }
        System.out.println("Total: "+Entrance.getTotalCount());
        System.out.println("Sum of Entrances: : "+Entrance.sumEntrances());
    }
}
