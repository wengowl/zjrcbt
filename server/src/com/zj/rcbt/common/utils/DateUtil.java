package com.zj.rcbt.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private static Logger log = LogManager.getLogger(DateUtil.class.getName());
    public static String dateAddMonth(String str,int month) {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = null;
        try {
            dt = sdf.parse(str);
        } catch (ParseException e) {
            log.error(e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, month);// 日期加3个月
        // rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }
    public static int getMonthNum(String month1,String month2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(month1);
            date2 = sdf.parse(month2);
        } catch (ParseException e) {
            log.error(e.getMessage());
            log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        Calendar cal1=Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2=Calendar.getInstance();
        cal2.setTime(date2);
        return (cal2.get(1)-cal1.get(1))*12+(cal2.get(2)-cal1.get(2));

    }



    public static String getCurrentTime(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(day);
    }
    public static String getCurrentDate(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return  df.format(day);
    }
    public static String formatTime( Timestamp time){
        Date day=new Date(time.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(day);
    }
    public static String getYear(String date){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        Date ss = null;
        try {
            ss = format1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }



        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return  df.format(ss);
    }

    public static String getCurrentMonth(){
        Date day=new Date();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return  df.format(day);
    }

    public static String formatDate(String date,String format1,String format2){
        SimpleDateFormat sdf = new SimpleDateFormat(format1);
        SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
        Date dt = null;
        try {
            dt = sdf.parse(date);

        } catch (ParseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error(ExceptionUtils.getStackTrace(e));
        }
       return  sdf2.format(dt);

    }

    public static String  dateChange(String s){
        if (s.contains("-")||s.equals("")||s==null){
            return s;
        }else {

           return formatDate(s,"yyyyMM","yyyy-MM");


        }


    }

    public static String getLastMonth(){
       String month1=getCurrentMonth();
       String month2=getYear(month1)+"-06";
       String month3=dateAddMonth(month2,-6);
       int num = getMonthNum(month1,month2);
       if (num<0){
           return month2;
       }
       return month3;
    }


    public static void main(String[] args) {
        System.out.println(dateChange("201801"));
        System.out.println(dateChange("2018-01"));
        System.out.println(getLastMonth());
        System.out.println(getMonthNum("2015-07","2018-07"));
        System.out.println(getYear("2015-07"));
    }


}
