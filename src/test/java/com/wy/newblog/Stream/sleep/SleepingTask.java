package com.wy.newblog.Stream.sleep;

import com.wy.newblog.Stream.thread.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @suther: wy
 * @Date: 2018/10/14 16
 * @Description:
 */
public class SleepingTask extends LiftOff {

    public void run() {
        while (countDown-- > 0) {
            System.err.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(180);
            } catch (InterruptedException e) {
                System.err.println("Inter");
            }
        }
    }


}
