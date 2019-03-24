package com.wy.newblog.thread;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author wy
 * @Description 基于线程池的web服务器
 * @createTime 2019/03/24
 */
public class TaskExecutionWebServer {

    private static final int NTHREADS = 100;

    private static final Executor exec
            = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.err.println("创建连接");
                }
            });
        }
    }
}
