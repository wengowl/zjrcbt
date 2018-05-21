package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "allowance", schema = "rcbt", catalog = "")
public class AllowanceBean {
    private int allownceId;
    private String idNum;
    private String beginTime;
    private String lastTime;
    private int sumMoney;
    private Integer lastMoney;
    private int monthes;
    private String allowancetype;
    private String updatetime;
    private String shebao;
    private String bank;
    private String bankCard;
    private String phone;
    private String name;
    private String company;
    private String isfirstschool;
    private String batch;
    private String over;

    @Id
    @Column(name = "allownce_id")
    public int getAllownceId() {
        return allownceId;
    }

    public void setAllownceId(int allownceId) {
        this.allownceId = allownceId;
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
    @Column(name = "sum_money")
    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    @Basic
    @Column(name = "last_money")
    public Integer getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(Integer lastMoney) {
        this.lastMoney = lastMoney;
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
    @Column(name = "allowancetype")
    public String getAllowancetype() {
        return allowancetype;
    }

    public void setAllowancetype(String allowancetype) {
        this.allowancetype = allowancetype;
    }

    @Basic
    @Column(name = "updatetime")
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
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
    @Column(name = "over")
    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllowanceBean that = (AllowanceBean) o;
        return allownceId == that.allownceId &&
                sumMoney == that.sumMoney &&
                monthes == that.monthes &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(lastMoney, that.lastMoney) &&
                Objects.equals(allowancetype, that.allowancetype) &&
                Objects.equals(updatetime, that.updatetime) &&
                Objects.equals(shebao, that.shebao) &&
                Objects.equals(bank, that.bank) &&
                Objects.equals(bankCard, that.bankCard) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(name, that.name) &&
                Objects.equals(company, that.company) &&
                Objects.equals(isfirstschool, that.isfirstschool) &&
                Objects.equals(batch, that.batch) &&
                Objects.equals(over, that.over);
    }

    @Override
    public int hashCode() {

        return Objects.hash(allownceId, idNum, beginTime, lastTime, sumMoney, lastMoney, monthes, allowancetype, updatetime, shebao, bank, bankCard, phone, name, company, isfirstschool, batch, over);
    }
}
