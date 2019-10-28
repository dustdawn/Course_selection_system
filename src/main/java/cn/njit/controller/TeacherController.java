package cn.njit.controller;

import cn.njit.entity.Course;
import cn.njit.entity.Notice;
import cn.njit.entity.Teacher;
import cn.njit.service.CourseService;
import cn.njit.service.NoticeService;
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
 * @date 2019/10/18 15:04
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    //创建一个日志对象，就可以通过日志输出
    private static final Logger LOGGER =
            Logger.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private NoticeService noticeService;

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
                return "redirect:/teacher/index";
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

    //主页
    @RequestMapping(value = "index")
    public String noticeList(HttpServletRequest request) {
        List<Notice> noticeList = noticeService.findList();
        request.setAttribute("noticeList", noticeList);
        return "teacher/index";
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

    //课程编辑
    @RequestMapping(value = "/courseEdit")
    public String courseEdit(HttpServletRequest request, String cno) {
        if (null != cno && !cno.equals("")) {
            Course course = courseService.selectByPrimaryKey(cno);
            if (null != course) {
                request.setAttribute("course", course);
            }
        }
        return "teacher/courseEdit";
    }

    @RequestMapping(value = "/courseUpdate")
    public String courseUpdate(Course course) {
        String re = "";
        String type = "";
        if (null != course) {
            course.setDelFlag(0);
            type = course.getType();
            int flag = courseService.updateByPrimaryKeySelective(course);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>修改失败<<<");
                re = "fail";
            }
        }
        if (type.equals("选修课")) {
            return "redirect:/teacher/manageElective?flag=" + re;
        }
        return "redirect:/teacher/managePublic?flag=" + re;
    }

    //删除课程
    @RequestMapping(value = "/courseDelete")
    public String courseDelete(String cno) {
        Course course = null;
        if (null != cno && !cno.equals("")) {
            course = courseService.selectByPrimaryKey(cno);
            int flag = courseService.deleteByPrimaryKey(cno);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info(">>>删除失败<<<");
            }
        }

        if (null != course) {
            if (course.getType().equals("选修课")) {
                return "redirect:/teacher/manageElective";
            }
        }
        return "redirect:/teacher/managePublic";
    }

    //个人信息
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request) {
        if (null != teacher) {
            request.setAttribute("teacher", teacher);
        }

        return "teacher/info";
    }

    //信息修改
    @RequestMapping(value = "/teacherUpdate")
    public String teacherUpdate(Teacher teacher) {
        if (null != teacher) {
            teacher.setDelFlag(0);
            int flag = teacherService.updateByPrimaryKeySelective(teacher);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                this.teacher = teacherService.selectByPrimaryKey(teacher.getTno());
            }else {
                LOGGER.info(">>>修改失败<<<");
            }
        }
        return "redirect:/teacher/info";
    }

    //密码修改
    @RequestMapping(value = "/teacherPswChange")
    public String teacherPswChange(HttpServletRequest request) {
        String oldPassword = request.getParameter("passwordOld");
        String newPassword = request.getParameter("passwordNew");
        String re = "";
        if (oldPassword != null && newPassword != null) {
            if (null != teacher && teacher.getPassword().equals(oldPassword)) {
                teacher.setPassword(newPassword);
                int flag = teacherService.updateByPrimaryKeySelective(teacher);
                if (1 == flag) {
                    LOGGER.info(">>>修改成功<<<");
                    this.teacher = teacherService.selectByPrimaryKey(teacher.getTno());
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
        return "teacher/pswChange";
    }

}
