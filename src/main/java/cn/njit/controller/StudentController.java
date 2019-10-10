package cn.njit.controller;

import cn.njit.entry.Student;
import cn.njit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dustdawn
 * @date 2019/9/30 15:24
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/stu")
    @ResponseBody
    public String insert() {
        System.out.println("hello");
        Student student = studentService.selectByPrimaryKey("202160201");

        return student.toString();
    }
    @RequestMapping(value = "/toLogin")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index() {
        return "pace";
    }

}
