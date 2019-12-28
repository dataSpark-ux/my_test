package com.wy.newblog.thread.test;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/14
 */
public class Customer {

    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "顾客服务时间{" +
                "服务时间===>>>>>" + serviceTime +
                '}' + "/n";
    }
}
