package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.OperationBean;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.ArchivesService;
import com.zj.rcbt.spring.service.OperationService;
import com.zj.rcbt.spring.service.VerifyService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
@RequestMapping("/archives")
public class ArchivesController {

    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    ArchivesService archivesService;
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
    public Map<String, Object> getArchives(@RequestParam("idCard") String idCard,@RequestParam("status") String status,@RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("limit") int limit) {

        log.info("getArchives  "+idCard);
        List<ArchivesBean> archivesBeanList = archivesService.findByPages( idCard, status,name,(page - 1) * limit, limit);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ArchivesBean archivesBean : archivesBeanList) {
            Map<String, Object> so = new HashMap<String, Object>();
            so.put("userName",archivesBean.getUserName());
            so.put("idCard",archivesBean.getIdNum());
            so.put("status",archivesBean.getInzhuji());
            list.add(so);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("data", list);
        data.put("count", archivesService.findByPagesCount(idCard,status,name));
        data.put("code", "0");
        data.put("msg", "");
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
       data.put("token",token);




        return data;
    }


    @RequestMapping({"/update"})
    @ResponseBody
    public RequestResult updatearchives(@RequestBody String requestBody) {
        log.info("updatearchives");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String idnum = jsonObject.getString("idCard");
        String inzhuji = jsonObject.getString("inzhuji");
        String user_name = jsonObject.getString("username");

        ArchivesBean archivesBean = archivesService.findByIDnum(idnum);
        if (archivesBean==null){
          archivesBean = new ArchivesBean();
          archivesBean.setIdNum(idnum);
          archivesBean.setStatus(Constants.socialsecurity_new);
        }

        archivesBean.setInzhuji(inzhuji);
        archivesBean.setUserName(user_name);
//        archivesBean.setStatus(Constants.socialsecurity_new);
        archivesService.saveOrupdate(archivesBean);
        ApplytableBean applytableBean = applyService.findByIDnum(idnum);
        verifyService.updateisAchive(applytableBean);

        OperationBean operationBean= new OperationBean();
        operationBean.setUserid("admin");
        operationBean.setOperation("updatearchives: "+"@idnum:"+idnum+"@inzhuji:"+inzhuji);
        operationService.save(operationBean);

        RequestResult result = new RequestResult();
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);



        result.setStatus(0);
        return result;



    }

}
