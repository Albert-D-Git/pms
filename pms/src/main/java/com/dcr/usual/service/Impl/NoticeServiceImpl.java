package com.dcr.usual.service.Impl;

import com.dcr.usual.bean.Notice;
import com.dcr.usual.bean.NoticeExample;
import com.dcr.usual.dao.NoticeMapper;
import com.dcr.usual.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    public NoticeMapper noticeMapper;

    @Override
    public List<Notice> getLimitNotices() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        return noticeMapper.selectByExample(example);
    }
}
