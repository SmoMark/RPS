package com.hdu.rps.service;

import com.hdu.rps.mapper.RecommendMapper;
import com.hdu.rps.mapper.RecommendedPersonMapper;
import com.hdu.rps.model.Recommend;
import com.hdu.rps.model.RecommendedPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/20.
 */
@Service
public class HRDealImpl implements HRDeal {

    private ArrayList<Integer> recommendedPersonIDByPosno;
    private ArrayList<RecommendedPerson> recommendedPersonArrayList = new ArrayList<>();
    private RecommendedPerson recommendedPerson;
    private int index;
    private int recommendedID;
    private Logger logger = Logger.getLogger(String.valueOf(HRDealImpl.this));
    private Recommend recommend;
    private int state;
    private ArrayList<Integer> recommendedPersonIDByPosnoAndState;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private RecommendedPersonMapper recommendedPersonMapper;


    @Override
    public ArrayList<RecommendedPerson> findRecommendedPersonByPosNo(int posno) {
        recommendedPersonArrayList.clear();
        recommendedPersonIDByPosno = recommendMapper.selectRecommendedPersonIDByPosno(posno);
        if(recommendedPersonIDByPosno.size() <= 0) {
            return null;
        } else {
            for(index = 0;index<recommendedPersonIDByPosno.size();index++) {
                logger.info("----recommendedID:" + index + " : " + recommendedPersonIDByPosno.get(index));
                recommendedID = recommendedPersonIDByPosno.get(index);
                recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(recommendedID);
                recommendedPersonArrayList.add(recommendedPerson);
            }
            return recommendedPersonArrayList;
        }
    }

    @Override
    public void pass(int recommendedPersonID, int positionNo) {
        recommend = recommendMapper.selectByRecommendedNoAndPosNo(recommendedPersonID,positionNo);
        try {
            state = recommend.getRcdstate();
        } catch (NullPointerException nullException) {
            state = 1;
        }
        recommend.setRcdstate(state+1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        recommend.setRcdmodtime(simpleDateFormat.format(date));
        recommendMapper.updateByPrimaryKeySelective(recommend);
    }

    @Override
    public void notPass(int recommendedPersonID, int positionNo) {

    }

    @Override
    public ArrayList<RecommendedPerson> findRecommendedPersonByPosNoAndState(int posno, int state) {
        recommendedPersonArrayList.clear();
        recommendedPersonIDByPosnoAndState = recommendMapper.selectRecommendedPersonIDByPosnoAndState(posno,state);
        if(recommendedPersonIDByPosnoAndState.size() <= 0) {
            return null;    //没人在这个state
        }   else {
            for(index = 0;index < recommendedPersonIDByPosnoAndState.size();index++) {
                recommendedID = recommendedPersonIDByPosnoAndState.get(index);
                recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(recommendedID);
                recommendedPersonArrayList.add(recommendedPerson);
            }
        }
        return recommendedPersonArrayList;
    }
}
