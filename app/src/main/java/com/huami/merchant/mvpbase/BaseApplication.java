package com.huami.merchant.mvpbase;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.huami.merchant.imagepicker.RxPicker;
import com.huami.merchant.util.GlideImageLoader;
import com.huami.merchant.util.SPCache;

import cn.jpush.android.api.JPushInterface;

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
        context = this;
        getVersion();
        initSpCache();
        initUser();
        RxPicker.init(new GlideImageLoader());
        initJPush();
    }

    private void initJPush() {
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
