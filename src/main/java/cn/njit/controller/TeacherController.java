package cn.njit.controller;

import cn.njit.entity.Course;
import cn.njit.entity.Teacher;
import cn.njit.service.CourseService;
import cn.njit.service.TeacherService;
import cn.njit.utils.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author dustdawn
 * @date 2019/10/18 15:04
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    private Teacher teacher;

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("tno");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        teacher = teacherService.selectByPrimaryKey(no);
        if (teacher != null){
            System.out.println(teacher.getPassword());
            if (teacher.getPassword().equals(password)) {
                session.setAttribute("userSession", teacher);
                session.setAttribute("currentTime", LoginUtil.getTime());
                if ("true".equals(rememberMe)) {
                    Map<String, Cookie> map = LoginUtil.saveCookie("teacher", teacher.getTno(), teacher.getPassword());
                    response.addCookie(map.get("no"));
                    response.addCookie(map.get("password"));
                }
                return "redirect:/pages/teacher/index";
            }else {
                request.setAttribute("errorMsg", "用户密码错误");
                System.out.println("密码错误");
                return "teacher/login";
            }
        }else {
            request.setAttribute("errorMsg", "用户名不存在");
            return "teacher/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userSession");
        session.removeAttribute("currentTime");
        return "teacher/login";
    }

    @RequestMapping(value = "/getNameList",method = RequestMethod.POST)
    @ResponseBody
    public List<Teacher> getNameList() {
        return teacherService.findList();
    }

    //获取教师授课课程
    @RequestMapping(value = "/managePublic")
    public String managePublic(HttpServletRequest request) {
        if (null != teacher) {
            Course course =  new Course();
            course.setTno(this.teacher.getTno());
            course.setType("公选课");
            List<Course> listByEntity = courseService.findListByEntity(course);
            request.setAttribute("courseList", listByEntity);
        }

        return "teacher/managePublic";
    }

    //获取教师选修课课程
    @RequestMapping(value = "/manageElective")
    public String manageElective(HttpServletRequest request) {
        if (null != teacher) {
            Course course =  new Course();
            course.setTno(this.teacher.getTno());
            course.setType("选修课");
            List<Course> listByEntity = courseService.findListByEntity(course);
            request.setAttribute("courseList", listByEntity);
        }
        return "teacher/manageElective";
    }

}
