package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class SearchAction {
    private Logger logger = Logger.getLogger(String.valueOf(SearchAction.this));
    private String searchURL = null;

    @RequestMapping("/search")
    public String search(@RequestParam String searchTxt) throws UnsupportedEncodingException {
        logger.info("----------使用百度进行查询:" + searchTxt + "-------------");
        searchURL = URLEncoder.encode(searchTxt, "utf-8");
       return "redirect:https://www.baidu.com/#ie=utf-8&wd=" + searchURL;
    }
}
