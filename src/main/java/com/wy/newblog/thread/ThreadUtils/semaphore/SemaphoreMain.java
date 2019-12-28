package com.wy.newblog.thread.ThreadUtils.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 在main()中，创建了一个持有Fat对象的Pool,而一组CheckoutTask则开始操练这个Pool.然后
 * main线程签出池中的Fat对象，但是并不是签入它们。一旦池总所有的对象都被签出，semaphore()将不在允许执行任何签出操作。
 * blocked的run()方法因此会被阻塞，2秒钟之后，cancel()方法将被调用，以此来挣脱Futrue的束缚。注意，冗余的签入将被Pool忽略
 * <p>
 * 这个示例依赖于Pool的客户端严格的并愿意签入所持有的对象，
 * 当其工作时，这是最简单的解决方案。如果你无法总是可以依赖于此。
 * @createTime 2019/04/14
 */
public class SemaphoreMain {

    private static int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        System.err.println(args.length);
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckOutTask<Fat>(pool));
        }
        System.out.println("All checkoutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat fat = pool.checkOut();
            System.out.println(i + ":main thread checked out");
            fat.operation();
            list.add(fat);
        }
        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    //信号量可以防止额外的结账
                    //所以电话被阻止了
                    pool.checkOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        // 打破被阻止的电话
        blocked.cancel(true);
        System.out.println("checking in objects in" + list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat f : list) {
            //第二次检查被忽略了
            pool.checkIn(f);
        }
        exec.shutdown();
    }
}
