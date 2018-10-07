package com.wy.newblog.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author
 * @Date 2018/9/19 16:51
 * @Description top
 * @Version 1.0
 */
@Component
public class TopicReceiver1 {

    @RabbitListener(queues = "topic.message")
    @RabbitHandler
    public void process(String msg) {
        System.out.println("TopicReceiver1:" + msg);
    }

    @RabbitListener(queues = "topic.messages")
    @RabbitHandler
    public void process1(String msg) {
        System.out.println("TopicReceiver2:" + msg);
    }

}
