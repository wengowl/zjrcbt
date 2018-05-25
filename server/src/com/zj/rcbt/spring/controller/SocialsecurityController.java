package com.zj.rcbt.spring.controller;


import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import com.zj.rcbt.spring.service.SocialsecurityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping({"/get"})
    @ResponseBody
    public Map<String, Object> getSocialSecurity(@RequestParam("idCard") String idCard, @RequestParam("page") int page, @RequestParam("limit") int limit) {

        log.info("getSocialSecurity  "+idCard);
        List<SocialsecurityBean> socialsecurityBeanList = socialsecurityService.findByPages( idCard, (page - 1) * limit, limit);

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
        data.put("count", socialsecurityService.findByPagesCount(idCard));
        data.put("code", "0");
        data.put("msg", "");



        return data;
    }




}
