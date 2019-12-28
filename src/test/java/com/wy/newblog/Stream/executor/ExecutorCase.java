package com.wy.newblog.Stream.executor;

import javafx.concurrent.Task;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author
 * @Date 2018/10/16 15:22
 * @Description TODO
 * @Version 1.0
 */
public class ExecutorCase {

    // 1、Executors.newFixedThreadPool(10)初始化一个包含10个线程的线程池executor；
    // 2、通过executor.execute方法提交20个任务，每个任务打印当前的线程名；
    // 3、负责执行任务的线程的生命周期都由Executor框架进行管理；
    private static Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        System.err.println("开始时间" + LocalDateTime.now());


        for (int i = 0; i < 1000000; i++) {
            executor.execute(new Task());
        }
        System.err.println("结束时间" + LocalDateTime.now());

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            System.err.print("/");
        }
    }
}
