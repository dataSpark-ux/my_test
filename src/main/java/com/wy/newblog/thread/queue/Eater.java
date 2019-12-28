package com.wy.newblog.thread.queue;

/**
 * @author wy
 * @Description 吃吐司
 * @createTime 2019/04/03
 */
public class Eater implements Runnable {

    private ToastQueue finishedQueue;

    private int counter = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //直到下一片吐司可用
                Toast t = finishedQueue.take();
                // 验证吐司是否有序，并且所有碎片都被卡住了
                if (t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED) {
                    System.err.println(">>>>Error" + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp!" + t);
                }
            }
        } catch (InterruptedException e) {
            System.err.println("Eater 中断");
        }
        System.out.println("Eater off");
    }
}
