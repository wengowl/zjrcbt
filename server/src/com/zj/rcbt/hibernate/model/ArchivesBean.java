package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "archives", schema = "rcbt", catalog = "")
public class ArchivesBean {
    private int id;
    private String userName;
    private String idNum;
    private String status;
    private String inzhuji;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "id_num")
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "inzhuji")
    public String getInzhuji() {
        return inzhuji;
    }

    public void setInzhuji(String inzhuji) {
        this.inzhuji = inzhuji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchivesBean that = (ArchivesBean) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(status, that.status) &&
                Objects.equals(inzhuji, that.inzhuji);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, idNum, status, inzhuji);
    }
}
