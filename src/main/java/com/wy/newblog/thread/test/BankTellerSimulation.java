package com.wy.newblog.thread.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/14
 */
public class BankTellerSimulation {

    static final int MAX_LINE_SIZE = 50;

    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {

        ExecutorService exec = Executors.newCachedThreadPool();
        // 如果行太长，客户将离开
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        // 管理 将根据需要添加和删除出纳员
        exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        } else {
            System.out.println("按Enter键退出");
            System.in.read();
        }
        exec.shutdownNow();


    }

}
