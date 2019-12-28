package com.wy.newblog.thread.car;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author wy
 * @Description 机器人
 * @createTime 2019/03/24
 */
public abstract class Robot implements Runnable {

    private RobotPool pool;

    protected Assembler assembler;

    private boolean engage = false;

    public Robot(RobotPool pool) {
        this.pool = pool;
    }

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    public synchronized void enage() {
        engage = true;
        notifyAll();
    }

    /**
     * run（）的一部分，每个机器人都不同
     */
    abstract protected void performService();

    @Override
    public void run() {

        try {
            // wait until needed
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                // synchronize
                assembler.barrier().await();
                // 我们完成了那份工作
                powerDown();

            }

        } catch (InterruptedException e) {
            System.err.println("Exiting" + this + "via interrupt");
        } catch (BrokenBarrierException e) {
            //这个我们想知道的
            throw new RuntimeException(e);
        }
        System.err.println(this + "  off");
    }

    private synchronized void powerDown() throws InterruptedException {

        engage = false;
        // 断开与汇编程序的连接
        assembler = null;
        // 把自己放回到可用的池中
        pool.release(this);
        while (!engage) {
            wait();
        }
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
