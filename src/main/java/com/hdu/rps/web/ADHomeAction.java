package com.hdu.rps.web;

import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.ScoreRule;
import com.hdu.rps.model.User;
import com.hdu.rps.service.ADServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/23.
 */
@Transactional
@Controller
@RequestMapping("/ad")
public class ADHomeAction {

    private Logger logger = Logger.getLogger(String.valueOf(ADHomeAction.this));
    private ArrayList<User> userArrayList;
    private User user;
    private ArrayList<RecommendedPerson> recommendedPersonArrayList;
    private RecommendedPerson recommendedPerson;
    private ScoreRule scoreRule;

    @Autowired
    private ADServiceImpl adServiceImpl;

    @RequestMapping("/userManage")
    public String userManage(ModelMap modelMap) {
        logger.info("-----------/ad/userManage---------");
        userArrayList = adServiceImpl.findAllUserByJob("re");
        modelMap.addAttribute("userArrayList",userArrayList);
        return "userManage";
    }

    @RequestMapping("/userManage/edit")
    public String userEdit(@RequestParam String userID,ModelMap modelMap) {
        logger.info("-----------/ad/userManage/edit---------");
        user = adServiceImpl.userManageEditByUserNO(Integer.parseInt(userID));
        modelMap.addAttribute("user",user);
        return "userManageEdit";
    }

    @RequestMapping("/userManage/edit/submit/{userno}")
    public String userManageEditSubmit(@PathVariable String userno, @RequestParam String name, @RequestParam String phone,
                                       @RequestParam String email, @RequestParam String password, @RequestParam String sex,
                                       @RequestParam String score) {
        adServiceImpl.userManageEditSubmit(Integer.parseInt(userno),name,phone,email,password,sex,score);
        return "redirect:/ad/userManage";
    }

    @RequestMapping("/userManage/del")
    public String userDel(@RequestParam String userID) {
        logger.info("-----------/ad/userManage/del?userID=" + userID);
        adServiceImpl.userManageDelByUserNO(Integer.parseInt(userID));
        return "redirect:/ad/userManage";
    }

    @RequestMapping("/recommendedPersonHaveChecked")
    public String recommendedPersonHaveChecked(ModelMap modelMap) {
        recommendedPersonArrayList = adServiceImpl.selectRecommendedPersonHaveChecked();
        modelMap.addAttribute("recommendedPersonArrayList",recommendedPersonArrayList);
        modelMap.addAttribute("adchecked",true);
        return "recommend";
    }

    @RequestMapping("/recommendedHaveChecked/edit")
    public String recommendedHaveCheckedEdit(@RequestParam String recommendedPersonID,ModelMap modelMap) {
        recommendedPerson = adServiceImpl.selectRecommendPersonByRdpno(Integer.parseInt(recommendedPersonID));
        modelMap.addAttribute("recommendedPerson",recommendedPerson);
        return "recommendedhavepassededit";
    }

    @RequestMapping(value = "/recommendedHaveChecked/edit/submit",method = RequestMethod.POST)
    public String recommendedHaveCheckedEditSubmit(@RequestParam String recommendedPersonID,@RequestParam String name, @RequestParam String sex, @RequestParam String birthdate,
                                                   @RequestParam String minzu, @RequestParam String mianmao, @RequestParam String province,
                                                   @RequestParam String city, @RequestParam String telphone, @RequestParam String email,
                                                   @RequestParam String address, @RequestParam String school, @RequestParam String major,
                                                   @RequestParam String xueli, @RequestParam String computer, @RequestParam String english,
                                                   @RequestParam String interest, @RequestParam("file")MultipartFile file) {
        logger.info("--------/ad/recommendedHaveChecked/edit/submit-----");
        adServiceImpl.haveCheckedPersonEditSubmit(Integer.parseInt(recommendedPersonID),name,sex,birthdate,minzu,mianmao,province,city,telphone,email,address,school,major,xueli,computer,english,interest,file);
        return "redirect:/ad/recommendedPersonHaveChecked";
    }

    @RequestMapping("/recommendedHaveChecked/del")
    public String recommendedHaveCheckedDel(@RequestParam String recommendedPersonID) {
        logger.info("--------/ad/recommendedHaveChecked/del?recommendedPersonID=" + recommendedPersonID);
        adServiceImpl.delHavePassedRecommended(Integer.parseInt(recommendedPersonID));
        return "redirect:/ad/recommendedPersonHaveChecked";
    }

