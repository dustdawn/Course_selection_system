package cn.njit;

import cn.njit.dao.CourseStudentMapper;
import cn.njit.dao.NoticeMapper;
import cn.njit.service.TeacherService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dustdawn
 * @date 2019/10/20 15:31
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/*.xml"})
public class noticeTest {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseStudentMapper csMapper;

    /*@Test
    public void test1() {
        Notice notice = new Notice();
        notice.setId(UUID.randomUUID().toString());
        notice.setText("4444444444444444444444");
        notice.setDelFlag(0);
        noticeMapper.insertSelective(notice);
    }

    @Test
    public void test2() {
        Teacher teacher = new Teacher();
        teacher.setTno("101160201");
        teacher.setBirthday(new Date());
        teacherService.updateByPrimaryKeySelective(teacher);
    }

    @Test
    public void test3() {
        Teacher teacher = new Teacher();
        teacher.setTno("101160201");
        teacherService.selectByPrimaryKey("101160201").getBirthday();
    }*/

    /*@Test
    public void test4() {
        List<Course> courseByStudentNo = csMapper.getCourseByStudentNo("202160201");
        courseByStudentNo.forEach((Course c)-> System.out.println(c.toString()));

    }*/

    /*@Test
    public void test5() {
        CourseStudent cs = new CourseStudent();
        cs.setSno("202160204");
        cs.setCno("004");
        csMapper.insert(cs);

        List<Course> courseByStudentNo = csMapper.getCourseByStudentNo(cs.getSno());
        courseByStudentNo.forEach((Course c)-> System.out.println(c.toString()));

        csMapper.delete(cs);

    }*/


}
