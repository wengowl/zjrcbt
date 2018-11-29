package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.hibernate.model.ApplytablecompareBean;
import com.zj.rcbt.spring.service.ApplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/apply")
public class ApplyController {
    private static  Logger log =LogManager.getLogger(ApplyController.class.getName());

    @Autowired
    private ApplyService applyService;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping({"/get"})
    @ResponseBody
    public RequestResult applyInfoGet(@RequestBody String requestBody){
       System.out.println("********"+requestBody);
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String idNum = jsonObject.getString("idCard");
        System.out.println("********idnum  "+idNum);
        log.info(idNum);
        ApplytableBean applytableBean = applyService.findByIDnum(idNum);
        RequestResult result=new RequestResult();
        if (applytableBean==null){
            result = RequestResult.statusCode(-1);

        }else {
           result = RequestResult.statusCode(0);
            result.setData(applyService.packApplyinfo(applytableBean));
        }
        return result;


    }
    @RequestMapping({"/getcompare"})
    @ResponseBody
    public RequestResult applycompareInfoGet(@RequestBody String requestBody){
        System.out.println("********"+requestBody);
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String idNum = jsonObject.getString("idCard");
        log.info(idNum);
        ApplytablecompareBean applytableBean = applyService.findCompareByIDnum(idNum);
        RequestResult result=new RequestResult();
        if (applytableBean==null){
            result = RequestResult.statusCode(-1);

        }else {
            result = RequestResult.statusCode(0);
            result.setData(applyService.packApplyCompareinfo(applytableBean));
        }

        return result;


    }

    @RequestMapping({"/update"})
    @ResponseBody
    public RequestResult applyInfupdate(@RequestBody String requestBody) throws IOException {
        log.info("applyInfupdate");
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        int iret = applyService.saveparserJsonObject(jsonObject);

        RequestResult result = new RequestResult();
        result.setStatus(iret);
       if (iret!=0){
           result.setErrorMsg("保存失败");
       }
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);

       return result;




    }
}






