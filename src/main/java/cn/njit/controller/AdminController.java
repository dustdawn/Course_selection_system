package cn.njit.controller;

import cn.njit.entry.Admin;
import cn.njit.entry.Teacher;
import cn.njit.service.AdminService;
import cn.njit.service.TeacherService;
import cn.njit.utils.LoginUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author dustdawn
 * @date 2019/10/16 14:30
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    //创建一个日志对象，就可以通过日志输出
    private Logger logger=
            Logger.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;

    //登录
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String no = request.getParameter("admin");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        Admin admin = adminService.selectByPrimaryKey(no);
        if (admin != null){
            if (admin.getPassword().equals(password)) {
                session.setAttribute("userSession", admin);
                session.setAttribute("currentTime", LoginUtil.getTime());
                if ("true".equals(rememberMe)) {
                    Map<String, Cookie> map = LoginUtil.saveCookie(admin.getNo(), admin.getPassword());
                    response.addCookie(map.get("no"));
                    response.addCookie(map.get("password"));
                }
                return "redirect:/pages/admin/index";
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

    //注销
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        session.removeAttribute("currentTime");
        return "admin/login";
    }
    //教师管理
    @RequestMapping(value = "/teacherForm")
    public String teacherForm(HttpServletRequest request, Teacher teacher) {
        if (null != teacher) {
            teacher.setDelFlag(0);
            teacherService.insertSelective(teacher);
        }

        return "redirect:/pages/admin/teacherForm";
    }

    @RequestMapping(value = "/teacherList")
    public String teacherList() {
        return "redirect:/pages/admin/teacherList";
    }

    //学生管理
    @RequestMapping(value = "/studentForm")
    public String studentForm() {
        return "redirect:/pages/admin/studentForm";
    }

    @RequestMapping(value = "/studentList")
    public String studentList() {
        return "redirect:/pages/admin/studentList";
    }

    //课程管理
    @RequestMapping(value = "/courseForm")
    public String courseForm() {
        return "redirect:/pages/admin/courseForm";
    }

    @RequestMapping(value = "/courseList")
    public String courseList() {
        return "redirect:/pages/admin/courseList";
    }

    //个人信息管理
    @RequestMapping(value = "/info")
    public String info() {
        return "redirect:/pages/admin/info";
    }
    //修改密码
    @RequestMapping(value = "/pswChange")
    public String pswChange() {
        return "redirect:/pages/admin/pswChange";
    }
}
