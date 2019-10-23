package cn.njit.service;

import cn.njit.entity.Course;

import java.util.List;

/**
 * courseService
 */
public interface CourseService {
    int deleteByPrimaryKey(String sno);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String cno);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    //查询所有Course
    List<Course> findList();
    //条件查询Course
    List<Course> findListByEntity(Course course);
}
