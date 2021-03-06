package com.zj.rcbt.spring.service;


import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.DateUtil;
import com.zj.rcbt.common.utils.ExceptionUtils;
import com.zj.rcbt.hibernate.dao.*;
import com.zj.rcbt.hibernate.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("allowanceService")
public class AllowanceService {


    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private ResumeDao resumeDao;
    @Autowired
    private AllowanceDao allowanceDao;
    @Autowired
    private SocialsecurityDao socialsecurityDao;
    @Autowired
    private ArchivesDao archivesDao;
    @Autowired
    private AllowancehistoryDao allowancehistoryDao;

    public void save(AllowanceBean allowanceBean) {
        allowanceDao.save(allowanceBean);
    }

    public void update(AllowanceBean allowanceBean) {
        allowanceDao.update(allowanceBean);
    }

    public void updateHistory(AllowancehistoryBean allowanceBean) {
        allowancehistoryDao.update(allowanceBean);
    }

    public void deleteByidnum(String  idnum) {
        List<AllowanceBean> allowanceBeans = allowanceDao.findByIDnum(idnum);
        for (AllowanceBean allowanceBean:allowanceBeans) {
            allowanceDao.delete(allowanceBean);
        }
    }


    /**
     * 发放补贴
     */
    public void updateAllowance() {
//        将已经发放完毕的汇总最新发放金额置为0；
        List<AllowanceBean> overallowances=allowanceDao.findMonthesOver();
        for (AllowanceBean allowanceBean:overallowances){
            allowanceBean.setLastMoney(0);
            allowanceDao.update(allowanceBean);
        }

//        查找补贴汇总表中补帖数少于36个月的（若是初次申请，则需复核通过后，自动插入数据为零的信息）
        List<AllowanceBean> allowanceBeanList = allowanceDao.findMonthesRemain();
        for (AllowanceBean allowancebean : allowanceBeanList) {
//            TODO 去掉重复申请的获取申请表银行卡等信息，防止重复申请的错误更改
            ApplytableBean applytableBean = applyDao.findByIDnum(allowancebean.getIdNum());
            if (applytableBean!=null){
                allowancebean.setPhone(applytableBean.getPhoneNum());
                allowancebean.setCompany(applytableBean.getCompanyName());
                allowancebean.setName(applytableBean.getName());
                allowancebean.setBank(applytableBean.getBank());
                allowancebean.setBankCard(applytableBean.getBankCard());

            }
            AllowancehistoryBean allowancehistoryBean = new AllowancehistoryBean();
//            allowancehistoryBean.seta(allowancebean.getAllownceId());
            allowancehistoryBean.setAllowancetype(allowancebean.getAllowancetype());
            allowancehistoryBean.setBank(allowancebean.getBank());
            allowancehistoryBean.setBankCard(allowancebean.getBankCard());
            allowancehistoryBean.setCompany(allowancebean.getCompany());
            allowancehistoryBean.setPhone(allowancebean.getPhone());
            allowancehistoryBean.setName(allowancebean.getName());
            allowancehistoryBean.setIdNum(allowancebean.getIdNum());
            allowancehistoryBean.setIsfirstschool(allowancebean.getIsfirstschool());
            allowancehistoryBean.setBatch(allowancebean.getBatch());
            allowancehistoryBean.setComedate(allowancebean.getBeginTime());
            allowancehistoryBean.setApplyType(allowancebean.getApplyType());
            allowancehistoryBean.setRcType(allowancebean.getRcType());


            SocialsecurityBean socialsecurityBean = socialsecurityDao.findByIDnum(allowancebean.getIdNum());
            ArchivesBean archivesBean = archivesDao.findByIDnum(allowancebean.getIdNum());

            allowancebean.setLastMoney(0);

//            社保不为空--*---
            if (socialsecurityBean != null&&socialsecurityBean.getBeginTime()!=null &&!socialsecurityBean.getBeginTime().equals("") && socialsecurityBean.getMonthes() >=allowancebean.getMonthes()) {

                int monthes = 0;
                //社保连续缴纳月份
                if (socialsecurityBean.getMonthes() < 36) {
//                    本次应发放月数
                    monthes = socialsecurityBean.getMonthes() - allowancebean.getMonthes();
//                    发放的最后社保月份
                    allowancebean.setLastTime(socialsecurityBean.getLastTime());
                    if (!(DateUtil.dateAddMonth(socialsecurityBean.getLastTime(),1)).equals(DateUtil.getCurrentMonth())){
                        allowancebean.setShebao(socialsecurityBean.getLastTime());
                        allowancehistoryBean.setShebao("社保缴纳发放至"+socialsecurityBean.getLastTime());
                    }


                }
                if (socialsecurityBean.getMonthes() >=36) {
                    monthes = 36 - allowancebean.getMonthes();
                    try {
                        allowancebean.setLastTime(DateUtil.dateAddMonth(allowancebean.getBeginTime(), 35));
                        allowancebean.setShebao("发放完毕");
                        allowancehistoryBean.setShebao("发放完毕至"+allowancebean.getLastTime());
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                        log.error(ExceptionUtils.getStackTrace(e));
                    }
                }






//                TODO(历史记录双一流问题)
/*
                    String type="3";
                 if (allowancebean.getAllowancetype().equals("0")){
                     type="1";
                     if (allowancebean.getIsfirstschool().equals("0")){
                         type="2";
                     }

                 }*/
// TODO allowancetype存放单位补贴金额

                Integer permoney = Integer.valueOf(allowancebean.getAllowancetype());
                int lastMoney = monthes * permoney;
                allowancebean.setLastMoney(lastMoney);
                allowancehistoryBean.setOfferMoney(lastMoney);
                allowancehistoryBean.setIdNum(allowancebean.getIdNum());


                allowancebean.setMonthes(allowancebean.getMonthes()+monthes);
                allowancebean.setSumMoney(allowancebean.getSumMoney() + allowancebean.getLastMoney());
//引进时间已经超过36个月后，停止发放(由社保导出时间控制)
                if (DateUtil.getMonthNum(socialsecurityBean.getBeginTime(),DateUtil.getCurrentMonth())>=36){
                    allowancebean.setOver(Constants.allowance_over);
                }


            }

            if (socialsecurityBean==null){
                allowancehistoryBean.setShebao("社保为空");
                allowancehistoryBean.setOfferMoney(0);
            }


          /*  //TODO 档案不用判断
            if (archivesBean==null){
                allowancehistoryBean.setComment("档案信息为空");
            }else if (archivesBean.getInzhuji().equalsIgnoreCase("N")){
                allowancehistoryBean.setComment("档案不在诸暨");
            }*/



            allowancebean.setUpdatetime(DateUtil.getCurrentTime());
            allowancehistoryBean.setOfferTime(DateUtil.getCurrentTime());
            allowancehistoryBean.setGraduatetime(allowancebean.getGraduatetime());
            allowancehistoryBean.setEducation(allowancebean.getEducation());

            allowancehistoryDao.save(allowancehistoryBean);
            allowancehistoryDao.refresh();
            allowanceDao.update(allowancebean);
            allowanceDao.flush();


        }

        socialsecurityDao.updateAll();
        archivesDao.updateAll();


        List<ApplytableBean> applytableBeans = applyDao.findBystatus(Constants.applystatus_deny);
        for (ApplytableBean applytableBean:applytableBeans){
           /* if (!applytableBean.getApplyType().equals("0")&& !applytableBean.getApplyType().equals("1")){
                continue;
            }*/
            applytableBean.setApplyStatus(Constants.applystatus_over);
            applyDao.update(applytableBean);
        }
//  已复核待修改未修改的，也置为不通过
        List<ApplytableBean> applytableBeans1 = applyDao.findBystatus(Constants.applystatus_wait);
        for (ApplytableBean applytableBean:applytableBeans1){
          /*  if (!applytableBean.getApplyType().equals("0")&& !applytableBean.getApplyType().equals("1")){
                continue;
            }*/
            applytableBean.setApplyStatus(Constants.applystatus_over);
            applyDao.update(applytableBean);
        }


//      复核未通过的，设置为历史未通过
        List<ApplytableBean> applytableBeans2 = applyDao.findBystatus(Constants.applystatus_auditdeny);
        for (ApplytableBean applytableBean:applytableBeans2){
            /*if (!applytableBean.getApplyType().equals("0")&& !applytableBean.getApplyType().equals("1")){
                continue;
            }*/
            applytableBean.setApplyStatus(Constants.applystatus_over);
            applyDao.update(applytableBean);
        }



    }


