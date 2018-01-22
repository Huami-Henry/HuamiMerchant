package com.huami.merchant.activity.task.model;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
/**
 * Created by henry on 2018/1/17.
 */

public class AuditLastStepModelImp implements AuditLastStepModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    public void setCallbackListener(InterLoadListener listener){
        this.listener = listener;
    }
    @Override
    public void getAuditTag(String url,String type) throws Exception{
        if (listener == null) {
            throw new Exception("DataCallBack is null,Please set first");
        }
        biz.getHomeData(url + "?type=" + type, url);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        try {
            JSONObject object = new JSONObject(json);
            int code = object.getInt("code");
            if (code == 0) {
                if (listener != null) {
                    listener.loadSuccess(model.getTag(), json);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        if (listener != null) {
            listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
        }
    }
}
