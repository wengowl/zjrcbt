package com.zj.rcbt.spring.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.DateUtil;
import com.zj.rcbt.common.utils.FileUtils;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.dao.ApplyCompareDao;
import com.zj.rcbt.hibernate.dao.ApplyDao;
import com.zj.rcbt.hibernate.dao.ResumeDao;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.ApplytablecompareBean;
import com.zj.rcbt.hibernate.model.ResumeBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service("applyService")
public class ApplyService {
    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private ResumeDao resumeDao;
    @Autowired
    HttpServletRequest request;
    @Autowired
    private ApplyCompareDao applyCompareDao;

    public int saveOrupdate(ApplytableBean applytableBean) {
     /*   if (applytableBean.getBatch()==null||applytableBean.getBatch().equals("")){
            applytableBean.setBatch(DateUtil.getCurrentMonth());
        }*/
//        applyDao.clear()
        applyDao.update(applytableBean);

        return 0;

    }
    public void deleteResume(String idnum){
        resumeDao.deleteByidunm(idnum);
//        resumeDao.clear();

    }

    public void saveResume(ResumeBean resumeBean) {
           resumeDao.save(resumeBean);
           resumeDao.refresh();


    }

    public List<ApplytableBean> findBystatus(String status) {

        return applyDao.findBystatus(status);
    }

    public ApplytableBean findByIDnum(String idnum) {
        return applyDao.findByIDnum(idnum);
    }
    public ApplytablecompareBean findCompareByIDnum(String idnum) {
        return applyCompareDao.findByIdnum(idnum);
    }



    public Map<String, Object> packApplyinfo(ApplytableBean applytableBean) {
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("name", applytableBean.getName());
        info.put("idCard", applytableBean.getIdNum());
        info.put("sex", applytableBean.getSex());
        info.put("national", applytableBean.getNation());
        info.put("nativePlace", applytableBean.getNativePalce());
        info.put("politicsStatus", applytableBean.getPoliticalLandscape());
        info.put("birth", applytableBean.getBirthDate());
        info.put("pictureUrl", applytableBean.getPhotoLocation());
        info.put("educationQrcode", applytableBean.getEducationQrcode());
        info.put("graduationDate", applytableBean.getGraduateDate());
        info.put("school", applytableBean.getSchool());
        info.put("professional", applytableBean.getMajor());
        info.put("education", applytableBean.getEducation());
        info.put("duty", applytableBean.getProfessionalTitle());
        info.put("workingTime", applytableBean.getWorkDate());
        info.put("introductionTime", applytableBean.getComeDate());
        info.put("applyTime", DateUtil.formatTime(applytableBean.getApplyTime()));
        info.put("TalentCategory", applytableBean.getRcType());
        info.put("applicationCategory", applytableBean.getApplyType());
        info.put("appliedAmount", applytableBean.getApplyMoney());
        info.put("doubleTop", applytableBean.getIsFirstschool());
        info.put("userPhone", applytableBean.getPhoneNum());
        info.put("companyType", applytableBean.getCompanyType());
        info.put("companyName", applytableBean.getCompanyName());
        info.put("companyAddress", applytableBean.getCompanyAddress());
        info.put("companyContact", applytableBean.getCompanyContact());
        info.put("contactProfessional", applytableBean.getPost());
        info.put("contactPhone", applytableBean.getContactPhone());
        info.put("bankCard", applytableBean.getBankCard());
        info.put("bank", applytableBean.getBank());
        info.put("applystatus", applytableBean.getApplyStatus());
        info.put("auditcomment", applytableBean.getAuditComment());
        info.put("attachment", applytableBean.getAttatchment());
        info.put("inzhuji",applytableBean.getInzhuji());
        String token = JWTUtils.createToken(request.getHeader("idcard"));
        info.put("token",token);


        List<Map<String, Object>> resumes = new ArrayList<Map<String, Object>>();
        List<ResumeBean> resumeBeans = resumeDao.findByidnum(applytableBean.getIdNum());
        for (ResumeBean resumeBean : resumeBeans) {
            Map<String, Object> resume = new HashMap<String, Object>();
            resume.put("qizhiTime", resumeBean.getTimeScape());
            resume.put("company", resumeBean.getCompany());
            resume.put("department", resumeBean.getDepartment());
            resume.put("position", resumeBean.getPost());

            resumes.add(resume);
        }
        info.put("workExperience", resumes);

        return info;


    }


