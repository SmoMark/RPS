package com.hdu.rps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/16.
 */
@Service
public class MailServiceImpl implements MailService{
    private Logger logger = Logger.getLogger(String.valueOf(MailServiceImpl.this));

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendSimpleMail(String to, String subject, String content) {
       /* SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);*/
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            sender.send(message);
            logger.info("------发送邮件成功------");
        } catch (Exception e) {
            logger.warning("------发送邮件失败-------" + e.getMessage());
        }
    }
}
