package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zj.rcbt.common.utils.Constants;
import com.zj.rcbt.common.utils.ExcelReader;
import com.zj.rcbt.common.utils.JWTUtils;
import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import com.zj.rcbt.spring.service.ApplyService;
import com.zj.rcbt.spring.service.ImportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/import")
public class ImportExcelController {

    private Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private ImportService importService;

    @Autowired
    private ApplyService applyService;

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public RequestResult importExcel(HttpServletRequest request,
                                @RequestParam("file") MultipartFile file,@RequestParam("type") String type) throws Exception {

        RequestResult result = new RequestResult();
        String filename = "";
        String originalname=file.getOriginalFilename();
        Map<String,Object> json= new HashMap<String, Object>();
        filename = filename+originalname;

        log.info("import Excel"+filename+"  type:"+type);
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);
        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("")+"importfile";
            //上传文件名
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            log.info(filepath);

            file.transferTo(filepath);

            List<SocialsecurityBean> socialsecurityBeanList = new ArrayList<>();
            List<ArchivesBean> archivesBeanList = new ArrayList<>();
            ExcelReader reader = new ExcelReader();


            reader.readFile(filepath,type,archivesBeanList,socialsecurityBeanList);



            if (archivesBeanList.size()>0){
                importService.saveArchives(archivesBeanList);


            }

            if (socialsecurityBeanList.size()>0){
                importService.saveSocialsecurity(socialsecurityBeanList);

            }






            result.setStatus(0);
            return result;
        } else {
            result.setStatus(-1);
            result.setErrorMsg("please choose the file");
            return result;
        }

    }



    @RequestMapping(value="/uploadnew",method=RequestMethod.POST)
    public void importExcelnew(HttpServletRequest request,HttpServletResponse response,
                                     @RequestParam("file") MultipartFile file,@RequestParam("type") String type) throws Exception {

        RequestResult result = new RequestResult();
        String filename = "";
        String originalname=file.getOriginalFilename();
        Map<String,Object> json= new HashMap<String, Object>();
        filename = filename+originalname;
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);


        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("")+"importfile";
            //上传文件名
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            log.info(filepath);

            file.transferTo(filepath);

            List<SocialsecurityBean> socialsecurityBeanList = new ArrayList<>();
            List<ArchivesBean> archivesBeanList = new ArrayList<>();
            ExcelReader reader = new ExcelReader();


            reader.readFile(filepath,type,archivesBeanList,socialsecurityBeanList);



            if (archivesBeanList.size()>0){
                importService.saveArchives(archivesBeanList);


            }

            if (socialsecurityBeanList.size()>0){
                importService.saveSocialsecurity(socialsecurityBeanList);

            }






            result.setStatus(0);
        } else {
            result.setStatus(-1);
            result.setErrorMsg("please choose the file");

        }


        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Charset", "utf-8");
        response.setHeader("Cache-Control", "no-cache");


        response.getWriter().print(JSON.toJSON(result));

    }

    @RequestMapping({"/init"})
    @ResponseBody
    public RequestResult initstatus(@RequestBody String requestBody,HttpServletRequest request){
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        String usertype = jsonObject.getString("userType");
        RequestResult result = new RequestResult();
        Map<String, Object> resultData = new HashMap();

        String token = JWTUtils.createToken(request.getHeader("idcard"),900000);
        resultData.put("token",token);
        result.setData(resultData);
        if (usertype.equals("1")) {
            applyService.updateinitStatus();
            result.setStatus(0);
            return result;
        }
        result.setErrorMsg("无权限");
        result.setStatus(-1);
        return result;
    }
}
