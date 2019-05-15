package com.wy.newblog.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 等待/通知机制
 * 等待/通知机制
 * 一个线程修改了一个对象的值，而另一个线程感知到了变化，然后进行相应的操作，整个
 * 过程开始于一个线程，而最终执行又是另一个线程。前者是生产者，后者就是消费者，这种模
 * 式隔离了“做什么”（what）和“怎么做”（How），在功能层面上实现了解耦，体系结构上具备了良
 * 好的伸缩性，但是在Java语言中如何实现类似的功能呢？
 * 简单的办法是让消费者线程不断地循环检查变量是否符合预期，如下面代码所示，在
 * while循环中设置不满足的条件，如果条件满足则退出while循环，从而完成消费者的工作。
 * @createTime 2019/04/22
 */
public class Test7WaitNotify {

    static boolean flag = true;

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            // 枷锁，用于lock的Monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    System.out.println(Thread.currentThread()+" " +
                            "flag is true .wait@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 当条件满足时，完成工作
                System.out.println(Thread.currentThread()+" flag is false. running @ "
                        +new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            // 枷锁，拥有lock的monitor
            synchronized (lock) {
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁
                // 直到当前线程放了lock后，WaitThread才能从wait方法返回
                System.out.println(Thread.currentThread()+" hold lock. notify @ "+
                        new SimpleDateFormat("HH:mm::ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
