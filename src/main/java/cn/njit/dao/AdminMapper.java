package cn.njit.dao;

import cn.njit.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(String no);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String no);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}