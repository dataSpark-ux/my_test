package com.wy.newblog.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wy
 * @Description 使用Fork/Join框架首先要考虑到的是如何分割任务，如果希望每个子任务最多执行两个
 * 数的相加，那么我们设置分割的阈值是2，由于是4个数字相加，所以Fork/Join框架会把这个任
 * 务fork成两个子任务，子任务一负责计算1+2，子任务二负责计算3+4，然后再join两个子任务
 * 的结果。因为是有结果的任务，所以必须继承RecursiveTask，
 * @createTime 2019/04/28
 */
public class Test9ForkJoinCountTast extends RecursiveTask<Integer> {
    /**
     * 阀值
     */
    private static final int THRESHOLD = 50000000;

    private int start;

    private int end;

    public Test9ForkJoinCountTast(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阀值，则分裂成两个子任务计算
            int middle = (start + end) / 2;
            Test9ForkJoinCountTast leftTask = new Test9ForkJoinCountTast(start, middle);
            Test9ForkJoinCountTast rightTask = new Test9ForkJoinCountTast(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
            System.err.println(Thread.currentThread().getName());
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算，负责计算1+2+3+4
        Test9ForkJoinCountTast task = new Test9ForkJoinCountTast(1, 1000000000);
        ForkJoinTask<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
