//package com.wy.newblog.Stream.sleep;
//
//import org.junit.Test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * @suther: wy
// * @Date: 2018/10/14 16
// * @Description:
// */
//public class TestMain {
//    public static void main(String[] args) {
//        ExecutorService exec = Executors.newCachedThreadPool();
//        for (int i = 0; i < 5; i++) {
//            exec.execute(new SleepingTask());
//            exec.shutdown();
//        }
//    }
//
//    @Test
//    public void testSimple() {
//
//            ExecutorService exec = Executors.newCachedThreadPool();
//            for (int i = 0; i < 5; i++) {
//                exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
//                exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
//                exec.shutdown();
//            }
//    }
//}
