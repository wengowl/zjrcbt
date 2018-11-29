package com.zj.rcbt.common.filter;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.zj.rcbt.common.utils.ExceptionUtils;
import com.zj.rcbt.common.utils.JWTUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;


public class TokenInterceptor implements HandlerInterceptor {
    private  Logger log = LogManager.getLogger(this.getClass().getName());

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Charset", "utf-8");
        response.setHeader("Cache-Control", "no-cache");
        String uri = request.getRequestURI().toString();
        log.info(uri);
        String token = request.getHeader("token");
        String userid = request.getHeader("idcard");
        Enumeration headerNames = request.getHeaderNames();
       /* while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            log.info(key+"     "+value);
        }*/
        log.info("token:"+token);
        log.info("idcard:"+userid);
//        log.info("host abc:"+request.getHeader("host"));

        //token不存在
        if (null != token) {
          boolean sec = JWTUtils.verifyToken(token,userid);
            if (sec) {
               return true;
            } else {
                PrintWriter writer = response.getWriter();
                Map map=new HashMap();
                map.put("status", -3);
                map.put("msg", "用户登录超时");
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                return false;
            }
        } else if (uri.indexOf("html")>=0||uri.indexOf("images") >= 0 || uri.indexOf("gif") >= 0 || uri.indexOf("jpg") >= 0||uri.endsWith("js")||uri.indexOf("css") >= 0){

            return true;

        }else {
            PrintWriter writer = response.getWriter();
            Map map=new HashMap();
            map.put("status", -1);
            map.put("msg", "请求错误");
            writer.write(JSONUtils.toJSONString(map));
            writer.flush();

            return false;
        }


    }


}

