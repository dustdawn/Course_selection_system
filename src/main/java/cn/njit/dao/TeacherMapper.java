package cn.njit.dao;

import cn.njit.entity.Teacher;
import org.apache.ibatis.annotations.Param;

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

    //模糊查询
    List<Teacher> findListByParameter(@Param("tno")String tno, @Param("name")String name);
}