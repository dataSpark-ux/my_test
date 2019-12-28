package com.wy.newblog.repository;

import com.wy.newblog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author WY
 * @date 2018/9/18
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String userName);

    UserEntity findByEmail(String email);
}
