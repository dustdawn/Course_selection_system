package cn.njit.filter;

import cn.njit.utils.logger.LoggerUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//Filter的生命周期先于springMvcServlet创建所以注解无效
//@WebFilter(filterName = "ValidateCodeFilter", urlPatterns = "/**/toLogin")

public class ValidateCodeFilter implements Filter {
    public void destroy() {
        LoggerUtils.debug(getClass(), "验证码校验过滤器关闭>>>>>>>>");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        String _code = (String) session.getAttribute("_code");
        String code = request.getParameter("lastestCode");

        if (null != _code && !_code.equalsIgnoreCase(code)) {
            request.setAttribute("errorMsg", "验证码错误");

            String servletPath = request.getServletPath();
            String path = servletPath.substring(servletPath.indexOf('/') + 1, servletPath.lastIndexOf('/'));

            request.getRequestDispatcher("/WEB-INF/pages/" + path + "/login.jsp").forward(request,response);
            //response.sendRedirect(request.getContextPath() + "/admin/index");
            return;
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        LoggerUtils.debug(getClass(), "验证码校验过滤器启动>>>>>>>>");
    }

}
