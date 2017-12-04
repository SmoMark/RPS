package com.hdu.rps.service;

import com.hdu.rps.mapper.*;
import com.hdu.rps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/23.
 */
@Transactional
@Service
public class ADServiceImpl implements ADSercive {

    private Logger logger = Logger.getLogger(String.valueOf(ADServiceImpl.this));
    private ArrayList<User> userArrayList;
    private User user;
    private int userno;
    private int userscore;
    private RecommendedPerson recommendedPerson = null;
    private File file;
    private String photoPath;
    private int gender;
    private int nation;
    private int deal;
    private int insurance;
    private int pointIndex;
    private String fileLastName;
    private String oldFileName;
    private File oldFile;
    private Counts counts;
    private Recommend recommend;
    private ScoreRule scoreRule;

    @Value("${my.basePhotoPath}")
    private String basePhotoPath;

    private ArrayList<RecommendedPerson> recommendedPersonArrayList;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CountsMapper countsMapper;

    @Autowired
    private RecommendedPersonMapper recommendedPersonMapper;

    @Autowired
    private RecommendMapper recommendMapper;

    @Autowired
    private ScoreRuleMapper scoreRuleMapper;

    @Override
    public ArrayList<User> findAllUserByJob(String job) {
        userArrayList = userMapper.findAllUserByJob(job);
        if("re".equals(job)) {
            for(int i = 0;i < userArrayList.size(); i++) {
                user = userArrayList.get(i);
                userno = user.getUserno();
                userscore = countsMapper.selectByUserNo(userno).getCountsquantity();
                user.setUserscore(userscore);
                if(user.getUsersex() == 0) {
                    user.setUsergender("男");
                } else {
                    user.setUsergender("女");
                }
            }
        } else {
            for (int i = 0; i < userArrayList.size(); i++) {
                user = userArrayList.get(i);
                if(user.getUsersex() == 0) {
                    user.setUsergender("男");
                } else {
                    user.setUsergender("女");
                }
            }
        }
        return userArrayList;
    }

    @Override
    public void userManageDelByUserNO(int userno) {
        try {
            logger.info("/ad/userManage/del删除用户表");
            String userEmail = userMapper.selectByPrimaryKey(userno).getUseremail();
            userMapper.deleteByPrimaryKey(userno);
            recommendedPerson = recommendedPersonMapper.selectByEmail(userEmail);
            if(recommendedPerson != null) {
                logger.info("/ad/userManage/del更新被推荐表");
                recommendedPerson.setRdpincompany(0);
                recommendedPersonMapper.updateByPrimaryKeySelective(recommendedPerson);
            }
        } catch (Exception e) {
            logger.warning("/ad/userManage/del删除失败：" + e.getMessage());
        }
    }

    @Override
    public User userManageEditByUserNO(int userno) {
        user = userMapper.selectByPrimaryKey(userno);
        if(user.getUserjob().equals("re")) {
            user.setUserscore(countsMapper.selectByUserNo(userno).getCountsquantity());
        }
        if(user.getUsersex() == 0) {
            user.setUsergender("男");
        } else if (user.getUsersex() == 1) {
            user.setUsergender("女");
        }
        return user;
    }

    @Override
    public ArrayList<RecommendedPerson> selectRecommendedPersonHaveChecked() {
        recommendedPersonArrayList = recommendedPersonMapper.findAllHaveChecked();
        return recommendedPersonArrayList;
    }

    @Override
    public ArrayList<RecommendedPerson> selectRecommendedPersonNotChecked() {
        recommendedPersonArrayList = recommendedPersonMapper.findAllNotChecked();
        return recommendedPersonArrayList;
    }

