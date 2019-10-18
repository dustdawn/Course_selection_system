package cn.njit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author dustdawn
 * @date 2019/10/8 13:51
 */
@RequestMapping("/pages")
@Controller
public class PageController {

    @RequestMapping("{pageName}")
    public ModelAndView toPage(@PathVariable("pageName") String pageName){
        ModelAndView mv = new ModelAndView(pageName);
        return mv ;
    }

    @RequestMapping("table/{pageName}")
    public ModelAndView tablePage(@PathVariable("pageName") String pageName){
        ModelAndView mv = new ModelAndView("table/" + pageName);
        return mv ;
    }

    @RequestMapping("student/{pageName}")
    public ModelAndView studentPage(@PathVariable("pageName") String pageName){
        ModelAndView mv = new ModelAndView("student/" + pageName);
        return mv ;
    }

    @RequestMapping("teacher/{pageName}")
    public ModelAndView toTable(@PathVariable("pageName") String pageName){
        ModelAndView mv = new ModelAndView("teacher/" + pageName);
        return mv ;
    }


    @RequestMapping("admin/{pageName}")
    public ModelAndView toAdmin(@PathVariable("pageName") String pageName){
        ModelAndView mv = new ModelAndView("admin/" + pageName);
        return mv ;
    }


}
