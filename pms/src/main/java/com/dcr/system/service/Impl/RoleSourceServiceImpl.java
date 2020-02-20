package com.dcr.system.service.Impl;

import com.dcr.system.dao.RoleSourceMapper;
import com.dcr.system.service.RoleSourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourceServiceImpl implements RoleSourceService {

    @Resource
    private RoleSourceMapper roleSourceMapper;

    @Override
    public void saveInfo(Integer rid, Integer[] sourcesId) {
        roleSourceMapper.save(rid,sourcesId);
    }
}
