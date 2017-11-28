package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class HomeAction {
    private HttpSession httpSession;
    private Logger logger = Logger.getLogger(String.valueOf(HomeAction.this));
    private int userID = 0;
    private String job;
    @RequestMapping("/home")
    public String home(HttpServletResponse response, HttpServletRequest request, ModelMap modelMap) {
        response.setHeader("Cache-Control","no-cache"); //不对页面进行缓存，再次访问时将从服务器重新获取最新版本
        response.setHeader("Cache-Control","no-store"); //任何情况下都不缓存页面
        response.setDateHeader("Expires", 0); //使缓存过期
        response.setHeader("Pragma","no-cache"); //HTTP 1.0 向后兼容
        httpSession = request.getSession();
        try{
            userID = (int) httpSession.getAttribute("userID");
            job = (String) httpSession.getAttribute("job");
            modelMap.addAttribute("job",job);
            return "home";
        } catch (Exception e) {
            logger.info("-----不存在HomeAction.httpSession.useID----");
            return "index";
        }
    }
}
