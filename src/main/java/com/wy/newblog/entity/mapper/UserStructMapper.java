package com.wy.newblog.entity.mapper;

import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.entity.vo.UserVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStructMapper {

    public UserVo userToUserVo(UserEntity userEntity);

}
