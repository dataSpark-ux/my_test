package com.wy.newblog.service;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.UserEntity;

/**
 * @author WY
 * @date 2018/9/18
 */
public interface IUserService {
    /**
     * 保存用户
     *
     * @param userEntity 用户实体
     * @return
     * @author WY
     * @date 2018/9/18
     */
    Result save(UserEntity userEntity);

    /**
     * 用户登录
     *
     * @param userName   用户名
     * @param password   密码
     * @param ip
     * @param rememberMe
     * @return
     * @author WY
     * @date 2018/9/18
     */
    Result login(String userName, String password, String ip, Boolean rememberMe);

    /**
     * 修改用户密码
     *
     * @param email    邮箱
     * @param password 新密码
     * @param refcode  验证码
     * @return
     * @author WY
     * @date 2018/9/19
     */
    Result modifyPwd(String email, String password, String refcode);

    /**
     *
     * 测试
     * @return
     */
    Result findAll();
}
