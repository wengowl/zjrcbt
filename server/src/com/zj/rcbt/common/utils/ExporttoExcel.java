package com.zj.rcbt.common.utils;

import com.zj.rcbt.hibernate.model.AllowanceBean;
import com.zj.rcbt.hibernate.model.AllowancehistoryBean;
import com.zj.rcbt.hibernate.model.ApplytableBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExporttoExcel {

    public void export(List<AllowanceBean> allowanceBeanList,File exportFile) throws IOException {
        /*Workbook wb = null;
        FileInputStream is = null;

        try {
            is = new FileInputStream(exportFile);
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }*/



        HSSFWorkbook wb = new HSSFWorkbook();

        Sheet sheet =wb.createSheet("补贴汇总");

        exportAlllowance(allowanceBeanList,sheet);





        FileOutputStream out = null;
        try {
            out = new FileOutputStream(exportFile);
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }




    }


    public void exportHistory(List<AllowancehistoryBean> allowanceBeanList,File exportFile) throws IOException {
      /*  Workbook wb = null;
        FileInputStream is = null;

        try {
            is = new FileInputStream(exportFile);
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }*/

        HSSFWorkbook wb = new HSSFWorkbook();

        Sheet sheet =wb.createSheet("补贴记录总");
        List<AllowancehistoryBean> allowanceBeanListzufangnew =new ArrayList<>();
        List<AllowancehistoryBean> allowanceBeanListzufangold =new ArrayList<>();
        List<AllowancehistoryBean> allowanceBeanListshenghuonew =new ArrayList<>();
        List<AllowancehistoryBean> allowanceBeanListshenghuonew18 =new ArrayList<>();
        List<AllowancehistoryBean> allowanceBeanListshenghuoold =new ArrayList<>();
        List<AllowancehistoryBean> allowanceBeanListxingzhengnew18 =new ArrayList<>();
        exportAlllowancehistory(allowanceBeanList,sheet,allowanceBeanListzufangnew,allowanceBeanListzufangold,allowanceBeanListshenghuonew,allowanceBeanListshenghuonew18,allowanceBeanListxingzhengnew18,allowanceBeanListshenghuoold);

        Sheet sheet1 =wb.createSheet("新申报租房补贴");
        Sheet sheet2 =wb.createSheet("历年申报的租房补贴");
        Sheet sheet0 =wb.createSheet("新申报生活津贴(18年新政)");
        Sheet sheet5 =wb.createSheet("新申报生活津贴(18年新政行政事业单位)");
        Sheet sheet3 =wb.createSheet("新申报生活津贴");
        Sheet sheet4 =wb.createSheet("历年申报的生活津贴");
        exportAlllowancehistoryzufang(allowanceBeanListzufangnew,sheet1);
        exportAlllowancehistoryzufang(allowanceBeanListzufangold,sheet2);
        exportAlllowancehistoryshenghuo(allowanceBeanListshenghuonew,sheet3);
        exportAlllowancehistoryshenghuo(allowanceBeanListshenghuoold,sheet4);
        exportAlllowancehistoryshenghuo(allowanceBeanListshenghuonew18,sheet0);
        exportAlllowancehistoryshenghuo(allowanceBeanListxingzhengnew18,sheet5);




        FileOutputStream out = null;
        try {
            out = new FileOutputStream(exportFile);
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } finally {
            if (out != null) {
                out.close();
            }
        }




    }

    public void exportAlllowance(List<AllowanceBean> allowanceBeans,Sheet sheet){
        int rowNo =-1;
        Row row1 = sheet.createRow(++rowNo);
        int column1 =-1;
        row1.createCell(++column1).setCellValue("单位");
        row1.createCell(++column1).setCellValue("姓名");
        row1.createCell(++column1).setCellValue("身份证号");
        row1.createCell(++column1).setCellValue("起始发放月份");
        row1.createCell(++column1).setCellValue("最新发放月份");
        row1.createCell(++column1).setCellValue("已发放金额");
        row1.createCell(++column1).setCellValue("最新一次发放金额");
        row1.createCell(++column1).setCellValue("总共发放月数");
        row1.createCell(++column1).setCellValue("补贴金额（元/月）");


        row1.createCell(++column1).setCellValue("银行");
        row1.createCell(++column1).setCellValue("银行卡号");
        row1.createCell(++column1).setCellValue("联系电话");
        row1.createCell(++column1).setCellValue("备注");
        row1.createCell(++column1).setCellValue("申请时间");
        row1.createCell(++column1).setCellValue("毕业时间");
        row1.createCell(++column1).setCellValue("学历");
        row1.createCell(++column1).setCellValue("申请类别");
        row1.createCell(++column1).setCellValue("人才类别");

        row1.createCell(++column1).setCellValue("更新时间");



        for (AllowanceBean allowanceBean:allowanceBeans){
            Row row = sheet.createRow(++rowNo);

            int column =-1;
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getCompany()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getName()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getIdNum()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBeginTime()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getLastTime()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getSumMoney()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getLastMoney()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getMonthes()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getAllowancetype()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBank()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBankCard()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getPhone()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getShebao()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBatch()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getGraduatetime()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getEducation()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getApplyType()));
            boolean flag = true;
            int a=-1;
            try {
                a = Integer.parseInt(allowanceBean.getRcType());
            }catch (Exception e){
                flag=false;
            }
            if (!flag && a < 7) {
                row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getRcType()));
            }else {
                row.createCell(++column).setCellValue(StringUtils.checkNULL(Constants.type_rc.get(a)));
            }
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getUpdatetime()));
        }

    }

    public void exportAlllowancehistory(List<AllowancehistoryBean> allowanceBeans, Sheet sheet,  List<AllowancehistoryBean> allowanceBeanListzufangnew,  List<AllowancehistoryBean> allowanceBeanListzufangold,  List<AllowancehistoryBean> allowanceBeanListshenghuonew, List<AllowancehistoryBean> allowanceBeanListshenghuonew18,List<AllowancehistoryBean> allowanceBeanListxingzhengnew18 , List<AllowancehistoryBean> allowanceBeanListshenghuoold){
        int rowNo =-1;
        Row row1 = sheet.createRow(++rowNo);
        int column1 =-1;
        row1.createCell(++column1).setCellValue("单位");
        row1.createCell(++column1).setCellValue("姓名");
        row1.createCell(++column1).setCellValue("身份证号");
       row1.createCell(++column1).setCellValue("结算时间");
        row1.createCell(++column1).setCellValue("发放金额");
        row1.createCell(++column1).setCellValue("补贴金额（元/月）");
        row1.createCell(++column1).setCellValue("社保是否断缴");
        row1.createCell(++column1).setCellValue("银行");
        row1.createCell(++column1).setCellValue("银行卡号");
        row1.createCell(++column1).setCellValue("联系电话");
        row1.createCell(++column1).setCellValue("申请时间");
        row1.createCell(++column1).setCellValue("其他");
        row1.createCell(++column1).setCellValue("毕业时间");
        row1.createCell(++column1).setCellValue("学历");
        row1.createCell(++column1).setCellValue("引进时间");
        row1.createCell(++column1).setCellValue("申请类别");
        row1.createCell(++column1).setCellValue("人才类别");

        for (AllowancehistoryBean allowanceBean:allowanceBeans){
            Row row = sheet.createRow(++rowNo);

            int column =-1;
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getCompany()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getName()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getIdNum()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getOfferTime()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getOfferMoney()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getAllowancetype()));

            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getShebao()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBank()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBankCard()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getPhone()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBatch()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getComment()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getGraduatetime()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getEducation()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getComedate()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getApplyType()));

            boolean flag = true;
            int a=-1;
            try {
                a = Integer.parseInt(allowanceBean.getRcType());
            }catch (Exception e){
                flag=false;
            }
            if (!flag && a < 7) {
                row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getRcType()));
            }else {
                row.createCell(++column).setCellValue(StringUtils.checkNULL(Constants.type_rc.get(a)));
                row.createCell(++column).setCellValue("2018年新政");

            }
            if (allowanceBean.getBatch().equals(DateUtil.getLastMonth()) && allowanceBean.getOfferMoney()!=0){
                if (allowanceBean.getApplyType().trim().equals("租房补贴")){
                    allowanceBeanListzufangnew.add(allowanceBean);
                }
                if (allowanceBean.getApplyType().trim().equals("生活津贴")){
                    if (!flag && a < 7) {
                        allowanceBeanListshenghuonew.add(allowanceBean);
                    }else if (flag && a>11) {
                        allowanceBeanListxingzhengnew18.add(allowanceBean);
                    }else {
                        allowanceBeanListshenghuonew18.add(allowanceBean);
                    }
                }
            }

            if (!allowanceBean.getBatch().equals(DateUtil.getLastMonth()) && allowanceBean.getOfferMoney()!=0){
                if (allowanceBean.getApplyType().trim().equals("租房补贴")){
                    allowanceBeanListzufangold.add(allowanceBean);
                }
                if (allowanceBean.getApplyType().trim().equals("生活津贴")){
                    allowanceBeanListshenghuoold.add(allowanceBean);
                }
            }
        }

    }
    public void exportAlllowancehistoryzufang(List<AllowancehistoryBean> allowanceBeans, Sheet sheet){
        int rowNo =-1;
        Row row1 = sheet.createRow(++rowNo);
        int column1 =-1;
        row1.createCell(++column1).setCellValue("序号");
        row1.createCell(++column1).setCellValue("姓名");
        row1.createCell(++column1).setCellValue("单位");
        row1.createCell(++column1).setCellValue("身份证");
        row1.createCell(++column1).setCellValue("学历");
        row1.createCell(++column1).setCellValue("引进时间");
        row1.createCell(++column1).setCellValue("补贴金额");
        row1.createCell(++column1).setCellValue("开户行");
        row1.createCell(++column1).setCellValue("银行卡号");
        row1.createCell(++column1).setCellValue("联系电话");
        row1.createCell(++column1).setCellValue("申请时间");

        int summoney=0;
        int xuhao=0;
        Row row = sheet.createRow(++rowNo);
        for (AllowancehistoryBean allowanceBean:allowanceBeans){
            int column =-1;

            row.createCell(++column).setCellValue(++xuhao);
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getName()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getCompany()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getIdNum()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(DateUtil.getYear(allowanceBean.getGraduatetime())+allowanceBean.getEducation()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getComedate()));

            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getOfferMoney()));
            summoney=summoney+allowanceBean.getOfferMoney();
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBank()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBankCard()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getPhone()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBatch()));
            row = sheet.createRow(++rowNo);
        }
       row.createCell(0) .setCellValue("补贴人数："+allowanceBeans.size());
        row.createCell(2) .setCellValue("补贴金额："+summoney);

    }
    public void exportAlllowancehistoryshenghuo(List<AllowancehistoryBean> allowanceBeans, Sheet sheet1){
        int rowNo =-1;
        Row row1 = sheet1.createRow(++rowNo);
        int column1 =-1;
        row1.createCell(++column1).setCellValue("序号");
        row1.createCell(++column1).setCellValue("姓名");
        row1.createCell(++column1).setCellValue("单位");
        row1.createCell(++column1).setCellValue("身份证");
        row1.createCell(++column1).setCellValue("人才类别");
        row1.createCell(++column1).setCellValue("引进时间");
        row1.createCell(++column1).setCellValue("补贴金额");
        row1.createCell(++column1).setCellValue("开户行");
        row1.createCell(++column1).setCellValue("银行卡号");
        row1.createCell(++column1).setCellValue("联系电话");
        row1.createCell(++column1).setCellValue("申请时间");

        int summoney=0;
        Row row = sheet1.createRow(++rowNo);
        int xuhao=0;
        for (AllowancehistoryBean allowanceBean:allowanceBeans){
            int column =-1;

            row.createCell(++column).setCellValue(++xuhao);
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getName()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getCompany()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getIdNum()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getRcType()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getComedate()));

            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getOfferMoney()));
            summoney=summoney+allowanceBean.getOfferMoney();
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBank()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBankCard()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getPhone()));
            row.createCell(++column).setCellValue(StringUtils.checkNULL(allowanceBean.getBatch()));
            row = sheet1.createRow(++rowNo);
        }
        row.createCell(0) .setCellValue("补贴人数："+allowanceBeans.size());
        row.createCell(2) .setCellValue("补贴金额："+summoney);

    }







    public void exportIdnums(List<ApplytableBean> idnums1, List<AllowanceBean> idnums2, File exportFile,String type) throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();

        Sheet sheet=null;
        Sheet sheet1=null;
        if (type.equals("0")) {

            sheet = wb.createSheet("导社保所需身份证");
            sheet1 = wb.createSheet("行政事业编导社保所需身份证");



            int rowNo =-1;
            Row row1 = sheet.createRow(++rowNo);
            int column1 =-1;
            row1.createCell(++column1).setCellValue("姓名");
            row1.createCell(++column1).setCellValue("身份证号");
            row1.createCell(++column1).setCellValue("毕业时间");
            row1.createCell(++column1).setCellValue("引进时间");
            row1.createCell(++column1).setCellValue("申请时间");
            row1.createCell(++column1).setCellValue("申请类别（0：租房补贴 1：生活津贴）");
//        row1.createCell(++column1).setCellValue("政策类别（0：旧政 1：新政）");
//        row1.createCell(++column1).setCellValue("是否行政事业编(0:否，1：是)");
            int rowNo1 =-1;
            Row row2 = sheet1.createRow(++rowNo1);
            int column2 =-1;
            row2.createCell(++column2).setCellValue("姓名");
            row2.createCell(++column2).setCellValue("身份证号");
            row2.createCell(++column2).setCellValue("毕业时间");
            row2.createCell(++column2).setCellValue("引进时间");
            row2.createCell(++column2).setCellValue("申请时间");
            row2.createCell(++column2).setCellValue("申请类别（0：租房补贴 1：生活津贴）");


            for (ApplytableBean  idnums:idnums1){
                Row row = null;

                boolean flag = true;
                int a=-1;
                try {
                    a = Integer.parseInt(idnums.getRcType());
                }catch (Exception e){
                    flag=false;
                }

                if (flag && a>11) {
//     行政事业编
                   row= sheet1.createRow(++rowNo1);
                }else {
                    row= sheet.createRow(++rowNo);
                }



                int column =-1;
                row.createCell(++column).setCellValue(idnums.getName());
                row.createCell(++column).setCellValue(idnums.getIdNum());
                row.createCell(++column).setCellValue(idnums.getGraduateDate());
                row.createCell(++column).setCellValue("");
                row.createCell(++column).setCellValue(idnums.getBatch());
                row.createCell(++column).setCellValue(idnums.getApplyType());




            }

            for (AllowanceBean  idnums:idnums2) {
                Row row = null;

                boolean flag = true;
                int a=-1;
                try {
                    a = Integer.parseInt(idnums.getRcType());
                }catch (Exception e){
                    flag=false;
                }

                if (flag && a>11) {
//     行政事业编
                    row= sheet1.createRow(++rowNo1);
                }else {
                    row= sheet.createRow(++rowNo);
                }

                int column = -1;
                row.createCell(++column).setCellValue(idnums.getName());
                row.createCell(++column).setCellValue(idnums.getIdNum());
                row.createCell(++column).setCellValue(idnums.getGraduatetime());
                row.createCell(++column).setCellValue(idnums.getBeginTime());
                row.createCell(++column).setCellValue(idnums.getBatch());
                row.createCell(++column).setCellValue(" ");//多了
            }


        }else if (type.equals("1")){
            sheet = wb.createSheet("导档案所需身份证");
            CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 3);
            sheet.addMergedRegion(cra);
            int rowNo =0;
            Row row0 = sheet.createRow(rowNo);
            row0.createCell(0).setCellValue(" 档案数据导入表");
            Row row1 = sheet.createRow(++rowNo);
            int column1 =-1;
            row1.createCell(++column1).setCellValue("序号");
            row1.createCell(++column1).setCellValue("身份证号");
            row1.createCell(++column1).setCellValue("姓名");

            row1.createCell(++column1).setCellValue("是否在档（Y/N）");
