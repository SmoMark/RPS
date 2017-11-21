package com.hdu.rps.web;

import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.service.RecommendServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/16.
 */
@Controller
@RequestMapping("/re")
public class ReHomeAction {

    private Logger logger = Logger.getLogger(String.valueOf(ReHomeAction.this));
    private ArrayList<RecommendedPerson> recommendedPersonArrayList;
    private RecommendedPerson recommendedPerson;
    private HttpSession httpSession;
    private int userID;
    private int haveRecomended;

    @Autowired
    private RecommendServiceImpl recommendServiceImpl;

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "此功能暂未实现";
    }

    @RequestMapping("/recommendBtn")
    public String recommendBtn(ModelMap modelMap,@RequestParam String positionID) {
        logger.info("---------检查是否该职位已经误期-------");
        int havaDealyed = recommendServiceImpl.haveDelayed(Integer.parseInt(positionID));
        if(havaDealyed == 1) {
            //已经延误
            logger.info("///////////该招聘信息已经延误/////");
            return "redirect:/hr/homeDetail?havaDelayed=1";
        } else {
            logger.info("---------进入被推荐人列表-------");
            recommendedPersonArrayList = recommendServiceImpl.findAll();
            modelMap.addAttribute("recommendedPersonArrayList",recommendedPersonArrayList);
            modelMap.addAttribute("positionID",positionID);
            return "recommend";
        }
    }

    @RequestMapping("/addToReposHome")
    public String addToReposHome() {
        logger.info("-------进入新增人才库页面-------");
        return "addToReposHome";
    }

    @RequestMapping("/recommendFollow")
    public String recommendFollow() {
        logger.info("-------进入推荐跟踪页面----");
        return "recommendFollow";
    }

    @RequestMapping("/scoreBoard")
    public String scoreBoard() {
        logger.info("----进入积分排行榜页面----");
        return "scoreBoard";
    }

    @RequestMapping(value = "/addToRepos",method = RequestMethod.POST)
    public String addToRepos(@RequestParam String name, @RequestParam String sex, @RequestParam String birthdate,
                             @RequestParam String minzu, @RequestParam String mianmao, @RequestParam String province,
                             @RequestParam String city, @RequestParam String telphone, @RequestParam String email,
                             @RequestParam String address, @RequestParam String school, @RequestParam String major,
                             @RequestParam String xueli, @RequestParam String computer, @RequestParam String english,
                             @RequestParam String interest, @RequestParam("file")MultipartFile file) {

        logger.info("-------------新增人才库------------");
        recommendServiceImpl.addToRepos(name,sex,birthdate,minzu,mianmao,province,city,telphone,email,address,school,
                major,xueli,computer,english,interest,file);
        return "redirect:/hr/homeDetail";

        /*int pointIndex = file.getOriginalFilename().lastIndexOf('.');
        logger.info("------------pointIndex : " + pointIndex);
        String fileLastName = file.getOriginalFilename().substring(pointIndex,file.getOriginalFilename().length());
        return "name:" + name + ",sex:" + sex + ",birthday:" + birthdate + ",minzu:" + minzu + ",mianmao:" + mianmao
                + ",province:" + province + ",city:" + city + "telphone:" + telphone + ",email:" + email + ",address:"
                + address  +",school" + school + ",major:" + major + ",xueli:" + xueli + ",computer:" + computer +
                ",english:" + english + "interest:" + interest + ",file:" + (email + fileLastName);*/
    }

    @RequestMapping("/getRecommendedPersonPhoto")
    public void getRecommendedPersonPhoto(HttpServletResponse httpServletResponse, @RequestParam String userPhoto) {
        logger.info("-----------userPhoto:" + userPhoto);
        BufferedInputStream bis = null;
        int length;
        try {
            bis = new BufferedInputStream(new FileInputStream(new File("E:\\recommendedPersonPhoto\\" + userPhoto)));
            byte[] bytes = new byte[1024*1024];
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024*1024);
            while((length = bis.read(bytes))!=-1){
                out.write(bytes,0,length);
            }
            bis.close();
            ServletOutputStream sevletOutputStream = httpServletResponse.getOutputStream();
            out.writeTo(sevletOutputStream);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getRecommendedPersonDetail")
    public String getRecommendedPersonDetail(@RequestParam String personID,ModelMap modelMap) {
        logger.info("-----查询ID为" + personID + "的被推荐人详情信息");
        recommendedPerson = recommendServiceImpl.findByID(Integer.parseInt(personID));
        modelMap.addAttribute("recommendedPerson",recommendedPerson);
        return "recommendedPersonDetail";
    }

    @RequestMapping("/recommend/{positionID}/{recommendedPersonID}")
    public String recommend(@PathVariable String positionID,@PathVariable String recommendedPersonID, HttpServletRequest httpServletRequest) {
        httpSession = httpServletRequest.getSession();
        try {
            userID = (int) httpSession.getAttribute("userID");
            logger.info("--------positionID:" + positionID + ",recommendedPersonID:" + recommendedPersonID + ",userID:" + userID);
            haveRecomended = recommendServiceImpl.recommend(userID,Integer.parseInt(recommendedPersonID),Integer.parseInt(positionID));
            if(haveRecomended == -1) {
                //该被推荐人已经被推荐过
                return "redirect:/hr/homeDetail?haveRecomended=1";
            }
        } catch (Exception e) {
            logger.warning("-------推荐出错-----" + e.getMessage());
        }
        return "redirect:/hr/homeDetail";
    }

}
