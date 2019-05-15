package com.wy.newblog.thread.queue.Test2;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/09
 */
public class Sender implements Runnable {

    private Random random = new Random(47);

    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c >= 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }
        } catch (IOException e) {
            System.err.println("sender writ");
        } catch (InterruptedException e) {
            System.err.println("sender sleep");
        }

    }
}
