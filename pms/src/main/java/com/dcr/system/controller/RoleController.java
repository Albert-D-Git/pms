package com.dcr.system.controller;

import com.dcr.system.bean.Role;
import com.dcr.system.service.RoleService;
import com.dcr.system.service.RoleSourceService;
import com.dcr.utils.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleSourceService roleSourceService;

    @RequestMapping("/save")
    public String saveInfo(Role role,Integer[] sourcesId){

        //1.必须先保存role表，返回存的主键
        Integer rid = roleService.saveInfo(role);
        //2.根据返回的主键作为rid,权限内容（sourcesId作为sid）存到role_source表里
        roleSourceService.saveInfo(rid,sourcesId);
        return "redirect:/role.jsp";
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getRoleList(){
         List<Role> list = roleService.getRoleList();
         return ResultEntity.success().put("list",list);
    }
}
