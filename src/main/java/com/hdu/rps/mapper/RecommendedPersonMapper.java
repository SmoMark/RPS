package com.hdu.rps.mapper;

import com.hdu.rps.model.RecommendedPerson;

import java.util.ArrayList;

public interface RecommendedPersonMapper {
    int deleteByPrimaryKey(Integer rdpno);

    int insert(RecommendedPerson record);

    int insertSelective(RecommendedPerson record);

    RecommendedPerson selectByPrimaryKey(Integer rdpno);

    int updateByPrimaryKeySelective(RecommendedPerson record);

    int updateByPrimaryKey(RecommendedPerson record);

    ArrayList<RecommendedPerson> findAllHaveChecked();

    RecommendedPerson selectByEmail(String rdpemail);

    void deleteByEmail(String rdpemail);

    ArrayList<RecommendedPerson> findAllNotChecked();
}