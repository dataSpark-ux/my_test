//package com.wy.newblog.thread;
//
//import java.io.IOException;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//
///**
// * @author wy
// * @Description CyclicBarrier还提供一个更高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)，用于在线程到达屏障时，
// * 优先执行barrierAction，方便处理更复杂的业务场景。代码如下：
// * @createTime 2019/03/24
// */
//public class CyclicBarrierTest3 implements Runnable {
//
//    private String player;
//
//    private CyclicBarrier cyclicBarrier;
//
//    public static void main(String[] args) throws IOException {
//        CyclicBarrier barrier = new CyclicBarrier(6,()->{
//            try {
//                System.out.println("阶段完成，等待2秒...");
//                Thread.sleep(2000);
//                System.out.println("进入下个阶段...");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        });
//        Thread player1 = new Thread(new CyclicBarrierTest3("1",barrier));
//        Thread player2 = new Thread(new CyclicBarrierTest3("2",barrier));
//        Thread player3 = new Thread(new CyclicBarrierTest3("3",barrier));
//        Thread player4 = new Thread(new CyclicBarrierTest3("4",barrier));
//        Thread player5 = new Thread(new CyclicBarrierTest3("5",barrier));
//        Thread player6 = new Thread(new CyclicBarrierTest3("6",barrier));
//        player1.start();
//        player2.start();
//        player3.start();
//        player4.start();
//        player5.start();
//        player6.start();
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            System.out.println(this.getPlayer()+" 开始匹配玩家...");
//            Thread.sleep(5000);
//            cyclicBarrier.await();
//
//            System.out.println(this.getPlayer()+" 进行选择角色...");
//            Thread.sleep(5000);
//            System.out.println(this.getPlayer()+" 角色选择完毕等待其他玩家...");
//            cyclicBarrier.await();
//
//            System.out.println(this.getPlayer()+" 开始游戏,进行游戏加载...");
//            Thread.sleep(5000);
//            System.out.println(this.getPlayer()+" 游戏加载完毕等待其他玩家加载完成...");
//            cyclicBarrier.await();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String getPlayer() {
//        return player;
//    }
//
//    public void setPlayer(String player) {
//        this.player = player;
//    }
//
//    public CyclicBarrier getCyclicBarrier() {
//        return cyclicBarrier;
//    }
//
//    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
//        this.cyclicBarrier = cyclicBarrier;
//    }
//
//    public CyclicBarrierTest3(String player, CyclicBarrier cyclicBarrier) {
//        this.player = player;
//        this.cyclicBarrier = cyclicBarrier;
//    }
//}
