package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "allowancehistory", schema = "rcbt", catalog = "")
public class AllowancehistoryBean {
    private int id;
    private String idNum;
    private String offerTime;
    private Integer offerMoney;
    private String allowancetype;
    private String comment;
    private String shebao;
    private String bank;
    private String bankCard;
    private String phone;
    private String name;
    private String company;
    private String isfirstschool;
    private String batch;
    private String education;
    private String graduatetime;
    private String comedate;
    private String applyType;
    private String rcType;

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
    @Column(name = "offer_time")
    public String getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(String offerTime) {
        this.offerTime = offerTime;
    }

    @Basic
    @Column(name = "offer_money")
    public Integer getOfferMoney() {
        return offerMoney;
    }

    public void setOfferMoney(Integer offerMoney) {
        this.offerMoney = offerMoney;
    }

    @Basic
    @Column(name = "allowancetype")
    public String getAllowancetype() {
        return allowancetype;
    }

    public void setAllowancetype(String allowancetype) {
        this.allowancetype = allowancetype;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "shebao")
    public String getShebao() {
        return shebao;
    }

    public void setShebao(String shebao) {
        this.shebao = shebao;
    }

    @Basic
    @Column(name = "bank")
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Basic
    @Column(name = "bank_card")
    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "isfirstschool")
    public String getIsfirstschool() {
        return isfirstschool;
    }

    public void setIsfirstschool(String isfirstschool) {
        this.isfirstschool = isfirstschool;
    }

    @Basic
    @Column(name = "batch")
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "graduatetime")
    public String getGraduatetime() {
        return graduatetime;
    }

    public void setGraduatetime(String graduatetime) {
        this.graduatetime = graduatetime;
    }

    @Basic
    @Column(name = "comedate")
    public String getComedate() {
        return comedate;
    }

    public void setComedate(String comedate) {
        this.comedate = comedate;
    }

    @Basic
    @Column(name = "apply_type")
    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    @Basic
    @Column(name = "rc_type")
    public String getRcType() {
        return rcType;
    }

    public void setRcType(String rcType) {
        this.rcType = rcType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllowancehistoryBean that = (AllowancehistoryBean) o;
        return id == that.id &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(offerTime, that.offerTime) &&
                Objects.equals(offerMoney, that.offerMoney) &&
                Objects.equals(allowancetype, that.allowancetype) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(shebao, that.shebao) &&
                Objects.equals(bank, that.bank) &&
                Objects.equals(bankCard, that.bankCard) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(name, that.name) &&
                Objects.equals(company, that.company) &&
                Objects.equals(isfirstschool, that.isfirstschool) &&
                Objects.equals(batch, that.batch) &&
                Objects.equals(education, that.education) &&
                Objects.equals(graduatetime, that.graduatetime) &&
                Objects.equals(comedate, that.comedate) &&
                Objects.equals(applyType, that.applyType) &&
                Objects.equals(rcType, that.rcType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idNum, offerTime, offerMoney, allowancetype, comment, shebao, bank, bankCard, phone, name, company, isfirstschool, batch, education, graduatetime, comedate, applyType, rcType);
    }
}
