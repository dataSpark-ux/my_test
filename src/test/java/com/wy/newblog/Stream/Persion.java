package com.wy.newblog.Stream;

/**
 * @author
 * @Date 2018/9/13 14:42
 * @Description TODO
 * @Version 1.0
 */
public class Persion{

    private String student;

    private String teacher;

    private Integer age;

    private Integer sex;

    public Persion() {
    }

    public Persion(String student, String teacher, Integer age, Integer sex) {
        this.student = student;
        this.teacher = teacher;
        this.age = age;
        this.sex = sex;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public static boolean isStudent(Persion persion) {
        if (persion.getStudent() != null) {

            return true;
        }
        return false;
    }
}
