package com.hdu.rps.web;

import com.hdu.rps.model.Position;
import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.service.HRDealImpl;
import com.hdu.rps.service.HRRecruitServiceImpl;
import com.hdu.rps.service.LoginServiceImpl;
import com.hdu.rps.service.MailServiceImpl;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private HRDealImpl hrDealImpl;

    private Logger logger = Logger.getLogger(String.valueOf(HRHomeAction.this));
    private List<Position> positionList = new ArrayList<>();
    private String[] ids;
    private HttpSession httpSession;
    private String job;
    private String hrEmail;
    private ArrayList<String> emailList;
    private String[] emailStrings;
    private ArrayList<RecommendedPerson> recommendedPersonArrayList;

    @RequestMapping("/homeDetail")
    public String homeDetail(ModelMap modelMap, HttpServletRequest request,@RequestParam(required = false)String haveRecomended,@RequestParam(required = false)String havaDelayed) {
        if(havaDelayed == null) {

        } else if(havaDelayed.equals("1")) {
            logger.info("///////////该招聘信息确认已经延误/////");
            modelMap.addAttribute("havaDelayed",true);
        }
        positionList = hrRecruitServiceImpl.getPositionList();
        httpSession = request.getSession();
        job = (String) httpSession.getAttribute("job");
        modelMap.addAttribute("positionList",positionList);
        modelMap.addAttribute("job",job);
        modelMap.addAttribute("noNeeds",false);
        if(haveRecomended == null) {

        } else if(haveRecomended.equals("1")) {
            logger.info("****************************************");
            //前端提示功能（已经被推荐）
            modelMap.addAttribute("haveRecomended",true);
        }
        return "hrHomeDetail";
    }

    @RequestMapping("/findAllHaveNoNeeds")
    public String findAllHaveNoNeeds(ModelMap modelMap, HttpServletRequest request,@RequestParam(required = false)String haveRecomended) {
        positionList = hrRecruitServiceImpl.getPositionListHaveNoNeeds();
        httpSession = request.getSession();
        job = (String) httpSession.getAttribute("job");
        modelMap.addAttribute("positionList",positionList);
        modelMap.addAttribute("job",job);
        modelMap.addAttribute("noNeeds",true);
        if(haveRecomended == null) {

        } else if(haveRecomended.equals("1")) {
            logger.info("****************************************");
            //前端提示功能（已经被推荐）
            modelMap.addAttribute("haveRecomended",true);
        }
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

    @RequestMapping("/needToBeDoneDetail")
    public String needToBeDoneDetail(@RequestParam String positionID, ModelMap modelMap) {
        logger.info("*******/hr/needToBeDoneDetail******");
        recommendedPersonArrayList = hrDealImpl.findRecommendedPersonByPosNo(Integer.parseInt(positionID));
        if(recommendedPersonArrayList == null) {
            logger.info("******/hr/needToBeDoneDetail尚未有被推荐人选****");
            modelMap.addAttribute("zero",true);
            modelMap.addAttribute("positionNo",positionID);
            return "needToBeDoneDetail";  //尚未有被推荐人选，前端显示
        }
        return "redirect:/hr/showRecomendedPersonByState/?positionID=" + positionID + "&state=1";
    }

    @RequestMapping("/pass/{recommendedPersonID}/{positionNo}")
    public String pass(@PathVariable String recommendedPersonID,@PathVariable String positionNo) {
        hrDealImpl.pass(Integer.parseInt(recommendedPersonID),Integer.parseInt(positionNo));
        return "redirect:/hr/needToBeDoneDetail?positionID=" + positionNo;
    }

    @RequestMapping("/notPass/{recommendedPersonID}/{positionNo}")
    public String notPass(@PathVariable String recommendedPersonID,@PathVariable String positionNo) {
        hrDealImpl.notPass(Integer.parseInt(recommendedPersonID),Integer.parseInt(positionNo));
        return "redirect:/hr/needToBeDoneDetail?positionID=" + positionNo;
    }

    @RequestMapping("/showRecomendedPersonByState")
    public String showRecomendedPersonByState(@RequestParam String positionID,@RequestParam int state,ModelMap modelMap){
        logger.info("++++++positionID:" + positionID + ",state:" + state);
        recommendedPersonArrayList = hrDealImpl.findRecommendedPersonByPosNoAndState(Integer.parseInt(positionID),state);
        if(recommendedPersonArrayList == null) {
            modelMap.addAttribute("positionNo",positionID);
            modelMap.addAttribute("zero",true);
            return "needToBeDoneDetail";  //尚未有处于此状态的被推荐人，前端显示
        }
        modelMap.addAttribute("recommendedPersonByPosNo",recommendedPersonArrayList);
        modelMap.addAttribute("positionNo",positionID);
        return "needToBeDoneDetail";
    }

    @RequestMapping("/lookHaveNoNeedsPosition")
    public String lookHaveNoNeedsPosition(@RequestParam String positionID,ModelMap modelMap) {
        recommendedPersonArrayList = hrDealImpl.findPassedPersonByPos(Integer.parseInt(positionID));
        modelMap.addAttribute("recommendedPersonByPosNo",recommendedPersonArrayList);
        modelMap.addAttribute("positionNo",positionID);
        modelMap.addAttribute("havaPassed",true);
        return "needToBeDoneDetail";
    }
}
