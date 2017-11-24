package com.hdu.rps.mapper;

import com.hdu.rps.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userno);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userno);

    User selectByUserEmail(String useremail);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<String> findEmailByJob(String job);

    ArrayList<User> findAllUserByJob(String job);

}