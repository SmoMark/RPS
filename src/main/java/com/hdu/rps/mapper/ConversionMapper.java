package com.hdu.rps.mapper;

import com.hdu.rps.model.Conversion;

public interface ConversionMapper {
    int deleteByPrimaryKey(Integer cvsno);

    int insert(Conversion record);

    int insertSelective(Conversion record);

    Conversion selectByPrimaryKey(Integer cvsno);

    int updateByPrimaryKeySelective(Conversion record);

    int updateByPrimaryKey(Conversion record);
}