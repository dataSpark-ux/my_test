package com.wy.newblog.thread.aTest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/21
 */
public class PutTakeTest {


    private static final ExecutorService exec = Executors.newCachedThreadPool();

    private final AtomicInteger putSum = new AtomicInteger(0);

    private final AtomicInteger takeSum = new AtomicInteger(0);



}
