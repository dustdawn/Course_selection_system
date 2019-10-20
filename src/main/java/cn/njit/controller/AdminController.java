package cn.njit.controller;

import cn.njit.entry.Admin;
import cn.njit.entry.Course;
import cn.njit.entry.Student;
import cn.njit.entry.Teacher;
import cn.njit.service.AdminService;
import cn.njit.service.CourseService;
import cn.njit.service.StudentService;
import cn.njit.service.TeacherService;
import cn.njit.utils.LoginUtil;
import org.apache.log4j.Logger;
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
 * @date 2019/10/16 14:30
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    //创建一个日志对象，就可以通过日志输出
    private static final Logger LOGGER =
            Logger.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    private Admin admin;

    //登录
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String no = request.getParameter("admin");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        admin = adminService.selectByPrimaryKey(no);
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
        session.invalidate();
        return "admin/login";
    }
    //教师管理
    @RequestMapping(value = "/teacherForm", method = RequestMethod.POST)
    public String teacherForm(Teacher teacher) {

        if (null != teacher) {
            teacher.setDelFlag(0);
            int flag = teacherService.insertSelective(teacher);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
            }else {
                LOGGER.info(">>>添加失败<<<");
            }
        }
        return "redirect:/pages/admin/teacherForm";
    }

    @RequestMapping(value = "/teacherList")
    public String teacherList(HttpServletRequest request) {
        System.out.println(request.getParameter("tno"));
        System.out.println(request.getParameter("name"));
        List<Teacher> teacherList = teacherService.findList();
        request.setAttribute("teacherList", teacherList);
        return "admin/teacherList";
    }

    //学生管理
    @RequestMapping(value = "/studentForm")
    public String studentForm(Student student) {

        if (null != student) {
            student.setDelFlag(0);
            int flag = studentService.insertSelective(student);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
            }else {
                LOGGER.info(">>>添加失败<<<");
            }
        }
        return "redirect:/pages/admin/studentForm";
    }

    @RequestMapping(value = "/studentList")
    public String studentList(HttpServletRequest request) {
        System.out.println(request.getParameter("sno"));
        System.out.println(request.getParameter("name"));

        List<Student> studentList = studentService.findList();
        request.setAttribute("studentList", studentList);
        return "admin/studentList";
    }

    //课程管理
    @RequestMapping(value = "/courseForm")
    public String courseForm(Course course) {
        if (null != course) {
            course.setDelFlag(0);
            int flag = courseService.insertSelective(course);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
            }else {
                LOGGER.info(">>>添加失败<<<");
            }
        }
        return "redirect:/pages/admin/courseForm";
    }

    @RequestMapping(value = "/courseList")
    public String courseList(HttpServletRequest request) {
        System.out.println(request.getParameter("cno"));
        System.out.println(request.getParameter("name"));

        List<Course> courseList = courseService.findList();
        request.setAttribute("courseList", courseList);
        System.out.println();
        return "admin/courseList";
    }

    //个人信息管理
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request) {
        request.setAttribute("user", admin);
        return "redirect:/pages/admin/info";
    }
    //修改密码
    @RequestMapping(value = "/pswChange")
    public String pswChange() {
        return "redirect:/pages/admin/pswChange";
    }

    @RequestMapping(value = "checkTeacher")
    @ResponseBody
    public String checkTeacher(String tno) {

        String result = "{\"valid\":false}";
        if (null != tno) {
            Teacher teacher = teacherService.selectByPrimaryKey(tno);
            if (null == teacher) {
                //用户不存在
                result = "{\"valid\":true}";
            }else {
                //用户已经存在
                result = "{\"valid\":false}";
            }
        }
        return result;
    }

    @RequestMapping(value = "checkStudent")
    @ResponseBody
    public String checkStudent(String sno) {

        String result = "{\"valid\":false}";
        if (null != sno) {
            Student student = studentService.selectByPrimaryKey(sno);
            if (null == student) {
                //用户不存在
                result = "{\"valid\":true}";
            }else {
                //用户已经存在
                result = "{\"valid\":false}";
            }
        }
        return result;
    }

    @RequestMapping(value = "checkCourse")
    @ResponseBody
    public String checkCourse(String cno) {

        String result = "{\"valid\":false}";
        if (null != cno) {
            Course course = courseService.selectByPrimaryKey(cno);
            if (null == course) {
                //用户不存在
                result = "{\"valid\":true}";
            }else {
                //用户已经存在
                result = "{\"valid\":false}";
            }
        }
        return result;
    }
}
