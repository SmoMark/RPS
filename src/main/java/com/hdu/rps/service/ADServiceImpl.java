package com.hdu.rps.service;

import com.hdu.rps.mapper.CountsMapper;
import com.hdu.rps.mapper.RecommendedPersonMapper;
import com.hdu.rps.mapper.UserMapper;
import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/23.
 */
@Transactional
@Service
public class ADServiceImpl implements ADSercive {

    private Logger logger = Logger.getLogger(String.valueOf(ADServiceImpl.this));
    private ArrayList<User> userArrayList;
    private User user;
    private int userno;
    private int userscore;
    private RecommendedPerson recommendedPerson = null;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CountsMapper countsMapper;

    @Autowired
    private RecommendedPersonMapper recommendedPersonMapper;

    @Override
    public ArrayList<User> findAllUserByJob(String job) {
        userArrayList = userMapper.findAllUserByJob(job);
        for(int i = 0;i < userArrayList.size(); i++) {
            user = userArrayList.get(i);
            userno = user.getUserno();
            userscore = countsMapper.selectByUserNo(userno).getCountsquantity();
            user.setUserscore(userscore);
        }
        return userArrayList;
    }

    @Override
    public void userManageDelByUserNO(int userno) {
        try {
            logger.info("/ad/userManage/del删除用户表");
            userMapper.deleteByPrimaryKey(userno);
            String userEmail = userMapper.selectByPrimaryKey(userno).getUseremail();
            recommendedPerson = recommendedPersonMapper.selectByEmail(userEmail);
            if(recommendedPerson != null) {
                logger.info("/ad/userManage/del更新被推荐表");
                recommendedPerson.setRdpincompany(0);
                recommendedPersonMapper.updateByPrimaryKeySelective(recommendedPerson);
            }
        } catch (Exception e) {
            logger.warning("/ad/userManage/del删除失败：" + e.getMessage());
        }
    }

    @Override
    public User userManageEditByUserNO(int userno) {
        user = userMapper.selectByPrimaryKey(userno);
        user.setUserscore(countsMapper.selectByUserNo(userno).getCountsquantity());
        if(user.getUsersex() == 0) {
            user.setUsergender("男");
        } else if (user.getUsersex() == 1) {
            user.setUsergender("女");
        }
        return user;
    }
}
