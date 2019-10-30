package cn.njit.service.Impl;

import cn.njit.dao.CourseMapper;
import cn.njit.entity.Course;
import cn.njit.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/20 14:48
 */
@Transactional
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public int deleteByPrimaryKey(String cno) {
        return courseMapper.deleteByPrimaryKey(cno);
    }

    @Override
    public int insert(Course record) {
        return courseMapper.insert(record);
    }

    @Override
    public int insertSelective(Course record) {
        return courseMapper.insertSelective(record);
    }

    @Override
    public Course selectByPrimaryKey(String cno) {
        return courseMapper.selectByPrimaryKey(cno);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return courseMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Course> findList() {
        return courseMapper.findList();
    }

    @Override
    public List<Course> findListByEntity(Course course) {
        return courseMapper.findListByEntity(course);
    }

    @Override
    public List<Course> findListByParameter(String cno, String name, String dname) {
        return courseMapper.findListByParameter(cno, name, dname);
    }
}
