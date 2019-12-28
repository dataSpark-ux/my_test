package com.wy.newblog.thread.java7.synchronize;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/05/13
 */
public class TestMain {

    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);

        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

        companyThread.start();
        bankThread.start();
        /**
         *等待两个使用join()方法结束的线程，
         * 并且在控制台打印账户的最终余额（balance值）。
         */
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %f\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
