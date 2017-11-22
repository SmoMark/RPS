package com.hdu.rps.service;

import com.hdu.rps.mapper.CountsMapper;
import com.hdu.rps.mapper.UserMapper;
import com.hdu.rps.model.Counts;
import com.hdu.rps.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/22.
 */
@Service
public class CountsServiceImpl implements CountsService{

    ArrayList<Counts> countsArrayList;
    private int userNo;
    private User user;

    @Autowired
    private CountsMapper countsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ArrayList<Counts> findAllOrderByCounts() {
        countsArrayList = countsMapper.findAllOrderByCounts();
        for(int i = 0;i<countsArrayList.size();i++) {
            userNo = countsArrayList.get(i).getUserno();
            user = userMapper.selectByPrimaryKey(userNo);
            countsArrayList.get(i).setCountsintro(user.getUsername());
        }
        return countsArrayList;
    }
}
