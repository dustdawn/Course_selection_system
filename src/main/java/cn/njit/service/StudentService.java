package cn.njit.service;

import cn.njit.entity.Student;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/9/30 9:57
 *
 * StudentService
 */

public interface StudentService{
    /**
     * 删
     * @param cno
     * @return
     */
    int deleteByPrimaryKey(String cno);

    /**
     * 增
     * @param record
     * @return
     */
    int insert(Student record);

    /**
     * 增
     * @param record
     * @return
     */
    int insertSelective(Student record);

    /**
     * 查
     * @param sno
     * @return
     */
    Student selectByPrimaryKey(String sno);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * 改
     * @param record
     * @return
     */
     int updateByPrimaryKey(Student record);

    /**
     * 查询所有student
     * @return
     */
    List<Student> findList();

    /**
     * 条件查询
     * @param student
     * @return
     */

    List<Student> findListByEntity(Student student);

    /**
     * 模糊查询
     * @param sno
     * @param name
     * @param dname
     * @return
     */
    List<Student> findListByParameter(String sno, String name, String dname);

}
