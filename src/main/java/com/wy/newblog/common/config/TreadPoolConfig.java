package com.wy.newblog.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author
 * @Date 2018/11/12 14:12
 * @Description 创建了一个线程池的 bean，在使用时直接从 Spring 中取出即可。
 * @Version 1.0
 */
@Configuration
public class TreadPoolConfig {
    @Bean(value = "consumerQueueThreadPool")
    public ExecutorService buildConsumerQueueThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("consumer-queue-thread-%d").build();

        ExecutorService pool = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(500),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }
}
