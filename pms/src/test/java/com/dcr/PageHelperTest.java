package com.dcr;

import com.dcr.cust.bean.Customer;
import com.dcr.cust.service.CustomerService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService service = context.getBean(CustomerService.class);
        List<Customer> test = service.test();
        for (Customer customer : test) {

            System.out.println(customer.toString());
        }
    }
}
