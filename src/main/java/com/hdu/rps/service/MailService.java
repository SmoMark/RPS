package com.hdu.rps.service;

/**
 * Created by SJH on 2017/11/16.
 */
public interface MailService {
    void sendSimpleMail(String to,String subject,String content);
}
