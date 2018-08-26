package com.wy.newblog.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    @NotNull(message = "用户名不能为空")
    @Size(max = 15, min = 8, message = "用户长度最少大于8且小于15")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "密码不能为空")
    @Size(max = 50, min = 8, message = "密码最少八位")
    private String password;

    private Date birthDate;

    @NotNull(message = "昵称不能为空")
    private String nickname;

    private String email;

    private String github;

    private String qq;

    private String wecaht;

    private String avatar;

    private Integer status;

    private Date gmtCreateTime;

    private Date gmtUpdateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWecaht() {
        return wecaht;
    }

    public void setWecaht(String wecaht) {
        this.wecaht = wecaht;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(Date gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

    public Date getGmtUpdateTime() {
        return gmtUpdateTime;
    }

    public void setGmtUpdateTime(Date gmtUpdateTime) {
        this.gmtUpdateTime = gmtUpdateTime;
    }
}
