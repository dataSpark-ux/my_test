package com.wy.newblog.thread;

import java.util.concurrent.*;

/**
 * @author wy
 * @Description 线程死锁
 * @createTime 2019/03/24
 */
public class ThreadDeadlock {

    static ExecutorService exec = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int i = Runtime.getRuntime().availableProcessors();
        System.err.println(i);
//        Future<String> submit = exec.submit(new RenderPageTask());
//        System.err.println(submit.get());
    }

    public static class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws ExecutionException, InterruptedException {
            Future<String> header, footer;
            header = exec.submit(new Callable<String>() {
                @Override
                public String call()  {
                    return "tm";
                }
            });
            footer = exec.submit(new Callable<String>() {
                @Override
                public String call()  {
                    return "wy";
                }
            });
            return header.get() + footer.get();
        }
    }

}
