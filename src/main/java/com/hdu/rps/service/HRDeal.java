package com.hdu.rps.service;

import com.hdu.rps.model.Position;
import com.hdu.rps.model.RecommendedPerson;

import java.util.ArrayList;

/**
 * Created by SJH on 2017/11/20.
 */
public interface HRDeal {
    ArrayList<RecommendedPerson> findRecommendedPersonByPosNo(int posno);
    void pass(int recommendedPersonID,int positionNo);
    void notPass(int recommendedPersonID,int positionNo);
    ArrayList<RecommendedPerson> findRecommendedPersonByPosNoAndState(int posno, int state);
    ArrayList<RecommendedPerson> findPassedPersonByPos(int posno);
    Position findPosByPosno(int posno);
}
