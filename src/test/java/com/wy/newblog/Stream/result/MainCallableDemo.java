package com.wy.newblog.Stream.result;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @suther: wy
 * @Date: 2018/10/7 21
 * @Description:
 */
public class MainCallableDemo {

    @Test
    public void TaskWithResultMain() {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            /**
             * submit()方法会产生futrue对象，它用Callable返回结果的特定类型进行了参数化
             * 可以用isDone()方法来查询Future是否已经完成。当任务完成时，它具有一个结果
             * 可以调用get()方法来获取结果。你也可以不用isDone()进行检查就可以直接调用get()，
             * 在这种情况下，get()将阻塞，直至结果准备就绪。你还可以在试图调用get()来过去结果之前
             * 先调用具有超时的get(),或者调用isDone()来查看任务是否完成。
             * */
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results) {
            try {
                // get() blocks until completion
                boolean done = fs.isDone();
                if (done == true) {
                    System.err.println(fs.get() + "**");
                }
            } catch (InterruptedException e) {
                System.err.println(e);
                return;
            } catch (ExecutionException e) {
                System.err.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}
