package cn.njit.controller;

import cn.njit.entity.Dept;
import cn.njit.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "院系管理接口", description = "院系管理接口，获取院系信息")
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("获取院系名称集合")
    @RequestMapping(value = "/getNameList",method = RequestMethod.POST)
    @ResponseBody
    public List<Dept> getNameList() {
        return deptService.findList();
    }
}
