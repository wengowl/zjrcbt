package com.zj.rcbt.spring.job;

import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import com.zj.rcbt.spring.service.AllowanceService;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.VerifyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("quartzAuditJob")
public class QuartzAuditJob {

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private VerifyService verifyService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private AllowanceService allowanceService;


    public void runTask(){

        List<ApplytableBean> applytableBeans = applyService.findBystatus(Constants.applystatus_init);
        for (ApplytableBean applytableBean:applytableBeans){
            if (allowanceService.findByidnum(applytableBean.getIdNum()).size()>0){
                applytableBean.setApplyStatus(Constants.applystatus_re);
                applytableBean.setAuditComment("系统已有您的补贴申请记录，无需重复申请，此次申请表的审核状态系统会显示为不通过，但您提交的信息将作为最新信息保存并覆盖原有信息，请认真填写！\n");
            }else {
                if (applytableBean.getEducationQrcode() != null && !applytableBean.getEducationQrcode().equals("")) {
                    String chsireturn = verifyService.updateXueli(applytableBean);
                    if (!chsireturn.equals("0")) {
                        applytableBean.setApplyStatus(Constants.applystatus_deny);
                        applytableBean.setAuditComment(chsireturn);

                    } else {
                        applytableBean.setApplyStatus(Constants.applystatus_first);
                        applytableBean.setAuditComment("");
                    }

                    applytableBean.setChsiReturn(chsireturn);


                } else {
                    applytableBean.setChsiReturn("没有学籍二维码");
                    applytableBean.setApplyStatus(Constants.applystatus_first);
                }
            }



            applyService.saveOrupdate(applytableBean);
        }

        List<SocialsecurityBean> socialsecurityBeanList= verifyService.findBystatus(Constants.socialsecurity_new);
        List<ArchivesBean> archivesBeanList=verifyService.findByArchivesstatus(Constants.socialsecurity_new);

        if (archivesBeanList!=null&&archivesBeanList.size()>0&&socialsecurityBeanList!=null&&socialsecurityBeanList.size()>0){
            List<ApplytableBean> applytables = applyService.findBystatus(Constants.applystatus_first);
            for (ApplytableBean applytableBean:applytables){
                log.info("shebao xiaoyan "+applytableBean.getIdNum());
                String socialSecurityreturn = verifyService.updateSocialsecurity(applytableBean);//判断社保
//               //判断档案
                    verifyService.updateisAchive(applytableBean);


                if (socialSecurityreturn.equals("-1")){
//                    TODO 必须存在社保数据和档案数据才会进行下一步
//                    applytableBean.setApplyStatus(Constants.applystatus_deny);
                    applytableBean.setAuditComment("暂无社保和档案数据比对");
                }else if (socialSecurityreturn.equals("1")){
                    applytableBean.setApplyStatus(Constants.applystatus_second);
                    applytableBean.setAuditComment("社保数据为空");
                }
                else if (socialSecurityreturn.equals("0")){
                    applytableBean.setApplyStatus(Constants.applystatus_second);
                    applytableBean.setAuditComment("");
                }else {
                    applytableBean.setApplyStatus(Constants.applystatus_second);
                    applytableBean.setAuditComment(socialSecurityreturn);
                }
                applyService.saveOrupdate(applytableBean);


            }
        }







    }
}
