package com.hdu.rps.mapper;

import com.hdu.rps.model.Gift;

public interface GiftMapper {
    int deleteByPrimaryKey(Integer giftno);

    int insert(Gift record);

    int insertSelective(Gift record);

    Gift selectByPrimaryKey(Integer giftno);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);
}