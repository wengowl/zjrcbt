package com.zj.rcbt.common.utils;

import com.zj.rcbt.hibernate.model.ArchivesBean;
import com.zj.rcbt.hibernate.model.SocialsecurityBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelRowReader {
    public void importSocialSecurity(Sheet sheet, List<SocialsecurityBean> socialsecurityBeans){
        if (sheet==null)
            return;
        int rowCount=sheet.getPhysicalNumberOfRows();
        if (rowCount<2){
            return;
        }

        for (int i=1; i<rowCount;i++){
            Row row = sheet.getRow(i);
            SocialsecurityBean socialsecurityBean = new SocialsecurityBean();
            socialsecurityBean.setUserName(getCellValue(row,0));

            socialsecurityBean.setIdNum(getCellValue(row,1));
            if (socialsecurityBean.getIdNum().equals("")||socialsecurityBean.getIdNum()==null)
                continue;
            socialsecurityBean.setBeginTime(getCellValue(row,2));
            socialsecurityBean.setLastTime(getCellValue(row,3));
            String monthes=getCellValue(row,4);
            if (monthes.equals("")){
                monthes="0";
            }
            socialsecurityBean.setCompany(getCellValue(row,5));
            socialsecurityBean.setMonthes(new Integer(monthes));
            socialsecurityBean.setStatus(Constants.socialsecurity_new);
            socialsecurityBeans.add(socialsecurityBean);

        }
    }

    public void importArchives(Sheet sheet, List<ArchivesBean> archivesBeanList){
        if (sheet==null)
            return;
        int rowCount=sheet.getPhysicalNumberOfRows();
        if (rowCount<2){
            return;
        }

        for (int i=1; i<rowCount;i++){
            Row row = sheet.getRow(i);
            int columnNo=-1;
            ArchivesBean archivesBean = new ArchivesBean();
            archivesBean.setUserName(getCellValueOrNull(row,++columnNo));
            archivesBean.setIdNum(getCellValueOrNull(row,++columnNo));
            if (archivesBean.getIdNum().equals("")||archivesBean.getIdNum()==null)
                continue;
            archivesBean.setStatus(Constants.socialsecurity_new);
            archivesBean.setInzhuji(getCellValueOrNull(row,++columnNo).trim());
            archivesBeanList.add(archivesBean);

        }
    }


    public String getCellValue(Row row, Integer columnNo)
    {
        String value = getCellValueOrNull(row,  columnNo);
        if (StringUtils.isBlank(value)) {

            return "";
        }

        return value;
    }


    public String getCellValueOrNull(Row row,  Integer columnNo)
    {
        //System.out.println("----------" + getPositionStr(rowNo, columnNo));
        String cellValue = "";
        Cell cell = row.getCell(columnNo);
        if (cell == null)
            return "";
        System.out.print(cell.getCellType());
        switch (cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING:
                cellValue = cell.getRichStringCellValue().getString().trim();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                double value = cell.getNumericCellValue();
                CellStyle style = cell.getCellStyle();

                String temp = style.getDataFormatString();
                // 单元格设置成常规
                if (temp.equals("General"))
                {
                    DecimalFormat format = new DecimalFormat();
                    format.applyPattern("#");
                    cellValue = format.format(value);
                } else
                {
                    short format = cell.getCellStyle().getDataFormat();
                    SimpleDateFormat sdf = null;
                    if (format == 14 || format == 31 || format == 57 || format == 58)
                    {
                        // 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    } else if (format == 20 || format == 32)
                    {
                        // 时间
                        sdf = new SimpleDateFormat("HH:mm");
                    }
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    cellValue = sdf.format(date);
                }

                break;

            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue()).trim();
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                cellValue = "";
        }

        System.out.println("  "+cellValue);
        return cellValue;
    }

    public static void main(String args[]){
        ExcelReader excelReader= new ExcelReader();
        List<SocialsecurityBean> socialsecurityBeanList = new ArrayList<>();
        List<ArchivesBean> archivesBeanList = new ArrayList<>();
        File file = new File("d:\\社保1.xls");
        excelReader.readFile(file,"1",archivesBeanList,socialsecurityBeanList);
    }




}
