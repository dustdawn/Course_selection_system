package cn.njit.controller;

import cn.njit.entity.Course;
import cn.njit.service.CourseService;
import cn.njit.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author dustdawn
 * @date 2019/11/14 10:53
 */
@Controller
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    private CourseService courseService;

    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Course> list = courseService.findList();

        //excel标题
        String[] title = {"课程类型", "课程号", "课程名", "授课教师", "所属学院", "课程周期", "授课地点", "学分", "剩余名额"};

        //excel文件名
        String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "学生信息表";

        String[][] content = new String[list.size()][list.size()];
        Course course = null;
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            course = list.get(i);
            content[i][0] = course.getType();
            content[i][1] = course.getCno();
            content[i][2] = course.getName();
            if (null != course.getTeacher()) {
                content[i][3] = course.getTeacher().getName();
            }
            if (null != course.getDept()) {
                content[i][4] = course.getDept().getName();
            }
            content[i][5] = course.getDate();
            content[i][6] = course.getPlace();
            content[i][7] = course.getCredit();
            content[i][8] = String.valueOf(course.getTotal());
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
