package com.wy.newblog.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wy
 * @Date 2018/9/19 15:35
 * @Description 队列配置，队列的名称，发送者和接受者的名称必须一致，否则接收不到消息
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue queueTest() {
        return new Queue("test");
    }
    @Bean
    public Queue quenceTest1() {
        return new Queue("test2");
    }
}
