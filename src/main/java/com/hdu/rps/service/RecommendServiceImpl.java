package com.hdu.rps.service;

import com.hdu.rps.mapper.RecommendedPersonMapper;
import com.hdu.rps.model.RecommendedPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/17.
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    private ArrayList<RecommendedPerson> recommendedPersonArrayList;
    private RecommendedPerson recommendedPerson;
    private int gender;
    private int nation;
    private int deal;
    private int insurance;
    private int pointIndex;
    private String fileLastName;
    private Logger logger = Logger.getLogger(String.valueOf(RecommendServiceImpl.this));

    @Autowired
    private RecommendedPersonMapper recommendedPersonMapper;


    @Override
    public ArrayList<RecommendedPerson> findAll() {
        recommendedPersonArrayList = recommendedPersonMapper.findAll();
        return recommendedPersonArrayList;
    }

    @Override
    public void addToRepos(String name, String sex, String birthdate, String minzu, String mianmao, String province, String city,
                           String telphone, String email, String address, String school, String major,
                           String xueli, String computer, String english, String interest, MultipartFile file) {
        recommendedPerson = new RecommendedPerson();
        //数据类型转换
        if("男".equals(sex)) {
            gender = 0;
        } else {
            gender = 1;
        }
        if("汉".equals(minzu) || "汉族".equals(minzu)) {
            nation = 0;
        } else {
            nation = 1;
        }
        if("群众".equals(mianmao)) {
            deal = 0;
        } else if("团员".equals(mianmao)) {
            deal = 1;
        } else if("党员".equals(mianmao)) {
            deal = 2;
        } else {
            deal = 3;
        }
        if("专科".equals(xueli)) {
            insurance = 5;
        } else if ("本科".equals(xueli)) {
            insurance = 4;
        } else if ("硕士".equals(xueli)) {
            insurance = 6;
        } else if ("博士".equals(xueli)) {
            insurance = 7;
        } else {
            insurance = 1;
        }
        pointIndex = file.getOriginalFilename().lastIndexOf('.');
        fileLastName = file.getOriginalFilename().substring(pointIndex,file.getOriginalFilename().length());

        //上传照片
        if(!file.isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                new FileOutputStream(new File("E:\\recommendedPersonPhoto\\" + email + fileLastName)));
                        bufferedOutputStream.write(file.getBytes());
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    } catch (FileNotFoundException e) {
                        logger.warning("------------上传照片失败1------------");
                        e.printStackTrace();
                    } catch (IOException e) {
                        logger.warning("------------上传照片失败2------------");
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            logger.warning("-------文件是空的------");
        }

        recommendedPerson.setRdpname(name);
        recommendedPerson.setRdpsex(gender);
        recommendedPerson.setRdpbirthday(birthdate);
        recommendedPerson.setRdpnation(nation);
        recommendedPerson.setRdpdeal(deal);
        recommendedPerson.setRdplocate(province + city);
        recommendedPerson.setRdpphone(telphone);
        recommendedPerson.setRdpemail(email);
        recommendedPerson.setRdpaddress(address);
        recommendedPerson.setRdpschool(school);
        recommendedPerson.setRdpmajor(major);
        recommendedPerson.setRdpinsurance(insurance);
        recommendedPerson.setRdpcomputlevel(computer);
        recommendedPerson.setRdpenglishlevel(english);
        recommendedPerson.setRdpbrief(interest);
        recommendedPerson.setRdpphoto(email + fileLastName);

        recommendedPersonMapper.insert(recommendedPerson);
    }

    @Override
    public RecommendedPerson findByID(int id) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(id);
        return recommendedPerson;
    }
}
