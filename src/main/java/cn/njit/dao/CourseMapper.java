package cn.njit.dao;

import cn.njit.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String cno);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String cno);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    //查询所有Course
    List<Course> findList();
    //条件查询Course
    List<Course> findListByEntity(Course course);

    //模糊查询
    List<Course> findListByParameter(@Param("courseNo")String cno, @Param("courseName")String name, @Param("deptName")String dname);

}