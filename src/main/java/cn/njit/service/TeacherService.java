package cn.njit.service;

/**
 * @author dustdawn
 * @date 2019/10/18 15:01
 */

import cn.njit.entity.Teacher;

import java.util.List;

/**
 * TeacherService
 */
public interface TeacherService {
    int deleteByPrimaryKey(String tno);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    List<Teacher> findList();

    //模糊查询
    List<Teacher> findListByParameter(String tno, String name);
}
