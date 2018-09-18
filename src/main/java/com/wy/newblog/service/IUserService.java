package com.wy.newblog.service;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.UserEntity;
/**
  * @author WY
  * @date 2018/9/18
 */
public interface IUserService {
    /**
      * @author WY
      * @date 2018/9/18
      * @param userEntity 用户实体
      * @return
     */
    Result save(UserEntity userEntity);
    /**
      * @author WY
      * @date 2018/9/18
      * @param userName 用户名   password 密码
      * @return 
     */
    Result login(String userName, String password,String ip,Boolean rememberMe);

    Result modifyPwd(String email, String password,String refcode);
}
