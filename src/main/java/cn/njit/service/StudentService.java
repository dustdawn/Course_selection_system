package cn.njit.service;

/**
 * @author dustdawn
 * @date 2019/9/30 9:57
 */

import cn.njit.entity.Student;

import java.util.List;

/**
 * StudentService
 */
public interface StudentService{

    int deleteByPrimaryKey(String cno);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //查询所有student
    List<Student> findList();

}
