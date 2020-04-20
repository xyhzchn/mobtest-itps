package com.yoozoo.controller.annotation;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * session拦截器
 * Created by guoxx on 2019/1/4.
 */
public class SystemSessionInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URL = "/jsp/login.jsp";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession(true);
        //session中获取用户信息
        Object obj = session.getAttribute("userInfo");
        if(obj == null || "".equals(obj.toString())){
            response.sendRedirect(request.getSession().getServletContext().getContextPath()+LOGIN_URL);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
