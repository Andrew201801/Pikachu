package com.Pikachu.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.tools.java.Constants;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的URL
        String url = request.getRequestURI();

        // 注意：一些静态文件不能拦截，否则会死循环，知道内存耗尽
        // URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        // /RMSClient/WEB-INF/view/register/login.html
        // 地址为/RMSClient/media/js/lib/jquery-1.7.2.min.js
        // 地址为/RMSClient/media/css/base.css
        // 地址为/RMSClient/media/css/login.css
        // 地址为/RMSClient/media/js/lib/html5shiv.js
        // 地址为/RMSClient/media/js/server/login.js
        if (url.indexOf("/RMSClient/login") >= 0 || url.indexOf("/register/login.html") > 0
                || url.indexOf("/media") > 0 || url.indexOf("/user/login") > 0) {
            return true;
        }
        // 获取Session，判断是否能拿到token，如果没有的话，就需要跳转到主界面
        HttpSession session = request.getSession();

        String token = (String) session.getAttribute(Constants.TOKEN);
        System.out.println("得到的token为：" + token);
        if (token != null) {
            return true;
        }
        // 不符合条件的，跳转到登录界面
        request.getRequestDispatcher("/WEB-INF/view/register/login.html").forward(request, response);

        // response.sendRedirect("/WEB-INF/view/register/login.html");

        return false;
    }
    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

}
