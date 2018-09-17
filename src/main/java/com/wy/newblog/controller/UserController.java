package com.wy.newblog.controller;
import com.wy.newblog.core.Result;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private IUserService userService;
	@ApiOperation("创建用户")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserEntity userEntity, HttpServletRequest request) {
        String access_token = request.getHeader("access_token");
        System.err.println(access_token);

        return userService.save(userEntity);
    }
}