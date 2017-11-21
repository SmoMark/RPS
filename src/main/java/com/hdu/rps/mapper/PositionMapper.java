package com.hdu.rps.mapper;

import com.hdu.rps.model.Position;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface PositionMapper {
    int deleteByPrimaryKey(Integer posno);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer posno);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> findAllHaveNeeds();

    List<Position> findAllHaveNoNeeds();

    String selectDeatTimeByPositionID(int posno);

}