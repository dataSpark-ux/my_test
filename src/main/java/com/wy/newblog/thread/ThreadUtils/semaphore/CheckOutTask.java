package com.wy.newblog.thread.ThreadUtils.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/14
 */
public class CheckOutTask<T> implements Runnable {

    private static int counter = 0;

    private final int id = counter++;

    private Pool<T> pool;

    public CheckOutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "checked out" + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checked in" + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckOutTask{" +
                "id=" + id +
                '}';
    }
}
