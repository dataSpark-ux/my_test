package com.wy.newblog.thread.queue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/03
 */
public class Toaster implements Runnable {

    private ToastQueue toastQueue;

    private int count = 0;

    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                //做吐司
                Toast t = new Toast(count++);
                System.out.println("toaster" + t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.err.println("Toaster 中段 ");
        }
        System.out.println("Toaster off");
    }
}
