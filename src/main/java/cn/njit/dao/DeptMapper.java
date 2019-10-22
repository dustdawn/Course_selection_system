package cn.njit.dao;

import cn.njit.entity.Dept;

import java.util.List;

public interface DeptMapper {
    int deleteByPrimaryKey(String dno);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(String dno);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> findList();
}