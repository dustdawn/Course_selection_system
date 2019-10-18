package cn.njit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dustdawn
 * @date 2019/10/16 14:30
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @RequestMapping(value = "/toLogin")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("admin");
        String password = request.getParameter("password");

        //数据库中查询工号
        //Admin admin = AdminService.findById(no);
        if ("" != null){//admin
            if ("123".equals(password)) {//admin.getPassword
                return "admin/admin";
            }else {
                request.setAttribute("errorMsg", "用户密码错误");
                System.out.println("密码错误");
                return "admin/login";
            }
        }else {
            request.setAttribute("errorMsg", "用户名不存在");
            return "admin/login";
        }


    }
}
