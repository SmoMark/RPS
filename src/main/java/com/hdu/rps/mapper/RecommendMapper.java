package com.hdu.rps.mapper;

import com.hdu.rps.model.Recommend;

public interface RecommendMapper {
    int deleteByPrimaryKey(Integer rcdno);

    int insert(Recommend record);

    int insertSelective(Recommend record);

    Recommend selectByPrimaryKey(Integer rcdno);

    int updateByPrimaryKeySelective(Recommend record);

    int updateByPrimaryKey(Recommend record);
}