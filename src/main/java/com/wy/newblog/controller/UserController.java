package com.wy.newblog.controller;
import com.wy.newblog.base.BaseController;
import com.wy.newblog.common.utils.IpUtil;
import com.wy.newblog.common.Result;
import com.wy.newblog.entity.UserEntity;
import com.wy.newblog.entity.enums.ResultCode;
import com.wy.newblog.repository.UserMapper;
import com.wy.newblog.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
  * @author WY
  * @date 2018/9/19
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;
	@ApiOperation("创建用户")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid UserEntity userEntity, HttpServletRequest request) {
        String ip = IpUtil.getIpAddr(request);
        userEntity.setLastLoginIp(ip);
        return userService.save(userEntity);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestParam @ApiParam(value = "用户名")@NotNull  String username,
                        @RequestParam @ApiParam(value = "密码")@NotNull  String password,
                        Boolean rememberMe,
                        HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        return userService.login(username, password, ipAddr,rememberMe);
    }
    @ApiOperation("修改密码")
    @PutMapping("/modify")
    public Result modifyPwd(@RequestParam @ApiParam(value = "邮箱")@NotNull String email,
                            @RequestParam @ApiParam(value = "验证码")@NotNull String refCode,
                            @RequestParam @ApiParam(value = "新密码")@NotNull String newPwd){
        return userService.modifyPwd(email,newPwd,refCode);
    }
    @ApiOperation("查询所有用户")
    @GetMapping
    public Result findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Long id) {
        Optional<UserEntity> byId = userMapper.findById(id);
        return new Result(ResultCode.OK, byId.get());
    }

}