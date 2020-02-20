package com.dcr.system.service.Impl;

import com.dcr.system.bean.Role;
import com.dcr.system.bean.RoleExample;
import com.dcr.system.dao.RoleMapper;
import com.dcr.system.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Integer saveInfo(Role role) {
        roleMapper.insert(role);
        return role.getRoleid();
    }

    @Override
    public List<Role> getRoleList() {
        RoleExample example = new RoleExample();
        return roleMapper.selectByExample(example);
    }
}
