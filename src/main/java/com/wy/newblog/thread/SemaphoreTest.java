package com.wy.newblog.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wy
 * @Description Semaphore（信号量）是用来控制同时访问特定资源的线程数量，
 * 它通过协调各个线程，以保证合理的使用公共资源。
 * 很多年以来，我都觉得从字面上很难理解Semaphore所表达的含义，只能把它比作是控制流量的红绿灯，
 * 比如XX马路要限制流量，只允许同时有一百辆车在这条路上行使，其他的都必须在路口等待，
 * 所以前一百辆车会看到绿灯，可以开进这条马路，后面的车会看到红灯，不能驶入XX马路，
 * 但是如果前一百辆中有五辆车已经离开了XX马路，那么后面就允许有5辆车驶入马路，这个例子里说的车就是线程，
 * 驶入马路就表示线程在执行，离开马路就表示线程执行完成，看见红灯就表示线程被阻塞，不能执行。
 * @createTime 2019/03/24
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 5;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    /**
    *Semaphore(10)表示允许10个线程获取许可证，
    */
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //使用Semaphore的acquire()获取一个许可证
                        semaphore.acquire();
                        System.out.println(semaphore.availablePermits()+"====save data");
                        Thread.sleep(5000);
                        //使用完之后调用release()归还许可证。还可以用tryAcquire()方法尝试获取许可证。
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

}
