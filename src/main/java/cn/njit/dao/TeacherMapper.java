package cn.njit.dao;

import cn.njit.entry.Teacher;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String tno);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String tno);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> findTeacherList();
}