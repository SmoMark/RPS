package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class ExceptionAction {
    @RequestMapping("/404")
    public String notFountException() {
        return "404error";
    }

    @RequestMapping("/405")
    public String notAllowedException() {
        return "index";
    }
}
