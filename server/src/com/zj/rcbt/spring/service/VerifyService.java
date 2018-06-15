package com.zj.rcbt.spring.service;


import com.zj.rcbt.chsi.Chsi;
import com.zj.rcbt.chsi.ChsiParser;
import com.zj.rcbt.hibernate.dao.*;
import com.zj.rcbt.hibernate.model.*;
import com.zj.rcbt.qrcode.QRCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service("verifyService")
public class VerifyService {
    private Logger log =LogManager.getLogger(this.getClass());

    @Autowired
    private ApplyCompareDao applyCompareDao;

    @Autowired
    private ApplyDao applyDao;


    @Autowired
    private SocialsecurityDao socialsecurityDao;

    @Autowired
    private ArchivesDao archivesDao;
    @Autowired
    HttpServletRequest request;

    @Autowired
    private TopSchoolDao topSchoolDao;





    public String updateverifyXueli(ApplytablecompareBean applytablecompareBean){
        //       引进时间在毕业到次年12月31日


        return "";
    }

    public String updateXueli(ApplytableBean applytableBean){

        QRCode qrCode = new QRCode();
        String y=applytableBean.getEducationQrcode();
        String filename=y.substring(y.lastIndexOf("/")+1);
        log.info("filename :"+filename);
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
        log.info(path);
        path=path.replace("file:", ""); //去掉file:
        path=path.replace("classes/", ""); //去掉class/
        path=path.replace("WEB-INF/", "");
//        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb
        File srcFile = new File(path+"QRCode",filename);
        log.info(srcFile.getAbsolutePath());
        String x= null;
        try {
            x = qrCode.Decode(srcFile.getAbsolutePath());

        x=x.substring(x.indexOf("#")+1,x.lastIndexOf("#"));
        System.out.println(x);
        } catch (Exception e) {
            log.error(e.getMessage());
//            return "二维码识别失败";
        }
        ChsiParser chsiParser = new ChsiParser();

        Chsi chsi = chsiParser.getContentFromChsi(x);

        ApplytablecompareBean applytablecompareBean =applyCompareDao.findByIdnum(applytableBean.getIdNum());
        if (applytablecompareBean==null) {

             applytablecompareBean = new ApplytablecompareBean();
        }

        if (chsi.getError()==null||chsi.getError().equals("")) {


//        applytablecompareBean.setId(applytableBean.getApplyId());
            applytablecompareBean.setAuditComment("");
            applytablecompareBean.setEducationQrcode(x);
            applytablecompareBean.setBirthDate(chsi.getBirthdate());
            applytablecompareBean.setNation(chsi.getMingzu());
            applytablecompareBean.setEducation(chsi.getEducation());
            applytablecompareBean.setGraduateDate(chsi.getGraduatetime());
            applytablecompareBean.setEducationType(chsi.getXingshi());
            applytablecompareBean.setSex(chsi.getSex());
            applytablecompareBean.setSchool(chsi.getSchool());
            applytablecompareBean.setMajor(chsi.getMajor());
            if (chsi.getId() != null) {
                applytablecompareBean.setIdNum(chsi.getId());
            } else {
                applytablecompareBean.setIdNum(applytableBean.getIdNum());
            }
// TODO 双一流学校
          /*  if (topSchoolDao.findByName(applytablecompareBean.getSchool()) != null ||topSchoolDao.findByNameAndMajor(applytablecompareBean.getSchool(),applytablecompareBean.getMajor())!=null) {
                applytablecompareBean.setIsFirstschool("0");
            } else {
                applytablecompareBean.setIsFirstschool("1");
            }
*/
        }else {
            applytablecompareBean.setIdNum(applytableBean.getIdNum());
            applytablecompareBean.setEducationQrcode(x);
            applytablecompareBean.setAuditComment("学信网信息获取失败,请人工查看");
            applytablecompareBean.setBirthDate(chsi.getBirthdate());
            applytablecompareBean.setNation(chsi.getMingzu());
            applytablecompareBean.setEducation(chsi.getEducation());
            applytablecompareBean.setGraduateDate(chsi.getGraduatetime());
            applytablecompareBean.setEducationType(chsi.getXingshi());
            applytablecompareBean.setSex(chsi.getSex());
            applytablecompareBean.setSchool(chsi.getSchool());
            applytablecompareBean.setMajor(chsi.getMajor());
//            TODO 双一流，一流学科
         /*   if (topSchoolDao.findByName(applytableBean.getSchool()) != null) {
                applytablecompareBean.setIsFirstschool("0");
            } else {
                applytablecompareBean.setIsFirstschool("1");
            }*/
            applyCompareDao.save(applytablecompareBean);
//            TODO 暂不比较
           /* if (!applytablecompareBean.getIsFirstschool().equals(applytableBean.getIsFirstschool()) && applytableBean.getApplyType().equals("0")) {
                return "双一流资格不匹配";
            }*/
            return "0";

        }


            applyCompareDao.save(applytablecompareBean);


            if (!applytableBean.getEducation().equals(applytablecompareBean.getEducation())) {
                return "学历与学信网信息不一致";
            }

            if (!applytableBean.getMajor().equals(applytablecompareBean.getMajor())){
                return "专业与学信网信息不一致";
            }
       /* if (!applytableBean.getBirthDate().equals(applytablecompareBean.getBirthDate())){
            return "生日不符合";
        }*/
       /* if (!applytableBean.getGraduateDate().equals(applytablecompareBean.getGraduateDate())){
            return "民族不符合";
        }*/
            if (!applytablecompareBean.getEducationType().equals("普通全日制")&&!applytablecompareBean.getEducationType().equals("全日制")) {
                return "不为全日制";
            }
            if (!applytableBean.getSchool().equals(applytablecompareBean.getSchool())) {
                return "毕业院校与学信网信息不一致";
            }
            if (!applytableBean.getIdNum().equals(applytablecompareBean.getIdNum())) {
                return "身份证与学信网信息不一致";
            }
            if (!applytableBean.getGraduateDate().equals(applytablecompareBean.getGraduateDate().substring(0, 7))) {
                return "毕业时间与学信网信息不一致";
            }
//            TODO 双一流暂不比较

            /*if (!applytablecompareBean.getIsFirstschool().equals(applytableBean.getIsFirstschool()) ) {
                return "双一流资格不匹配";
            }*/


            return "0";





    }


