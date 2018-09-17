package com.wy.newblog.service.impl;

import com.wy.newblog.base.BaseServiceImpl;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.entity.enums.Status;
import com.wy.newblog.repository.UserRepository;
import com.wy.newblog.service.IEmailService;
import com.wy.newblog.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author
 * @Date 2018/8/24 10:50
 * @Description TODO
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private IEmailService emailService;

    @Override
    public Result save(UserEntity userEntity) {
        UserEntity user;
        try {
            userEntity.setStatus(Status.INVALID);
            user = userRepository.save(userEntity);
            emailService.sendSimpleMail(userEntity.getEmail(),"注册成功","您的账号是："+userEntity.getPassword());
        } catch (Exception e) {
            logger.error("新增失败，原因{}", e);
            return new Result(ResultCode.ADD_FAILURE);
        }
        logger.info("新增成功==>>用户{}",userEntity.getUsername());
        return new Result(ResultCode.OK, user.getId());
    }
}
