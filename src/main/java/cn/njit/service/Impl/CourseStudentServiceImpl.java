package cn.njit.service.Impl;

import cn.njit.dao.CourseStudentMapper;
import cn.njit.entity.Course;
import cn.njit.entity.CourseStudent;
import cn.njit.entity.Student;
import cn.njit.service.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dustdawn
 * @date 2019/10/28 15:49
 */
@Transactional
@Service
public class CourseStudentServiceImpl implements CourseStudentService {

    @Autowired
    private CourseStudentMapper csMapper;

    @Override
    public int insert(CourseStudent cs) {
        return csMapper.insert(cs);
    }

    @Override
    public int delete(CourseStudent cs) {
        return csMapper.delete(cs);
    }

    @Override
    public List<Course> getCourseByStudentNo(String sno) {
        return csMapper.getCourseByStudentNo(sno);
    }

    @Override
    public List<Student> getStudentByCourseNo(String cno) {
        return csMapper.getStudentByCourseNo(cno);
    }
}
