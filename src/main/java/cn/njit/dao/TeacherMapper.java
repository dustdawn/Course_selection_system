package cn.njit.dao;

import cn.njit.entity.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String tno);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String tno);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    //查询所有teacher
    List<Teacher> findList();
}