package com.zj.rcbt.spring.controller;


import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.OperationBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.OperationService;
import com.zj.rcbt.spring.service.SocialsecurityService;
import com.zj.rcbt.spring.service.VerifyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/socialsecurity")
public class SocialsecurityController {

    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private SocialsecurityService socialsecurityService;

    @Autowired
    VerifyService verifyService;
    @Autowired
    ApplyService applyService;
    @Autowired
    OperationService operationService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping({"/get"})
    @ResponseBody
    public Map<String, Object> getSocialSecurity(@RequestParam("idCard") String idCard, @RequestParam("name") String name,@RequestParam("page") int page, @RequestParam("limit") int limit) {

        log.info("getSocialSecurity  "+idCard);
        List<SocialsecurityBean> socialsecurityBeanList = socialsecurityService.findByPages( idCard, name,(page - 1) * limit, limit);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (SocialsecurityBean socialsecurityBean : socialsecurityBeanList) {
            Map<String, Object> so = new HashMap<String, Object>();
            so.put("userName",socialsecurityBean.getUserName());
            so.put("idCard",socialsecurityBean.getIdNum());
            so.put("beginTime",socialsecurityBean.getBeginTime());
            so.put("lastTime",socialsecurityBean.getLastTime());
            so.put("monthes",socialsecurityBean.getMonthes());
            so.put("company",socialsecurityBean.getCompany());

            list.add(so);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("count", socialsecurityService.findByPagesCount(idCard,name));
        data.put("code", "0");
        data.put("msg", "");

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        data.put("token",token);




        return data;
    }
    @RequestMapping({"/update"})
    @ResponseBody
    public RequestResult updatesocialsecurity(@RequestBody String requestBody) {
        log.info("updatesocialsecurity");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String idnum = jsonObject.getString("idCard");
        String begintime = jsonObject.getString("begintime");
        int monthes = jsonObject.getIntValue("monthes");
        String lasttime = jsonObject.getString("lasttime");
        String companyname = jsonObject.getString("companyname");
        String user_name = jsonObject.getString("username");
        RequestResult result = new RequestResult();

        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);


        if (begintime.equals("")||lasttime.equals("")||user_name.equals("")){
            result.setStatus(-1);
            return result;
        }


        SocialsecurityBean socialsecurityBean = socialsecurityService.findByIDnum(idnum);
        if (socialsecurityBean==null){
            socialsecurityBean = new SocialsecurityBean();
            socialsecurityBean.setIdNum(idnum);
            socialsecurityBean.setStatus(Constants.socialsecurity_new);
        }

            socialsecurityBean.setBeginTime(begintime);
            socialsecurityBean.setMonthes(monthes);
            socialsecurityBean.setCompany(companyname);
            socialsecurityBean.setLastTime(lasttime);
            socialsecurityBean.setUserName(user_name);
//            socialsecurityBean.setStatus(Constants.socialsecurity_new);


            socialsecurityService.saveOrUpdate(socialsecurityBean);
        ApplytableBean applytableBean = applyService.findByIDnum(idnum);
        verifyService.updateSocialsecurity(applytableBean);

        OperationBean operationBean= new OperationBean();
        operationBean.setUserid("admin");
        operationBean.setOperation("updatesocialsecurity: "+socialsecurityBean.toString());
        operationService.save(operationBean);









        result.setStatus(0);


        return result;



    }



}
