package com.hdu.rps.web;

import com.hdu.rps.service.LoginServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by SJH on 2017/11/5.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction {
    private Logger logger = Logger.getLogger(String.valueOf(LoginAction.this));
    private boolean result = false;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        logger.info("---------进入首页-----------");
        return "index";
    }

    @RequestMapping(value = "/selectLogin",method = RequestMethod.GET)
    public String login(@RequestParam String btn, ModelMap modelMap) {
        if("HR入口".equals(btn) || "hr".equals(btn)) {
            modelMap.addAttribute("job","hr");
            logger.info("------------HR入口-------------");
        } else if("推荐人入口".equals(btn) || "re".equals(btn)) {
            modelMap.addAttribute("job","re");
            logger.info("------------推荐人入口-------------");
        } else if("管理员入口".equals(btn) || "ad".equals(btn)) {
            modelMap.addAttribute("job","ad");
            logger.info("------------管理员入口-------------");
        }   else {
            logger.error("--------LoginAction:selectLogin出错------");
            return "index";
        }
        return "login";
    }

    @RequestMapping(value = "/hr",method = RequestMethod.POST)
    private String hrLogin(@RequestParam String email, @RequestParam String password,ModelMap modelMap) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"hr");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == true) {
            return "hrHome";
        } else {
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=hr";
        }
    }

    @RequestMapping(value = "/re",method = RequestMethod.POST)
    private String reLogin(@RequestParam String email, @RequestParam String password,ModelMap modelMap) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"re");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result) {
            return "reHome";
        } else {
            modelMap.addAttribute("error",true);
            return "redirect:/login/selectLogin?btn=re";
        }
    }

    @RequestMapping(value = "/ad",method = RequestMethod.POST)
    private String adLogin(@RequestParam String email, @RequestParam String password,ModelMap modelMap) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"ad");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result) {
            return "adHome";
        } else {
            modelMap.addAttribute("error",true);
            return "redirect:/login/selectLogin?btn=ad";
        }
    }
}
