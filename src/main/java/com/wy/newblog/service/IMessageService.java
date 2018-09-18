package com.wy.newblog.service;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.MessageEntity;

/**
  * @author WY
  * @date 2018/9/18
 */
public interface IMessageService {

    Result save(MessageEntity messageEntity);

    Boolean checkCode(String msgTo, String code);
}
