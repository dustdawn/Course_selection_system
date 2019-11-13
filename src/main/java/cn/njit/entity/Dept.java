package cn.njit.entity;

/**
 * @author dustdawn
 * @date 2019/10/9 9:31
 */
public class Dept {
    private String dno;

    private String name;

    private Integer delFlag;

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno == null ? null : dno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}