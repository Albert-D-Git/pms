package com.dcr.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //session中有loginUser不拦截 login不拦截 loginController不拦截 静态资源不拦截 验证码不拦截
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object loginUser = session.getAttribute("loginUser");

        String requestURI = req.getRequestURI();//请求地址
        if(loginUser!=null || requestURI.contains("login") || requestURI.contains("static") || requestURI.contains("code")){
            chain.doFilter(req,resp);//放行
        }else{
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
