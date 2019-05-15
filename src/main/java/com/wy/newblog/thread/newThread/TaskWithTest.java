package com.wy.newblog.thread.newThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/31
 */
public class TaskWithTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            Future<String> submit = exec.submit(new TaskWithResult(i));
            results.add(submit);
        }
        exec.shutdownNow();
        for (Future f : results) {
            System.err.println("==" + f.get());
        }
    }
}
