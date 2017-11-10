package com.hdu.rps.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by SJH on 2017/11/10.
 */
@Controller
public class AboutAction {
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
}
