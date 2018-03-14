package com.huami.merchant.util;

import android.util.Log;

/**
 * Created by henry on 2018/1/25.
 */

public class TagUtil {
    public static void i(String tag, String msg) {
        int max_str_length = 2001 - tag.length();
        while (msg.length() > max_str_length) {
            Log.e(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        Log.e(tag, msg);
    }
}
