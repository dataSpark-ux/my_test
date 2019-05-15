package com.wy.newblog.test.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author wy
 * @Description 原子更新数组
 * @createTime 2019/04/28
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1, 2, 3};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    /**
    *需要注意的是，数组value通过构造方法传递进去，然后AtomicIntegerArray会将当前数组
     * 复制一份，所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响传入的数组
    */
    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
