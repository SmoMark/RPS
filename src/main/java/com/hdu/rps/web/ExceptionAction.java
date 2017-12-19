package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class  ExceptionAction {
    private Logger logger = Logger.getLogger(String.valueOf(ExceptionAction.this));
    @RequestMapping("/404")
    public String notFountException() {
        logger.warning("---------------404错误-----------------");
        return "404error";
    }

    @RequestMapping("/405")
    public String notAllowedException() {
        logger.warning("---------------405错误-----------------");
        return "index";
    }
}
