package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class SearchAction {
    private Logger logger = Logger.getLogger(String.valueOf(SearchAction.this));
    private String baiduSearchText = null;
    @RequestMapping("/search")
    public String search(@RequestParam String searchTxt) {
        logger.info("----------使用百度进行查询:" + searchTxt + "-------------");
        try {
            baiduSearchText = new String(searchTxt.getBytes(),"utf-8");
            logger.info("----------对于百度查询关键字转码成功-------------");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.info("----------对于百度查询关键字转码失败-------------");
        }
        return "redirect:https://www.baidu.com/#ie=utf-8&wd=" + baiduSearchText;
    }
}
