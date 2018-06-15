package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.AuditcommentBean;
import com.zj.rcbt.spring.service.AllowanceService;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.AuditService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/audit")
public class AuditController {

    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private AuditService auditService;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private AllowanceService allowanceService;


    @RequestMapping({"/update"})
    @ResponseBody
    public RequestResult auditDO(@RequestBody String requestBody){
        RequestResult result =  new RequestResult();
        result.setStatus(-1);
        JSONObject jsonObject=JSONObject.parseObject(requestBody);
        String status=jsonObject.getString("status");
        String commnet= jsonObject.getString("comment");
        String idNUm = jsonObject.getString("idCard");
        String comedate = jsonObject.getString("introductionTime");

        log.info("auditDO "+idNUm+": "+status);


        ApplytableBean applytableBean = applyService.findByIDnum(idNUm);

//        applytableBean.setApplyStatus(status);
        applytableBean.setAuditComment(commnet);
        applytableBean.setComeDate(comedate);

        if (status.equals(Constants.applystatus_pass)){
            auditService.updateauditPass(applytableBean);
        }

        if (status.equals(Constants.applystatus_wait)){
            if (applytableBean.getApplyStatus().equals(Constants.applystatus_pass)){
                allowanceService.deleteByidnum(idNUm);
            }
            auditService.updateauditDenyEdit(applytableBean);

        }

        if (status.equals(Constants.applystatus_deny)){
            if (applytableBean.getApplyStatus().equals(Constants.applystatus_pass)){
                allowanceService.deleteByidnum(idNUm);
            }
            auditService.updateauditDeny(applytableBean);

        }

        result.setData("");
        result.setStatus(0);




        return result;

    }


    @RequestMapping({"/list"})
    @ResponseBody
    public RequestResult getAuditList(@RequestParam("status") String status, @RequestParam("applicationCategory") String applytype,@RequestParam("batch") String batch, @RequestParam("page") int page, @RequestParam("limit") int limit,@RequestParam("idCard") String idCard){
        log.info("getAuditList");
        RequestResult result =  new RequestResult();
        result.setStatus(-1);
        /*JSONObject jsonObject=JSONObject.parseObject(requestBody);
        String applytype = jsonObject.getString("applicationCategory");
        String status = jsonObject.getString("status");
        int page=jsonObject.getInteger("page");
        int limit = jsonObject.getInteger("limit");
*/


        List<ApplytableBean> applytableBeans = auditService.findByPages(applytype,status,batch,(page-1)*limit, limit,idCard);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ApplytableBean applytableBean : applytableBeans) {
            Map<String, Object> applytable = new HashMap<String, Object>();
            applytable.put("name", applytableBean.getName());
            applytable.put("idCard",applytableBean.getIdNum() );
            applytable.put("appliCationCategory",applytableBean.getApplyType() );
            applytable.put("introductionTime",applytableBean.getComeDate() );
            applytable.put("appliedAmount",applytableBean.getApplyMoney());
            applytable.put("bank",applytableBean.getBank());
            applytable.put("bankCard",applytableBean.getBankCard());
            applytable.put("userPhone",applytableBean.getPhoneNum());
            applytable.put("applyStatus",applytableBean.getApplyStatus());
            applytable.put("batch",applytableBean.getBatch());



            list.add(applytable);
        }

        Map<String,Object> data = new HashMap<>();
        data.put("data",list);
        data.put("totalNum",auditService.findByPagesCount(applytype,status,batch,idCard));

        result.setData(data);

        result.setStatus(0);



        return result;
    }



    @RequestMapping({"/getlist"})
    @ResponseBody
    public Map<String,Object> getAuditListnew(@RequestParam("status") String status, @RequestParam("applicationCategory") String applytype, @RequestParam("batch") String batch,@RequestParam("page") int page, @RequestParam("limit")int limit,@RequestParam("idCard") String idCard){
        log.info("getAuditListnew");
        Map<String,Object> result =  new HashMap<String, Object>();


        /*JSONObject jsonObject=JSONObject.parseObject(requestBody);
        String applytype = jsonObject.getString("applicationCategory");
        String status = jsonObject.getString("status");
        int page=jsonObject.getInteger("page");
        int limit = jsonObject.getInteger("limit");
*/


        List<ApplytableBean> applytableBeans = auditService.findByPages(applytype,status,batch,(page-1)*limit, limit,idCard);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ApplytableBean applytableBean : applytableBeans) {
            Map<String, Object> applytable = new HashMap<String, Object>();
            applytable.put("name", applytableBean.getName());
            applytable.put("idCard",applytableBean.getIdNum() );
            applytable.put("appliCationCategory",applytableBean.getApplyType() );
            applytable.put("introductionTime",applytableBean.getComeDate() );
            applytable.put("appliedAmount",applytableBean.getApplyMoney());
            applytable.put("bank",applytableBean.getBank());
            applytable.put("bankCard",applytableBean.getBankCard());
            applytable.put("userPhone",applytableBean.getPhoneNum());
            applytable.put("applyStatus",applytableBean.getApplyStatus());
            applytable.put("batch",applytableBean.getBatch());



            list.add(applytable);
        }
         result.put("code","0");
        result.put("msg","");
        result.put("count",auditService.findByPagesCount(applytype,status,batch,idCard));
        result.put("data",list);





        return result;
    }


    @RequestMapping({"/changstatus"})
    @ResponseBody
    public RequestResult changestatus(@RequestBody String requestBody){
        RequestResult result =  new RequestResult();
        result.setStatus(-1);
        JSONObject jsonObject=JSONObject.parseObject(requestBody);
        String status=jsonObject.getString("status");
        String idNUm = jsonObject.getString("idCard");

        log.info("changestatus "+idNUm+": "+status);


        ApplytableBean applytableBean = applyService.findByIDnum(idNUm);

       applytableBean.setApplyStatus(status);


       applyService.saveOrupdate(applytableBean);

        result.setData("");
        result.setStatus(0);




        return result;

    }

    @RequestMapping({"/getauditcomment"})
    @ResponseBody
    public RequestResult getAuditComment(@RequestParam("idCard") String idCard){
        log.info("getAuditComment");
        RequestResult result =  new RequestResult();


        /*JSONObject jsonObject=JSONObject.parseObject(requestBody);
        String applytype = jsonObject.getString("applicationCategory");
        String status = jsonObject.getString("status");
        int page=jsonObject.getInteger("page");
        int limit = jsonObject.getInteger("limit");
*/


        List<AuditcommentBean> auditcommentBeans = auditService.getAuditComment(idCard);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (AuditcommentBean auditcommentBean : auditcommentBeans) {
            Map<String, Object> applytable = new HashMap<String, Object>();

            applytable.put("idCard",auditcommentBean.getIdNum() );
            applytable.put("auditcomment",auditcommentBean.getAuditcomment() );
            applytable.put("audittime",auditcommentBean.getAudittime() );
            list.add(applytable);
        }

        result.setData(list);
        result.setStatus(0);






        return result;
    }




}
