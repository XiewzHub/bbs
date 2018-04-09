package com.hnie.forum.web.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiewz on 2018/4/8.
 */
public class LoginedInterceptor implements HandlerInterceptor {
    private final String ADMINSESSION = "user";

    // 拦截前处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object sessionObj = request.getSession().getAttribute(ADMINSESSION);
        if(sessionObj!=null) {
            return true;
        }
        if(request.getServletPath().matches("^/user/login.*$") ||
                request.getServletPath().matches("^/user/register.*$")
                ){
            return true;
        }
        // response.sendRedirect(/*"/"+request.getContextPath()+*/"http://localhost:7777/smart_froum/user/loginOrRegister");
        request.getRequestDispatcher("/user/loginOrRegister").forward(request,response);
        return false;
    }

    /**
     * 拦截后处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 全部完成后处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