    @RequestMapping("/recommendedPersonNotChecked")
    public String recommendedPersonNotChecked(ModelMap modelMap) {
        recommendedPersonArrayList = adServiceImpl.selectRecommendedPersonNotChecked();
        if(recommendedPersonArrayList.size() == 0) {
            modelMap.addAttribute("allchecked",true);
        } else {
            modelMap.addAttribute("recommendedPersonArrayList",recommendedPersonArrayList);
        }
        modelMap.addAttribute("adnotchecked",true);
        return "recommend";
    }

    @RequestMapping("/recommendedNotChecked/detail")
    public String recommendedNotCheckedDetail(@RequestParam String recommendedPersonID,ModelMap modelMap) {
        recommendedPerson = adServiceImpl.selectRecommendPersonByRdpno(Integer.parseInt(recommendedPersonID));
        modelMap.addAttribute("recommendedPerson",recommendedPerson);
        modelMap.addAttribute("adcheck",true);
        return "recommendedPersonDetail";
    }

    @RequestMapping("/recommendedNotChecked/pass")
    public String recommendedNotcheckedPass(@RequestParam String recommendedPersonID) {
        adServiceImpl.recommendedNotcheckedPass(Integer.parseInt(recommendedPersonID));
        return "redirect:/ad/recommendedPersonNotChecked";
    }

    @RequestMapping("/recommendedNotChecked/notpass")
    public String recommendedNotcheckedNotPass(@RequestParam String recommendedPersonID) {
        adServiceImpl.recommendedNotcheckedNotPass(Integer.parseInt(recommendedPersonID));
        return "redirect:/ad/recommendedPersonNotChecked";
    }

    @RequestMapping("/hrManage")
    public String hrManage(ModelMap modelMap) {
        userArrayList = adServiceImpl.findAllUserByJob("hr");
        if(userArrayList.size() == 0) {
            modelMap.addAttribute("empty",true);
        } else {
            modelMap.addAttribute("userArrayList",userArrayList);
        }
        return "hrManage";
    }

    @RequestMapping("/hrManage/add")
    public String hrManageAdd() {
        logger.info("-----/ad/hrManage/add-----");
        return "hrManageAdd";
    }

    @RequestMapping("/hrManage/add/submit")
    public String hrManageAddSubmit(@RequestParam String name,@RequestParam String phone,
                                    @RequestParam String email,@RequestParam String password,
                                    @RequestParam String sex) {
        adServiceImpl.hrManageAddSubmit(name,phone,email,password,sex);
        return "redirect:/ad/hrManage";
    }

    @RequestMapping("/hrManage/edit")
    public String hrManageEdit(@RequestParam String userID, ModelMap modelMap) {
        user = adServiceImpl.userManageEditByUserNO(Integer.parseInt(userID));
        modelMap.addAttribute("user",user);
        return "hrManageEdit";
    }

    @RequestMapping("/hrManage/edit/submit/{userno}")
    public String hrManageEditSubmit(@PathVariable String userno,@RequestParam String name,@RequestParam String phone,
                                     @RequestParam String email,@RequestParam String password,@RequestParam String sex) {
        adServiceImpl.hrManageEditSubmit(Integer.parseInt(userno),name,phone,email,password,sex);
        return "redirect:/ad/hrManage";
    }

    @RequestMapping("/hrManage/del")
    public String hrManageDel(@RequestParam String userID, ModelMap modelMap) {
        adServiceImpl.delHR(Integer.parseInt(userID));
        return "redirect:/ad/hrManage";
    }

    @RequestMapping("/scoreRuleManage")
    public String scoreRuleManage(ModelMap modelMap) {
        scoreRule = adServiceImpl.selectScoreRule();
        modelMap.addAttribute("scoreRule",scoreRule);
        return "scoreRuleManage";
    }

    @RequestMapping("/updateScoreRule")
    public String updateScoreRule(@ModelAttribute ScoreRule scoreRule) {
        adServiceImpl.updateScoreRule(scoreRule);
        return "redirect:/ad/scoreRuleManage";
    }
}
