package com.wy.newblog.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @suther: wy
 * @Date: 2018/9/3 23
 * @Description:
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msg = "hello" + new Date();
        this.rabbitTemplate.convertAndSend("hello-wy-queue",msg);
    }
}
