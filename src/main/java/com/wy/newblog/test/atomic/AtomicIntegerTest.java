package com.wy.newblog.test.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wy
 * @Description ：以原子方式将当前值加1，注意，这里返回的是自增前的值
 * @createTime 2019/04/28
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }

}
