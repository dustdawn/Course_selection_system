package cn.njit.controller;

import cn.njit.entity.Dept;
import cn.njit.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/20 21:43
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/getNameList",method = RequestMethod.POST)
    @ResponseBody
    public List<Dept> getNameList() {
        return deptService.findList();
    }
}
