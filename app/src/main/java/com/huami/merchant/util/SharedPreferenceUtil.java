package com.huami.merchant.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.huami.merchant.mvpbase.BaseApplication;
public enum SharedPreferenceUtil {
    Instance;
    private Context context;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    SharedPreferenceUtil() {
        this.context = BaseApplication.getContext();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        editor = prefs.edit();
    }

    public void putStringValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String name, String defaultStr) {
        return prefs.getString(name, defaultStr);
    }

    public boolean getBooleanValue(String name, boolean defaultBoolean) {
        boolean value = prefs.getBoolean(name, defaultBoolean);
        return value;
    }

    public void putBooleanValue(String name, boolean value) {
        editor.putBoolean(name, value);
        editor.commit();
    }

    public void setInteger(String name, int value) {
        editor.putInt(name, value).commit();
    }

    public int getInteger(Context context, String name, int default_i) {
        return prefs.getInt(name, default_i);
    }

}
