package com.wy.newblog.thread.newThread;

import java.util.concurrent.Callable;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/03/31
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.err.println(Thread.currentThread().getName());
        return "result of TaskWithResult " + id;
    }


}
