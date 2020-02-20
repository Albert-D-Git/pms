package com.dcr.usual.controller;

import com.dcr.usual.bean.Notice;
import com.dcr.usual.service.NoticeService;
import com.dcr.utils.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    public NoticeService noticeService;

    //异步请求加载notices三条信息
    @RequestMapping(value = "/latest",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getLimitNotices(){
        //异步 规范一般返回pojo对象
        List<Notice> noticeList = noticeService.getLimitNotices();
        return ResultEntity.success().put("noticeList",noticeList);
    }
}
