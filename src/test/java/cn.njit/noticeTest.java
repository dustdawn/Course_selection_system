package cn.njit;

import cn.njit.dao.NoticeMapper;
import cn.njit.entry.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void test1() {
        Notice notice = new Notice();
        notice.setId(UUID.randomUUID().toString());
        notice.setText("4444444444444444444444");
        notice.setDelFlag(0);
        noticeMapper.insertSelective(notice);
    }


}
