package com.zj.rcbt.spring.controller;

import com.zj.rcbt.common.utils.DateUtil;
import com.zj.rcbt.common.utils.ExporttoExcel;
import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.AllowancehistoryBean;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import com.zj.rcbt.spring.service.AllowanceService;
import com.zj.rcbt.spring.service.ApplyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/export")
public class ExcelExportController {

    private Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private AllowanceService allowanceService;
    @Autowired
    private ApplyService applyService;

    @RequestMapping({"/allowance"})
    @ResponseBody
    public RequestResult getAllowanceExcel(HttpServletRequest request, @RequestParam("month") String month, @RequestParam("applicationCategory") String applicationCategory, @RequestParam("idCard")String idCard){
        log.info("getAllowanceExcel");
        RequestResult result = new RequestResult();
        List<AllowanceBean> allowanceBeanList = allowanceService.findByPorperty(month,applicationCategory,idCard);
        ExporttoExcel exporttoExcel = new ExporttoExcel();
        String filename="Allowance.xls";
        String path = request.getSession().getServletContext().getRealPath("")+"export";
        //上传文件名
        File filepath = new File(path,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        try {
            exporttoExcel.export(allowanceBeanList,filepath);
        } catch (IOException e) {
            log.error(e.getMessage());
            result.setStatus(-1);
            result.setErrorMsg(e.getMessage());
            return result;
        }

        result.setStatus(0);
        Map<String,Object> json= new HashMap<String, Object>();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/export";
        json.put("fileUrl",basePath + "/" + filename);
        log.info("return url:"+basePath + "/" + filename);
        result.setData(json);


        return result;
    }
    @RequestMapping({"/allowancehistory"})
    @ResponseBody
    public RequestResult getAllowanceHistoryExcel(HttpServletRequest request,@RequestParam("month") String month, @RequestParam("applicationCategory") String applicationCategory, @RequestParam("idCard")String idCard){
        log.info("getAllowanceHistoryExcel");

        RequestResult result = new RequestResult();

        List<AllowancehistoryBean> allowanceBeanList = allowanceService.findhistoryByPorperty(month,applicationCategory,idCard);
        ExporttoExcel exporttoExcel = new ExporttoExcel();
        String filename="Allowancehistory.xls";
        String path = request.getSession().getServletContext().getRealPath("")+"export";
        //上传文件名
        File filepath = new File(path,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        try {
            exporttoExcel.exportHistory(allowanceBeanList,filepath);
        } catch (IOException e) {
            log.error(e.getMessage());
            result.setStatus(-1);
            result.setErrorMsg(e.getMessage());
            return result;
        }

        result.setStatus(0);
        Map<String,Object> json= new HashMap<String, Object>();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/export";
        json.put("fileUrl",basePath + "/" + filename);
        log.info("return url:"+basePath + "/" + filename);
        result.setData(json);


        return result;
    }


    @RequestMapping({"/idCard"})
    @ResponseBody
    public RequestResult getidCard(HttpServletRequest request,@RequestParam("type") String type,@RequestParam("all") String all){
        log.info("get idnums" +type +" "+all);

        RequestResult result = new RequestResult();

      List<AllowanceBean> idCardsallowance = new ArrayList<>();
        List<ApplytableBean> idCardapply = new ArrayList<>();
        if (all.equals("1")) {
//            全量
            idCardsallowance   =allowanceService.getIdnumsAllowance();
            idCardapply  =applyService.getIdnumsApply();
        }



        if (type.equals("0")&&all.equals("0")) {
//            增量社保
//            idCardsallowance   =allowanceService.getIdnumsAllowancenew();
            idCardapply  =applyService.getIdnumsApplynewshebao();
        }

        if (type.equals("1")&&all.equals("0")) {
//            增量档案
//            idCardsallowance   =allowanceService.getIdnumsAllowancenew();
            idCardapply  =applyService.getIdnumsApplynewdangan();
        }


        ExporttoExcel exporttoExcel = new ExporttoExcel();
        String filename="idCards.xls";
        String path = request.getSession().getServletContext().getRealPath("")+"export";
        //上传文件名
        File filepath = new File(path,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        try {
            exporttoExcel.exportIdnums(idCardapply,idCardsallowance,filepath,type);
        } catch (IOException e) {
            log.error(e.getMessage());
            result.setStatus(-1);
            result.setErrorMsg(e.getMessage());
            return result;
        }

        result.setStatus(0);
        Map<String,Object> json= new HashMap<String, Object>();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/export";
        json.put("fileUrl",basePath + "/" + filename);
        log.info("return url:"+basePath + "/" + filename);
        result.setData(json);


        return result;
    }

}
