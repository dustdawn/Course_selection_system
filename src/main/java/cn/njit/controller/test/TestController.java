package cn.njit.controller.test;

import cn.njit.entity.Course;
import cn.njit.entity.Student;
import cn.njit.service.CourseService;
import cn.njit.service.NoticeService;
import cn.njit.service.StudentService;
import cn.njit.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/19 17:43
 */
@Api(value = "测试接口", description = "测试接口，功能测试")
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private NoticeService noticeService;

    @Data
    class ResponseResult {
        String success;
        int code;
        String message;
        Object object;

        public ResponseResult(String success, int code, String message, Object object) {
            this.success = success;
            this.code = code;
            this.message = message;
            this.object = object;
        }
    }

    @ApiOperation("学生添加")
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addStudent(@RequestBody Student student) {

        Student student1 = studentService.selectByPrimaryKey(student.getSno());
        if (null == student1) {
            studentService.insertSelective(student);
            return new ResponseResult("success", 100, "执行成功", student);
        }
        return new ResponseResult("fail", 200, "执行失败", null);
    }

    @ApiOperation("获取学生所有选修课程")
    @RequestMapping(value = "/getCourseListBySno/{sno}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getCourseListBySno(@PathVariable("sno") String sno) {
        Course course = new Course();
        Student student = new Student();
        student.setSno(sno);
        course.setStudent(student);
        List<Course> listByEntity = courseService.findListByEntity(course);

        return new ResponseResult("success", 100, "执行成功", listByEntity);
    }


    @ApiOperation("课程添加")
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult addCourse(@RequestBody Course course) {

        Course course1 = courseService.selectByPrimaryKey(course.getCno());
        if (null == course1) {
            courseService.insertSelective(course);
            return new ResponseResult("success", 100, "执行成功", course);
        }
        return new ResponseResult("fail", 200, "执行失败", null);
    }
    @ApiOperation("获取选修课程的所有学生")
    @RequestMapping(value = "/getStudentListByCno/{cno}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult getStudentListByCno(@PathVariable("cno") String cno) {
        Student student = new Student();
        Course course = new Course();
        course.setCno(cno);
        student.setCourse(course);

        List<Student> listByEntity = studentService.findListByEntity(student);
        return new ResponseResult("success", 100, "执行成功", listByEntity);
    }







}
