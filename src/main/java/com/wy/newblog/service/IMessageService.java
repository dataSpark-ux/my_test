package com.wy.newblog.service;

import com.wy.newblog.common.Result;
import com.wy.newblog.entity.MessageEntity;

/**
 * @author WY
 * @date 2018/9/18
 */
public interface IMessageService {
    /**
     * 保存每次消息的内容
     *
     * @param messageEntity 消息实体
     * @return
     * @author WY
     * @date 2018/9/19
     */
    Result save(MessageEntity messageEntity);

    /**
     * 校验验证码的正确性
     *
     * @param msgTo 接受者邮箱
     * @param code  验证码
     * @return Boolean
     * @author WY
     * @date 2018/9/19
     */
    Boolean checkCode(String msgTo, String code);
}
