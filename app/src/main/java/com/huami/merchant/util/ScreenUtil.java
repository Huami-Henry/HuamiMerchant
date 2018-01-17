package com.huami.merchant.util;
import android.content.Context;
import android.util.DisplayMetrics;

import com.huami.merchant.mvpbase.BaseApplication;

/**
 * Created by young chan on 2017/2/18
 * 屏幕工具类
 */

public enum ScreenUtil {

    Instance;
    private int screenWidth;
    private int screenHeight;
    private Context context;

    ScreenUtil() {
        context = BaseApplication.getContext();
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenMaxWidth(int offsetDip) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int offsetPixels = dip2px(offsetDip);

        return (screenWidth - offsetPixels);
    }

    public int getScreenMaxHeight(int offsetDip) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        int offsetPixels = dip2px(offsetDip);
        return (screenHeight - offsetPixels);
    }

    public int dip2px(float dipValue) {
        if (dipValue == 0)
            return 0;
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(float pxValue) {
        if (pxValue == 0) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }

    //将pixel转换成sp
    public int pixel2Sp(float pixelValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int sp = (int) (pixelValue / scaledDensity + 0.5f);
        return sp;
    }

    //将sp转换成pixel
    public int sp2Pixel(float spValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int pixelValue = (int) (spValue * scaledDensity + 0.5f);
        return pixelValue;
    }
}
