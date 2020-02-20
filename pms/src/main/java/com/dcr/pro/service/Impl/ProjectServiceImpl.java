package com.dcr.pro.service.Impl;

import com.dcr.cust.bean.Customer;
import com.dcr.cust.bean.CustomerExample;
import com.dcr.cust.dao.CustomerMapper;
import com.dcr.emp.bean.Employee;
import com.dcr.emp.bean.EmployeeExample;
import com.dcr.emp.dao.EmployeeMapper;
import com.dcr.pro.bean.Project;
import com.dcr.pro.bean.ProjectExample;
import com.dcr.pro.dao.ProjectMapper;
import com.dcr.pro.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    //项目经理
    @Resource
    private EmployeeMapper employeeMapper;





    @Override
    public void insertPro(Project project) {
        projectMapper.insertSelective(project);
    }

    @Override
    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> list = projectMapper.selectAllPro(example);

        return list;
    }

    @Override
    public Project getProjectByPid(Integer pid) {
        ProjectExample example = new ProjectExample();
        return projectMapper.selectByPrimaryKey(pid);
    }

    @Override
    public void edit(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Transactional(rollbackFor = SQLException.class)
    @Override
    public Boolean batchDel(Integer[] ids) {

        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(Arrays.asList(ids));
        int i = projectMapper.deleteByExample(example);

        return i==ids.length;

    }

    /**
     *
     * @param cid 1项目名称pname  2项目经理employee
     * @param keyword 关键字
     * @param orderby id排序 buildTime立项时间  endTime计划完成时间
     * @return
     */
    @Override
    public List<Project> search(Integer cid, String keyword, Integer orderby) {
        return projectMapper.findProject(cid,keyword,orderby);
    }

    @Override
    public List<Project> getProListWithoutAnalyze() {
        return projectMapper.getProListWithoutAnalyze();
    }
}
