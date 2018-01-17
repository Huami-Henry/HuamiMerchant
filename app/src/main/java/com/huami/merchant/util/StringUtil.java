package com.huami.merchant.util;

import android.text.TextUtils;

/**
 * Created by henry on 2018/1/17.
 */

public class StringUtil {
    public static  String subStringTime(String time){
        if (!TextUtils.isEmpty(time)) {
            if (time.contains(".0")) {
                return time.substring(0, time.indexOf("."));
            }
        }
        return "";
    }
}
