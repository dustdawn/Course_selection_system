package cn.njit.controller.test;

import cn.njit.entity.Course;
import cn.njit.entity.Notice;
import cn.njit.entity.Student;
import cn.njit.entity.Teacher;
import cn.njit.service.CourseService;
import cn.njit.service.NoticeService;
import cn.njit.service.StudentService;
import cn.njit.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author dustdawn
 * @date 2019/10/19 17:43
 */
@Controller
@RequestMapping("/test")
public class adminTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private NoticeService noticeService;
    @RequestMapping(value = "/method", method = RequestMethod.POST)
    public ModelAndView method(Teacher teacher) {
        System.out.println(teacher);
        ModelAndView view = new ModelAndView();
        return view;
    }

    @RequestMapping(value = "yy", method = RequestMethod.POST)
    public String yy(Teacher teacher) {
        System.out.println();
        return "success";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public String list() {
        Student student = studentService.selectByPrimaryKey("202160201");
        List<Student> students = studentService.findList();
        System.out.println();
        Teacher teacher = teacherService.selectByPrimaryKey("101160201");
        List<Teacher> teacherList = teacherService.findList();
        System.out.println();
        Course course = courseService.selectByPrimaryKey("001");
        List<Course> courseList = courseService.findList();
        System.out.println();
        return "success";
    }

    @RequestMapping(value = "studentList")
    @ResponseBody
    public String studentList() {
        Course course = new Course();
        course.setCno("002");

        Student student = new Student();
        student.setCourse(course);

        List<Student> listByEntity = studentService.findListByEntity(student);
        System.out.println();
        return "success";
    }

    //点击主页，把连接改成<%=basePath%>/test/noticeList,表示点击主页时走这个方法
    @RequestMapping(value = "noticeList")
    public String noticeList(HttpServletRequest request) {
        //然后把查询到的noticeList放到request域里，调用request.setAttribute,处理完转发到admin下的index页面
        List<Notice> noticeList = noticeService.findList();
        request.setAttribute("noticeList", noticeList);
        return "admin/index";
    }

    //添加通告时调用的方法，notice接收，去看实体类的属性，记得设置delFlag
    @RequestMapping(value = "noticeForm")
    public String noticeForm(HttpServletRequest request, Notice notice) {
        //保存后，重定向到noticeList方法达到显示所有通告效果
        String re="";
        if (notice!=null){
            notice.setId(UUID.randomUUID().toString());
            notice.setDelFlag(0);
            int flag = noticeService.insertSelective(notice);
            if (1 == flag) {
                re = "success";
            }else {
                re = "fail";
            }
        }
        return "redirect:/test/noticeList";
    }

    public static void main(String[] args) {

    }



}
