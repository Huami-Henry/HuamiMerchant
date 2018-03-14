package com.huami.merchant.activity.service.model;

import android.text.TextUtils;
import android.util.Log;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by henry on 2018/2/5.
 */
public class PurchaseModelImp implements PurchaseModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz=new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getPrice(String url,String token, String uuid, String packageId, String number, InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null) {
            throw new Exception("call back is null");
        }
        if (TextUtils.isEmpty(uuid)) {
            throw new Exception("用户未登录");
        }
        if (TextUtils.isEmpty(token)) {
            throw new Exception("用户未登录");
        }
        String[] keys = new String[]{"uuid", "merUserId", "packageId", "number"};
        String[] values = new String[]{token, uuid, packageId, number};
        biz.getMainThread(url,keys,values,url);
    }

    @Override
    public void payService(String url, String token, String uuid, String cellPhone, String contact, String packageId, String number, InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null) {
            throw new Exception("call back is null");
        }
        if (TextUtils.isEmpty(uuid)) {
            throw new Exception("用户未登录");
        }
        if (TextUtils.isEmpty(token)) {
            throw new Exception("用户未登录");
        }
        String[] keys = new String[]{"uuid", "merUserId","cellPhone","contact", "packageId", "number"};
        String[] values = new String[]{token, uuid,cellPhone,contact,packageId, number};
        biz.getMainThread(url,keys,values,url);
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        String tag = model.getTag();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                listener.loadSuccess(tag, json);
            } else {
                listener.loadFailure(tag, ErrorCode.ACTION_FAILURE);
            }
        } catch (JSONException e) {
            listener.loadFailure(tag, ErrorCode.TRY_CATCH);
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
