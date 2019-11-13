package cn.njit.controller;

import cn.njit.entity.*;
import cn.njit.service.*;
import cn.njit.utils.LoginUtils;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author dustdawn
 * @date 2019/10/16 14:30
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    /**
     * 创建一个日志对象，就可以通过日志输出
     */
    private static final Logger LOGGER = Logger.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private NoticeService noticeService;

    private Admin admin;

    /**
     * 管理员登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String no = request.getParameter("admin");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        HttpSession session = request.getSession();
        //数据库查询用户
        admin = adminService.selectByPrimaryKey(no);
        if (null != admin){
            if (admin.getPassword().equals(password)) {
                session.setAttribute("userSession", admin);
                session.setAttribute("currentTime", LoginUtils.getTime());
                if ("true".equals(rememberMe)) {
                    Map<String, Cookie> map = LoginUtils.saveCookie("admin", admin.getNo(), admin.getPassword());
                    response.addCookie(map.get("no"));
                    response.addCookie(map.get("password"));
                }
                return "redirect:/admin/index";
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

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "admin/login";
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
        return "admin/index";
    }

    /**
     * 通告提交
     * @param request
     * @param notice
     * @return
     */
    @RequestMapping(value = "noticeForm", method = RequestMethod.POST)
    public String noticeForm(HttpServletRequest request, Notice notice) {
        //保存后，重定向到noticeList方法达到显示所有通告效果
        String re="";
        if (notice != null){
            //notice.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            notice.setId(Calendar.getInstance().getTimeInMillis() + "");
            notice.setDelFlag(0);
            notice.setContent(notice.getContent().replaceAll("\\r\\n", "<br/>"));
            int flag = noticeService.insertSelective(notice);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
                re = "success";
            }else {
                LOGGER.info( ">>>添加失败<<<" );
                re = "fail";
            }
        }
        return "redirect:/admin/index?flag=" + re;
    }

    /**
     * 添加通告时
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "noticeDelete")
    public String noticeDelete(HttpServletRequest request, String id) {
        //保存后，重定向到noticeList方法达到显示所有通告效果
        if (null != id && !"".equals(id)){
            int flag = noticeService.deleteByPrimaryKey(id);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info( ">>>删除失败<<<" );
            }
        }
        return "redirect:/admin/index";
    }

    /**
     * 教师管理
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/teacherForm", method = RequestMethod.POST)
    public String teacherForm(Teacher teacher) {
        String re = "";
        if (null != teacher) {
            teacher.setDelFlag(0);
            int flag = teacherService.insertSelective(teacher);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>添加失败<<<");
                re = "fail";
            }
        }
        return "redirect:/pages/admin/teacherForm?flag=" + re;
    }

    /**
     * 教师列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacherList")
    public String teacherList(HttpServletRequest request) {
        String tno = request.getParameter("tno");
        String name = request.getParameter("name");
        List<Teacher> teacherList = teacherService.findListByParameter(tno, name);
        request.setAttribute("teacherList", teacherList);
        request.setAttribute("tno", tno);
        request.setAttribute("name", name);
        return "admin/teacherList";
    }

    /**
     * 教师编辑回显
     * @param request
     * @param tno
     * @return
     */
    @RequestMapping(value = "/teacherEdit")
    public String teacherEdit(HttpServletRequest request, String tno) {

        if (null != tno && !tno.equals("")) {
            Teacher teacher = teacherService.selectByPrimaryKey(tno);
            if (null != teacher) {
                request.setAttribute("teacher", teacher);
            }

        }
        return "admin/teacherEdit";
    }

    /**
     * 教师编辑修改
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/teacherUpdate", method = RequestMethod.POST)
    public String teacherUpdate(Teacher teacher) {
        String re = "";
        if (null != teacher) {
            teacher.setDelFlag(0);
            int flag = teacherService.updateByPrimaryKeySelective(teacher);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>修改失败<<<");
                re = "fail";
            }
        }
        return "redirect:/admin/teacherList?flag=" + re;
    }

    @RequestMapping(value = "/teacherDelete")
    public String teacherDelete(String tno) {

        if (null != tno && !tno.equals("")) {
            int flag = teacherService.deleteByPrimaryKey(tno);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info(">>>删除失败<<<");
            }
        }
        return "redirect:/admin/teacherList";
    }

    /**
     * 学生添加
     * @param student
     * @return
     */
    @RequestMapping(value = "/studentForm", method = RequestMethod.POST)
    public String studentForm(Student student) {
        String re = "";
        if (null != student) {
            student.setDelFlag(0);
            int flag = studentService.insertSelective(student);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>添加失败<<<");
                re = "fail";
            }
        }
        return "redirect:/pages/admin/studentForm?flag=" + re;
    }

    @RequestMapping(value = "/studentList")
    public String studentList(HttpServletRequest request) {
        String sno = request.getParameter("sno");
        String name = request.getParameter("name");
        String dname = request.getParameter("dname");

        List<Student> studentList = studentService.findListByParameter(sno, name, dname);
        request.setAttribute("studentList", studentList);
        request.setAttribute("sno", sno);
        request.setAttribute("name", name);
        request.setAttribute("dname", dname);
        return "admin/studentList";
    }

    @RequestMapping(value = "/studentEdit")
    public String studentEdit(HttpServletRequest request, String sno) {
        if (null != sno && !sno.equals("")) {
            Student student = studentService.selectByPrimaryKey(sno);
            if (null != student) {
                request.setAttribute("student", student);
            }
        }
        return "admin/studentEdit";
    }

    @RequestMapping(value = "/studentUpdate", method = RequestMethod.POST)
    public String studentUpdate(Student student) {
        String re = "";
        if (null != student) {
            student.setDelFlag(0);
            int flag = studentService.updateByPrimaryKeySelective(student);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>修改失败<<<");
                re = "fail";
            }
        }
        return "redirect:/admin/studentList?flag=" + re;
    }

    @RequestMapping(value = "/studentDelete")
    public String studentDelete(String sno) {

        if (null != sno && !sno.equals("")) {
            int flag = studentService.deleteByPrimaryKey(sno);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info(">>>删除失败<<<");
            }
        }
        return "redirect:/admin/studentList";
    }

    /**
     * 课程管理
     * @param course
     * @return
     */
    @RequestMapping(value = "/courseForm", method = RequestMethod.POST)
    public String courseForm(Course course) {
        String re = "";
        if (null != course) {
            course.setDelFlag(0);
            int flag = courseService.insertSelective(course);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>添加失败<<<");
                re = "fail";
            }
        }
        return "redirect:/pages/admin/courseForm?flag=" + re;
    }

    @RequestMapping(value = "/courseList")
    public String courseList(HttpServletRequest request) {
        String cno = request.getParameter("cno");
        String name = request.getParameter("name");
        String dname = request.getParameter("dname");

        List<Course> courseList = courseService.findListByParameter(cno, name, dname);
        request.setAttribute("courseList", courseList);
        request.setAttribute("cno", cno);
        request.setAttribute("name", name);
        request.setAttribute("dname", dname);
        return "admin/courseList";
    }

    @RequestMapping(value = "/courseEdit")
    public String courseEdit(HttpServletRequest request, String cno) {
        if (null != cno && !cno.equals("")) {
            Course course = courseService.selectByPrimaryKey(cno);
            if (null != course) {
                request.setAttribute("course", course);
            }
        }
        return "admin/courseEdit";
    }

    @RequestMapping(value = "/courseUpdate", method = RequestMethod.POST)
    public String courseUpdate(Course course) {
        String re = "";
        if (null != course) {
            course.setDelFlag(0);
            int flag = courseService.updateByPrimaryKeySelective(course);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>修改失败<<<");
                re = "fail";
            }
        }
        return "redirect:/admin/courseList?flag=" + re;
    }

    @RequestMapping(value = "/courseDelete")
    public String courseDelete(String cno) {
        if (null != cno && !cno.equals("")) {
            int flag = courseService.deleteByPrimaryKey(cno);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info(">>>删除失败<<<");
            }
        }
        return "redirect:/admin/courseList";
    }

    /**
     * 院系管理
     * @param dept
     * @return
     */
    @RequestMapping(value = "/deptForm", method = RequestMethod.POST)
    public String deptForm(Dept dept) {
        String re = "";
        if (null != dept) {
            dept.setDelFlag(0);
            int flag = deptService.insertSelective(dept);
            if (1 == flag) {
                LOGGER.info(">>>添加成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>添加失败<<<");
                re = "fail";
            }
        }
        return "redirect:/pages/admin/deptForm?flag="+re;
    }

    @RequestMapping(value = "/deptList")
    public String deptList(HttpServletRequest request) {
        String dno = request.getParameter("dno");
        String name = request.getParameter("name");
        List<Dept> deptList = deptService.findListByParameter(dno, name);
        request.setAttribute("deptList", deptList);
        request.setAttribute("dno", dno);
        request.setAttribute("name", name);
        return "admin/deptList";
    }

    @RequestMapping(value = "/deptEdit")
    public String deptEdit(HttpServletRequest request, String dno) {
        if (null != dno && !dno.equals("")) {
            Dept dept = deptService.selectByPrimaryKey(dno);
            if (null != dept) {
                request.setAttribute("dept", dept);
            }
        }
        return "admin/deptEdit";
    }

    @RequestMapping(value = "/deptUpdate", method = RequestMethod.POST)
    public String deptUpdate(Dept dept) {
        String re = "";
        if (null != dept) {
            dept.setDelFlag(0);
            int flag = deptService.updateByPrimaryKeySelective(dept);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                re = "success";
            }else {
                LOGGER.info(">>>修改失败<<<");
                re = "fail";
            }
        }
        return "redirect:/admin/deptList?flag="+re;
    }

    @RequestMapping(value = "/deptDelete")
    public String deptDelete(String dno) {
        if (null != dno && !dno.equals("")) {
            int flag = deptService.deleteByPrimaryKey(dno);
            if (1 == flag) {
                LOGGER.info(">>>删除成功<<<");
            }else {
                LOGGER.info(">>>删除失败<<<");
            }
        }
        return "redirect:/admin/deptList";
    }

    /**
     * 个人信息管理
     * @param request
     * @return
     */
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request) {
        if (null != admin) {
            request.setAttribute("admin", admin);
        }

        return "admin/info";
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/pswChange", method = RequestMethod.POST)
    public String pswChange(HttpServletRequest request) {
        String oldPassword = request.getParameter("passwordOld");
        String newPassword = request.getParameter("passwordNew");
        String re = "";
        if (oldPassword != null && newPassword != null) {
            if (null != admin && admin.getPassword().equals(oldPassword)) {
                admin.setPassword(newPassword);
                int flag = adminService.updateByPrimaryKeySelective(admin);
                if (1 == flag) {
                    LOGGER.info(">>>修改成功<<<");
                    this.admin = adminService.selectByPrimaryKey(admin.getNo());
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
        return "admin/pswChange";
    }

    /**
     * 信息修改
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping(value = "/adminUpdate", method = RequestMethod.POST)
    public String teacherUpdate(Admin admin, HttpServletRequest request) {
        if (null != admin) {
            int flag = adminService.updateByPrimaryKeySelective(admin);
            if (1 == flag) {
                LOGGER.info(">>>修改成功<<<");
                this.admin = adminService.selectByPrimaryKey(admin.getNo());
                request.getSession().setAttribute("userSession", admin);
            }else {
                LOGGER.info(">>>修改失败<<<");
            }
        }
        return "redirect:/admin/info";
    }

    /**
     * Ajax校验部分
     */
    @RequestMapping(value = "checkTeacher")
    @ResponseBody
    public String checkTeacher(String tno) {

        String result = "{\"valid\":false}";
        if (null != tno) {
            Teacher teacher = teacherService.selectByPrimaryKey(tno);
            if (null == teacher && !tno.equals("")) {
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
        if (null != sno && !sno.equals("")) {
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
        if (null != cno && !cno.equals("")) {
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

    @RequestMapping(value = "checkDept")
    @ResponseBody
    public String checkDept(String dno) {

        String result = "{\"valid\":false}";
        if (null != dno && !dno.equals("")) {
            Dept dept = deptService.selectByPrimaryKey(dno);
            if (null == dept) {
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
