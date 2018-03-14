package com.huami.merchant.mvpbase;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.huami.merchant.imagepicker.RxPicker;
import com.huami.merchant.util.GlideImageLoader;
import com.huami.merchant.util.SPCache;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class BaseApplication extends MultiDexApplication {
    private static Context context;
    public static String UUID;
    public static String UU_TOKEN;
    public static Context getContext(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        context = this;
        getVersion();
        initSpCache();
        initUser();
        RxPicker.init(new GlideImageLoader());
    }

    /**
     * 版本升级判断
     */
    public void getVersion(){

    }
    private void initUser() {
        UUID = SPCache.getString(BaseConsts.USER_CENTER.USER_ID, "");
        UU_TOKEN = SPCache.getString(BaseConsts.USER_CENTER.USER_TOKEN, "");
    }
    /**
     * 返回当前程序版本名
     */
    public String getAppVersionName(Context context) {
        String versioncode = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = String.valueOf(pi.versionCode);
            if (versioncode == null || versioncode.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versioncode;
    }
    //数据存储的初始化操作
    private void initSpCache() {
        SPCache.init(this);
    }
}