    public int saveparserJsonObject(JSONObject jsonObject) throws IOException {
        int a = 0;
        String id_num =jsonObject.getString("idCard");
        ApplytableBean applytableBean = applyDao.findByIDnum(id_num);
        //历史未通过的，重新修改提交改为当前批次
        if (applytableBean!=null && applytableBean.getApplyStatus().equals("-1")){
            applytableBean.setBatch(DateUtil.getCurrentMonth());

        }
        if (applytableBean==null){
                applytableBean=new ApplytableBean();
            applytableBean.setBatch(DateUtil.getCurrentMonth());
        }

        applytableBean.setName(jsonObject.getString("name"));
        applytableBean.setIdNum(id_num);
        applytableBean.setSex(jsonObject.getString("sex"));
        applytableBean.setNation(jsonObject.getString("national"));
        applytableBean.setNativePalce(jsonObject.getString("nativePlace"));
        applytableBean.setPoliticalLandscape(jsonObject.getString("politicsStatus"));
        applytableBean.setBirthDate(jsonObject.getString("birth"));
        applytableBean.setPhotoLocation(jsonObject.getString("pictureUrl"));
        applytableBean.setEducationQrcode(jsonObject.getString("educationQrcode"));
        applytableBean.setGraduateDate(jsonObject.getString("graduationDate"));
        applytableBean.setSchool(jsonObject.getString("school"));
        applytableBean.setMajor(jsonObject.getString("professional"));
        applytableBean.setEducation(jsonObject.getString("education"));
        applytableBean.setProfessionalTitle(jsonObject.getString("duty"));
        applytableBean.setWorkDate(jsonObject.getString("workingTime"));
        applytableBean.setComeDate(jsonObject.getString("introductionTime"));
//        applytableBean.setApplyTime(jsonObject.getString("applyTime"));
        applytableBean.setRcType(jsonObject.getString("TalentCategory"));
        applytableBean.setApplyType(jsonObject.getString("applicationCategory"));
        applytableBean.setApplyMoney(jsonObject.getString("appliedAmount"));
        applytableBean.setIsFirstschool(jsonObject.getString("doubleTop"));
        applytableBean.setPhoneNum(jsonObject.getString("userPhone"));
        applytableBean.setCompanyType(jsonObject.getString("companyType"));
        applytableBean.setCompanyName(jsonObject.getString("companyName"));
        applytableBean.setCompanyAddress(jsonObject.getString("companyAddress"));
        applytableBean.setCompanyContact(jsonObject.getString("companyContact"));
        applytableBean.setPost(jsonObject.getString("contactProfessional"));
        applytableBean.setContactPhone(jsonObject.getString("contactPhone"));
        applytableBean.setBankCard(jsonObject.getString("bankCard"));
        applytableBean.setBank(jsonObject.getString("bank"));
        applytableBean.setAttatchment(jsonObject.getString("attachment"));
        applytableBean.setApplyStatus(jsonObject.getString("applyStatus"));
        applytableBean.setInzhuji(jsonObject.getString("inzhuji"));

//        TODO:move attchement and photo
        String photourl = FileUtils.getdestUrl(applytableBean.getPhotoLocation(), "photo", request);


        applytableBean.setPhotoLocation(photourl);
        if (applytableBean.getEducationQrcode() != null && !applytableBean.getEducationQrcode().equals("")) {
            String qrcode = FileUtils.getdestUrl(applytableBean.getEducationQrcode(), "QRCode", request);
            applytableBean.setEducationQrcode(qrcode);
        }
        if (applytableBean.getAttatchment() != null && !applytableBean.getAttatchment().equals("")) {

            String attatchement = FileUtils.getdestUrl(applytableBean.getAttatchment(), "attachment", request);
            applytableBean.setAttatchment(attatchement);
        }


        JSONArray resumeBeans = jsonObject.getJSONArray("workExperience");

        deleteResume(applytableBean.getIdNum());

        for (int i = 0; i < resumeBeans.size(); i++) {
            JSONObject jresumeBean = (JSONObject) resumeBeans.get(i);
            ResumeBean resumeBean = new ResumeBean();
            resumeBean.setIdNum(applytableBean.getIdNum());
            resumeBean.setCompany(jresumeBean.getString("company"));
            resumeBean.setTimeScape(jresumeBean.getString("qizhiTime"));
            resumeBean.setDepartment(jresumeBean.getString("department"));
            resumeBean.setPost(jresumeBean.getString("position"));
            saveResume(resumeBean);
            // TODO: 2018/4/13
//            update 待观察

        }

        a = saveOrupdate(applytableBean);

        FileUtils.moveFile(applytableBean.getPhotoLocation(), "photo", request);
        if (applytableBean.getEducationQrcode() != null && !applytableBean.getEducationQrcode().equals("")) {
            FileUtils.moveFile(applytableBean.getEducationQrcode(), "QRCode", request);
        }
        if (applytableBean.getAttatchment() != null && !applytableBean.getAttatchment().equals("")) {

            FileUtils.moveFile(applytableBean.getAttatchment(), "attachment", request);
        }


        return a;


    }

