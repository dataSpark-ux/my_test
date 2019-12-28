package com.wy.newblog.thread.car;

/**
 * @author wy
 * @Description
 * @createTime 2019/03/24
 */
public class Reporter implements Runnable {

    private CarQueue carQueue;

    public Reporter(CarQueue car) {
        carQueue = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.err.println(carQueue.take());
            }
        } catch (InterruptedException e) {
            System.err.println("通过中断退出Reporter");
        }
        System.err.println("Reporter off");
    }
}
