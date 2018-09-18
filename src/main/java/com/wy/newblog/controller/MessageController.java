package com.wy.newblog.controller;

import com.wy.newblog.common.utils.RedisUtils;
import com.wy.newblog.common.utils.VerifyCodeUtil;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.MessageEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.service.IEmailService;
import com.wy.newblog.service.IMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @ApiOperation("发送修改密码验证码")
    @GetMapping("/{email}")
    public Result sendMessage(@PathVariable String email){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsgTo(email);
        return messageService.save(messageEntity);
    }
}
