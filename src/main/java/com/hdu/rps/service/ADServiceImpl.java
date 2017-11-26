package com.hdu.rps.service;

import com.hdu.rps.mapper.CountsMapper;
import com.hdu.rps.mapper.RecommendedPersonMapper;
import com.hdu.rps.mapper.UserMapper;
import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
    private File file;
    private String photoPath;

    @Value("${my.basePhotoPath}")
    private String basePhotoPath;

    private ArrayList<RecommendedPerson> recommendedPersonArrayList;

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

    @Override
    public ArrayList<RecommendedPerson> selectRecommendedPersonHaveChecked() {
        recommendedPersonArrayList = recommendedPersonMapper.findAllHaveChecked();
        return recommendedPersonArrayList;
    }

    @Override
    public ArrayList<RecommendedPerson> selectRecommendedPersonNotChecked() {
        recommendedPersonArrayList = recommendedPersonMapper.findAllNotChecked();
        return recommendedPersonArrayList;
    }

    @Override
    public RecommendedPerson selectRecommendPersonByRdpno(int rdpno) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(rdpno);
        return recommendedPerson;
    }

    @Override
    public void recommendedNotcheckedPass(int rdpno) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(rdpno);
        recommendedPerson.setRdphavechecked(1);
        recommendedPersonMapper.updateByPrimaryKeySelective(recommendedPerson);
    }

    @Override
    public void recommendedNotcheckedNotPass(int rdpno) {
        try {
            photoPath = recommendedPersonMapper.selectByPrimaryKey(rdpno).getRdpphoto();
            file = new File(basePhotoPath + "/" + photoPath);
            logger.info("照片路径：" + basePhotoPath + "/" + photoPath);
            if(file.exists()) {
                file.delete();
            }
            recommendedPersonMapper.deleteByPrimaryKey(rdpno);
        } catch (Exception e) {
            logger.warning("删除失败：" + e.getMessage());
        }
    }
}
