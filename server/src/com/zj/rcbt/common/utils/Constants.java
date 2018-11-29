package com.zj.rcbt.common.utils;

import java.util.HashMap;

public final class Constants {

    public final static String applystatus_pass="0";//复核通过
    public final static String applystatus_first="1";//初次验证通过（学信网信息）
    public final static String applystatus_second="2";//初次用户社保信息档案更新验证通过（等待复核）
    public final static String applystatus_deny="3";//初次验证未通过
    public final static String applystatus_wait="4";//复核未通过，等待修改后再次复核
    public final static String applystatus_over="-1";//历史复核未通过
    public final static String applystatus_init="5";
    public final static String applystatus_auditdeny="-3"; //复核未通过，不允许修改

    public final static String applystatus_re="-2";//重复申请

    public final static String socialsecurity_new="0";//新导入社保未做处理
    public final static String socialsecurity_old="1";


    public final static String allowance_over="1";//引进时间已经满三年

    public final static String rc_dazhuan = "0";//大专
    public final static String rc_benke = "1";//本科
    public final static String rc_shuoshi = "2";  //  硕士
    public final static String rc_fugao = "3";    //副高
    public final static String rc_gaojijishi  = "4";//高级技师
    public final static String rc_boshi = "5";//博士
    public final static String rc_zhenggao = "6";//正高

//    新高校毕业生
    public final static String rc_newdazhuan= "7";//大专毕业生
    public final static String rc_newbenke = "8";//其他高校本科生
    public final static String rc_topschoolbenke = "9";//“双一流”高校本科生
    public final static String rc_newshuoshifugao = "10";//硕士或副高职称、高级技师
    public final static String rc_newboshizhenggao = "11";//博士或正高职称
//    行政事业单位
    public final static String rc_xinzhengtopschoolshuoshi = "12";//全日制“双一流”高校硕士生
    public final static String rc_xingzengboshi  = "13";//全日制博士生










    //补贴类型，金额
    public final static String allowance_1="1";//600元
    public final static String allowance_2="2";//800
    public final static String allowance_3="3";//1000

    public final static HashMap<String, Integer> type_Money = new HashMap<String, Integer>();

    static
    {
        type_Money.put("1", 600);
        type_Money.put("2", 800);
        type_Money.put("3", 1000);

    }

    public final static HashMap<Integer, String> type_rc = new HashMap<Integer, String>();

    static
    {
        type_rc.put(7, "大专毕业生");
        type_rc.put(8, "其他高校本科生");
        type_rc.put(9, "“双一流”高校本科生");
        type_rc.put(10, "硕士、副高职称或高级技师");
        type_rc.put(11, "博士或正高职称");
        type_rc.put(12, "全日制“双一流”高校硕士生");
        type_rc.put(13, "全日制博士生");


    }

}
