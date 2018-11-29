package com.zj.rcbt.spring.service;

import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.DateUtil;
import com.zj.rcbt.hibernate.dao.AllowanceDao;
import com.zj.rcbt.hibernate.dao.ApplyDao;
import com.zj.rcbt.hibernate.dao.AuditDao;
import com.zj.rcbt.hibernate.dao.SocialsecurityDao;
import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.AuditcommentBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("auditService")
public class
AuditService {
    private Logger log =LogManager.getLogger(this.getClass());

    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private SocialsecurityDao socialsecurityDao;

    @Autowired
    private AllowanceDao allowanceDao;
    @Autowired
    private AuditDao auditDao;


    public int updateauditPass(ApplytableBean applytableBean){

        applytableBean.setApplyStatus(Constants.applystatus_pass);


        AllowanceBean allowanceBean = new AllowanceBean();
        // TODO allowancetype存放单位补贴金额
        allowanceBean.setAllowancetype(applytableBean.getApplyMoney());
        allowanceBean.setUpdatetime(DateUtil.getCurrentTime());
        allowanceBean.setIsfirstschool(applytableBean.getIsFirstschool());

        allowanceBean.setBeginTime(applytableBean.getComeDate());
        allowanceBean.setBatch(applytableBean.getBatch());
        allowanceBean.setOver("0");
        try {
            allowanceBean.setLastTime(DateUtil.dateAddMonth(applytableBean.getComeDate(),-1));
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
//        组装补贴发放汇总表初始化数据
        allowanceBean.setLastMoney(0);
        allowanceBean.setMonthes(0);
        allowanceBean.setIdNum(applytableBean.getIdNum());
        allowanceBean.setSumMoney(0);
        allowanceBean.setCompany(applytableBean.getCompanyName());
        allowanceBean.setName(applytableBean.getName());
        allowanceBean.setBank(applytableBean.getBank());
        allowanceBean.setBankCard(applytableBean.getBankCard());
        allowanceBean.setPhone(applytableBean.getPhoneNum());
        allowanceBean.setGraduatetime(applytableBean.getGraduateDate());


        if(applytableBean.getRcType().equals("0")){
            allowanceBean.setRcType("大专");
        }else if(applytableBean.getRcType().equals("1")){
            allowanceBean.setRcType("本科");
        }else if(applytableBean.getRcType().equals("2")){
            allowanceBean.setRcType("硕士");
        }else if(applytableBean.getRcType().equals("3")){
            allowanceBean.setRcType("副高");
        }else if(applytableBean.getRcType().equals("4")){
            allowanceBean.setRcType("高级技师");
        }else if (applytableBean.getRcType().equals("5")){
            allowanceBean.setRcType("博士");
        }else if (applytableBean.getRcType().equals("6")){
            allowanceBean.setRcType("正高");
        }else{
            allowanceBean.setRcType(applytableBean.getRcType());
        }

        allowanceBean.setEducation(applytableBean.getEducation());

        if (applytableBean.getApplyType().equals("0")){
            allowanceBean.setApplyType("租房补贴");
            allowanceDao.save(allowanceBean);
        }else if (applytableBean.getApplyType().equals("1")){
            allowanceBean.setApplyType("生活津贴");
            allowanceDao.save(allowanceBean);
        }



        applyDao.update(applytableBean);
        AuditcommentBean auditcommentBean = new AuditcommentBean();
        auditcommentBean.setAuditcomment("通过");
        auditcommentBean.setAudittime(DateUtil.getCurrentTime());
        auditcommentBean.setIdNum(applytableBean.getIdNum());
        auditDao.save(auditcommentBean);

        return 0;


    }


    public int updateauditDeny(ApplytableBean applytableBean){
        applytableBean.setApplyStatus(Constants.applystatus_auditdeny);
        AuditcommentBean auditcommentBean = new AuditcommentBean();
        int a=-1;
        try {
            a = Integer.parseInt(applytableBean.getRcType());
        }catch (Exception e){
            a=-1;
        }
        String s="";
        if (a==-1){
            s=s+"社会事业人才申请";
        }
        else {
            s=s+"高校毕业生人才申请";
        }
        auditcommentBean.setAuditcomment(applytableBean.getAuditComment()+" "+s+" 复核未通过");
        auditcommentBean.setAudittime(DateUtil.getCurrentTime());
        auditcommentBean.setIdNum(applytableBean.getIdNum());
        applyDao.update(applytableBean);
        auditDao.save(auditcommentBean);

        return 0;
    }

    public int updateauditDenyEdit(ApplytableBean applytableBean){
        AuditcommentBean auditcommentBean = new AuditcommentBean();
        int a=-1;
        try {
            a = Integer.parseInt(applytableBean.getRcType());
        }catch (Exception e){
            a=-1;
        }
        String s="";
        if (a==-1){
            s=s+"社会事业人才申请";
        }
        else {
            s=s+"高校毕业生人才申请";
        }
        auditcommentBean.setAuditcomment(applytableBean.getAuditComment()+"  "+s+"复核未通过等待修改");
        auditcommentBean.setAudittime(DateUtil.getCurrentTime());
        auditcommentBean.setIdNum(applytableBean.getIdNum());


        applytableBean.setApplyStatus(Constants.applystatus_wait);
        applyDao.update(applytableBean);
        auditDao.save(auditcommentBean);

        return 0;
    }
    public List<ApplytableBean> findByPages( String applyType, String status,String batch,String company,String name, int startRow, int pageSize,String idCard,String rcType){
        return applyDao.findByPages(applyType,status,batch, company,name,startRow,pageSize,idCard,rcType);
    }

    public int findByPagesCount( String applyType, String status,String batch,String company,String name,String idCard,String rcType){
        return applyDao.findByPagesCount(applyType,status, batch,company,name,idCard,rcType);

    }


    public List<AuditcommentBean> getAuditComment(String idnum){
        return auditDao.findByIdNum(idnum);
    }

}
