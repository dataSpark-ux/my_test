package com.wy.newblog.thread.newThread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description 由于线程的本质，
 * @createTime 2019/04/02
 */
public class NaiveExceptionHanding {
    public static void main(String[] args) {

        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (Exception e) {
            System.err.println("00");
        }
    }

}
