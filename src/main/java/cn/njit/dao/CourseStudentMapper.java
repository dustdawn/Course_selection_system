package cn.njit.dao;

import cn.njit.entity.Course;
import cn.njit.entity.CourseStudent;
import cn.njit.entity.Student;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/28 9:28
 */
public interface CourseStudentMapper {
    //添加
    int insert(CourseStudent cs);

    //删除
    int delete(CourseStudent cs);

    //根据学生id查询课程
    List<Course> getCourseByStudentNo(String sno);

    //根据学生id查询课程
    List<Student> getStudentByCourseNo(String cno);


}
