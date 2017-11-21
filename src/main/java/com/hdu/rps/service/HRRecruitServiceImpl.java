package com.hdu.rps.service;

import com.hdu.rps.mapper.PositionMapper;
import com.hdu.rps.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/14.
 */
@Transactional
@Service
public class HRRecruitServiceImpl implements HRRecruitService {

    @Autowired
    private PositionMapper positionMapper;

    private Logger logger = Logger.getLogger(String.valueOf(HRRecruitServiceImpl.this));
    private Position position = null;
    private int posType = 0;
    private int posState = 0;   //岗位余量
    private int afterConPosCount;
    private StringBuffer afterConDeadTime;
    private List<Position> positionList = new ArrayList<>();
    private int index;
    private int[] nums;
    @Override
    public void recruit(String jobname, String jobcount, String province, String city,
                        String deadtime, int salary1, int salary2, String duty, String skill, String message) {
        position = new Position();  //重构
        if("java工程师".equals(jobname)) {
            posType = 1;
        } else if("前端工程师".equals(jobname)) {
            posType = 2;
        } else if("后台工程师".equals(jobname)) {
            posType = 3;
        } else if("UI设计师".equals(jobname)) {
            posType = 4;
        } else if("市场经理".equals(jobname)) {
            posType = 5;
        } else if("财务".equals(jobname)) {
            posType = 6;
        } else if("总经理助理".equals(jobname)) {
            posType = 7;
        } else if("文员".equals(jobname)) {
            posType = 8;
        }
        posState = Integer.parseInt(jobcount);
        afterConPosCount = Integer.parseInt(jobcount);
        afterConDeadTime = new StringBuffer(deadtime.substring(0,10) + " " + deadtime.substring(11,deadtime.length()));
        try {
            //添加属性
            position.setPostype(posType);
            position.setPosstate(posState);        //余量
            position.setPosoffice(province + city);

            //获取当前时间
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            logger.info("-------当前时间： " + simpleDateFormat.format(date) + " ------");

            position.setPostime(simpleDateFormat.format(date));
            position.setPosdeadline(String.valueOf(afterConDeadTime));
            position.setPosneeds(afterConPosCount);
            position.setPosintro(duty);
            position.setPossalary1(salary1);
            position.setPossalary2(salary2);
            position.setPosskill(skill);
            position.setPosmessage(message);

            positionMapper.insert(position);
        } catch (Exception e) {
            logger.warning("------------存储招聘信息失败---------");
            e.printStackTrace();
        }
    }

    @Override
    public List<Position> getPositionList() {
        positionList = positionMapper.findAllHaveNeeds();
        return positionList;
    }

    @Override
    public void delByID(int id) {
        positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delSelected(String[] ids) {
        nums = new int[ids.length];
        //string数组转int数组
        for(index = 0;index < ids.length; index++) {
            nums[index] = Integer.parseInt(ids[index]);
        }

        for(index = 0;index < ids.length; index++) {
            logger.info("------多项删除：第" + (index+1) + "项id： " + nums[index]);
            positionMapper.deleteByPrimaryKey(nums[index]);
        }
    }

    @Override
    public List<Position> getPositionListHaveNoNeeds() {
        positionList = positionMapper.findAllHaveNoNeeds();
        return positionList;
    }


}
