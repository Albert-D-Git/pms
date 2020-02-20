package com.dcr.emp.controller;

import com.dcr.emp.bean.Employee;
import com.dcr.emp.service.EmployeeService;
import com.dcr.system.bean.Sources;
import com.dcr.system.service.SourcesService;
import com.dcr.utils.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private SourcesService sourcesService;

    @RequestMapping("/managers")
    @ResponseBody
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }

    /**
     *
     * RedirectAttributes 用于重定向时候想页面传递数据
     * 要求 使用addFlushAttribute 键值对 而且不能直接到达页面，需要通过mvc接口
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String text = (String) session.getAttribute("text");
        session.removeAttribute("text");//获取session域中的值之后要立即移除
        if(code.equalsIgnoreCase(text)){
            Employee emp = employeeService.getEmployeeInLogin(employee);

            if(emp == null){
                attributes.addFlashAttribute("msg","用户名或密码错误！");//返回到页面取出之后框架会自动移除
                return "redirect:/login";
            }else{

                List<Sources> secondSources = sourcesService.getSecondSourcesListByEid(emp.getEid());
                session.setAttribute("secondSources",secondSources);
                session.setAttribute("loginUser",emp);
            }
        }else{
            //重定向同时如果想携带数据到页面 mvc方法 参数携带RedirectAttributes参数
            attributes.addFlashAttribute("msg","验证码错误！");
            return "redirect:/login";
        }


        return "redirect:/index.jsp";
    }


    @RequestMapping("/list")
    @ResponseBody
    public ResultEntity getEmployeeList(){

        List<Employee> employeeList = employeeService.getEmployeeList();

        return ResultEntity.success().put("employeeList",employeeList);
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity logout(HttpSession session){
        session.removeAttribute("loginUser");
        return ResultEntity.success();
    }
}
