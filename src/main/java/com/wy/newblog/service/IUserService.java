package com.wy.newblog.service;

import com.wy.newblog.core.Result;
import com.wy.newblog.entity.UserEntity;

public interface IUserService {
    Result save(UserEntity userEntity);

}
