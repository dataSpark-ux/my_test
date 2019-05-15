package com.wy.newblog.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/21
 */
public class Test5Deprecated {

    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");

        Thread printThread = new Thread(new Runner(), "PrintThread");
        printThread.setDaemon(true);
        printThread.start();

        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行暂停，输出内容工作停止
        printThread.suspend();

    }

    static class Runner implements Runnable {
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                System.out.println(Thread.currentThread().getName() + "Run at " + format.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }

}
