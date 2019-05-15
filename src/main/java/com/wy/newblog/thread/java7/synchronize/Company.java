package com.wy.newblog.thread.java7.synchronize;

/**
 * @author wy
 * @Description 8.实现一个类来模拟公司，它调用addAmount()
 * 方法来增加账户上的余额（balance值）。这个类必须实现Runnable接口，作为一个线程执行。
 * @createTime 2019/05/13
 */
public class Company implements Runnable{

    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.addAmout(1000);
        }
    }
}
