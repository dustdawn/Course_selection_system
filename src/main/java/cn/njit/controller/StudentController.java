package cn.njit.controller;

import cn.njit.entity.*;
import cn.njit.service.CourseService;
import cn.njit.service.CourseStudentService;
import cn.njit.service.NoticeService;
import cn.njit.service.StudentService;
import cn.njit.utils.LoginUtils;
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
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private CourseStudentService csService;

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
                session.setAttribute("currentTime", LoginUtils.getTime());
                if ("true".equals(rememberMe)) {
                    Map<String, Cookie> map = LoginUtils.saveCookie("student", student.getSno(), student.getPassword());
                    response.addCookie(map.get("no"));
                    response.addCookie(map.get("password"));
                }
                return "redirect:/student/index";
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

    /**
     * 主页
     * @param request
     * @return
     */
    @RequestMapping(value = "index")
    public String noticeList(HttpServletRequest request) {
        List<Notice> noticeList = noticeService.findList();
        request.setAttribute("noticeList", noticeList);
        return "student/index";
    }

    /**
     * 课程管理
     * @param request
     * @return
     */
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

    /**
     * 课程选修
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectPublic")
    public String selectPublic(HttpServletRequest request) {
        String cno = request.getParameter("cno");
        String name = request.getParameter("name");
        String dname = request.getParameter("dname");
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());

            Course course = new Course();
            Dept dept = new Dept();
            dept.setName(dname);

            course.setType("公选课");
            course.setCno(cno);
            course.setName(name);
            course.setDept(dept);

            //所有公选课
            List<Course> allPublicCourse = courseService.findListByEntity(course);

            course.setStudent(s);
            //已选公选课
            List<Course> selectPublicCourse = courseService.findListByEntity(course);

            //过滤已选课程
            List<Course> collect = allPublicCourse.stream().filter(
                    (Course c) -> !selectPublicCourse.contains(c)).collect(Collectors.toList());
            request.setAttribute("courseList", collect);
            request.setAttribute("cno", cno);
            request.setAttribute("name", name);
            request.setAttribute("dname", dname);

        }




        return "student/selectPublic";
    }

    @RequestMapping(value = "/selectElective")
    public String selectElective(HttpServletRequest request) {
        String cno = request.getParameter("cno");
        String name = request.getParameter("name");
        String dname = request.getParameter("dname");
        Student s = new Student();
        if (null != this.student) {
            s.setSno(student.getSno());

            Course course = new Course();
            Dept dept = new Dept();
            dept.setName(dname);

            course.setType("选修课");
            course.setCno(cno);
            course.setName(name);
            course.setDept(dept);

            //所有选修课
            List<Course> allPublicCourse = courseService.findListByEntity(course);

            course.setStudent(s);
            //已选选修课
            List<Course> selectPublicCourse = courseService.findListByEntity(course);

            //过滤已选课程
            List<Course> collect = allPublicCourse.stream().filter(
                    (Course c) -> !selectPublicCourse.contains(c)).collect(Collectors.toList());
            request.setAttribute("courseList", collect);
            request.setAttribute("cno", cno);
            request.setAttribute("name", name);
            request.setAttribute("dname", dname);

        }

        return "student/selectElective";
    }

    /**
     * 个人信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request) {
        if (null != student) {
            request.setAttribute("student", student);
        }

        return "student/info";
    }

    /**
     * 信息修改
     * @param student
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
    public String studentUpdate(Student student, HttpServletRequest request) {
        if (null != student) {
            student.setDelFlag(0);
            int flag = studentService.updateByPrimaryKeySelective(student);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                this.student = studentService.selectByPrimaryKey(student.getSno());
                request.getSession().setAttribute("userSession", student);
            }else {
                LOGGER.info(">>>修改失败<<<");
            }
        }
        return "redirect:/student/info";
    }

    /**
     * 密码修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentPswChange", method = RequestMethod.POST)
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

    /**
     * 选课
     * @param request
     * @param cno
     * @return
     */
    @RequestMapping(value = "/courseSelect")
    public String courseSelect(HttpServletRequest request, String cno) {
        String re = "";
        if (null != cno && request.getSession().getAttribute("userSession") != null && null != this.student) {
            Course course = courseService.selectByPrimaryKey(cno);
            if (course != null) {
                //获取cs对象，添加cs
                CourseStudent cs = new CourseStudent();
                cs.setCno(course.getCno());
                cs.setSno(this.student.getSno());
                //选课业务逻辑
                if (course.getTotal() > 0) {
                    synchronized (this) {
                        if (course.getTotal() > 0) {
                            int flag = csService.insert(cs);
                            course.setTotal(course.getTotal()-1);
                            courseService.updateByPrimaryKeySelective(course);
                            if (1 == flag) {
                                LOGGER.info(">>>选课成功<<<");
                                re = "success";
                            }else {
                                LOGGER.info(">>>选课失败<<<");
                            }
                        }
                    }
                }else {
                    LOGGER.info(">>>选课失败<<<");
                    re = "fail";
                }
                //-------------




                if (course.getType().equals("选修课")) {
                    return "redirect:/student/selectElective?flag=" + re;
                }
            }
        }
        return "redirect:/student/selectPublic?flag=" + re;
    }

    /**
     * 退选
     * @param request
     * @param cno
     * @return
     */
    @RequestMapping(value = "/courseWithdrawal")
    public String courseWithdrawal(HttpServletRequest request, String cno) {
        if (null != cno && request.getSession().getAttribute("userSession") != null && null != this.student) {
            Course course = courseService.selectByPrimaryKey(cno);
            if (null != course) {
                //获取cs对象，删除cs
                CourseStudent cs = new CourseStudent();
                cs.setCno(course.getCno());
                cs.setSno(this.student.getSno());

                //退选业务逻辑

                int flag = csService.delete(cs);
                course.setTotal(course.getTotal()+1);
                courseService.updateByPrimaryKeySelective(course);
                if (1 == flag) {
                    LOGGER.info(">>>选课成功<<<");

                }else {
                    LOGGER.info(">>>选课失败<<<");
                }

                //-------------



                if (course.getType().equals("选修课")) {
                    return "redirect:/student/manageElective";
                }
            }
        }
        return "redirect:/student/managePublic";
    }






}
