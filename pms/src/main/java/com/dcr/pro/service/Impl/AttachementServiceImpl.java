package com.dcr.pro.service.Impl;

import com.dcr.pro.bean.Attachment;
import com.dcr.pro.dao.AttachmentMapper;
import com.dcr.pro.service.AttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttachementServiceImpl implements AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public void save(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }
}
