package com.huami.merchant.util;

/**
 * Created by henry on 2018/1/17.
 */

public class AuditUtil {
    /**
     * 获取审核状态
     * @param state
     * @return
     */
    public static String getState(int checkTime,int state){
        switch (state) {
            case 1:
                return upCase(checkTime+1)+"待审";
            case 2:
                return upCase(checkTime)+"通过";
            case 3:
                return upCase(checkTime)+"不通过";
            default:
                return "数据错误";
        }
    }
    /**
     * 转化成大写
     * @param number
     * @return
     */
    public static String upCase(int number){
        switch (number) {
            case 0:
                return "一审";
            case 1:
                return "一审";
            case 2:
                return "二审";
            case 3:
                return "三审";
            case 4:
                return "四审";
            case 5:
                return "五审";
            case 6:
                return "六审";
            case 7:
                return "七审";
            case 8:
                return "八审";
            case 9:
                return "九审";
            default:
                return "N审";

        }
    }
}
