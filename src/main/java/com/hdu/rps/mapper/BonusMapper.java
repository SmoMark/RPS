package com.hdu.rps.mapper;

import com.hdu.rps.model.Bonus;

public interface BonusMapper {
    int deleteByPrimaryKey(Integer bonusno);

    int insert(Bonus record);

    int insertSelective(Bonus record);

    Bonus selectByPrimaryKey(Integer bonusno);

    int updateByPrimaryKeySelective(Bonus record);

    int updateByPrimaryKey(Bonus record);
}