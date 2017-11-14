package com.hdu.rps.service;

import com.hdu.rps.mapper.PositionMapper;
import com.hdu.rps.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/14.
 */
@Service
public class HRRecruitServiceImpl implements HRRecruitService {

    @Autowired
    private PositionMapper positionMapper;

    private Logger logger = Logger.getLogger(String.valueOf(HRRecruitServiceImpl.this));
    private Position position = null;
    private int posType = 0;
    private int posState = 0;   //岗位余量
    private int afterConPosCount;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private Date date;
    private Date afterConDate;
    private List<Position> positionList = new ArrayList<>();
    @Override
    public void recruit(String jobname, String jobcount, String province, String city,
                        String deadtime, int salary1, int salary2, String duty, String skill, String message) {
        date = new Date();
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
        //添加属性
        position.setPostype(posType);
        position.setPosstate(posState);        //余量
        position.setPosoffice(province + city);
        try {
            position.setPostime(simpleDateFormat.parse(simpleDateFormat.format(date)));
            position.setPosdeadline(simpleDateFormat.parse(deadtime));
            position.setPosneeds(afterConPosCount);
            position.setPosintro(duty);
            position.setPossalary1(salary1);
            position.setPossalary2(salary2);
            position.setPosskill(skill);
            position.setPosmessage(message);

            positionMapper.insert(position);
        } catch (ParseException e) {
            logger.warning("------------存储招聘信息失败---------");
            e.printStackTrace();
        }
    }

    @Override
    public List<Position> getPositionList() {
        positionList = positionMapper.findAll();
        return positionList;
    }
}
