package com.zj.rcbt.common.utils;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUtils {
    private static Logger log =LogManager.getLogger(FileUtils.class.getName());

    public static void moveFile(String srcfile, String destDirpath, HttpServletRequest request) throws IOException {
        String filename=srcfile.substring(srcfile.lastIndexOf("/")+1);
        log.info("filename :"+filename);
        File srcFile = new File(request.getSession().getServletContext().getRealPath("")+"tempfile",filename);
        log.info("srcfile:"+srcFile.getAbsolutePath());
        if (!srcFile.exists()){
            return ;
        }

        File destfile = new File(request.getSession().getServletContext().getRealPath("")+destDirpath,filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!destfile.getParentFile().exists()) {
            destfile.getParentFile().mkdirs();
        }

        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"+destDirpath;
          if (destfile.exists()) {
              org.apache.commons.io.FileUtils.deleteQuietly(destfile);
          }
        org.apache.commons.io.FileUtils.moveFile(srcFile, destfile);
        log.info(""+basePath + "/" + filename);

    }

    public  static String getdestUrl(String srcfile, String destDirpath, HttpServletRequest request){
        String filename=srcfile.substring(srcfile.lastIndexOf("/")+1);
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"+destDirpath;
        return basePath + "/" + filename;
    }


}
