package cn.njit.service;

import cn.njit.entity.Notice;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/27 13:21
 */
public interface NoticeService {
    /**
     * 删
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 增
     * @param record
     * @return
     */
    int insert(Notice record);

    /**
     * 增
     * @param record
     * @return
     */
    int insertSelective(Notice record);

    /**
     * 查
     * @param id
     * @return
     */
    Notice selectByPrimaryKey(String id);

    /**
     * 改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Notice record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Notice record);

    /**
     * 查询所有
     * @return
     */
    List<Notice> findList();
}
