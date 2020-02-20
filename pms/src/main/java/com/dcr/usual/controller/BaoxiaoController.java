package com.dcr.usual.controller;

import com.dcr.emp.bean.Employee;
import com.dcr.usual.bean.Baoxiao;
import com.dcr.usual.service.BaoxiaoService;
import com.dcr.utils.MapUtil;
import com.dcr.utils.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/bx")
public class BaoxiaoController {

    @Resource
    private BaoxiaoService baoxiaoService;

    //同步分页
    /*@RequestMapping("/list")
    public ModelAndView getBaoxiaoList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, HttpSession session, HttpServletRequest request){
        String requestURI = request.getRequestURI();//  pms/bx/list  将这个路径传回去在jsp上获取就可以复用，比如项目传回去pms/pro/list

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "search_");

        //查询我的报销 不能把所有的报销都显示出来
        Employee employee = (Employee) session.getAttribute("loginUser");
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        PageInfo<Baoxiao> page = baoxiaoService.getBaoxiaoList(employee.getEid(),pageNo,paramMap);

        String queryStr = MapUtil.parseParamMapToQueryString(paramMap);
        //将获取到的requestUri传回去
        mv.addObject("requestURI",requestURI);
        //将查询的条件拼接成一个字符串返回去，点击下一页或者其他操作把查询条件再传过来
        mv.addObject("queryStr",queryStr);
        mv.addObject("page",page);//返回到mybaoxiao-base.jsp中
        return mv;
    }*/

    //异步请求分页
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    //此处的返回值正规开发一般会返回一个pojo对象，所以在工具类中自定义一个pojo对象
    public ResultEntity getBaoxiaoList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo, HttpSession session, HttpServletRequest request){
        String requestURI = request.getRequestURI();//  pms/bx/list  将这个路径传回去在jsp上获取就可以复用，比如项目传回去pms/pro/list

        Map<String, Object> paramMap = WebUtils.getParametersStartingWith(request, "search_");//会自动去掉search_这个前缀，取得map{cid=?,keyword=?}

        //查询我的报销 不能把所有的报销都显示出来
        Employee employee = (Employee) session.getAttribute("loginUser");
        PageInfo<Baoxiao> page = baoxiaoService.getBaoxiaoList(employee.getEid(),pageNo,paramMap);
        String queryStr = MapUtil.parseParamMapToQueryString(paramMap);
        return ResultEntity.success().put("requestURI",requestURI).put("queryStr",queryStr).put("page",page);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity edit(@RequestBody Baoxiao baoxiao){
        boolean flag = baoxiaoService.edit(baoxiao);
        if(flag){
            return ResultEntity.success();
        }else{
            return ResultEntity.error();
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(@RequestBody Baoxiao baoxiao,HttpSession session){
        boolean flag = baoxiaoService.saveInfo(baoxiao,session);
        if(flag){
            return ResultEntity.success();
        }else{
            return ResultEntity.error();
        }
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResultEntity del(@PathVariable("id") String id){
        boolean flag = baoxiaoService.del(id);
        if(flag){
            return ResultEntity.success();
        }else{
            return ResultEntity.error();
        }
    }

}