//            row1.createCell(++column1).setCellValue("是否行政事业编(0:否，1：是)");
            int i=0;

            for (ApplytableBean  idnums:idnums1){


                int column =-1;
                boolean flag = true;
                int a=-1;
                try {
                    a = Integer.parseInt(idnums.getRcType());
                }catch (Exception e){
                    flag=false;
                }

                if (flag && a>11) {
//     行政事业编不用档案
//               row.createCell(++column).setCellValue("1");
                }else {
                    Row row = sheet.createRow(++rowNo);
                    row.createCell(++column).setCellValue(++i);
                    row.createCell(++column).setCellValue(idnums.getIdNum());
                    row.createCell(++column).setCellValue(idnums.getName());
                    row.createCell(++column).setCellValue("");
                }



            }

            for (AllowanceBean  idnums:idnums2){
                Row row = sheet.createRow(++rowNo);
                int column =-1;

                boolean flag = true;
                int a=-1;
                try {
                    a = Integer.parseInt(idnums.getRcType());
                }catch (Exception e){
                    flag=false;
                }

                if (flag && a>11) {

                }else {
                    row.createCell(++column).setCellValue(++i);
                    row.createCell(++column).setCellValue(idnums.getIdNum());
                    row.createCell(++column).setCellValue(idnums.getName());
                    row.createCell(++column).setCellValue("");
                }

            }
        }





        FileOutputStream out = null;
        try {
            out = new FileOutputStream(exportFile);
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }


    }


}
