package com.huami.merchant.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.huami.merchant.mvpbase.BaseApplication;

/**
 * Created by Henry on 2017/6/13.
 */

public class DeviceInstance {
    private static DeviceInstance instance;
    private DeviceInstance(){}
    public static DeviceInstance getInstance(){
        if (instance == null) {
            instance = new DeviceInstance();
        }
        return instance;
    }

    /**
     * 获取手机的id
     * @return
     */
    public String getDeviceId(){
        TelephonyManager TelephonyMgr;
        try {
            TelephonyMgr = (TelephonyManager) BaseApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        } catch (Exception e) {
            return null;
        }
        return TelephonyMgr.getDeviceId();
    }
    /**
     * 获取手机的型号
     * @return
     */
    public String getDeviceName(){
        return Build.MODEL;
    }
}
