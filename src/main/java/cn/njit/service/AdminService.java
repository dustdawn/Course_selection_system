package cn.njit.service;
import cn.njit.entity.Admin;

/**
 * @author dustdawn
 * @date 2019/10/18 9:58
 *
 * AdminService
 */
public interface AdminService {
    /**
     * 根据工号删除
     * @param no
     * @return
     */
    int deleteByPrimaryKey(String no);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(Admin record);

    /**
     * 全值插入
     * @param record
     * @return
     */
    int insertSelective(Admin record);

    /**
     * 根据工号查询
     * @param sno
     * @return
     */
    Admin selectByPrimaryKey(String sno);

    /**
     * 根据实体修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * 根据主键修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Admin record);
}
