package com.hdu.rps.web;

import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.model.User;
import com.hdu.rps.service.ADServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
