package com.hdu.rps.service;

import com.hdu.rps.mapper.UserMapper;
import com.hdu.rps.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SJH on 2017/11/5.
 */
@Service
public class LoginServiceImpl implements LoginService {
    private Logger logger = Logger.getLogger(String.valueOf(LoginServiceImpl.this));
    private User user = null;
    private String job = null;
    private String password = null;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findByUserEmailAndUserPasswordAndUserJob(String useremail, String userpassword, String userjob) {
        try {
            user = userMapper.selectByUserEmail(useremail);
        }catch (Exception e) {
            System.out.println("LoginService"+e.getMessage());
        }

        if(user == null) {
            return false;
        }
        logger.info("--------查找结束-------");
        job = user.getUserjob();
        password = user.getUserpassword();
        logger.info("-------job:" + job + ",password:" + password);
        if(userjob.equals(job) & password.equals(userpassword)) {
            logger.info("--------找到该账号-------");
            return true;
        } else {
            logger.info("-------未找到该账号---------");
            return false;
        }
    }
}
