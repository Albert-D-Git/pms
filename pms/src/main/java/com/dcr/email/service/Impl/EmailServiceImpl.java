package com.dcr.email.service.Impl;

import com.dcr.email.dao.EmailMapper;
import com.dcr.email.service.EmailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailService {

    @Resource
    private EmailMapper emailMapper;
}
