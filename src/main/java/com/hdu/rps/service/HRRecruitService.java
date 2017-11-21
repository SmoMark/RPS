package com.hdu.rps.service;

import com.hdu.rps.model.Position;

import java.util.List;

/**
 * Created by SJH on 2017/11/14.
 */
public interface HRRecruitService {
    void recruit(String jobname,String jobcount,String province,String city,String deadtime,int salary1,int salary2
            ,String duty,String skill,String message);
    List<Position> getPositionList();
    void delByID(int id);
    void delSelected(String[] ids);
    List<Position> getPositionListHaveNoNeeds();
}
