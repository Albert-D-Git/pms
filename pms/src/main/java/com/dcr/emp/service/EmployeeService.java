package com.dcr.emp.service;

import com.dcr.emp.bean.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getManagers();

    Employee getEmployeeInLogin(Employee employee);

    List<Employee> getEmployeeList();
}
