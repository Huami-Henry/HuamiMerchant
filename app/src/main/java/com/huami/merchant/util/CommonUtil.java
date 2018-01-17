package com.huami.merchant.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by young chan on 2017/4/25
 */

public enum CommonUtil {

    Instance;

    //判断手机号码格式是否正确
    public static boolean isMobile(String mobiles) {
        Pattern pattern=Pattern.compile("^1[0-9]{10}$");
        Matcher matcher=pattern.matcher(mobiles);
        return matcher.matches();
    }

    //判断邮箱是否正确
    public static boolean isMail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    //距离转化
    public static String getDistance(double distance){
        if(distance < 1000){
            return String.format("%.2f", distance)+"m";
        }else{
            double dis = distance/1000;
            if (dis > 1000) {
                return null;
            }
            return String.format("%.1f", dis)+"Km";
        }
    }

    //时间转化字符
    public static String getTime(long time){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date= new Date(time);
        return format.format(date);
    }

    //时间转化字符
    public String getTime(String format_type,long time){
        SimpleDateFormat format = new SimpleDateFormat(format_type);
        Date date= new Date(time);
        return format.format(date);
    }
}
