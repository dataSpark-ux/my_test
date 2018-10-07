package com.wy.newblog.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: wy
 * @Date: 2018/9/3 23
 * @Description:
 */
@Component
public class Receiver {



    @RabbitListener(queues = "test")
    @RabbitHandler
    public void process(String msg) {
        System.err.println("receiver:"+msg);
    }



}
