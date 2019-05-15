package com.wy.newblog.thread.queue;

/**
 * @author wy
 * @Description 将果酱涂在黄油吐司上
 * @createTime 2019/04/03
 */
public class Jammer implements Runnable {

    private ToastQueue bufferedQueue, finishedQueue;

    public Jammer(ToastQueue bufferedQueue, ToastQueue finishedQueue) {
        this.bufferedQueue = bufferedQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 直到下一片吐司可用
                Toast toast = bufferedQueue.take();
                toast.jam();
                System.out.println("jammer=="+toast);
                finishedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.err.println("jammer 中断");
        }
    }
}
