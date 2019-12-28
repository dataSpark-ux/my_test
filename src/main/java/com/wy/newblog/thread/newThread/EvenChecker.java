package com.wy.newblog.thread.newThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/02
 */
public class EvenChecker implements Runnable {

    private IntGenerator generator;

    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.generator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + "not even!");
                // cancels all EvenCheckers取消所有EvenCheckers
                generator.cancel();
            }
        }
    }

    /**
     * 测试任何类型的intgenerator
     */
    public static void test(IntGenerator generator, int count) {
        System.out.println("press control-c to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(generator, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
