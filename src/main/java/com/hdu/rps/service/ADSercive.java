package com.hdu.rps.service;

import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/23.
 */
public interface ADSercive {
    ArrayList<User> findAllUserByJob(String job);
    void userManageDelByUserNO(int userno);
    User userManageEditByUserNO(int userno);

    ArrayList<RecommendedPerson> selectRecommendedPersonHaveChecked();
    ArrayList<RecommendedPerson> selectRecommendedPersonNotChecked();

    RecommendedPerson selectRecommendPersonByRdpno(int rdpno);

    void recommendedNotcheckedPass(int rdpno);
    void recommendedNotcheckedNotPass(int rdpno);

    void delHavePassedRecommended(int rdpno);
    void haveCheckedPersonEditSubmit(int rdpno,String name, String sex, String birthdate, String minzu, String mianmao, String province, String city,
                                     String telphone, String email, String address, String school, String major,
                                     String xueli, String computer, String english, String interest, MultipartFile file);

    void delHR(int rdpno);
    void userManageEditSubmit(int rdpno, String name, String phone, String email, String password, String sex, String score);
    void hrManageEditSubmit(int rdpno, String name, String phone, String email, String password, String sex);
    void hrManageAddSubmit(String name, String phone,
                           String email, String password, String sex);
}
