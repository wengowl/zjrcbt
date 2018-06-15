package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "auditcomment", schema = "rcbt", catalog = "")
public class AuditcommentBean {
    private int id;
    private String idNum;
    private String audittime;
    private String auditcomment;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "audittime")
    public String getAudittime() {
        return audittime;
    }

    public void setAudittime(String audittime) {
        this.audittime = audittime;
    }

    @Basic
    @Column(name = "auditcomment")
    public String getAuditcomment() {
        return auditcomment;
    }

    public void setAuditcomment(String auditcomment) {
        this.auditcomment = auditcomment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditcommentBean that = (AuditcommentBean) o;
        return id == that.id &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(audittime, that.audittime) &&
                Objects.equals(auditcomment, that.auditcomment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idNum, audittime, auditcomment);
    }
}
