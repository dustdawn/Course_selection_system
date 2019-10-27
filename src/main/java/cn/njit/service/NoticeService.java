package cn.njit.service;

import cn.njit.entity.Notice;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/27 13:21
 */
public interface NoticeService {
    int deleteByPrimaryKey(String id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> findList();
}
