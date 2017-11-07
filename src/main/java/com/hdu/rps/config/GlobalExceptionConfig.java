package com.hdu.rps.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/7.
 */

@ControllerAdvice
public class GlobalExceptionConfig {
    private Logger logger = Logger.getLogger(String.valueOf(GlobalExceptionConfig.this));

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("/notFoundError");
        logger.info("---------未找到资源-------");
        return mav;
    }

    @ExceptionHandler({Exception.class})
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView("/error");
        logger.info("--------------未登录，自动跳转------------");
        return modelAndView;
    }
}
