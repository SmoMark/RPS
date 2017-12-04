package com.hdu.rps.mapper;

import com.hdu.rps.model.ScoreRule;

public interface ScoreRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreRule record);

    int insertSelective(ScoreRule record);

    ScoreRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreRule record);

    int updateByPrimaryKey(ScoreRule record);
}