package com.dcr.cust.service;

import com.dcr.cust.bean.Customer;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    public List<Customer> test();

    List<Customer> getCustomerList();

    boolean validateComname(String comname);

    void insertCustomer(Customer customer);


    List<Customer> search(Integer cid, String keyword, Integer orderby);

    Boolean batchDel(Integer[] ids);

    Customer getCustomerListById(Integer id);

    void editCustomer(Customer customer);

    List<Customer> findCustomers();

    void batchInsert(List<Customer> list);

    PageInfo<Customer> getCustomerPage(Integer pageNo, Map<String, Object> paramMap);
}
