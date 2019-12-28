package com.wy.newblog.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author
 * @Date 2018/9/19 16:20
 * @Description TODO
 * @Version 1.0
 */
@Component
public class Receivers {

    @RabbitListener(queues = "test")
    @RabbitHandler
    public void process2(String msg) {
        System.err.println("receiver2:" + msg);
    }

    @RabbitListener(queues = "test2")
    @RabbitHandler
    public void process(String msg) {
        System.err.println("receiver2===>>:" + msg);
    }
}
