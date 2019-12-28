package com.wy.newblog.Stream.executor;

import com.wy.newblog.Stream.thread.LiftOff;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @suther: wy
 * @Date: 2018/10/7 20
 * @Description: 我们可以用Executor来代替其他显示的创建Thread对象，LiftOff对象知道如何运行具体的任务
 * 与命令模式一样，他暴露了要执行的单一方法。
 * ExecutorService(具有服务生命周期的Executor,例如关闭)知道如何构建恰当的上下文来执行Runnable对象，在下面的示例中
 * CachedThreadPool将为每一个任务都创建一个线程。
 * 注意，ExecutorService对象是使用静态的Executor方法创建的。这个方法可以确定其Executor类型
 * 从而简化了并发编程
 */
public class CachedThreadPool {
    /**
     * 非常常见的情况是，单个的Executor被使用来创建和管理系统中所有的任务。
     * 对shutdown()方法的调用可以防止新任务被提交给这个executor,当前线程（在本例中，即驱动poolOne()的线程）
     * 将继续运行在shutdown()被调用之前提交的所有任务，这个程序将在Executor中所有任务完成之后尽快退出
     * <p>
     * .
     */
    @Test
    public void CachedPool() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }

    /**
     * 你可以很容易的将上面面示例中的CachedPool替换为不同类型的Executor
     * fixedThreadPool使用了有限的线程集来执行所提交的任务
     * <p>
     * 有了FixedThreadPool，就可以一次性预先执行代价高昂的线程分配，因而也就可以限制线程的数量了
     * 这可以节省时间，因为你不用为每个任务都固定的付出创建线程的开销。在事件驱动的系统中，需要线程的事件处理器
     * 通过直接从池中获取线程，也可以如你所愿的尽快的得到服务，你不会滥用可获得的资源，因为FixedThreadPool使用的Thread
     * 对象的数量是有界的
     * <p>
     * <p>
     * 注意：在任何线程池中，现有线程在可能的情况下，都会被自动复用
     */
    @Test
    public void fixedThreadPool() {
        // 构造函数参数是线程数
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }

    /**
     * 假设你有大量的线程，那他们运行的任务将使用文件系统
     * 你可以使用SingleThreadExecutor来运行这些线程，以确保任意时刻在任何线程中都只有唯一的任务在运行
     * 在这种方式中，你不需要在共享资源上处理同步（同时也不会过度使用文件系统），
     * 有时更好的解决方案是资源上同步，但是SingleThreadExecutor可以让你省去只是为了维持某些事物的原型而进行的各种协调努力
     * 通过序列化任务，你可以消除对序列化对象的需求
     */
    @Test
    public void singleThreadPool() {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }


}
