package org.ty.cloudCourse.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangtaiyang
 * @date 2018/6/27
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        if (req.getServletPath().contains("login")||req.getServletPath().contains("checkCode")||req.getServletPath().contains("exit")) {
            return true;
        }
        if (req.getSession().getAttribute("userId") == null) {
            req.setAttribute("msg", "登录信息已过期，请重新登录");
            req.getRequestDispatcher("../error.jsp").forward(req, res);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object o, Exception e) throws Exception {

    }
}
