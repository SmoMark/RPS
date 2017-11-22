package com.hdu.rps.mapper;

import com.hdu.rps.model.Counts;

import java.util.ArrayList;

public interface CountsMapper {
    int deleteByPrimaryKey(Integer countsno);

    int insert(Counts record);

    int insertSelective(Counts record);

    Counts selectByPrimaryKey(Integer countsno);

    int updateByPrimaryKeySelective(Counts record);

    int updateByPrimaryKey(Counts record);

    Counts selectByUserNo(Integer userno);

    ArrayList<Counts> findAllOrderByCounts();

    int selectCountByUserNo(Integer userno);
}