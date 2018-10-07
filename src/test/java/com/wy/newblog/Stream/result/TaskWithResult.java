package com.wy.newblog.Stream.result;

import java.util.concurrent.Callable;

/**
 * @suther: wy
 * @Date: 2018/10/7 21
 * @Description: Runnable是执行工作的独立任务，但是它不返回任何值
 * 如果需要在完成时返回一个值，那么可以实现Callable接口而不是Runnable接口
 * 在javase 5中引入的callable是一种具有类型参数的泛型，他的类型参数表示的是从方法call()(而不是run()中返回的值)
 * 并且必须使用ExecutorService.submit()方法调用它
 */
public class TaskWithResult implements Callable<String> {

    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() {
        return "result of TaskWithResult" + id;
    }
}
