package com.wy.newblog.test;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 安全的终止线程
 * 在4.2.3节中提到的中断状态是线程的一个标识位，而中断操作是一种简便的线程间交互
 * 方式，而这种交互方式最适合用来取消或停止任务。除了中断以外，还可以利用一个boolean变
 * 量来控制是否需要停止任务并终止该线程。
 * 在代码清单4-9所示的例子中，创建了一个线程CountThread，它不断地进行变量累加，而
 * 主线程尝试对其进行中断操作和停止操作
 * @createTime 2019/04/21
 *
 */
public class Test6Shutdown {
    /**
    *示例在执行过程中，main线程通过中断操作和cancel()方法均可使CountThread得以终止。
     * 这种通过标识位或者中断操作的方式能够使线程在终止时有机会去清理资源，而不是武断地
     * 将线程停止，因此这种终止线程的做法显得更加安全和优雅。
    */
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        // 睡眠1秒，main线程对CountThread进行中断，是CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "countThread");
        countThread.start();
        // 睡眠1秒， main线程对Runner two进行取消，使CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }

}
