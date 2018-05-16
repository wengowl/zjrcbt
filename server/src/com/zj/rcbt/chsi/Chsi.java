package com.zj.rcbt.chsi;

public class Chsi {
    private String name;//姓名
    private String id;//身份证号
    private String education;//学历
    private String graduatetime;//毕业日期
    private String sex;//性别
    private String mingzu;//民族
    private String birthdate;//出生日期
    private String major;//专业
    private String xingshi;//形式，是否全日制
    private String school;//毕业学校
    private String error;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduatetime() {
        return graduatetime;
    }

    public void setGraduatetime(String graduatetime) {
        this.graduatetime = graduatetime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMingzu() {
        return mingzu;
    }

    public void setMingzu(String mingzu) {
        this.mingzu = mingzu;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getXingshi() {
        return xingshi;
    }

    public void setXingshi(String xingshi) {
        this.xingshi = xingshi;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString(){
        return "身份证号："+id+" 学历："+education+" 毕业时间："+graduatetime+"  "+sex+"  "+mingzu+"  "+birthdate+"  "+major+"  "+xingshi+"  "+school;
    }
}
