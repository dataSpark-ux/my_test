package com.wy.newblog.thread.queue.Test2;

import java.io.IOException;
import java.io.PipedReader;
import java.util.Scanner;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/04/09
 */
public class Receiver implements Runnable {

    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.err.println("Read: " + (char) in.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
