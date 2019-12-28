package com.wy.newblog.Stream.thread;

import org.junit.Test;

/**
 * @suther: wy
 * @Date: 2018/10/7 18
 * @Description:
 */
public class MainThread {
    /**
     * 2.1
     * 当从Runnable到处一个类时，他必须具有run()方法，但是这个方法并无特殊之处
     * * 不会产生任何内在的线程能力，要实现线程行为，必须显示的将一个任务附到线程上
     */
    @Test
    public void mainRunnable() {
        LiftOff launch = new LiftOff();
        launch.run();
    }

    /**
     * 将Runnable对象转变为工作任务的传统方式是把它提交给一个Thread构造器
     * Thread构造器只需要一个Runnable对象，调用Thread对象的start()方法为该线程执行必须的初始化操作
     * 然后调用Runnable的run()方法，以便在这个新线程中启动该任务。尽管start()看起来产生了一个对长期运行方法的调用
     * 但是从输出中可以看到，start()迅速的返回了，因为Waiting for ListOff消息在倒计时完成之前就出现了。
     * 实际上，你产生的是对LiftOff.run()的方法调用，并且这个方法还没有完成。但是因为Liftoff.run()是由不同的线程执行的
     * 因此你仍旧可以执行basicThreads()线程中的其他操作（这种能力并不局限于basicThreads()线程），任何线程都可以启动另一个线程）。
     * 因此，程序会同时运行两个方法，basicThreads()和LiftOff.run()是程序中与其他线程“t同时”执行的代码
     */
    @Test
    public void basicThreads() {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.err.println("Waiting for LiftOff");
    }

    /**
     * 可以很容易的添加更多的线程去驱动更多的任务。如下
     * 可以看到所有任务彼此之间是如何相互呼应的
     * <p>
     * 输出说明不同的任务执行在线程被换进换出是混在了一起，这种交换是有线程调度自动控制的
     * 如果在你的机器上有多个处理器，线程调度将会在这些处理器之间默默地分发线程
     * 这个程序一次运行的结果可能与另外一次运行的结果不同，因为线程调度机制是非确定性的
     * 事实上，你可以看到。在某个版本的JDK与下个JDK版本之间，这个简单的程序的输出会产生巨大的差异。列如，较早的jdk不会频繁对时间切片
     * 因此线程1可能会首先循环到尽头，然后线程2会经历其所有循环，等等。
     * 这实际上与调用一个例程去同时执行所有的循环一样，只是启动所有线程的代价更加高昂。
     * <p>
     * 当main（）创建Thread对象时，它并没有捕获任何对象的引用。在使用普通对象时
     * 这对于垃圾回收来说是一场公平的游戏，但是在使用Thread()时，情况就不同了。每个Thread都“注册”了它自己，因此确实有一个对它的引用
     * ，而且在他的任务退出其run()并死亡之前，垃圾回收器无法清除它，你可以从输出中看到，这些任务确实运行到了结束，因此，一个线程会创建单独的执行线程
     * 在对start()的调用完成之后，它仍旧会继续存在。
     */
    @Test
    public void moreBasicThreads() {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
            System.err.println("Waiting for LiftOff");
        }
    }
}
