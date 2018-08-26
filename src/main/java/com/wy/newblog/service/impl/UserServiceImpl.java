package com.wy.newblog.service.impl;

import com.wy.newblog.core.BaseServiceImpl;
import com.wy.newblog.core.Result;
import com.wy.newblog.core.ResultCode;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.repository.UserRepository;
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

    @Override
    public Result save(UserEntity userEntity) {
        UserEntity user;
        try {
            user = userRepository.save(userEntity);
        } catch (Exception e) {
            logger.error("新增失败，原因{}", e);
            e.printStackTrace();
            return new Result(ResultCode.ADD_FAILURE);
        }
        logger.info("新增成功");
        return new Result(ResultCode.OK, user.getId());
    }
}
