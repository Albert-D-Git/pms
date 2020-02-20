package com.dcr.system.controller;

import com.dcr.system.bean.Sources;
import com.dcr.system.service.SourcesService;
import com.dcr.utils.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/source")
public class SourcesController {
    @Resource
    private SourcesService sourcesService;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public List<Sources> getSoueceList(){
        //根据父id查询该id下的所有子id
        List<Sources> list = sourcesService.getSourceListByPid(0);

        queryChildren(list.get(0));
        return list;
    }

    //递归查children
    private void queryChildren(Sources topSources) {
        Integer id = topSources.getId();//id作为接下来的pid
        List<Sources> sources = sourcesService.getSourceListByPid(id);//查出来的是二级菜单
        for (Sources source : sources) {
            queryChildren(source);
        }
        topSources.setChildren(sources);
    }
    //增
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }
    //改的回显
    @RequestMapping(value = "/info/{id}",method = RequestMethod.GET)
    public ModelAndView getSourceById(@PathVariable("id") Integer sid){
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources sources =  sourcesService.getSourceById(sid);
        mv.addObject("source",sources);
        return mv;
    }
    //改
    @RequestMapping(value = "/edit",method = RequestMethod.PUT)
    public String edit(Sources sources){
        sourcesService.edit(sources);
        return "redirect:/pm.jsp";
    }
    //删
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity delteSourceById(@PathVariable("id") Integer sid){
        sourcesService.delteSourceById(sid);
        return ResultEntity.success();
    }
}
