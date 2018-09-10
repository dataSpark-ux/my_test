package com.wy.newblog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
@Entity
@Table(name = "t_user")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    @NotNull(message = "用户名不能为空")
    @Size(max = 15, min = 8, message = "用户长度最少大于8且小于15")
    @ApiModelProperty(value = "用户名称")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "密码不能为空")
    @Size(max = 50, min = 8, message = "密码最少八位")
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "生日")
    private Date birthDate;

    @NotNull(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "头像")
    private String avatar;


}
