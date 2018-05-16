package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topmajor", schema = "rcbt", catalog = "")
public class TopmajorBean {
    private int id;
    private String school;
    private String major;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopmajorBean that = (TopmajorBean) o;
        return id == that.id &&
                Objects.equals(school, that.school) &&
                Objects.equals(major, that.major);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, school, major);
    }
}
