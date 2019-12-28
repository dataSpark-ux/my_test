package com.wy.newblog.test;

import java.util.concurrent.TimeUnit;

/**
 * @author wy   182页
 * @Description 中断可以理解为线程的一个标识位属性，它表示一个运行中的线程是否被其他线程进行
 * 了中断操作。中断好比其他线程对该线程打了个招呼，其他线程通过调用该线程的interrupt()
 * 方法对其进行中断操作。
 * 线程通过检查自身是否被中断来进行响应，线程通过方法isInterrupted()来进行判断是否
 * 被中断，也可以调用静态方法Thread.interrupted()对当前线程的中断标识位进行复位。如果该
 * 线程已经处于终结状态，即使该线程被中断过，在调用该线程对象的isInterrupted()时依旧会返
 * 回false。
 * @createTime 2019/04/21
 */
public class Test4Interrupted {

    public static void main(String[] args) throws InterruptedException {
        // sleepThread 不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        // busyThread 不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        // 休眠5秒，让sleepThread和BusyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is ==》" + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is ===》" + busyThread.isInterrupted());
        // 防止SleepThread和BusyThread 立刻退出
        SleepUtils.second(2);
    }


    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }


}
