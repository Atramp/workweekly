package com.teradata.workweekly.filter;

import com.teradata.workweekly.bean.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by alex on 15/8/20.
 */
public class AuthFilter extends HandlerInterceptorAdapter {
    public static ThreadLocal<User> username;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        if(true)
            return true;
        if (request.getRequestURI().contains("/verifycode"))
            return true;
        User user = (User) request.getSession().getAttribute("USER");
        if (user == null) {
            response.sendError(403, "用户未登录");
            return false;
        }
        username.set(user);
        return true;
    }
}
