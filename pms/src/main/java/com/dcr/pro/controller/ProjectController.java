package com.dcr.pro.controller;

import com.dcr.cust.bean.Customer;
import com.dcr.pro.bean.Project;
import com.dcr.pro.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        ModelAndView mv = new ModelAndView("project-base");
        List<Project> list = projectService.getProjectList();
        mv.addObject("list",list);

        return mv;
    }



    @RequestMapping(value = "/insertPro",method = RequestMethod.POST)
    public String insertPro(Project project){
        projectService.insertPro(project);
        return "redirect:/pro/list";
    }


    @RequestMapping(value = "/edit/{pid}",method = RequestMethod.GET)
    public ModelAndView updatePro(@PathVariable("pid") Integer pid){
        ModelAndView mv = new ModelAndView("project-base-edit");
        Project project = projectService.getProjectByPid(pid);
        mv.addObject("project",project);
        return mv;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String edit(Project project){
        projectService.edit(project);
        return "redirect:/pro/list";
    }

    @RequestMapping(value = "/detail/{pid}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("pid") Integer pid){
        ModelAndView mv = new ModelAndView("project-base-look");
        Project project = projectService.getProjectByPid(pid);
        mv.addObject("project",project);
        return mv;
    }

    @RequestMapping(value = "/batchDel",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDel(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            Boolean flag = projectService.batchDel(ids);
            map.put("status",200);
            map.put("message","删除成功！");
        }catch (Exception e){
            map.put("status",1000);
            map.put("message","删除失败！");
        }
        return map;
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String search(Integer cid, String keyword, Integer orderby, Map<String,Object> map){
        List<Project> list = projectService.search(cid,keyword,orderby);
        map.put("list",list);
        return "project-base";
    }


    @RequestMapping(value = "/proListWithoutAnalyze",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> proListWithoutAnalyze(){
        return projectService.getProListWithoutAnalyze();
    }

    @RequestMapping(value = "/listAtt",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectListAtt(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Project> list = projectService.getProjectList();
        return list;
    }
}
