package com.wy.newblog.thread.newThread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/02
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }
}
