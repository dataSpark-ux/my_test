package com.wy.newblog.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description Exchanger（交换者）是一个用于线程间协作的工具类。
 * Exchanger用于进行线程间的数据交换。它提供一个同步点，在这个同步点两个线程可以交换彼此的数据。
 * 这两个线程通过exchange方法交换数据， 如果第一个线程先执行exchange方法，
 * 它会一直等待第二个线程也执行exchange，
 * 当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方
 * @createTime 2019/03/24
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static Executor exec = Executors.newFixedThreadPool(4);


    public static void main(String[] args) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                String A = "老公接受被打1";
                try {
                    exgr.exchange(A);
                    System.err.println("A=="+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                String B = "老公接受被打";
                try {
                    System.err.println("b=="+Thread.currentThread().getName());

                    String A = exgr.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是："
                                    + A + ",B录入是：" + B);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
