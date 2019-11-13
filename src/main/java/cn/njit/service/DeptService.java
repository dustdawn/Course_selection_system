package cn.njit.service;

import cn.njit.entity.Dept;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/20 21:44
 */
public interface DeptService {
    /**
     * 删除
     * @param cno
     * @return
     */
    int deleteByPrimaryKey(String cno);

    /**
     * 增
     * @param record
     * @return
     */
    int insert(Dept record);

    /**
     * 增
     * @param record
     * @return
     */
    int insertSelective(Dept record);

    /**
     * 查
     * @param cno
     * @return
     */
    Dept selectByPrimaryKey(String cno);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Dept record);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Dept record);

    /**
     * 查询所有
     * @return
     */
    List<Dept> findList();

    /**
     * 模糊查询
     * @param dno
     * @param name
     * @return
     */
    List<Dept> findListByParameter(String dno, String name);
}