    @Override
    public RecommendedPerson selectRecommendPersonByRdpno(int rdpno) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(rdpno);
        if(recommendedPerson.getRdpsex() == 0) {
            recommendedPerson.setGender("男");
        } else {
            recommendedPerson.setGender("女");
        }
        if(recommendedPerson.getRdpdeal() == 0) {
            recommendedPerson.setDeal("群众");
        } else if(recommendedPerson.getRdpdeal() == 1) {
            recommendedPerson.setDeal("团员");
        } else if(recommendedPerson.getRdpdeal() == 2) {
            recommendedPerson.setDeal("党员");
        } else {
            recommendedPerson.setDeal("其他");
        }
        if(recommendedPerson.getRdpinsurance() == 1) {
            recommendedPerson.setInsurance("其他");
        } else if (recommendedPerson.getRdpinsurance() == 4) {
            recommendedPerson.setInsurance("本科");
        } else if (recommendedPerson.getRdpinsurance() == 5) {
            recommendedPerson.setInsurance("专科");
        } else if (recommendedPerson.getRdpinsurance() == 6) {
            recommendedPerson.setInsurance("硕士");
        } else if (recommendedPerson.getRdpinsurance() == 7) {
            recommendedPerson.setInsurance("博士");
        }
        if(recommendedPerson.getRdpnation() == 0) {
            recommendedPerson.setNation("汉族");
        } else {
            recommendedPerson.setNation("少数民族");
        }
        if(recommendedPerson.getRdpcomputlevel().contains("精通JAVA")) {
            recommendedPerson.setKnow1(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("精通C++")) {
            recommendedPerson.setKnow2(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("精通JS")) {
            recommendedPerson.setKnow3(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("精通mysql")) {
            recommendedPerson.setKnow4(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("熟练掌握办公软件")) {
            recommendedPerson.setKnow5(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("熟练掌握PS技术")) {
            recommendedPerson.setKnow6(true);
        }
        if(recommendedPerson.getRdpcomputlevel().contains("熟练掌握AI技术")) {
            recommendedPerson.setKnow7(true);
        }
        return recommendedPerson;
    }

    @Override
    public void recommendedNotcheckedPass(int rdpno) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(rdpno);
        recommendedPerson.setRdphavechecked(1);
        recommendedPersonMapper.updateByPrimaryKeySelective(recommendedPerson);
    }

    @Override
    public void recommendedNotcheckedNotPass(int rdpno) {
        try {
            photoPath = recommendedPersonMapper.selectByPrimaryKey(rdpno).getRdpphoto();
            file = new File(basePhotoPath + "/" + photoPath);
            logger.info("照片路径：" + basePhotoPath + "/" + photoPath);
            if(file.exists()) {
                file.delete();
            }
            recommendedPersonMapper.deleteByPrimaryKey(rdpno);
        } catch (Exception e) {
            logger.warning("删除失败：" + e.getMessage());
        }
    }

    @Override
    public void delHavePassedRecommended(int repno) {
        recommendedPersonMapper.deleteByPrimaryKey(repno);
        //删除积分跟踪表此用户信息
        recommend = recommendMapper.selectByRepNo(repno);
        if(recommend != null) {
            recommendMapper.deleteByRepNo(repno);
        }
    }

    @Override
    public void haveCheckedPersonEditSubmit(int rdpno, String name, String sex, String birthdate, String minzu,
                                            String mianmao, String province, String city, String telphone,
                                            String email, String address, String school, String major,
                                            String xueli, String computer, String english, String interest,
                                            MultipartFile newfile) {
        recommendedPerson = recommendedPersonMapper.selectByPrimaryKey(rdpno);
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
        recommendedPerson.setRdphavechecked(1);
        //上传照片
        if(!newfile.isEmpty()) {
           /* new Thread(new Runnable() {
                @Override
                public void run() {*/
                    logger.info("/////////////文件不空////////////");
                    try {
                        oldFileName = recommendedPersonMapper.selectByPrimaryKey(rdpno).getRdpphoto();
                        oldFile = new File(basePhotoPath + "/" + oldFileName);
                        if(oldFile.exists()) {
                           oldFile.delete();
                        }
                        pointIndex = newfile.getOriginalFilename().lastIndexOf('.');
                        fileLastName = newfile.getOriginalFilename().substring(pointIndex,newfile.getOriginalFilename().length());
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                                new FileOutputStream(new File(basePhotoPath + "/" + email + fileLastName)));
                        bufferedOutputStream.write(newfile.getBytes());
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        recommendedPerson.setRdpphoto(email + fileLastName);
                    } catch (FileNotFoundException e) {
                        logger.warning("------------上传照片失败1------------");
                        e.printStackTrace();
                    } catch (IOException e) {
                        logger.warning("------------上传照片失败2------------");
                        e.printStackTrace();
                    }
             /*   }
            }).start();*/
        } else {
            logger.warning("-------照片为空------");
            String oldFileName = recommendedPerson.getRdpphoto();
            File file1 = new File(basePhotoPath + "/" + oldFileName);
            pointIndex = oldFileName.lastIndexOf(".");
            fileLastName = oldFileName.substring(pointIndex,oldFileName.length());
            file1.renameTo(new File(basePhotoPath + "/" + email + fileLastName));
            recommendedPerson.setRdpphoto(email + fileLastName);
        }
        recommendedPersonMapper.updateByPrimaryKeySelective(recommendedPerson);
    }

    @Override
    public void delHR(int rdpno) {
        userMapper.deleteByPrimaryKey(rdpno);
    }

    @Override
    public void userManageEditSubmit(int rdpno, String name, String phone, String email, String password, String sex, String score) {
        user = userMapper.selectByPrimaryKey(rdpno);
        user.setUsername(name);
        user.setUserphone(Integer.parseInt(phone));
        user.setUseremail(email);
        user.setUserpassword(password);
        if(sex.equals("男")) {
            gender = 0;
        } else {
            gender = 1;
        }
        user.setUsersex(gender);
        userMapper.updateByPrimaryKeySelective(user);
        counts = countsMapper.selectByUserNo(rdpno);
        counts.setCountsquantity(Integer.parseInt(score));
        countsMapper.updateByPrimaryKeySelective(counts);
    }

    @Override
    public void hrManageEditSubmit(int rdpno, String name, String phone, String email, String password, String sex) {
        user = userMapper.selectByPrimaryKey(rdpno);
        user.setUsername(name);
        user.setUserphone(Integer.parseInt(phone));
        user.setUseremail(email);
        user.setUserpassword(password);
        if(sex.equals("男")) {
            gender = 0;
        } else {
            gender = 1;
        }
        user.setUsersex(gender);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void hrManageAddSubmit(String name, String phone, String email, String password, String sex) {
        user = userMapper.selectByUserEmail(email);
        if(user == null) {
            user = new User();
            user.setUsername(name);
            user.setUserphone(Integer.parseInt(phone));
            user.setUseremail(email);
            user.setUserpassword(password);
            if(sex.equals("男")) {
                gender = 0;
            } else {
                gender = 1;
            }
            user.setUsersex(gender);
            user.setUserjob("hr");
            userMapper.insert(user);
        }

    }

    @Override
    public ScoreRule selectScoreRule() {
        scoreRule = scoreRuleMapper.selectByPrimaryKey(1);
        return scoreRule;
    }

    @Override
    public void updateScoreRule(ScoreRule scoreRule) {
        scoreRule.setId(1);
        scoreRuleMapper.updateByPrimaryKeySelective(scoreRule);
    }
}
