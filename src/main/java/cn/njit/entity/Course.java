package cn.njit.entity;

/**
 * @author dustdawn
 * @date 2019/10/9 9:30
 */

import java.util.List;
import java.util.Objects;

public class Course {
    private String cno;

    private String name;

    private String tno;

    private String dno;

    private String date;

    private String place;

    private String credit;

    private Integer total;

    private String type;

    private Integer delFlag;

    //教师对象
    private Teacher teacher;
    //学院对象
    private Dept dept;
    //学生对象
    private Student student;
    //学生集合
    private List<Student> studentList;

    @Override
    public String toString() {
        return "Course{" +
                "cno='" + cno + '\'' +
                ", name='" + name + '\'' +
                ", tno='" + tno + '\'' +
                ", dno='" + dno + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                ", credit='" + credit + '\'' +
                ", total=" + total +
                ", type='" + type + '\'' +
                ", delFlag=" + delFlag +
                ", teacher=" + teacher +
                ", dept=" + dept +
                ", student=" + student +
                ", studentList=" + studentList +
                '}';
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno == null ? null : dno.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit == null ? null : credit.trim();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    //重写equals方法

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(cno, course.cno) &&
                Objects.equals(name, course.name) &&
                Objects.equals(tno, course.tno) &&
                Objects.equals(dno, course.dno) &&
                Objects.equals(date, course.date) &&
                Objects.equals(place, course.place) &&
                Objects.equals(credit, course.credit) &&
                Objects.equals(total, course.total) &&
                Objects.equals(type, course.type) &&
                Objects.equals(delFlag, course.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, name, tno, dno, date, place, credit, total, type, delFlag);
    }
}