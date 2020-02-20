package com.dcr.analyse.controller;

import com.dcr.analyse.bean.Comparision;
import com.dcr.analyse.service.ComparisionService;
import com.dcr.emp.bean.Employee;
import com.dcr.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/compare")
public class ComparisionController {

    @Resource
    private ComparisionService comparisionService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Comparision comparision, HttpSession session){
        Employee emp = (Employee)session.getAttribute("loginUser");
        comparision.setEmpfk(emp.getEid());
        comparisionService.saveInfo(comparision);
        return ResultEntity.success();
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<Comparision> getComparisionList(){
        return comparisionService.getComparisionList();
    }


    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public ModelAndView getAllComparisionList(){
        ModelAndView mv = new ModelAndView("indexvalue-base");

        PageInfo<Comparision> page = comparisionService.getAllComparisionList();

        mv.addObject("page",page);
        return mv;
    }
}
