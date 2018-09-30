package com.wy.newblog.service.impl;

import com.wy.newblog.base.BaseServiceImpl;
import com.wy.newblog.common.utils.RedisUtils;
import com.wy.newblog.common.utils.VerifyCodeUtil;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.MessageEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.repository.MessageRepository;
import com.wy.newblog.service.IEmailService;
import com.wy.newblog.service.IMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wy
 * @Date 2018/9/18 16:56
 * @Description  消息
 * @Version 1.0
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl implements IMessageService {

    @Resource
    private MessageRepository messageRepository;

    @Resource
    private IEmailService emailService;
    @Override
    public Result save(MessageEntity messageEntity) {
        try {
            String code = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 6, null);
            emailService.sendSimpleMail(messageEntity.getMsgTo(),"你正在操作修改密码，请注意是否本人操作","五分钟有效，请尽快处理："+code);
            boolean result = RedisUtils.set(messageEntity.getMsgTo(), code, 300L);
            MessageEntity msg = messageRepository.save(messageEntity);
            if (!result) {
                throw new Exception("redis异常");
            }
        } catch (Exception e) {
            logger.error("信息发送失败{}",e);
            return new Result(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return new Result(ResultCode.OK);
    }

    @Override
    public Boolean checkCode(String msgTo, String code) {
        try {
            String codeRedis = (String) RedisUtils.get(msgTo);
            if (codeRedis != null && codeRedis.equals(code)) {
                //验证码匹配成功，从缓存中移除数据
                RedisUtils.remove(msgTo);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
