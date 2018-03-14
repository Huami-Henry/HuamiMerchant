package com.huami.merchant.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Henry on 2017/11/27.
 */

public class TimeUtil {
    /**
     * 日期转时间戳
     * @param s
     * @return
     * @throws ParseException
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }
    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }
    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(){
        long s = System.currentTimeMillis();
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取当前日期+加零点时间
     * @return
     */
    public static String getCurrentDate(){
        long time = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format_time = simpleDateFormat.format(time);
        return format_time + " 00:00:00";
    }
    /**
     * 获取当月最后一天日期+加零点时间
     * @return
     */
    public static String getCurrentMonthDate(){
        Calendar c = Calendar.getInstance();//
        int month = c.get(Calendar.MONTH) + 1;// 获取当前月份
        int year = c.get(Calendar.YEAR);
        String day = "";
        String time=year+"-"+(month<10?"0"+month:month);
        String endTime = " 23:59:59";
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = "-31";
                break;
            case 2:
                if (year % 4 == 0) {
                    day = "-29";
                } else {
                    day = "-28";
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = "-30";
                break;
        }
        return time+day+endTime;
    }
    /**
     * 将小时转毫秒
     * @param time
     * @return
     */
    public static long reserverTime(int time){
        return time * 60 * 60 * 1000;
    }
}
