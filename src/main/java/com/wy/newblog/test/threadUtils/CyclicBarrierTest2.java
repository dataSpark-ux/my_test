package com.wy.newblog.test.threadUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wy
 * @Description CyclicBarrier还提供一个更高级的构造函数CyclicBarrier（int parties，Runnable barrierAction），
 * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
 * @createTime 2019/04/28
 */
public class CyclicBarrierTest2 {

    static CyclicBarrier c = new CyclicBarrier(2,new A());

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
            try {
                c.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("101");
        }).start();
        c.await();
        System.out.println("202");

    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println("先执行我哟。。。。。");
        }
    }

}
