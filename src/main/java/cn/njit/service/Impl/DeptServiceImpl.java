package cn.njit.service.Impl;

import cn.njit.dao.DeptMapper;
import cn.njit.entity.Dept;
import cn.njit.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/20 21:45
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int deleteByPrimaryKey(String cno) {
        return deptMapper.deleteByPrimaryKey(cno);
    }

    @Override
    public int insert(Dept record) {
        return deptMapper.insert(record);
    }

    @Override
    public int insertSelective(Dept record) {
        return deptMapper.insertSelective(record);
    }

    @Override
    public Dept selectByPrimaryKey(String cno) {
        return deptMapper.selectByPrimaryKey(cno);
    }

    @Override
    public int updateByPrimaryKeySelective(Dept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }
    @Override
    public List<Dept> findList() {
        return deptMapper.findList();
    }
}
