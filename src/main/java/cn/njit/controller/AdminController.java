package cn.njit.controller;

import cn.njit.entry.Admin;
import cn.njit.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dustdawn
 * @date 2019/10/16 14:30
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("admin");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        //数据库中查询工号
        Admin admin = adminService.selectByPrimaryKey(no);
        if (admin != null){
            if (admin.getPassword().equals(password)) {
                session.setAttribute("userSession", admin);//后面改为admin对象
                Date date = Calendar.getInstance().getTime();
                String currentTime = new SimpleDateFormat("YYYY年MM月dd日").format(date);

                session.setAttribute("currentTime", currentTime);
                //return "admin/admin";
                return "redirect:/pages/admin/admin";
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
    @RequestMapping(value = "/check")
    public String checkedLogin(HttpSession session) {
        if (session.getAttribute("userSession") == null) {

            return "redirect:/admin/login";
        }else {
            return "admin/admin";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        session.removeAttribute("currentTime");
        return "admin/login";
    }
}
