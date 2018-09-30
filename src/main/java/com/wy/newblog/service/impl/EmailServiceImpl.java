package com.wy.newblog.service.impl;

import com.wy.newblog.base.BaseServiceImpl;
import com.wy.newblog.common.utils.RedisUtils;
import com.wy.newblog.core.config.EmailConfig;
import com.wy.newblog.entity.dto.Pair;
import com.wy.newblog.service.IEmailService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author wy
 * @Date 2018/9/15 12:53
 * @Description 邮箱服务
 * @Version 1.0
 */
@Service
public class EmailServiceImpl extends BaseServiceImpl implements IEmailService {

    @Resource
    private EmailConfig emailConfig;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    @Async
    public void sendSimpleMail(String sendTo, String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(titel);
        message.setText(content);
        mailSender.send(message);
        logger.info("发送邮箱信息====》》》{}",sendTo);
    }

    @Override
    @Async
    public void sendAttachmentsMail(String sendTo, String titel, String content, List<Pair<String, File>> attachments) {


        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(titel);
            helper.setText(content);

            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getLeft(), new FileSystemResource(pair.getRight()));
            }
        } catch (Exception e) {

        }

        mailSender.send(mimeMessage);
    }

    @Override
    @Async
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments) {


    }
}
