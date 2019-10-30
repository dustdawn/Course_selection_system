package cn.njit.dao;

import cn.njit.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String sno);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<Student> findList();

    //条件查询
    List<Student> findListByEntity(Student student);

    //模糊查询
    List<Student> findListByParameter(@Param("studentNo")String sno, @Param("studentName")String name, @Param("deptName")String dname);
}