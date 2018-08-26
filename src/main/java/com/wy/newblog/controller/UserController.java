package com.wy.newblog.controller;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
	
    @RequestMapping("/register")
    public Result register(UserEntity userEntity) {
        return userService.save(userEntity);
    }
}