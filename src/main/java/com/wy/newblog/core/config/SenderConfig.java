package com.wy.newblog.core.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @suther: wy
 * @Date: 2018/9/3 23
 * @Description:
 */
@Configuration
public class SenderConfig {
    @Bean
    public Queue queue() {
        return new Queue("hello-wy-queue");
    }
}
