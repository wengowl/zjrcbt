package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "operation", schema = "rcbt", catalog = "")
public class OperationBean {
    private int id;
    private String userid;
    private String operation;
    private Timestamp operationtime;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "operation")
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Basic
    @Column(name = "operationtime")
    public Timestamp getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Timestamp operationtime) {
        this.operationtime = operationtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationBean that = (OperationBean) o;
        return id == that.id &&
                Objects.equals(userid, that.userid) &&
                Objects.equals(operation, that.operation) &&
                Objects.equals(operationtime, that.operationtime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userid, operation, operationtime);
    }
}
