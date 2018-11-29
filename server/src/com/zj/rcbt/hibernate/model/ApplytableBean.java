package com.zj.rcbt.hibernate.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "applytable", schema = "rcbt", catalog = "")
public class ApplytableBean {
    private String idNum;
    private Integer userId;
    private String photoLocation;
    private String name;
    private String birthDate;
    private String sex;
    private String graduateDate;
    private String major;
    private String nation;
    private String nativePalce;
    private String education;
    private String school;
    private String politicalLandscape;
    private String professionalTitle;
    private String workDate;
    private String comeDate;
    private String applyDate;
    private String rcType;
    private String applyType;
    private String applyMoney;
    private String isFirstschool;
    private String phoneNum;
    private String companyType;
    private String companyName;
    private String companyAddress;
    private String companyContact;
    private String post;
    private String contactPhone;
    private String bank;
    private String bankCard;
    private String educationQrcode;
    private String attatchment;
    private Timestamp applyTime;
    private String chsiReturn;
    private String applyStatus;
    private Integer allowanceId;
    private String auditComment;
    private String batch;
    private String inzhuji;

    @Id
    @Column(name = "id_num")
    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "photo_location")
    public String getPhotoLocation() {
        return photoLocation;
    }

    public void setPhotoLocation(String photoLocation) {
        this.photoLocation = photoLocation;
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
    @Column(name = "birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "graduate_date")
    public String getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(String graduateDate) {
        this.graduateDate = graduateDate;
    }

    @Basic
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "nation")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Basic
    @Column(name = "native_palce")
    public String getNativePalce() {
        return nativePalce;
    }

    public void setNativePalce(String nativePalce) {
        this.nativePalce = nativePalce;
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
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "political_landscape")
    public String getPoliticalLandscape() {
        return politicalLandscape;
    }

    public void setPoliticalLandscape(String politicalLandscape) {
        this.politicalLandscape = politicalLandscape;
    }

    @Basic
    @Column(name = "professional_title")
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    @Basic
    @Column(name = "work_date")
    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    @Basic
    @Column(name = "come_date")
    public String getComeDate() {
        return comeDate;
    }

    public void setComeDate(String comeDate) {
        this.comeDate = comeDate;
    }

    @Basic
    @Column(name = "apply_date")
    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    @Basic
    @Column(name = "rc_type")
    public String getRcType() {
        return rcType;
    }

    public void setRcType(String rcType) {
        this.rcType = rcType;
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
    @Column(name = "apply_money")
    public String getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
    }

    @Basic
    @Column(name = "is_firstschool")
    public String getIsFirstschool() {
        return isFirstschool;
    }

    public void setIsFirstschool(String isFirstschool) {
        this.isFirstschool = isFirstschool;
    }

    @Basic
    @Column(name = "phone_num")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "company_type")
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Basic
    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Basic
    @Column(name = "company_contact")
    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    @Basic
    @Column(name = "post")
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    @Basic
    @Column(name = "contact_phone")
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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
    @Column(name = "education_qrcode")
    public String getEducationQrcode() {
        return educationQrcode;
    }

    public void setEducationQrcode(String educationQrcode) {
        this.educationQrcode = educationQrcode;
    }

    @Basic
    @Column(name = "attatchment")
    public String getAttatchment() {
        return attatchment;
    }

    public void setAttatchment(String attatchment) {
        this.attatchment = attatchment;
    }

    @Basic
    @Column(name = "apply_time")
    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    @Basic
    @Column(name = "chsi_return")
    public String getChsiReturn() {
        return chsiReturn;
    }

    public void setChsiReturn(String chsiReturn) {
        this.chsiReturn = chsiReturn;
    }

    @Basic
    @Column(name = "apply_status")
    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Basic
    @Column(name = "allowance_id")
    public Integer getAllowanceId() {
        return allowanceId;
    }

    public void setAllowanceId(Integer allowanceId) {
        this.allowanceId = allowanceId;
    }

    @Basic
    @Column(name = "audit_comment")
    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
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
        ApplytableBean that = (ApplytableBean) o;
        return Objects.equals(idNum, that.idNum) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(photoLocation, that.photoLocation) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(graduateDate, that.graduateDate) &&
                Objects.equals(major, that.major) &&
                Objects.equals(nation, that.nation) &&
                Objects.equals(nativePalce, that.nativePalce) &&
                Objects.equals(education, that.education) &&
                Objects.equals(school, that.school) &&
                Objects.equals(politicalLandscape, that.politicalLandscape) &&
                Objects.equals(professionalTitle, that.professionalTitle) &&
                Objects.equals(workDate, that.workDate) &&
                Objects.equals(comeDate, that.comeDate) &&
                Objects.equals(applyDate, that.applyDate) &&
                Objects.equals(rcType, that.rcType) &&
                Objects.equals(applyType, that.applyType) &&
                Objects.equals(applyMoney, that.applyMoney) &&
                Objects.equals(isFirstschool, that.isFirstschool) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(companyType, that.companyType) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(companyAddress, that.companyAddress) &&
                Objects.equals(companyContact, that.companyContact) &&
                Objects.equals(post, that.post) &&
                Objects.equals(contactPhone, that.contactPhone) &&
                Objects.equals(bank, that.bank) &&
                Objects.equals(bankCard, that.bankCard) &&
                Objects.equals(educationQrcode, that.educationQrcode) &&
                Objects.equals(attatchment, that.attatchment) &&
                Objects.equals(applyTime, that.applyTime) &&
                Objects.equals(chsiReturn, that.chsiReturn) &&
                Objects.equals(applyStatus, that.applyStatus) &&
                Objects.equals(allowanceId, that.allowanceId) &&
                Objects.equals(auditComment, that.auditComment) &&
                Objects.equals(batch, that.batch) &&
                Objects.equals(inzhuji, that.inzhuji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNum, userId, photoLocation, name, birthDate, sex, graduateDate, major, nation, nativePalce, education, school, politicalLandscape, professionalTitle, workDate, comeDate, applyDate, rcType, applyType, applyMoney, isFirstschool, phoneNum, companyType, companyName, companyAddress, companyContact, post, contactPhone, bank, bankCard, educationQrcode, attatchment, applyTime, chsiReturn, applyStatus, allowanceId, auditComment, batch, inzhuji);
    }
}
