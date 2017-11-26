package com.hdu.rps.service;

import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.User;

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
}
