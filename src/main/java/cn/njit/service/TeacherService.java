package cn.njit.service;

/**
 * @author dustdawn
 * @date 2019/10/18 15:01
 */

import cn.njit.entry.Teacher;

/**
 * TeacherService
 */
public interface TeacherService {
    int deleteByPrimaryKey(String sno);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}
