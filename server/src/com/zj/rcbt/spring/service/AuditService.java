package com.zj.rcbt.spring.service;

import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.DateUtil;
import com.zj.rcbt.hibernate.dao.AllowanceDao;
import com.zj.rcbt.hibernate.dao.ApplyDao;
import com.zj.rcbt.hibernate.dao.SocialsecurityDao;
import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.ApplytableBean;
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

        allowanceBean.setEducation(applytableBean.getEducation());

        allowanceDao.save(allowanceBean);

        applyDao.update(applytableBean);

        return 0;


    }


    public int updateauditDeny(ApplytableBean applytableBean){
        applytableBean.setApplyStatus(Constants.applystatus_over);
        applyDao.update(applytableBean);

        return 0;
    }

    public int updateauditDenyEdit(ApplytableBean applytableBean){

        applytableBean.setApplyStatus(Constants.applystatus_wait);
        applyDao.update(applytableBean);
        return 0;
    }
    public List<ApplytableBean> findByPages( String applyType, String status,String batch, int startRow, int pageSize,String idCard){
        return applyDao.findByPages(applyType,status,batch,startRow,pageSize,idCard);
    }

    public int findByPagesCount( String applyType, String status,String batch,String idCard){
        return applyDao.findByPagesCount(applyType,status, batch,idCard);

    }

}
