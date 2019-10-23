package cn.njit.controller;

/**
 * @author dustdawn
 * @date 2019/10/23 10:54
 */

import cn.njit.entity.Course;
import cn.njit.entity.Student;
import cn.njit.service.CourseService;
import cn.njit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 查询选课学生列表
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    //获取课程的学生名单
    @RequestMapping(value = "studentList")
    @ResponseBody
    public List<Student> studentList(String cno, HttpServletRequest request) {

        Course course = new Course();
        course.setCno(cno);

        Student student = new Student();
        student.setCourse(course);

        //根据课程号获取选修该课程的学生集合
        List<Student> listByEntity = studentService.findListByEntity(student);


        return listByEntity;
    }

    //获取学生的课程名单
    @RequestMapping(value = "courseList")
    public String courseList(String sno, HttpServletRequest request) {
        Student student = new Student();
        student.setSno(sno);

        Course course = new Course();
        course.setStudent(student);

        List<Course> listByEntity = courseService.findListByEntity(course);

        return "";
    }

}
