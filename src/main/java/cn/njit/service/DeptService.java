package cn.njit.service;

import cn.njit.entry.Dept;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/20 21:44
 */
public interface DeptService {
    int deleteByPrimaryKey(String cno);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String cno);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> findList();
}
