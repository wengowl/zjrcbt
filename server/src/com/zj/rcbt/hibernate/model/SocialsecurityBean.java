package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socialsecurity", schema = "rcbt", catalog = "")
public class SocialsecurityBean {
    private int id;
    private String userName;
    private String idNum;
    private String beginTime;
    private String lastTime;
    private int monthes;
    private String status;

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
    @Column(name = "begin_time")
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    @Basic
    @Column(name = "last_time")
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "monthes")
    public int getMonthes() {
        return monthes;
    }

    public void setMonthes(int monthes) {
        this.monthes = monthes;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialsecurityBean that = (SocialsecurityBean) o;
        return id == that.id &&
                monthes == that.monthes &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, idNum, beginTime, lastTime, monthes, status);
    }
}
