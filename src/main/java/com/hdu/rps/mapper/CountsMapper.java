package com.hdu.rps.mapper;

import com.hdu.rps.model.Counts;

public interface CountsMapper {
    int deleteByPrimaryKey(Integer countsno);

    int insert(Counts record);

    int insertSelective(Counts record);

    Counts selectByPrimaryKey(Integer countsno);

    int updateByPrimaryKeySelective(Counts record);

    int updateByPrimaryKey(Counts record);
}