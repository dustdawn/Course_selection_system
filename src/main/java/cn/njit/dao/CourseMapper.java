package cn.njit.dao;

import cn.njit.entity.Course;

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

}