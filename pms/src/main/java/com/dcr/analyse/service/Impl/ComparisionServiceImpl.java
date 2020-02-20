package com.dcr.analyse.service.Impl;

import com.dcr.analyse.bean.Comparision;
import com.dcr.analyse.bean.ComparisionExample;
import com.dcr.analyse.dao.ComparisionMapper;
import com.dcr.analyse.service.ComparisionService;
import com.dcr.emp.bean.Employee;
import com.dcr.emp.bean.EmployeeExample;
import com.dcr.emp.dao.EmployeeMapper;
import com.dcr.utils.ConstantUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComparisionServiceImpl implements ComparisionService {

    @Resource
    private ComparisionMapper comparisionMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public void saveInfo(Comparision comparision) {
        comparisionMapper.insert(comparision);
    }

    @Override
    public List<Comparision> getComparisionList() {
        ComparisionExample example = new ComparisionExample();
        ComparisionExample.Criteria criteria = example.createCriteria();
        criteria.andYearEqualTo("2019");
        return comparisionMapper.selectByExample(example);

    }

    @Override
    public PageInfo<Comparision> getAllComparisionList() {
        ComparisionExample example = new ComparisionExample();
        List<Comparision> comparisions = comparisionMapper.selectByExample(example);
        EmployeeExample example1 = new EmployeeExample();
        EmployeeExample.Criteria criteria1 = example1.createCriteria();

        for (Comparision comparision : comparisions) {
            criteria1.andEidEqualTo(comparision.getEmpfk());
            Employee employee = employeeMapper.selectByPrimaryKey(comparision.getEmpfk());
            comparision.setEmpName(employee.getEname());
        }


        PageInfo<Comparision> pageInfo = new PageInfo<Comparision>(comparisions, ConstantUtils.NAVIGATE_PAGENUM);
        return pageInfo;
    }


}
