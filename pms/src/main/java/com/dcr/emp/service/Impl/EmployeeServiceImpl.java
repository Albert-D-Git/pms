package com.dcr.emp.service.Impl;

import com.dcr.emp.bean.Employee;
import com.dcr.emp.bean.EmployeeExample;
import com.dcr.emp.dao.EmployeeMapper;
import com.dcr.emp.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);


        return employeeMapper.selectByExample(example);

    }

    @Override
    public Employee getEmployeeInLogin(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if(employees.size() >0){
            return employees.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeList() {
        EmployeeExample example = new EmployeeExample();
        return employeeMapper.selectByExample(example);
    }
}
