package com.hdu.rps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/16.
 */
@Service
public class MailServiceImpl implements MailService{
    private Logger logger = Logger.getLogger(String.valueOf(MailServiceImpl.this));

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendSimpleMail(String from, String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            sender.send(simpleMailMessage);
            logger.info("------发送邮件成功------");
        } catch (Exception e) {
            logger.warning("------发送邮件失败-------" + e.getMessage());
        }
    }
}
