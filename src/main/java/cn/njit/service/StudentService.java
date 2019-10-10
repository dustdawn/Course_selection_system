package cn.njit.service;

/**
 * @author dustdawn
 * @date 2019/9/30 9:57
 */

import cn.njit.entry.Student;

/**
 * StudentService
 */
public interface StudentService{

    int deleteByPrimaryKey(String sno);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

}
