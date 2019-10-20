package cn.njit;

import cn.njit.dao.NoticeMapper;
import cn.njit.entry.Notice;
import cn.njit.entry.Teacher;
import cn.njit.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

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

    @Test
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


}
