package com.wy.newblog.thread.garden;

import java.util.Random;

/**
 * @author wy
 * @Description 计数
 * @createTime 2019/03/25
 */
public class Count {

    private int count = 0;

    private Random random = new Random(47);

    /**
     * 删除synchronized关键字以查看计数失败
     */
    public synchronized int increment() {
        // yield一半的时间
        if (random.nextBoolean()) {
            Thread.yield();
        }
        return ++count;
    }

    public synchronized int value() {
        return count;
    }
}
