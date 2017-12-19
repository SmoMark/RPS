package com.hdu.rps.web;

import com.hdu.rps.service.LoginServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by SJH on 2017/11/5.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginAction {
    private Logger logger = Logger.getLogger(String.valueOf(LoginAction.this));
    private int result = 0;
    private int sessionUserID = 0;
    private HttpSession httpSession;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        logger.info("---------进入首页-----------");
        return "index";
    }

    @RequestMapping(value = "/selectLogin",method = RequestMethod.GET)
    public String login(@RequestParam String btn, ModelMap modelMap,@RequestParam(required = false)String result) {
            modelMap.addAttribute("result",result);
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
    private String hrLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String password, ModelMap modelMap, HttpServletRequest request
    , HttpServletResponse response) {
        response.setHeader("Cache-Control","no-cache"); //不对页面进行缓存，再次访问时将从服务器重新获取最新版本
        response.setHeader("Cache-Control","no-store"); //任何情况下都不缓存页面
        response.setDateHeader("Expires", 0); //使缓存过期
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 向后兼容
        if(email.isEmpty() || password.isEmpty()) {
            logger.info("--------返回到主页面----------");
            return "index";
        }
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"hr");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == -1) {  //无该用户
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=hr&result=-1";
        } else if (result == -2) {  //密码错误
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=hr&result=-2";
        } else {
            modelMap.addAttribute("job","hr");
            httpSession = request.getSession();
            httpSession.setAttribute("userID",result);
            httpSession.setAttribute("job","hr");
            httpSession.setAttribute("hrEmail",email);
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/re",method = RequestMethod.POST)
    private String reLogin(@RequestParam String email, @RequestParam String password,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Cache-Control","no-cache"); //不对页面进行缓存，再次访问时将从服务器重新获取最新版本
        response.setHeader("Cache-Control","no-store"); //任何情况下都不缓存页面
        response.setDateHeader("Expires", 0); //使缓存过期
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 向后兼容
        if(email.isEmpty() || password.isEmpty()) {
            logger.info("--------返回到主页面----------");
            return "index";
        }
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"re");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == -1) {  //无该用户
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=re&result=-1";
        } else if (result == -2) {  //密码错误
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=re&result=-2";
        } else {
            modelMap.addAttribute("job","re");
            httpSession = request.getSession();
            httpSession.setAttribute("userID",result);
            httpSession.setAttribute("job","re");
            httpSession.setAttribute("hrEmail",email);
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/ad",method = RequestMethod.POST)
    private String adLogin(@RequestParam String email, @RequestParam String password,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Cache-Control","no-cache"); //不对页面进行缓存，再次访问时将从服务器重新获取最新版本
        response.setHeader("Cache-Control","no-store"); //任何情况下都不缓存页面
        response.setDateHeader("Expires", 0); //使缓存过期
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 向后兼容
        if(email.isEmpty() || password.isEmpty()) {
            logger.info("--------返回到主页面----------");
            return "index";
        }
        try {
            result = loginServiceImpl.findByUserEmailAndUserPasswordAndUserJob(email,password,"ad");
        } catch (Exception e) {
            System.out.println("loginAction:"+e);
        }
        if(result == -1) {  //无该用户
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=ad&result=-1";
        } else if (result == -2) {  //密码错误
            modelMap.addAttribute("errorTip",true);
            return "redirect:/login/selectLogin?btn=ad&result=-2";
        } else {
            modelMap.addAttribute("job","ad");
            httpSession = request.getSession();
            httpSession.setAttribute("userID",result);
            httpSession.setAttribute("job","ad");
            httpSession.setAttribute("hrEmail",email);
            return "redirect:/home";
        }
    }
}
