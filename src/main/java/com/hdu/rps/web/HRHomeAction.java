package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SJH on 2017/11/7.
 */
@RequestMapping("/hr")
@Controller
public class HRHomeAction {
    @RequestMapping("/homeDetail")
    public String homeDetail() {
        return "hrHomeDetail";
    }

    @RequestMapping("/recruit")
    public String recruit() {
        return "addJob";
    }
}
