package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by SJH on 2017/11/16.
 */
@Controller
@RequestMapping("/re")
public class ReHomeAction {

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "该功能暂未实现";
    }

    @RequestMapping("/homeDetail")
    public String reHomeDetail(ModelMap modelMap) {
        modelMap.addAttribute("job","re");
        return "hrHomeDetail";
    }
}
