package cn.njit.service;

/**
 * @author dustdawn
 * @date 2019/10/18 9:58
 */

import cn.njit.entry.Admin;

/**
 * AdminService
 */
public interface AdminService {

    int deleteByPrimaryKey(String no);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String sno);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}
