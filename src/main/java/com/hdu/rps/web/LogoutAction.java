package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class LogoutAction {
    private HttpSession httpSession;
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        httpSession = request.getSession();
        httpSession.removeAttribute("userID");
        httpSession.invalidate();
        return "redirect:/login/";
    }
}
