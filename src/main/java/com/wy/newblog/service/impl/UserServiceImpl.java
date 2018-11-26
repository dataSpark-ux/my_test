package com.wy.newblog.service.impl;

import com.wy.newblog.base.BaseServiceImpl;
import com.wy.newblog.common.utils.EntityUtils;
import com.wy.newblog.common.utils.PasswordUtil;
import com.wy.newblog.common.utils.RedisKeyUtils;
import com.wy.newblog.common.utils.RedisUtils;
import com.wy.newblog.common.Result;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.entity.enums.Status;
import com.wy.newblog.entity.mapper.UserStructMapper;
import com.wy.newblog.repository.UserRepository;
import com.wy.newblog.service.IEmailService;
import com.wy.newblog.service.IUserService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wy
 * @Date 2018/8/24 10:50
 * @Description 用户相关
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements IUserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private IEmailService emailService;
    @Resource
    private MessageServiceImpl messageService;
    @Resource
    private UserStructMapper userStructMapper;
    @Resource
    private StringEncryptor encryptor;
    @Resource
    private RedisTemplate redisTemplate;

    private Long nowTime = System.currentTimeMillis();
    @Override
    public Result save(UserEntity userEntity) {
        UserEntity user;
        try {
            UserEntity userR = userRepository.findByUsername(userEntity.getUsername());
            if (StringUtils.isEmpty(userR)) {
                userEntity.setStatus(Status.INVALID);
                String encryptPwd = PasswordUtil.encrypt(userEntity.getPassword(), userEntity.getEmail());
                userEntity.setPassword(encryptPwd);
                userEntity.setCreateTime(nowTime);
                user = userRepository.save(userEntity);


                emailService.sendSimpleMail(userEntity.getEmail(),"Thanks","请妥善保管你的邮箱，万一哪天账号值钱了呢");
            }else {
                logger.info("新增失败，用户名已存在{}",userEntity.getUsername());
                return new Result(ResultCode.ADD_FAILURE.getCode(), "用户名已存在");
            }
        } catch (Exception e) {
            logger.error("新增用户失败，原因{}", e);
            return new Result(ResultCode.INTERNAL_SERVER_ERROR);
        }
        logger.info("新增成功==>>用户{}",userEntity.getUsername());
        return new Result(ResultCode.OK, user.getId());
    }

    @Override
    public Result login(String userName, String password,String ip,Boolean rememberMe) {

        UserEntity user = userRepository.findByUsername(userName);
        if (StringUtils.isEmpty(user)) {
            logger.info("当前用户不存在======》》{}",userName);
            return new Result(ResultCode.NOT_FOUND);
        }
        if (!user.getStatus().equals(Status.INVALID)) {
            logger.error("用户已被禁用{}",user);
            return new Result(ResultCode.ADD_FAILURE.getCode(), "用户已被禁用", null);
        }
        String token;
        try {
            token = PasswordUtil.encrypt(ip, userName);
            String encryptPwd = PasswordUtil.encrypt(password, user.getEmail());
            if (user.getPassword().equals(encryptPwd)) {
                boolean result = RedisUtils.set(user.getId().toString(), token, 10000L,TimeUnit.HOURS);
                user.setLastLoginIp(ip);
                user.setLastLoginTime(nowTime);
                userRepository.save(user);
                Map<String, Object> map = EntityUtils.beanToMap(user);
                redisTemplate.opsForHash().putAll(RedisKeyUtils.USER_INFO+user.getId(), map);

                if (!result) {
                    throw new Exception("redis异常");
                }
            }else {
                logger.info("密码不正确===》》{}", userName);
                return new Result(ResultCode.ADD_FAILURE.getCode(), "用户密码不正确");
            }
        } catch (Exception e) {
            logger.error("登录失败，原因{}", e);
            return new Result(ResultCode.INTERNAL_SERVER_ERROR.getCode(), "登录失败");
        }
        return new Result(ResultCode.OK,token);
    }

    @Override
    public Result modifyPwd(String email, String password, String refcode) {

        try {
            UserEntity user = userRepository.findByEmail(email);
            Boolean check = messageService.checkCode(email, refcode);
            //验证码是否正确
            if (check) {
                String newPassword = PasswordUtil.encrypt(password, email);
                user.setPassword(newPassword);
                UserEntity userEntity = userRepository.save(user);
                if (StringUtils.isEmpty(userEntity)) {
                    return new Result(ResultCode.UPDATE_FAILURE);
                }
            } else {
                logger.info("验证码输入错误===》》{}",email);
                return new Result(ResultCode.UPDATE_FAILURE.getCode(), "验证码输入错误");
            }
        } catch (Exception e) {
            logger.error("更新失败 {}",email);
            e.printStackTrace();
            return new Result(ResultCode.INTERNAL_SERVER_ERROR);
        }
        logger.info("修改密码成功===》》{}",email);
        return new Result(ResultCode.OK);
    }

    @Override
    public Result findAll() {
        List<UserEntity> userAll = userRepository.findAll();
        return new Result(ResultCode.OK,userAll);
    }


}
