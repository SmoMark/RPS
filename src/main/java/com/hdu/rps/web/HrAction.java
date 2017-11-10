package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by SJH on 2017/11/7.
 */
@Controller
public class HrAction {
    @RequestMapping("/hello")
    public String hello(ModelMap modelMap, @RequestParam String btn) {
        modelMap.addAttribute("job","hr");
        modelMap.addAttribute("btn","11111");
        return "hello";
    }

}
