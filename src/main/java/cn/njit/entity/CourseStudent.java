package cn.njit.entity;

/**
 * @author dustdawn
 * @date 2019/10/28 9:25
 *
 * 多对多关联表
 */

public class CourseStudent {
    private String cno;

    private String sno;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }
}
