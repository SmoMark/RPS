package com.hdu.rps.web;

import com.hdu.rps.model.User;
import com.hdu.rps.service.ADServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/23.
 */
@Transactional
@Controller
@RequestMapping("/ad")
public class ADHomeAction {

    private Logger logger = Logger.getLogger(String.valueOf(ADHomeAction.this));
    private ArrayList<User> userArrayList;
    private User user;

    @Autowired
    private ADServiceImpl adServiceImpl;

    @RequestMapping("/userManage")
    public String userManage(ModelMap modelMap) {
        logger.info("-----------/ad/userManage---------");
        userArrayList = adServiceImpl.findAllUserByJob("re");
        modelMap.addAttribute("userArrayList",userArrayList);
        return "userManage";
    }

    @RequestMapping("/userManage/edit")
    public String userEdit(@RequestParam String userID,ModelMap modelMap) {
        logger.info("-----------/ad/userManage/edit---------");
        user = adServiceImpl.userManageEditByUserNO(Integer.parseInt(userID));
        modelMap.addAttribute("user",user);
        return "userManageEdit";
    }

    @RequestMapping("/userManage/del")
    public String userDel(@RequestParam String userID) {
        logger.info("-----------/ad/userManage/del?userID=" + userID);
        adServiceImpl.userManageDelByUserNO(Integer.parseInt(userID));
        return "redirect:/ad/userManage";
    }
}
