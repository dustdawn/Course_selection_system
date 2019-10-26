package cn.njit.controller;

import cn.njit.entity.Course;
import cn.njit.entity.Student;
import cn.njit.service.CourseService;
import cn.njit.service.StudentService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dustdawn
 * @date 2019/9/30 15:24
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    //创建一个日志对象，就可以通过日志输出
    private static final Logger LOGGER =
            Logger.getLogger(StudentController.class);

    private Student student;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("sno");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        student = studentService.selectByPrimaryKey(no);
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

    //课程管理
    @RequestMapping(value = "/managePublic")
    public String managePublic(HttpServletRequest request) {
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());
            Course course = new Course();
            course.setType("公选课");
            course.setStudent(s);

            List<Course> courseList = courseService.findListByEntity(course);
            request.setAttribute("courseList", courseList);
        }



        return "student/managePublic";
    }

    @RequestMapping(value = "/manageElective")
    public String manageElective(HttpServletRequest request) {
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());
            Course course = new Course();
            course.setType("选修课");
            course.setStudent(s);

            List<Course> courseList = courseService.findListByEntity(course);
            request.setAttribute("courseList", courseList);
        }

        return "student/manageElective";
    }


    //课程选修
    @RequestMapping(value = "/selectPublic")
    public String selectPublic(HttpServletRequest request) {
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());

            Course course = new Course();
            course.setType("公选课");

            //所有公选课
            List<Course> allPublicCourse = courseService.findListByEntity(course);

            course.setStudent(s);
            //已选公选课
            List<Course> selectPublicCourse = courseService.findListByEntity(course);

            //过滤已选课程
            List<Course> collect = allPublicCourse.stream().filter(
                    (Course c) -> !selectPublicCourse.contains(c)).collect(Collectors.toList());
            request.setAttribute("courseList", collect);

        }




        return "student/selectPublic";
    }

    @RequestMapping(value = "/selectElective")
    public String selectElective(HttpServletRequest request) {
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());

            Course course = new Course();
            course.setType("选修课");
            //所有选修课
            List<Course> allPublicCourse = courseService.findListByEntity(course);

            course.setStudent(s);
            //已选选修课
            List<Course> selectPublicCourse = courseService.findListByEntity(course);

            //过滤已选课程
            List<Course> collect = allPublicCourse.stream().filter(
                    (Course c) -> !selectPublicCourse.contains(c)).collect(Collectors.toList());
            request.setAttribute("courseList", collect);

        }

        return "student/selectElective";
    }

    //个人信息
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request) {
        if (null != student) {
            request.setAttribute("student", student);
        }

        return "student/info";
    }

    //信息修改
    @RequestMapping(value = "/studentUpdate")
    public String studentUpdate(Student student) {
        if (null != student) {
            student.setDelFlag(0);
            int flag = studentService.updateByPrimaryKeySelective(student);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                this.student = studentService.selectByPrimaryKey(student.getSno());
            }else {
                LOGGER.info(">>>修改失败<<<");
            }
        }
        return "redirect:/student/info";
    }

    //密码修改
    @RequestMapping(value = "/studentPswChange")
    public String studentPswChange(HttpServletRequest request) {
        String oldPassword = request.getParameter("passwordOld");
        String newPassword = request.getParameter("passwordNew");
        String re = "";
        if (oldPassword != null && newPassword != null) {
            if (null != student && student.getPassword().equals(oldPassword)) {
                student.setPassword(newPassword);
                int flag = studentService.updateByPrimaryKeySelective(student);
                if (1 == flag) {
                    LOGGER.info(">>>修改成功<<<");
                    this.student = studentService.selectByPrimaryKey(student.getSno());
                    re = "修改密码成功";
                }else {
                    LOGGER.info(">>>修改失败<<<");
                    re = "修改密码失败";
                }
            }else {
                re = "输入原密码错误";
            }
        }
        request.setAttribute("re", re);
        return "student/pswChange";
    }






}