    public void updatebackAllowance(){
        List<AllowanceBean> allowanceBeans= allowanceDao.findLastMoneyBack();
        for (AllowanceBean allowanceBean:allowanceBeans){
            int lastMoney= allowanceBean.getLastMoney();
            int sumMoney = allowanceBean.getSumMoney();
            int monthes = allowanceBean.getMonthes();
            int allowancetype= new Integer(allowanceBean.getAllowancetype());
            String lasttime = allowanceBean.getLastTime();

            int month = lastMoney/allowancetype;
            allowanceBean.setLastMoney(0);
            allowanceBean.setSumMoney(sumMoney-lastMoney);
            allowanceBean.setMonthes(monthes-month);
            allowanceBean.setLastTime(DateUtil.dateAddMonth(lasttime,-month));
            allowanceBean.setUpdatetime(DateUtil.getCurrentTime());

            allowanceDao.update(allowanceBean);
        }

    }

    public List<AllowancehistoryBean> findhistoryByPages(String offer_time,String allowancetype,String idnum,String batch,String name,int startRow,int pageSize,String rcType ){
        return allowancehistoryDao.findByPages(offer_time,allowancetype,idnum,batch,name,startRow,pageSize,rcType);
    }

    public List<AllowanceBean> findByPages(String month,String allowancetype,String idnum,String batch,String name,int startRow, int pageSize,String rcType){
        return allowanceDao.findByPages(month,allowancetype,idnum,batch,name,startRow,pageSize,rcType);
    }




    public int getCounthistory(String offer_time,String allowancetype,String idnum,String batch,String name,String rcType){
        return allowancehistoryDao.findByPagesCount( offer_time, allowancetype, idnum,batch,name,rcType);

    }

    public int getCount(String month,String allowancetype,String idnum,String batch,String name,String rcType){
        return allowanceDao.findByPagesCount(month,allowancetype,idnum,batch,name,rcType);
    }


    public List<AllowanceBean> findByPorperty(String month,String allowancetype,String idnum){
        return allowanceDao.findByproperty(month,allowancetype,idnum);


    }
    public List<AllowancehistoryBean> findhistoryByPorperty(String month,String allowancetype,String idnum){
        return allowancehistoryDao.findByProperty(month,allowancetype,idnum);


    }

    public List<AllowanceBean> findByidnum(String idnum){

        return allowanceDao.findByIDnum(idnum);


    }


    public List<AllowanceBean> getIdnumsAllowance(){
        return allowanceDao.getIdnumsAllowance();
    }
    public List<AllowanceBean> getIdnumsAllowancenew(){
        return allowanceDao.getIdnumsAllowancenew();
    }



}
