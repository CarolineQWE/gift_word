package com.soft.gift.conf;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fyq on 2017/4/7.
 */
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String loginUrl="/login";
        //1、请求到登录页面 放行
        if(request.getServletPath().startsWith(loginUrl)) {
            return true;
        }

        //2、比如退出、首页等页面无需登录，即此处要放行 允许游客的请求
        if(request.getServletPath().startsWith("/index")) {
            return true;
        }
        if(request.getServletPath().startsWith("/giftInfo")) {
            return true;
        }

        if(request.getServletPath().startsWith("/strategy/all")) {
            return true;
        }

        if(request.getServletPath().startsWith("/strategy/strategies")) {
            return true;
        }

        if(request.getServletPath().startsWith("/strategy/strategyInfo")) {
            return true;
        }
        //3、如果用户已经登录 放行
        if(request.getSession().getAttribute("userInfo") != null) {
            //更好的实现方式的使用cookie
            return true;
        }

        //4、非法请求 即这些请求需要登录后才能访问
        //重定向到登录页面
        response.sendRedirect(request.getContextPath() + loginUrl);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
