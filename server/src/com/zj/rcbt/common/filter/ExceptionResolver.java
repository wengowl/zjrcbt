package com.zj.rcbt.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.zj.rcbt.common.utils.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;



public class ExceptionResolver extends
        SimpleMappingExceptionResolver {
    private Logger log = LogManager.getLogger(this.getClass().getName());



    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object arg2, Exception ex) {

        log.info("resolver ...");

        String viewName = determineViewName(ex, request);
        if (viewName != null) {// JSP格式返回  
            if (!(request.getHeader("accept").indexOf("application/json") > -1 || !(request.getHeader("accept").indexOf("text/*") > -1) ||(request
                    .getHeader("X-Requested-With") != null && request
                    .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
                // 如果不是异步请求  
                // Apply HTTP status code for error views, if specified.  
                // Only apply it if we're processing a top-level request.  
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            } else {// JSON格式返回  
                try {
                    PrintWriter writer = response.getWriter();
                    Map map=new HashMap();
                    map.put("status", 1);
                    map.put("msg", ex.getMessage());
                    ex.printStackTrace();
                    log.error(ex.getMessage());
                    log.error(ExceptionUtils.getStackTrace(ex));
                    writer.write(JSONUtils.toJSONString(map));
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(ExceptionUtils.getStackTrace(e));
                    log.error(e.getMessage());
                }
                return null;

            }
        } else {
            return null;
        }
    }

}  