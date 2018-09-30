package com.wy.newblog.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author
 * @Date 2018/9/15 11:13
 * @Description 邮箱服务
 * @Version 1.0
 */
@Component
public class EmailConfig {
    /**
     * 发件邮箱
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

}
