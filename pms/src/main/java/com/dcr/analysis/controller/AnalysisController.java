package com.dcr.analysis.controller;

import com.dcr.analysis.bean.Analysis;
import com.dcr.analysis.service.AnalysisService;
import com.dcr.utils.ResultEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/analyze")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @RequestMapping(value = "/insertProAna",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertProAna(Analysis analysis){
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = analysisService.insertProAna(analysis);
        if(true){
            map.put("status",200);
            map.put("msg","添加成功！");
        }else {
            map.put("status",1000);
            map.put("msg","添加失败！");
        }
        return map;

    }


    @RequestMapping(value = "/getAnalysisList",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis> getAnalysisList(){
        List<Analysis> analysisList = analysisService.getAnalysisList();
        return analysisList;
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Analysis getAnalysisById(@PathVariable("id") Integer id){
        return analysisService.getAnalysisById(id);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> updateAnalysisById(Analysis analysis){
        System.out.println(analysis.toString());
        Map<String,Object> map = new HashMap<String,Object>();
        boolean flag = analysisService.updateAnalysisById(analysis);
        if(flag){
            map.put("status",200);
            map.put("msg","更新成功！");
        }else {
            map.put("status",1000);
            map.put("msg","更新失败！");
        }
        return map;
    }

    /**
     *
     * @param cid  '0'>选择类型. 1'>项目名称 2'>标题
     * @param keyword
     * @param orderby  0'>排序.  1'>添加时间  2'>修改时间
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<Analysis> search(@Param("cid") Integer cid,@Param("keyword") String keyword,@Param("orderby") Integer orderby ){
        List<Analysis> list = analysisService.search(cid, keyword, orderby);
        return list;
    }


    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity del(@RequestParam("ids[]")Integer[] ids){
        System.out.println(ids.toString());
        boolean flag = analysisService.del(ids);
        if(flag){
            return ResultEntity.success();
        }
        else {
            return ResultEntity.error();
        }
    }
}
