package com.dcr.system.service;

import com.dcr.system.bean.Role;

import java.util.List;

public interface RoleService {
    Integer saveInfo(Role role);

    List<Role> getRoleList();
}
