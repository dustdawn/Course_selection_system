package cn.njit.service;

import cn.njit.entity.Teacher;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/18 15:01
 *
 * TeacherService
 */

public interface TeacherService {
    /**
     * 删
     * @param tno
     * @return
     */
    int deleteByPrimaryKey(String tno);

    /**
     * 增
     * @param record
     * @return
     */
    int insert(Teacher record);

    /**
     * 增
     * @param record
     * @return
     */
    int insertSelective(Teacher record);

    /**
     * 查询
     * @param sno
     * @return
     */
    Teacher selectByPrimaryKey(String sno);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Teacher record);

    /**
     * 查询所有教师
     * @return
     */
    List<Teacher> findList();

    /**
     * 模糊查询
     * @param tno
     * @param name
     * @return
     */

    List<Teacher> findListByParameter(String tno, String name);
}
