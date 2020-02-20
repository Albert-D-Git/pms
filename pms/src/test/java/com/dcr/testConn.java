package com.dcr;

import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class testConn {
    @Test
    public void testConnection(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactoryBean bean = context.getBean(SqlSessionFactoryBean.class);
        System.out.println(bean);
    }
}
