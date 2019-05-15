package com.wy.newblog.thread.queue;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/03
 */
public class Butterer implements Runnable {

    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dryQueue, ToastQueue butteredQueue) {
        this.dryQueue = dryQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //阻止，直到下一件os吐司可用
                Toast t = dryQueue.take();
                t.butter();
                System.out.println("buffer" + t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.err.println("butter 中断");
        }
        System.out.println("Butterer off");
    }
}
