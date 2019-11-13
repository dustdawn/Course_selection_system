package cn.njit.controller;



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
 * @author dustdawn
 * @date 2019/10/23 10:54
 */

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    /**
     * 获取课程的学生名单
     * @param cno
     * @param request
     * @return
     */
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

    /**
     * 获取学生的课程名单
     * @param sno
     * @param request
     * @return
     */
    @RequestMapping(value = "courseList")
    @ResponseBody
    public List<Course> courseList(String sno, HttpServletRequest request) {
        Student student = new Student();
        student.setSno(sno);

        Course course = new Course();
        course.setStudent(student);

        List<Course> listByEntity = courseService.findListByEntity(course);

        return listByEntity;
    }

    /**
     * 获取教师的授课名单
     * @param tno
     * @param request
     * @return
     */
    @RequestMapping(value = "/teacherCourseList")
    @ResponseBody
    public List<Course> teacherCourseList(String tno, HttpServletRequest request) {
        Course course =  new Course();
        course.setTno(tno);
        List<Course> listByEntity = courseService.findListByEntity(course);
        return listByEntity;
    }

}
