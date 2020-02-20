package com.dcr.usual.controller;

import com.dcr.usual.bean.TieZi;
import com.dcr.usual.service.TieZiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tiezi")
public class TieZiController {

    @Resource
    private TieZiService tieziService;

    @RequestMapping("save")
    public String saveInfo(TieZi tieZi){
        tieZi.setAddDate(new Date());
        tieziService.saveInfo(tieZi);
        return "main";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<TieZi> getTieziLimitedList(){
        return tieziService.getTieziLimitedList();
    }

}
