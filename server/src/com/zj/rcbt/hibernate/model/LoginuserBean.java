package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "loginuser", schema = "rcbt", catalog = "")
public class LoginuserBean {
    private String userName;
    private String idNum;
    private String passwd;
    private Integer userType;
    private String email;

    @Basic
    @Column(name = "user_name", nullable = false, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Id
    @Column(name = "id_num", nullable = false, length = 18)
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 32)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "user_type", nullable = true)
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginuserBean that = (LoginuserBean) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(passwd, that.passwd) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, idNum, passwd, userType, email);
    }
}
