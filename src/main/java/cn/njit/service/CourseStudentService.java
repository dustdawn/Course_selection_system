package cn.njit.service;

import cn.njit.entity.Course;
import cn.njit.entity.CourseStudent;
import cn.njit.entity.Student;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/28 15:48
 */
public interface CourseStudentService {
    /**
     * 添加
     * @param cs
     * @return
     */
    int insert(CourseStudent cs);

    /**
     * 删除
     * @param cs
     * @return
     */
    int delete(CourseStudent cs);

    /**
     * 根据学生id查询课程
     * @param sno
     * @return
     */
    List<Course> getCourseByStudentNo(String sno);

    /**
     * 根据学生id查询课程
     * @param cno
     * @return
     */
    List<Student> getStudentByCourseNo(String cno);
}
