package com.dcr.system.service.Impl;

import com.dcr.emp.dao.EmployeeMapper;
import com.dcr.system.bean.Sources;
import com.dcr.system.bean.SourcesExample;
import com.dcr.system.dao.SourcesMapper;
import com.dcr.system.service.SourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {

    @Resource
    private SourcesMapper sourcesMapper;



    @Override
    public List<Sources> getSourceListByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        return sourcesMapper.selectByExample(example);
    }

    @Override
    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }

    @Override
    public Sources getSourceById(Integer sid) {
        return sourcesMapper.selectByPrimaryKey(sid);
    }

    @Override
    public void edit(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
    public void delteSourceById(Integer sid) {
        sourcesMapper.deleteByPrimaryKey(sid);
    }

    @Override
    public List<Sources> getSecondSourcesListByEid(Integer eid) {
        //查询二级菜单
        List<Sources> sources = sourcesMapper.getSourcesByPidAndEid(eid,1);

        for (Sources source : sources) {
            Integer pid = source.getId();
            //保证三级菜单是属于员工的权限
            List<Sources> menus = sourcesMapper.getSourcesByPidAndEid(eid,pid);
            source.setChildren(menus);
        }

        return sources;
    }
}
