package com.dcr.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class ControllerLogAspect {

    private static final Logger logger = Logger.getLogger(ControllerLogAspect.class);

    @Before(value = "execution(* com.dcr.*.controller..*(..))")
    public void beforeLog(JoinPoint point){
        String className = point.getTarget().getClass().getName();//类名
        String methodName = point.getSignature().getName();//获取方法名
        Object[] args = point.getArgs();
        List<Object> params = Arrays.asList(args);//参数列表
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        String remoteHost = request.getRemoteHost();//访问者ip
        logger.info("请求类是："+className+",方法名是："+methodName+",参数列表为："+params+",访问者地址为："+remoteHost);
    }


    @AfterReturning(value = "execution(* com.dcr.*.controller..*(..))",returning = "result")
    public void afterLog(Object result){

        logger.info("目标方法返回的结果是："+result);
    }

    @AfterThrowing(value = "execution(* com.dcr.*.controller..*(..))",throwing = "ex")
    public void afterThrowing(Throwable ex){
        ex.printStackTrace();
    }
}
