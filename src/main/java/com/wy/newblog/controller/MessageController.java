package com.wy.newblog.controller;

import com.wy.newblog.common.Result;
import com.wy.newblog.entity.MessageEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author wy
 * @Date 2018/9/18 15:28
 * @Description wy
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
@Api(tags = "信息管理")
public class MessageController {
    @Resource
    private IMessageService messageService;

    @Resource
    private AmqpTemplate amqpTemplate;

    @ApiOperation("发送修改密码验证码")
    @GetMapping("/{email}")
    public Result sendMessage(@PathVariable String email) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsgTo(email);
        return messageService.save(messageEntity);
    }

    @ApiOperation("rabbitMq测试")
    @GetMapping("send")
    public Result sendRabbitMQ() {
        String content = "Date:" + new Date();
        amqpTemplate.convertAndSend("test", content);
        return new Result(ResultCode.OK, content);
    }

    @ApiOperation("rabbitMq测试2")
    @GetMapping("sends")
    public Result sendRabbitMQs() {
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            long time = System.nanoTime();
            amqpTemplate.convertAndSend("test", "第" + i + "次发送的时间：" + time);
            amqpTemplate.convertAndSend("test2", "第" + i + "次发送的时间：" + time);
            times.append(time + "<br>");
        }
        return new Result(ResultCode.OK, times);
    }

    @ApiOperation("rabbitMq测试3")
    @GetMapping("topicSend1")
    public Result topicSend1() {

        String content = "my topic1";
        System.err.println("发送者说："+content);
        amqpTemplate.convertAndSend("exchange","topic.message", content);
        return new Result(ResultCode.OK, content);
    }

    @ApiOperation("rabbitMq测试4")
    @GetMapping("topicSend2")
    public Result topicSend2() {

        String content = "my topic2";
        System.err.println("发送者说："+content);
        amqpTemplate.convertAndSend("exchange","topic.messages", content);
        return new Result(ResultCode.OK, content);
    }
}
