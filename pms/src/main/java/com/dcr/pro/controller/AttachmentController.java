package com.dcr.pro.controller;

import com.dcr.pro.bean.Attachment;
import com.dcr.pro.service.AttachmentService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    private AttachmentService attachmentService;


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment, MultipartFile file, HttpSession session){
        ServletContext application = session.getServletContext();//获取当前服务器对象

        String realPath = application.getRealPath("/upload");
        File directory = new File(realPath);
        if(!directory.exists()){
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-","")+file.getOriginalFilename();//获取文件的名字
        /**
         * 保存文件的三种方式
         * 1.IOUtils.copy(file.getInputStream(),new FileOutputStream(realPath+"\\"+fileName));
         * 2.file.transferTo(new File(realPath+"\\"+fileName));
         * 3.原生输入输出流，数组 读取
         */
        try{
            file.transferTo(new File(realPath+"\\"+fileName));
            attachment.setPath(realPath+"\\"+fileName);//将路径存入pojo对象的值中
            attachmentService.save(attachment);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "project-file";

    }
}
