package cn.njit.controller;

import cn.njit.entity.Student;
import cn.njit.service.StudentService;
import cn.njit.utils.LoginUtil;
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
 * @date 2019/9/30 15:24
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("sno");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        Student student = studentService.selectByPrimaryKey(no);
        if (student != null){
            if (student.getPassword().equals(password)) {
                session.setAttribute("userSession", student);
                session.setAttribute("currentTime", LoginUtil.getTime());
                if ("true".equals(rememberMe)) {
                    Map<String, Cookie> map = LoginUtil.saveCookie("student", student.getSno(), student.getPassword());
                    response.addCookie(map.get("no"));
                    response.addCookie(map.get("password"));
                }
                return "redirect:/pages/student/index";
            }else {
                request.setAttribute("errorMsg", "用户密码错误");
                System.out.println("密码错误");
                return "student/login";
            }
        }else {
            request.setAttribute("errorMsg", "用户名不存在");
            return "student/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        session.removeAttribute("currentTime");
        return "student/login";
    }






}
