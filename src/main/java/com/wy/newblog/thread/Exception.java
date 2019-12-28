package com.wy.newblog.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/25
 */
public class Exception {

    static class ExceptionTest implements Runnable {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionTest());
        } catch (java.lang.Exception e) {
            System.err.println(010);
        }
    }
}
