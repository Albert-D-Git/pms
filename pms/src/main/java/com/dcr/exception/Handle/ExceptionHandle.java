package com.dcr.exception.Handle;

import com.dcr.utils.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    public ResultEntity handleSqlException(Exception exception){
        //立即给开发人员：发短信、邮箱
        //log4j.error("sql异常");//异常记录到文件中
        return ResultEntity.error().put("message","系统维护中，请稍后重试");
    }

    @ExceptionHandler(value = {ArithmeticException.class})
    @ResponseBody
    public ResultEntity handleArithmeticException(Exception exception){
        //立即给开发人员：发短信、邮箱
        //log4j.error("数学异常");//异常记录到文件中
        return ResultEntity.error().put("message","系统维护中，请稍后重试");
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    public ResultEntity handleRuntimeException(Exception exception){
        //立即给开发人员：发短信、邮箱
        //log4j.error("运行时异常");//异常记录到文件中
        return ResultEntity.error().put("message","系统维护中，请稍后重试");
    }
}
