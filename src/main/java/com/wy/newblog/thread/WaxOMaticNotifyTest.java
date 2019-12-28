package com.wy.newblog.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/25
 */
public class WaxOMaticNotifyTest {


    static class Car {
        private boolean waxOn = false;

        public synchronized void waxed() {
            // ready to buff
            waxOn = true;
            notifyAll();
        }

        public synchronized void buffed() {
            // 准备另一层蜡
            waxOn = false;
            notifyAll();
        }

        public synchronized void waitForWaxing() throws InterruptedException {
            while (!waxOn) {
                wait();
            }
        }

        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn) {
                wait();
            }
        }
    }

    static class WaxOn implements Runnable {

        private Car car;

        public WaxOn(Car c) {
            this.car = c;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.err.println("wax on");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.err.println("Exiting via interrupt");
            }

            System.out.println("Ending Wax on task");
        }
    }

    static class WaxOff implements Runnable {
        private Car car;

        public WaxOff(Car c) {
            this.car = c;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    car.waitForWaxing();
                    System.out.println("Wax Off!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.buffed();
                }
            } catch (InterruptedException e) {
                System.err.println("Exiting via interrupt");
            }
            System.err.println("Ending Wax Off task");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        // 跑了一会儿
        TimeUnit.SECONDS.sleep(6);
        // 终端所有任务
        exec.shutdownNow();
    }

}

