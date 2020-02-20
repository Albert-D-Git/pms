package com.dcr.email.controller;

import com.dcr.email.service.EmailService;
import com.dcr.utils.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/email")
public class EmailController {

    @Resource
    private EmailService emailService;


}
