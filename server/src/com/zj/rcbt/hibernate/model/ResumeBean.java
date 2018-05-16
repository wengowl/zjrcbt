package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resume", schema = "rcbt", catalog = "")
public class ResumeBean {
    private int resumeId;
    private String timeScape;
    private String company;
    private String department;
    private String post;
    private String idNum;

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Id
    @Column(name = "resume_id", nullable = false)
    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    @Basic
    @Column(name = "time_scape", nullable = true, length = 255)
    public String getTimeScape() {
        return timeScape;
    }

    public void setTimeScape(String timeScape) {
        this.timeScape = timeScape;
    }

    @Basic
    @Column(name = "company", nullable = true, length = 255)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "department", nullable = true, length = 255)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "post", nullable = true, length = 32)
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResumeBean that = (ResumeBean) o;
        return resumeId == that.resumeId &&
                Objects.equals(timeScape, that.timeScape) &&
                Objects.equals(company, that.company) &&
                Objects.equals(department, that.department) &&
                Objects.equals(post, that.post);
    }

    @Override
    public int hashCode() {

        return Objects.hash(resumeId, timeScape, company, department, post);
    }
}
