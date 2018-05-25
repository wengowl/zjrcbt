package com.zj.rcbt.spring.controller;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file/")
public class FileUploadController {
    private static Logger log =LogManager.getLogger(FileUploadController.class.getName());

    @RequestMapping(value="/upload",method=RequestMethod.POST)
    @ResponseBody
    public RequestResult upload(HttpServletRequest request,
                         @RequestParam("file") MultipartFile file,@RequestParam("idCard") String idNUm,@RequestParam("pictureType") String pictureType) throws Exception {

        RequestResult result = new RequestResult();
        String filename = "";
        String originalname=file.getOriginalFilename();
        Map<String,Object> json= new HashMap<String, Object>();

        if (pictureType.equals("0")){
            filename=filename+idNUm+"_photo"+originalname.substring(originalname.lastIndexOf("."));
        }
        if (pictureType.equals("1")){
            filename=filename+idNUm+"_qrcode"+originalname.substring(originalname.lastIndexOf("."));
        }
        if (pictureType.equals("2")){
            filename=filename+idNUm+"_attchment"+originalname.substring(originalname.lastIndexOf("."));
        }

        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("")+"tempfile";
            //上传文件名
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            log.info(filepath);

            file.transferTo(filepath);
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/tempfile";
            json.put("fileUrl",basePath + "/" + filename);
            log.info("return url:"+basePath + "/" + filename);
            result.setData(json);
            result.setStatus(0);
            return result;
        } else {
            result.setStatus(-1);
            result.setErrorMsg("please choose the file");
            return result;
        }

    }
//ie客户端请求头 accept为非 application/json格式
    @RequestMapping(value="/uploadnew",method=RequestMethod.POST)
    @ResponseBody
    public void uploadnew(HttpServletRequest request,HttpServletResponse response,
                                @RequestParam("file") MultipartFile file,@RequestParam("idCard") String idNUm,@RequestParam("pictureType") String pictureType) throws Exception {

        log.info("uploadnew");
        RequestResult result = new RequestResult();
        String filename = "";
        String originalname=file.getOriginalFilename();
        Map<String,Object> json= new HashMap<String, Object>();

        if (pictureType.equals("0")){
            filename=filename+idNUm+"_photo"+originalname.substring(originalname.lastIndexOf("."));
        }
        if (pictureType.equals("1")){
            filename=filename+idNUm+"_qrcode"+originalname.substring(originalname.lastIndexOf("."));
        }
        if (pictureType.equals("2")){
            filename=filename+idNUm+"_attchment"+originalname.substring(originalname.lastIndexOf("."));
        }

        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("")+"tempfile";
            //上传文件名
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            log.info(filepath);

            file.transferTo(filepath);
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/tempfile";
            json.put("fileUrl",basePath + "/" + filename);
            log.info("return url:"+basePath + "/" + filename);
            result.setData(json);
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

    //ie客户端请求头 accept为非 application/json格式
    @RequestMapping(value="/uploadfile",method=RequestMethod.POST)
    @ResponseBody
    public void uploadfile(HttpServletRequest request,HttpServletResponse response,
                          @RequestParam("file") MultipartFile file,@RequestParam("usertype") String usertype) throws Exception {
        RequestResult result = new RequestResult();
        log.info("uploadfile "+file.getOriginalFilename());
        if (usertype.equals("3")) {



//        String filename = ""
        String originalname=file.getOriginalFilename();
        Map<String,Object> json= new HashMap<String, Object>();



        //如果文件不为空，写入上传路径
        if(!file.isEmpty()) {
            //上传文件路径
            String path = request.getSession().getServletContext().getRealPath("")+"fujian";
            //上传文件名
            File filepath = new File(path,originalname);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            log.info(filepath);

            file.transferTo(filepath);
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/fujian";
            json.put("fileUrl",basePath + "/" + originalname);
            log.info("return url:"+basePath + "/" +originalname);
            result.setData(json);
            result.setStatus(0);
        } else {
            result.setStatus(-1);
            result.setErrorMsg("please choose the file");

        }
        }else{
            result.setStatus(-1);
            result.setErrorMsg("无权限");
        }
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Charset", "utf-8");
        response.setHeader("Cache-Control", "no-cache");





        response.getWriter().print(JSON.toJSON(result));


    }
}