    public void updateinitStatus(){
        List<ApplytableBean> applytableBeans1 = applyDao.findBystatus(Constants.applystatus_deny);
        List<ApplytableBean> applytableBeans2 = applyDao.findBystatus(Constants.applystatus_second);
        for (ApplytableBean applytableBean:applytableBeans1){
            applytableBean.setApplyStatus(Constants.applystatus_init);
            applyDao.update(applytableBean);
        }
        for (ApplytableBean applytableBean:applytableBeans2){
            applytableBean.setApplyStatus(Constants.applystatus_init);
            applyDao.update(applytableBean);
        }

    }



    public Map<String, Object> packApplyCompareinfo(ApplytablecompareBean applytableBean) {
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("name", applytableBean.getName());
        info.put("idCard", applytableBean.getIdNum());
        info.put("sex", applytableBean.getSex());
        info.put("national", applytableBean.getNation());
        info.put("nativePlace", applytableBean.getNativePalce());
        info.put("politicsStatus", applytableBean.getPoliticalLandscape());
        info.put("birth", applytableBean.getBirthDate());
        info.put("pictureUrl", applytableBean.getPhotoLocation());
        info.put("educationQrcode", applytableBean.getEducationQrcode());
        info.put("graduationDate", applytableBean.getGraduateDate());
        info.put("school", applytableBean.getSchool());
        info.put("professional", applytableBean.getMajor());
        info.put("education", applytableBean.getEducation());
        info.put("duty", applytableBean.getProfessionalTitle());
        info.put("workingTime", applytableBean.getWorkDate());
        info.put("introductionTime", applytableBean.getComeDate());
        info.put("applyTime", DateUtil.formatTime(applytableBean.getApplyTime()));
        info.put("TalentCategory", applytableBean.getRcType());
        info.put("applicationCategory", applytableBean.getApplyType());
        info.put("appliedAmount", applytableBean.getApplyMoney());
        info.put("doubleTop", applytableBean.getIsFirstschool());
        info.put("userPhone", applytableBean.getPhoneNum());
        info.put("companyType", applytableBean.getCompanyType());
        info.put("companyName", applytableBean.getCompanyName());
        info.put("companyAddress", applytableBean.getCompanyAddress());
        info.put("companyContact", applytableBean.getCompanyContact());
        info.put("contactProfessional", applytableBean.getPost());
        info.put("contactPhone", applytableBean.getContactPhone());
        info.put("bankCard", applytableBean.getBankCard());
        info.put("bank", applytableBean.getBank());
//        info.put("applystatus", applytableBean.getApplyStatus());
       info.put("auditcomment", applytableBean.getAuditComment());
//        info.put("attachment", applytableBean.getAttatchment());
        info.put("chsireturn",applytableBean.getChsiReturn());
        info.put("eductiontype",applytableBean.getEducationType());
        info.put("isarchive",applytableBean.getIsarchive());
        info.put("inzhuji",applytableBean.getInzhuji());



        return info;


    }

    public List<ApplytableBean> getIdnumsApply(){
        return applyDao.getIdnumsApply();
    }
    public List<ApplytableBean> getIdnumsApplynewshebao(){
        return applyDao.getIdnumsApplynewshebao();
    }

    public List<ApplytableBean> getIdnumsApplynewdangan(){
        return applyDao.getIdnumsApplynewdangan();
    }
}
