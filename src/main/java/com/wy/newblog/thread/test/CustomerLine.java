package com.wy.newblog.thread.test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author wy
 * @Description g顾客队列
 * @createTime 2019/04/14
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {

    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.size() == 0) {
            return "[Empty]";
        }
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }



}
