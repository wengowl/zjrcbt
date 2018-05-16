package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.AllowancehistoryBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import com.zj.rcbt.spring.service.AllowanceService;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.VerifyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/allowance")
public class AllowanceController {

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    AllowanceService allowanceService;
    @Autowired
    ApplyService applyService;
    @Autowired
    VerifyService verifyService;

    @RequestMapping({"/gethistory"})
    @ResponseBody
    public Map<String, Object> getAllowanceHistory(@RequestParam("batch") String batch, @RequestParam("applicationCategory") String applicationCategory, @RequestParam("idCard") String idCard, @RequestParam("page") int page, @RequestParam("limit") int limit) {
//        JSONObject jsonObject = JSONObject.parseObject(requestBody);
//        String batch = jsonObject.getString("batch");
//        String applicationCategory = jsonObject.getString("applicationCategory");
//        String idCard = jsonObject.getString("idCard");
//        int page=jsonObject.getInteger("page");
//        int limit = jsonObject.getInteger("limit");
        log.info("getAllowanceHistory");
        List<AllowancehistoryBean> allowancehistoryBeanList = allowanceService.findhistoryByPages(batch, applicationCategory, idCard, batch,(page - 1) * limit, limit);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (AllowancehistoryBean allowancehistoryBean : allowancehistoryBeanList) {
            Map<String, Object> allowance = new HashMap<String, Object>();
            allowance.put("offertime", allowancehistoryBean.getOfferTime());
            allowance.put("idCard", allowancehistoryBean.getIdNum());
            allowance.put("appliCationCategory", allowancehistoryBean.getAllowancetype());
            allowance.put("moneyCurrent", allowancehistoryBean.getOfferMoney());
           allowance.put("comment",allowancehistoryBean.getComment());
            allowance.put("shebao", allowancehistoryBean.getShebao());
            allowance.put("bank", allowancehistoryBean.getBank());
            allowance.put("bankCard", allowancehistoryBean.getBankCard());
            allowance.put("phone", allowancehistoryBean.getPhone());
            allowance.put("name", allowancehistoryBean.getName());
            allowance.put("company", allowancehistoryBean.getCompany());
            allowance.put("batch", allowancehistoryBean.getBatch());

            list.add(allowance);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("count", allowanceService.getCounthistory(batch, applicationCategory, idCard,batch));
        data.put("code", "0");
        data.put("msg", "");

//        RequestResult result = new RequestResult();
//        result.setStatus(0);
//        result.setData(data);
        return data;
    }


    @RequestMapping({"/get"})
    @ResponseBody
    public Map<String, Object> getAllowance(@RequestParam("month") String month, @RequestParam("applicationCategory") String applicationCategory, @RequestParam("idCard") String idCard,@RequestParam("batch") String batch, @RequestParam("page") int page, @RequestParam("limit") int limit) {
//        JSONObject jsonObject = JSONObject.parseObject(requestBody);
//        String month = jsonObject.getString("month");
//        String applicationCategory = jsonObject.getString("applicationCategory");
//        String idCard = jsonObject.getString("idCard");
//        int page=jsonObject.getInteger("page");
//        int limit = jsonObject.getInteger("limit");
        log.info("getAllowance");
        List<AllowanceBean> allowanceBeanList = allowanceService.findByPages(month, applicationCategory, idCard, batch,(page - 1) * limit, limit);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (AllowanceBean allowanceBean : allowanceBeanList) {
            Map<String, Object> allowance = new HashMap<String, Object>();
            allowance.put("idCard", allowanceBean.getIdNum());
            allowance.put("appliCationCategory", allowanceBean.getAllowancetype());
            allowance.put("moneyCurrent", allowanceBean.getLastMoney());
            allowance.put("lasttime", allowanceBean.getLastTime());
            allowance.put("moneyAll", allowanceBean.getSumMoney());
            allowance.put("monthes",allowanceBean.getMonthes());
            allowance.put("shebao", allowanceBean.getShebao());
            allowance.put("bank", allowanceBean.getBank());
            allowance.put("bankCard", allowanceBean.getBankCard());
            allowance.put("phone", allowanceBean.getPhone());
            allowance.put("name", allowanceBean.getName());
            allowance.put("company", allowanceBean.getCompany());
            allowance.put("batch", allowanceBean.getBatch());
            list.add(allowance);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("count", allowanceService.getCount(month, applicationCategory, idCard,batch));
        data.put("code", "0");
        data.put("msg", "");


//        RequestResult result = new RequestResult();
//        result.setStatus(0);
//        result.setData(data);
        return data;
    }


    @RequestMapping({"/done"})
    @ResponseBody
    public RequestResult doneAllowance(@RequestBody String requestBody) {
        log.info("doneAllowance");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String usertype = jsonObject.getString("userType");
        RequestResult result = new RequestResult();
        if (usertype.equals("1")) {

            if (applyService.findBystatus(Constants.applystatus_second).size() > 0) {
                result.setErrorMsg("存在未复核的申请");
                result.setStatus(-1);

                return result;
            }
            if (applyService.findBystatus(Constants.applystatus_first).size() > 0) {
                result.setErrorMsg("存在还未验证社保和档案的申请，请导入社保信息与档案记录");
                result.setStatus(1);

                return result;
            }
            List<SocialsecurityBean> socialsecurityBeanList= verifyService.findBystatus(Constants.socialsecurity_new);
            if (socialsecurityBeanList.size()<1){
                result.setErrorMsg("请勿重复操作，已经根据当前社保数据做结算处理");
                result.setStatus(1);

                return result;
            }

            allowanceService.updateAllowance();

            result.setStatus(0);
            return result;
        }
        result.setErrorMsg("无权限");
        result.setStatus(-1);
        return result;


    }

    @RequestMapping({"/doneback"})
    @ResponseBody
    public RequestResult doneAllowanceBack(@RequestBody String requestBody) {
        log.info("doneAllowanceBack");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String usertype = jsonObject.getString("userType");
        RequestResult result = new RequestResult();
        if (usertype.equals("2")) {

            allowanceService.updatebackAllowance();

            result.setStatus(0);
            return result;
        }
        result.setErrorMsg("无权限");
        result.setStatus(-1);
        return result;


    }



}
