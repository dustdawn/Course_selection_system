package cn.njit.service.Impl;

import cn.njit.dao.TeacherMapper;
import cn.njit.entity.Teacher;
import cn.njit.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/18 15:03
 */
@Transactional
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public int deleteByPrimaryKey(String tno) {
        return teacherMapper.deleteByPrimaryKey(tno);
    }

    @Override
    public int insert(Teacher record) {
        return teacherMapper.insert(record);
    }

    @Override
    public int insertSelective(Teacher record) {
        return teacherMapper.insertSelective(record);
    }

    @Override
    public Teacher selectByPrimaryKey(String sno) {
        return teacherMapper.selectByPrimaryKey(sno);
    }

    @Override
    public int updateByPrimaryKeySelective(Teacher record) {
        return teacherMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Teacher record) {
        return teacherMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Teacher> findList() {
        return teacherMapper.findList();
    }

    @Override
    public List<Teacher> findListByParameter(String tno, String name) {
        return teacherMapper.findListByParameter(tno, name);
    }
}
