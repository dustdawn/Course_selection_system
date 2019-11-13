package cn.njit.service;

import cn.njit.entity.Course;

import java.util.List;


/**
 * @author dustdawn
 * @date 2019/10/28 15:48
 *
 * CourseService
 */
public interface CourseService {
    /**
     * 删
     * @param sno
     * @return
     */
    int deleteByPrimaryKey(String sno);

    /**
     *  增
     * @param record
     * @return
     */
    int insert(Course record);

    /**
     * 增
     * @param record
     * @return
     */
    int insertSelective(Course record);

    /**
     * 查
     * @param cno
     * @return
     */
    Course selectByPrimaryKey(String cno);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);

    /**
     * 查询所有Course
     * @return
     */
    List<Course> findList();

    /**
     * 条件查询Course
     * @param course
     * @return
     */
    List<Course> findListByEntity(Course course);

    /**
     * 模糊查询
     * @param cno
     * @param name
     * @param dname 学院名
     * @return
     */
    List<Course> findListByParameter(String cno, String name, String dname);
}
