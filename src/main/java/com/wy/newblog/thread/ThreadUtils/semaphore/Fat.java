package com.wy.newblog.thread.ThreadUtils.semaphore;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/14
 */
public class Fat {

    /**
     * 防止优化
     */
    private volatile double d;

    private static int counter = 0;

    private final int id = counter++;

    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }
    }

    public void operation() {
        System.out.println("this ==>>" + this);
    }

    @Override
    public String toString() {
        return "Fat id :" + id;
    }
}