    public String  updateSocialsecurity(ApplytableBean applytableBean){
         SocialsecurityBean socialsecurityBean = socialsecurityDao.findByIDnum(applytableBean.getIdNum());
         if (socialsecurityBean==null){
             return "-1";
         }
        ArchivesBean archivesBean = archivesDao.findByIDnum(applytableBean.getIdNum());
        if (archivesBean==null){
            return "-1";
        }
        if (socialsecurityBean.getBeginTime()==null||socialsecurityBean.getBeginTime().trim().equals("")){
            return "1";
        }
        ApplytablecompareBean applytablecompareBean =applyCompareDao.findByIdnum(applytableBean.getIdNum());
         if (applytablecompareBean==null){
             applytablecompareBean=new ApplytablecompareBean();
             applytablecompareBean.setIdNum(socialsecurityBean.getIdNum());
         }


        applytablecompareBean.setComeDate(socialsecurityBean.getBeginTime());
        applytablecompareBean.setWorkDate(socialsecurityBean.getLastTime());
        applytablecompareBean.setCompanyName(socialsecurityBean.getCompany());
        if (!(applytableBean.getCompanyName().trim()).equals(socialsecurityBean.getCompany().trim())){
            return "社保参保单位与用人单位不一致";
        }
        // TODO: 2018/4/20  引进时间做条件判断
      if (!socialsecurityBean.getBeginTime().trim().equals(applytableBean.getComeDate().trim())){
          return "引进时间与初次缴社保时间不一致";
      }

        applyCompareDao.save(applytablecompareBean);
//        applyCompareDao.refresh();


        return "0";

    }

    public void updateisAchive(ApplytableBean applytableBean){

        ApplytablecompareBean applytablecompareBean =applyCompareDao.findByIdnum(applytableBean.getIdNum());
        if (applytablecompareBean==null){
            applytablecompareBean=new ApplytablecompareBean();
            applytablecompareBean.setIdNum(applytableBean.getIdNum());
        }
        ArchivesBean archivesBean = archivesDao.findByIDnum(applytableBean.getIdNum());
        if (archivesBean==null){
            return;
        }
        if (archivesBean.getInzhuji().equalsIgnoreCase("Y")){
            applytablecompareBean.setIsarchive(1);
        }else {
            applytablecompareBean.setIsarchive(0);
        }


        applyCompareDao.save(applytablecompareBean);
//        applyCompareDao.refresh();
    }



    public List<SocialsecurityBean> findBystatus(String status){
        return socialsecurityDao.findBystatus(status);
    }

    public List<ArchivesBean> findByArchivesstatus(String status){
        return archivesDao.findBystatus(status);
    }





public static void main(String args[]){
        String s0="abc  ";
        System.out.println(s0.trim().length());
   String  s1="绍兴信息科技有限公司";
   String s2="绍兴信息科技有限公司   ";
    System.out.println(s1.trim().length());
   System.out.println(s2.trim().length());
}
}
