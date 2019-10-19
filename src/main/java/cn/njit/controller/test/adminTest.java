package cn.njit.controller.test;

import cn.njit.entry.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dustdawn
 * @date 2019/10/19 17:43
 */
@Controller
@RequestMapping("/test")
public class adminTest {
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
}
