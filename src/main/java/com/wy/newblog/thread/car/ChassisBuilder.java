package com.wy.newblog.thread.car;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/24
 */
public class ChassisBuilder implements Runnable {

    private CarQueue carQueue;

    private int counter = 0;

    public ChassisBuilder(CarQueue car) {
        this.carQueue = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MICROSECONDS.sleep(500);
                // 制作底盘
                Car c = new Car(counter++);
                System.err.println("ChassisBuilder create" + c);
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted ChassisBuilder");
        }

        System.err.println("ChassisBuilder off ");
    }
}
