package com.wy.newblog.thread.car;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wy
 * @Description 汇编
 * @createTime 2019/03/24
 */
public class Assembler implements Runnable {

    private CarQueue chassisQueue, finishingQueue;

    private Car car;

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    private RobotPool robotPool;

    public Assembler(CarQueue cq, CarQueue fq, RobotPool robotPool) {
        chassisQueue = cq;
        finishingQueue = fq;
        this.robotPool = robotPool;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 直到机箱可用
                car = chassisQueue.take();

                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class,this);
                robotPool.hire(WheelRobot.class,this);
                cyclicBarrier.await();
                // 把汽车放到整理车里进行进一步的工作
                finishingQueue.put(car);
                System.err.println(car+"121");

            }
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.err.println("Assembler off");
    }
}
