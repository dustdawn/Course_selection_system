package cn.njit.controller.test;

import cn.njit.entity.Course;
import cn.njit.entity.Student;
import cn.njit.entity.Teacher;
import cn.njit.service.CourseService;
import cn.njit.service.StudentService;
import cn.njit.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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



}
