package com.zj.rcbt.common.utils;

import java.util.HashMap;

public final class Constants {

    public final static String applystatus_pass="0";//复核通过
    public final static String applystatus_first="1";//初次验证通过（学信网信息）
    public final static String applystatus_second="2";//初次用户社保信息档案更新验证通过（等待复核）
    public final static String applystatus_deny="3";//初次验证未通过
    public final static String applystatus_wait="4";//复核未通过，等待修改后再次复核
    public final static String applystatus_over="-1";//复核未通过
    public final static String applystatus_init="5";

    public final static String socialsecurity_new="0";//新导入社保未做处理
    public final static String socialsecurity_old="1";


    public final static String allowance_over="1";//引进时间已经满三年


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

}
