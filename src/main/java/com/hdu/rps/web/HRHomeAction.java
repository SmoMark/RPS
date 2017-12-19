package com.hdu.rps.web;

import com.hdu.rps.model.Position;
import com.hdu.rps.model.RecommendedPerson;
import com.hdu.rps.service.HRDealImpl;
import com.hdu.rps.service.HRRecruitServiceImpl;
import com.hdu.rps.service.LoginServiceImpl;
import com.hdu.rps.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by SJH on 2017/11/7.
 * @author SJH
 * HR 控制层
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
    private ArrayList<String> emailList;
    private ArrayList<RecommendedPerson> recommendedPersonArrayList;
    private int state = 1;
    private Position position;


    /**
     * 进入待处理招聘信息列表
     * @param modelMap
     * @param request
     * @param haveRecomended 该推荐人在该岗位是否已经被推荐
     * @param havaDelayed 招聘信息是否已经延期
     * @param rep 执行结果返回值
     * @return 进入待处理招聘信息列表
     */
    @RequestMapping("/homeDetail")
    public String homeDetail(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = false)String haveRecomended, @RequestParam(required = false)String havaDelayed, @RequestParam(required = false)String rep) {
        if(havaDelayed == null) {

        } else if(havaDelayed.equals("1")) {
            logger.info("该招聘信息确认已经延误");
            modelMap.addAttribute("havaDelayed",true);
        }
        if(rep == null) {

        } else if(rep.equals("-1")) {
            logger.info("人才库已存在该人员");
            modelMap.addAttribute("rdp",-1);
        } else if(rep.equals("-2")) {
            logger.info("本公司已存在该人员");
            modelMap.addAttribute("rdp",-2);
        }
        positionList = hrRecruitServiceImpl.getPositionList();
        httpSession = request.getSession();
        job = (String) httpSession.getAttribute("job");
        modelMap.addAttribute("positionList",positionList);
        modelMap.addAttribute("job",job);
        modelMap.addAttribute("noNeeds",false);
        if(haveRecomended == null) {

        } else if(haveRecomended.equals("1")) {
            //前端提示功能（已经被推荐）
            modelMap.addAttribute("haveRecomended",true);
        }
        return "hrHomeDetail";
    }

    /**
     * 进入待处理招聘信息
     * @param modelMap
     * @param request
     * @param haveRecomended 该推荐人在该岗位是否被推荐
     * @return 进入待处理招聘信息列表
     */
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
            //前端提示功能（已经被推荐）
            modelMap.addAttribute("haveRecomended",true);
        }
        return "hrHomeDetail";
    }

    /**
     * 进入新增招聘信息界面
     * @return 进入新增招聘信息界面
     */
    @RequestMapping("/addJob")
    public String addJob() {
        return "addJob";
    }

    /**
     * 发布招聘信息
     * @param jobname 岗位名称
     * @param jobcount 招聘数量
     * @param province 工作地点所在省份
     * @param city 工作地点所在城市
     * @param deadtime 截止日期
     * @param salary1 薪水最低值
     * @param salary2 薪水最高值
     * @param duty 岗位职责
     * @param skill 要求技能
     * @param message 备注
     * @param btn 按钮
     * @param modelMap
     * @param request
     * @return 返回至岗位信息列表
     */
    @RequestMapping(value = "/recruit",method = RequestMethod.POST)
    public String recruit(@RequestParam String jobname,@RequestParam String jobcount,@RequestParam String province,@RequestParam String city
            ,@RequestParam String deadtime,@RequestParam int salary1,@RequestParam int salary2
            ,@RequestParam String duty,@RequestParam String skill,@RequestParam String message
            ,@RequestParam String btn,ModelMap modelMap,HttpServletRequest request
    ) {
        logger.info("------------btn:" + btn);
        if("普通发布".equals(btn)) {
            logger.info("---------------进入普通发布--------------");
            hrRecruitServiceImpl.recruit(jobname,jobcount,province,city,deadtime,salary1,salary2,duty,skill,message);
        } else if("紧急发布".equals(btn)) {
            logger.info("---------------进入紧急发布--------------");
            hrRecruitServiceImpl.recruit(jobname,jobcount,province,city,deadtime,salary1,salary2,duty,skill,message);
            //添加邮件服务
            try {
                httpSession = request.getSession();
                emailList = loginServiceImpl.findEmailByJob("re");
                logger.info("-------emailList.size:" + emailList.size());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0;i<emailList.size();i++) {
                            mailServiceImpl.sendSimpleMail(emailList.get(i),"紧急招聘"+jobname+","+jobcount+"人","紧急招聘"+jobname
                                    +jobcount+"人，工作地点为" + province + city + ",截止日期为" + deadtime + ",薪资为" + salary1 + "-" + salary2
                                    +",职责是：" + duty + ",需要" + skill + ",备注：" + message);
                        }
                    }
                }).start();
            } catch (Exception e) {
                logger.warning("-----发布紧急招聘失败--" + e.getMessage());
            }

        } else {
            logger.warning("----------发布有误,普通发布处理---------");
            hrRecruitServiceImpl.recruit(jobname,jobcount,province,city,deadtime,salary1,salary2,duty,skill,message);
        }
        return "redirect:/hr/homeDetail";
    }

    /**
     * 单个删除招聘信息
     * @param id 招聘信息ID
     * @return 返回至招聘信息列表
     */
    @RequestMapping("/del/{id}")
    public String delByID(@PathVariable int id) {
        logger.info("------------删除单个招聘信息id=" + id);
        hrRecruitServiceImpl.delByID(id);
        return "redirect:/hr/homeDetail";
    }

    /**
     * 多选删除招聘信息
     * @param checkedID
     * @return 返回至招聘信息列表
     */
    @RequestMapping("/delSelected")
    public String delSelected(@RequestParam String checkedID) {
        logger.info("-----------删除多个招聘信息-----------");
        ids = checkedID.split(",");
        hrRecruitServiceImpl.delSelected(ids);
        return "redirect:/hr/homeDetail";
    }

    /**
     * 岗位招聘详情
     * @param positionID
     * @param modelMap
     * @return 处于待审核状态的岗位招聘详情界面
     */
    @RequestMapping("/needToBeDoneDetail")
    public String needToBeDoneDetail(@RequestParam String positionID, ModelMap modelMap) {
        state = 1;
        recommendedPersonArrayList = hrDealImpl.findRecommendedPersonByPosNo(Integer.parseInt(positionID));
        if(recommendedPersonArrayList == null) {
            modelMap.addAttribute("zero",true);
            modelMap.addAttribute("positionNo",positionID);
            return "redirect:/hr/showRecomendedPersonByState?positionID=" + positionID + "&state=1";  //尚未有被推荐人选，前端显示
        }
        return "redirect:/hr/showRecomendedPersonByState?positionID=" + positionID + "&state=1";
    }

    /**
     * 通过该次筛选面试
     * @param recommendedPersonID 被推荐人ID
     * @param positionNo 招聘信息ID
     * @return 岗位招聘信息详情
     */
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
    public String showRecomendedPersonByState(@RequestParam(required = false) String positionID,@RequestParam int state,ModelMap modelMap){
        modelMap.addAttribute("index",state);
        modelMap.addAttribute("done",false);
        modelMap.addAttribute("display",true);
        if(positionID == null) {
            modelMap.addAttribute("zero",true);
            return "Prac";
        }
        recommendedPersonArrayList = hrDealImpl.findRecommendedPersonByPosNoAndState(Integer.parseInt(positionID),state);
        if(recommendedPersonArrayList == null) {
            modelMap.addAttribute("positionNo",positionID);
            modelMap.addAttribute("zero",true);
            return "Prac";  //尚未有处于此状态的被推荐人，前端显示
        }
        //modelMap.addAttribute("recommendedPersonByPosNo",recommendedPersonArrayList);
        modelMap.addAttribute("positionNo",positionID);
        modelMap.addAttribute("display",true);
        if(state == 6) {
            modelMap.addAttribute("display",false);
            modelMap.addAttribute("havaOver",false);
            logger.info("hr://///////////已经入职/////////");
        }
        return "Prac";
    }

    @RequestMapping("/lookHaveNoNeedsPosition")
    public String lookHaveNoNeedsPosition(@RequestParam String positionID,ModelMap modelMap) {
       /* recommendedPersonArrayList = hrDealImpl.findPassedPersonByPos(Integer.parseInt(positionID));
        modelMap.addAttribute("recommendedPersonByPosNo",recommendedPersonArrayList);*/
        modelMap.addAttribute("positionNo",positionID);
        modelMap.addAttribute("havePassed",false);
        modelMap.addAttribute("done",true);
        modelMap.addAttribute("display",false);
        return "Prac";
    }

    @RequestMapping("/state/{sid}/{positionNo}/{display}/{done}")
    public String state(@PathVariable String sid,@PathVariable String positionNo,@PathVariable boolean display, @PathVariable boolean done, ModelMap modelMap) {
        recommendedPersonArrayList = hrDealImpl.findRecommendedPersonByPosNoAndState(Integer.parseInt(positionNo),Integer.parseInt(sid));
        modelMap.addAttribute("recommendedPersonByPosNo",recommendedPersonArrayList);
        modelMap.addAttribute("positionNo",positionNo);
        modelMap.addAttribute("havePassed",false);
        modelMap.addAttribute("done",done);
        modelMap.addAttribute("display",display);
        if (recommendedPersonArrayList == null || recommendedPersonArrayList.size() == 0) {
            modelMap.addAttribute("zero",true);
        } else {
            modelMap.addAttribute("zero",false);
        }
        return "state";
    }

    @ResponseBody
    @RequestMapping("/state")
    public String ceshi() {
        return "测试";
    }
    /*--------------------------------------------------------------*/
    @RequestMapping("/findPosByPosno")
    public String findPosByPosno(ModelMap modelMap,@RequestParam String posno) {
        position = hrDealImpl.findPosByPosno(Integer.parseInt(posno));
        modelMap.addAttribute("position",position);
        return "followPosition";
    }
}
