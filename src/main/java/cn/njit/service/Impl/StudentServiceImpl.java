package cn.njit.service.Impl;

import cn.njit.dao.StudentMapper;
import cn.njit.entity.Student;
import cn.njit.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/9/30 13:27
 */
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(String sno) {
        return studentMapper.deleteByPrimaryKey(sno);
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(String sno) {
        return studentMapper.selectByPrimaryKey(sno);
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Student> findList() {
        return studentMapper.findList();
    }

    @Override
    public List<Student> findListByEntity(Student student) {
        return studentMapper.findListByEntity(student);
    }

    @Override
    public List<Student> findListByParameter(String sno, String name, String dname) {
        return studentMapper.findListByParameter(sno, name, dname);
    }
}
