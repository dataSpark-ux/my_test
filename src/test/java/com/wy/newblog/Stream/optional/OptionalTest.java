package com.wy.newblog.Stream.optional;

import com.wy.newblog.entity.UserEntity;
import org.junit.Test;

import java.util.Optional;

/**
 * @author
 * @Date 2018/11/9 10:18
 * @Description TODO
 * @Version 1.0
 */
public class OptionalTest {
    @Test
    public void test1() {

        UserEntity userEntity = null;
//        userEntity = Optional.ofNullable(userEntity).orElse(createUser(2));
//        System.err.println(userEntity.toString());
//        userEntity = Optional.ofNullable(userEntity).orElseGet(() -> createUser(3));
//        System.err.println(userEntity.toString());
        Optional<UserEntity> userEntity1 = Optional.ofNullable(userEntity);
        System.err.println(userEntity1);
        try {
            UserEntity entity = Optional.ofNullable(userEntity).orElseThrow(() -> new Exception("取值错误"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserEntity createUser(int i) {
        System.err.println("i===" + i);
        UserEntity user = new UserEntity();
        user.setAge(11);
        return user;
    }
}
