package com.huami.merchant.mvpbase;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Stack;

/**
 * Created by young chan on 2017/02/05
 * App管理类，用于退出App、添加移除Activity等功能
 */

public enum AppManager {
    Instance;
    private Stack<FragmentActivity> activityStack;
    private Context context;
    private PackageManager packageManager;
    private PackageInfo packageInfo;

    AppManager() {
        context = BaseApplication.getContext();
        activityStack = new Stack<>();
        packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {}
    }

    //添加Activity
    public boolean addActivity(FragmentActivity activity) {
        if (null == activity)
            return false;
        return activityStack.add(activity);
    }

    //删除Activity
    public void finishActivity(FragmentActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    //结束所有Activity
    public void finishAllActivity() {
        int acSize = activityStack.size();
        for (int i = 0; i < acSize; i++) {
            Activity activity = activityStack.get(i);
            if (null != activity) {
                activity.finish();
            }
        }
        activityStack.clear();
    }
    //结束所有Activity
    public void finishWithoutMain(FragmentActivity fa) {
        int acSize = activityStack.size();
        for (int i = 0; i < acSize; i++) {
            FragmentActivity activity = activityStack.get(i);
            if (null != activity) {
                if (!"MainActivity".equals(activity.getClass().getSimpleName())&&!fa.getClass().getSimpleName().equals(activity.getClass().getSimpleName())) {
                    Log.e("我的结果activity", activity.getClass().getSimpleName());
                    activity.finish();
                    activityStack.remove(activity);
                }
            }
        }
    }
    //退出App
    public void AppExit() {
        try {
            this.finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception var3) {
        }
    }

    //重启App
    public void AppRestart() {
        this.finishAllActivity();
        final Intent intent = packageManager.getLaunchIntentForPackage(packageInfo.packageName);
        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent restartIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 200, restartIntent); // 重启应用
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
