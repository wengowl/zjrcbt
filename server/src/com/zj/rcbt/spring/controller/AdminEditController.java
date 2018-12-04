package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.AllowancehistoryBean;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.OperationBean;
import com.zj.rcbt.spring.service.AllowanceService;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.OperationService;
import com.zj.rcbt.spring.service.VerifyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminedit")
public class AdminEditController {


    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    AllowanceService allowanceService;
    @Autowired
    ApplyService applyService;
    @Autowired
    VerifyService verifyService;

    @Autowired
    OperationService operationService;
    @Autowired
    HttpServletRequest request;



    @RequestMapping({"/userinfoupdate"})
    @ResponseBody
    public RequestResult updateallowance(@RequestBody String requestBody) {
        log.info("update user info");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String user_id = jsonObject.getString("userid");
        String idnum = jsonObject.getString("idCard");
        String phone = jsonObject.getString("phone");
        String bank =  jsonObject.getString("bank");
        String bankcard =  jsonObject.getString("bankcard");
        ApplytableBean applytableBean = applyService.findByIDnum(idnum);


        List<AllowanceBean> allowanceBeans = allowanceService.findByidnum(idnum);

        if (applytableBean!=null){
            if (phone!=null&&!phone.equals("")) {
                applytableBean.setPhoneNum(phone);
            }

            if (bank!=null&&!bank.equals("")) {
                applytableBean.setBank(bank);
            }

            if (bankcard!=null&&!bankcard.equals("")) {
                applytableBean.setBankCard(bankcard);
            }

            applyService.saveOrupdate(applytableBean);
        }

        if (allowanceBeans.size()>0){
            AllowanceBean allowanceBean = allowanceBeans.get(0);
            if (phone!=null&&!phone.equals("")) {
                allowanceBean.setPhone(phone);
            }

            if (bank!=null&&!bank.equals("")) {
                allowanceBean.setBank(bank);
            }

            if (bankcard!=null&&!bankcard.equals("")) {
                allowanceBean.setBankCard(bankcard);
            }
            allowanceService.update(allowanceBean);
        }

        RequestResult result = new RequestResult();

        OperationBean operationBean= new OperationBean();
        operationBean.setUserid(user_id);
        operationBean.setOperation( user_id+" userinfoupdate: "+"@idnum:"+idnum+"@phone:"+phone+"@bank:"+bank+"@bankcard:"+bankcard);
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);
        result.setData(resultData);
        operationService.save(operationBean);


        result.setStatus(0);
        return result;



    }


    @RequestMapping({"/updateallowancehistory"})
    @ResponseBody
    public RequestResult updateallowancehistory(@RequestBody String requestBody) {
        log.info("update allowancehistory");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String user_id = jsonObject.getString("userid");
        String idnum = jsonObject.getString("idCard");
        String phone = jsonObject.getString("phone");
        String bank =  jsonObject.getString("bank");
        String bankcard =  jsonObject.getString("bankcard");
       String offertime =jsonObject.getString("offertime");


        List<AllowancehistoryBean> allowanceBeans = allowanceService.findhistoryByPorperty(offertime,"",idnum);


        if (allowanceBeans.size()>0){
            AllowancehistoryBean allowanceBean = allowanceBeans.get(0);
            if (phone!=null&&!phone.equals("")) {
                allowanceBean.setPhone(phone);
            }

            if (bank!=null&&!bank.equals("")) {
                allowanceBean.setBank(bank);
            }

            if (bankcard!=null&&!bankcard.equals("")) {
                allowanceBean.setBankCard(bankcard);
            }
            allowanceService.updateHistory(allowanceBean);
        }

        RequestResult result = new RequestResult();

        OperationBean operationBean= new OperationBean();
        operationBean.setUserid(user_id);
        operationBean.setOperation("userinfoupdate: "+"@idnum:"+idnum+"@phone"+phone+"@bank:"+bank+"@bankcard:"+bankcard+"@offertime:"+offertime);

        operationService.save(operationBean);
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"));
        resultData.put("token",token);
        result.setData(resultData);

        result.setStatus(0);
        return result;



    }


}
