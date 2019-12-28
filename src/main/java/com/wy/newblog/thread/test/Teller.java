package com.wy.newblog.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description 柜员
 * @createTime 2019/04/14
 */
public class Teller implements Runnable, Comparable<Teller> {


    private static int counter = 0;

    private final int id = counter++;

    //customers served during this shift
    //客户在这个班次期间服务
    private int customersServed = 0;

    private CustomerLine customers;

    private boolean servingCustomerLine = true;

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public int compareTo(Teller other) {
        return customersServed < other.customersServed ? -1 :
                (customersServed == other.customersServed ? 0 : 1);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this) {
                    customersServed++;
                    while (!servingCustomerLine) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.err.println(this + "  InterruptedException");
        }
        System.err.println(this + "  terminating");
    }

    public synchronized void doSomethingElse() {
        customersServed = 0;
        servingCustomerLine = false;
    }

    public synchronized void serveCustomerLine() {
        assert !servingCustomerLine : "already serving: " + this;
        servingCustomerLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller{" +
                "id=" + id +
                '}';
    }

    public String shortString() {
        return "T===>>" + id;
    }
}
