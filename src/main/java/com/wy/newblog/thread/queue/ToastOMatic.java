package com.wy.newblog.thread.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description toast 是一个使用enum值的优秀例子。注意，在这个例子中没有任何显式的同步
 * （即使用lock对象或是synchronized关键字的同步），因为同步队列（其内部是同步的）和系统的设计隐式的管理了，
 * 每片toast在任何时刻都只由一个任务在操作。因为队列的阻塞，是的处理过程将yA被自动挂起和恢复。你可以看到由blockingQueue产生的简化十分明显
 * 在使用显式的wait()和notifyAll()时存在的类和类之间的耦合被消除了，因为每个类都只和它的BlockingQueue通信
 * @createTime 2019/04/03
 */
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                bufferedQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, bufferedQueue));
        exec.execute(new Jammer(bufferedQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();


    }
}
