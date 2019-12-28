package com.wy.newblog.repository;

import com.wy.newblog.entity.User;
import com.wy.newblog.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

/**
 * @author wy
 * @Description TODO
 * @createTime 2019/05/15
 */
@Mapper
public interface UserMapper {

    /**
     * 通过ID找
     *
     * @param id
     * @return
     */
    Optional<UserEntity> findById(Long id);
}
