package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "topschool", schema = "rcbt", catalog = "")
public class TopschoolBean {
    private int id;
    private String schoolname;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "schoolname")
    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopschoolBean that = (TopschoolBean) o;
        return id == that.id &&
                Objects.equals(schoolname, that.schoolname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, schoolname);
    }
}
