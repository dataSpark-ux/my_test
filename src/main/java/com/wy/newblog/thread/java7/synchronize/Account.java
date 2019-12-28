package com.wy.newblog.thread.java7.synchronize;

/**
 * @author wy
 * @Description 为了学习这个概念，我们将实现一个有两个线程访问共同对象的示例。
 * 我们将有一个银行帐户和两个线程：其中一个线程将钱转移到帐户而另一个线程将从账户中扣款
 * 。在没有同步方法，我们可能得到不正确的结果。同步机制保证了账户的正确。
 * @createTime 2019/05/13
 */
public class Account {

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 3.实现一个addAmount()方法，用来根据传入的参数增加balance的值。
     * 由于应该只有一个线程能改变balance的值，所以使用synchronized关键字将这个方法转换成临界区。
     */
    public synchronized void addAmout(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }

    /**
     * 4.实现一个subtractAmount()方法，用来根据传入的参数减少balance的值。
     * 由于应该只有一个线程能改变balance的值，
     * 所以使用synchronized关键字将这个方法转换成临界区。
     */
    public synchronized void subtractAmount(double amount) {
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }
}
