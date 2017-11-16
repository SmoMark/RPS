package com.hdu.rps.web;

import com.hdu.rps.model.Position;
import com.hdu.rps.service.HRRecruitServiceImpl;
import com.hdu.rps.service.LoginServiceImpl;
import com.hdu.rps.service.MailServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/7.
 */
@Transactional
@RequestMapping("/hr")
@Controller
public class HRHomeAction {

    @Autowired
    private HRRecruitServiceImpl hrRecruitServiceImpl;

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    private Logger logger = Logger.getLogger(String.valueOf(HRHomeAction.this));
    private List<Position> positionList = new ArrayList<>();
    private String[] ids;
    private HttpSession httpSession;
    private String job;
    private String hrEmail;
    private ArrayList<String> emailList;
    private String[] emailStrings;

    @RequestMapping("/homeDetail")
    public String homeDetail(ModelMap modelMap, HttpServletRequest request) {
        positionList = hrRecruitServiceImpl.getPositionList();
        httpSession = request.getSession();
        job = (String) httpSession.getAttribute("job");
        modelMap.addAttribute("positionList",positionList);
        modelMap.addAttribute("job",job);
        return "hrHomeDetail";
    }

    @RequestMapping("/addJob")
    public String addJob() {
        return "addJob";
    }

    @RequestMapping(value = "/recruit",method = RequestMethod.POST)
    public String recruit(@RequestParam String jobname,@RequestParam String jobcount,@RequestParam String province,@RequestParam String city
        ,@RequestParam String deadtime,@RequestParam int salary1,@RequestParam int salary2
                          ,@RequestParam String duty,@RequestParam String skill,@RequestParam String message
            ,@RequestParam String btn,ModelMap modelMap,HttpServletRequest request
    ) {
        if("普通发布".equals(btn)) {
            logger.info("---------------进入普通发布--------------");
            hrRecruitServiceImpl.recruit(jobname,jobcount,province,city,deadtime,salary1,salary2,duty,skill,message);
        } else if("紧急发布".equals(btn)) {
            logger.info("---------------进入紧急发布--------------");
            hrRecruitServiceImpl.recruit(jobname,jobcount,province,city,deadtime,salary1,salary2,duty,skill,message);
            //添加邮件服务
            try {
                httpSession = request.getSession();
                hrEmail = (String) httpSession.getAttribute("hrEmail");
                emailList = loginServiceImpl.findEmailByJob("re");
                logger.info("-------emailList.size:" + emailList.size());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0;i<emailList.size();i++) {
                            logger.info("-----"+hrEmail + "------发送邮件到 " + emailList.get(i));
                             mailServiceImpl.sendSimpleMail(hrEmail,emailList.get(i),"紧急招聘"+jobname+","+jobcount+"人","紧急招聘"+jobname
                            +jobcount+"人，工作地点为" + province + city + ",截止日期为" + deadtime + ",薪资为" + salary1 + "-" + salary2
                            +",职责是：" + duty + ",需要" + skill + ",备注：" + message);
                        }
                    }
                }).start();
            } catch (Exception e) {
                logger.warning("-----发布紧急招聘失败--" + e.getMessage());
            }

        } else {
            logger.warning("--------------发布有误---------");
        }
        return "redirect:/hr/homeDetail";
    }

    @RequestMapping("/del/{id}")
    public String delByID(@PathVariable int id) {
        logger.info("------------删除单个招聘信息id=" + id);
        hrRecruitServiceImpl.delByID(id);
        return "redirect:/hr/homeDetail";
    }

    @RequestMapping("/delSelected")
    public String delSelected(@RequestParam String checkedID) {
        logger.info("-----------删除多个招聘信息-----------");
        ids = checkedID.split(",");
        hrRecruitServiceImpl.delSelected(ids);
        return "redirect:/hr/homeDetail";
    }
}
