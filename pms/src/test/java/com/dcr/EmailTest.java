package com.dcr;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

public class EmailTest {
    @Test
    public void test01() throws MessagingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-email.xml");
        //创建发送邮件的对象
        JavaMailSenderImpl sender = context.getBean(JavaMailSenderImpl.class);
        //创建邮件对象
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");//发送图片还需要设置multipart encoding
        helper.setFrom("823067762@qq.com");
        helper.setTo("1763416407@qq.com");

        helper.setSubject("你好！");//设置主题
        helper.setText("<html><head></head><body>这是我们的<span style='color:red'>小帅哥</span><img src=cid:identifierIMG></body></html>",true);//设置内容

        //作文正文内容发送图片
        FileSystemResource res = new FileSystemResource(new File("C:\\Users\\CRnnn\\Desktop\\xpy.jpg"));
        helper.addInline("identifierIMG",res);

        //作为附件发送图片
        FileSystemResource fsr = new FileSystemResource(new File("C:\\Users\\CRnnn\\Desktop\\xpy.jpg"));
        helper.addAttachment("pic.jpg",fsr);
        //发送邮件
        sender.send(mimeMessage);
    }
}

