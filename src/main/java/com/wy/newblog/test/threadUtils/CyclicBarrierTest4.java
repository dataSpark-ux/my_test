package com.wy.newblog.test.threadUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wy
 * @Description CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法重
 * 置。所以CyclicBarrier能处理更为复杂的业务场景。例如，如果计算发生错误，可以重置计数
 * 器，并让线程重新执行一次
 *
 *
 * CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得Cyclic-Barrier
 * 阻塞的线程数量。isBroken()方法用来了解阻塞的线程是否被中断。代码清单8-5执行完之后会
 * 返回true
 * @createTime 2019/04/28
 */
public class CyclicBarrierTest4 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());

        }
    }
}
