package com.hdu.rps.web;

import com.hdu.rps.service.LoginServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by SJH on 2017/11/5.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction {
    private Logger logger = Logger.getLogger(String.valueOf(LoginAction.this));
    private ModelAndView modelAndView = null;
    private boolean result = false;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView index() {
        modelAndView = new ModelAndView("/index");
        logger.info("---------进入首页-----------");
        return modelAndView;
    }

    @RequestMapping(value = "/selectLogin",method = RequestMethod.GET)
    public ModelAndView login(@RequestParam String btn) {
        if("HR入口".equals(btn)) {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("job","hr");
            logger.info("------------HR入口-------------");
            return modelAndView;
        } else if("推荐人入口".equals(btn)) {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("job","re");
            logger.info("------------推荐人入口-------------");
            return modelAndView;
        } else {
            modelAndView = new ModelAndView("/login");
            modelAndView.addObject("job","ad");
            logger.info("------------管理员入口-------------");
            return modelAndView;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/hr",method = RequestMethod.POST)
    private String hrLogin(@RequestParam String email, @RequestParam String password) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"hr");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == true) {
            return "success";
        } else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/re",method = RequestMethod.POST)
    private String reLogin(@RequestParam String email, @RequestParam String password) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"re");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == true) {
            return "success";
        } else {
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/ad",method = RequestMethod.POST)
    private String adLogin(@RequestParam String email, @RequestParam String password) {
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"ad");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == true) {
            return "success";
        } else {
            return "error";
        }
    }
}
