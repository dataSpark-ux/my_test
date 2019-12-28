package com.wy.newblog.Stream.executor;

import java.time.LocalDateTime;

/**
 * @author
 * @Date 2018/10/16 15:30
 * @Description TODO
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        System.err.println("7887");
        System.err.println("开始时间" + LocalDateTime.now());
        for (int i = 0; i < 1000000; i++) {
            new TaskTest().test();
        }
        System.err.println("结束时间" + LocalDateTime.now());
    }

    static class TaskTest {

        public void test() {
            System.err.print("/");
        }
    }
}
