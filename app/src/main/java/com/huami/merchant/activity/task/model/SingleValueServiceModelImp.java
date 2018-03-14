package com.huami.merchant.activity.task.model;

import com.huami.merchant.activity.service.model.ValueServiceModelInter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by henry on 2018/1/29.
 */

public class SingleValueServiceModelImp implements ValueServiceModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getValueService(String url, InterLoadListener listener) throws Exception{
        this.listener = listener;
        if (listener == null||url==null) {
            throw new Exception("CallBack is null");
        }
        biz.getMainDataGet(url,url);
    }

    @Override
    public void getValueRightService(String url, String entryId, String page, String number,InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null||url==null) {
            throw new Exception("CallBack is null");
        }
        String[] keys=new String[]{"entryId"};
        String[] values=new String[]{entryId};
        biz.getMainThread(url, keys, values, url);
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String tag = model.getTag();
        try {
            JSONObject object = new JSONObject(model.getJson());
            if (object.getInt("code") == 0) {
                listener.loadSuccess(tag, model.getJson());
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