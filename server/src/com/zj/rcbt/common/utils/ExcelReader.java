package com.zj.rcbt.common.utils;

import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
 private Logger log = LogManager.getLogger(this.getClass());
    public void readFile(File file, String type, List<ArchivesBean> list , List<SocialsecurityBean> socialsecurityBeans){
        if (!file.exists())
            return;
        boolean isE2007 = false;
        //判断是否是excel2007格式
        if(file.getName().endsWith("xlsx")){
            isE2007 = true;
        }

        FileInputStream is=null;
        try {
            is = new FileInputStream(file);
            Workbook wb;
            if(isE2007){
                wb = new XSSFWorkbook(is);
            }else{
                wb = new HSSFWorkbook(is);
            }

            Sheet sheet=wb.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数

            if (rowCount<2){
                log.info("sheet is null");
            }

            ExcelRowReader excelRowReader = new ExcelRowReader();

            if (type.equals("0")){


                excelRowReader.importArchives(sheet,list);


            }
            if (type.equals("1")){
                excelRowReader.importSocialSecurity(sheet,socialsecurityBeans);
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
