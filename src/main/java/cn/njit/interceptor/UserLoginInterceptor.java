package cn.njit.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dustdawn
 * @date 2019/10/18 20:38
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER=
            Logger.getLogger(UserLoginInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        request.setCharacterEncoding("UTF-8");
        //记录请求日志
        LOGGER.info("上下文和请求路径：" + request.getRequestURI());

        String servletPath = request.getServletPath();
        LOGGER.info("请求资源路径：" + request.getServletPath());
        if(!servletPath.equals("")){
            //判断是否登录
            Object loginUser = request.getSession().getAttribute("userSession");
            if(loginUser == null){

                LOGGER.info(">>>未登录，请重新登录<<<");
                //未登录重定向到登录页面,getContextPath是返回的项目上下文
                response.sendRedirect(request.getContextPath() + "/pages/student/login");
                return false;
            }
        }
        //返回true，才会找下一个拦截器，如果没有下一个拦截器，则去Controller
        return true;

    }



}
