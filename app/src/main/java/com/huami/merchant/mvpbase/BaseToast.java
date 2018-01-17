package com.huami.merchant.mvpbase;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huami.merchant.R;
import com.huami.merchant.util.DisplayUtil;

/**
 * Created by Henry on 2017/7/3.
 */

public class BaseToast {
    private static int height = DisplayUtil.getScreenHeightPixels();
    private static long pre_time;
    public static void showToast(Context context, String text){
        long current_time = System.currentTimeMillis();
        if (current_time - pre_time >= 1000) {
            pre_time = current_time;
            Toast toast = new Toast(context);
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.toast, null,false);
            TextView tv_text = (TextView) view.findViewById(R.id.toast_text);
            tv_text.setText(text);
            toast.setGravity(Gravity.TOP,0,height/2+100);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public static void showToast(Context context, int res_id){
        long current_time = System.currentTimeMillis();
        if (current_time - pre_time >= 1000) {
            pre_time = current_time;
            Toast toast = new Toast(context);
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.toast, null,false);
            TextView tv_text = (TextView) view.findViewById(R.id.toast_text);
            tv_text.setText(res_id);
            toast.setGravity(Gravity.TOP,0,height/2+100);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
