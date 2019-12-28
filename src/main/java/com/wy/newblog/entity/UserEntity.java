package com.wy.newblog.entity;

import com.wy.newblog.base.BaseEntity;
import com.wy.newblog.entity.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author WY
 * @date 2018/9/13
 */
@Data
@Entity
@Table(name = "t_user")
@ApiModel("用户基本信息")
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
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    @NotNull
    private String email;

    @ApiModelProperty(value = "头像地址")
    private String avatar;
    @ApiModelProperty(value = "年龄")
    @Max(50)
    @Min(1)
    private Integer age;


    private String lastLoginIp;

    private Long lastLoginTime;

}
