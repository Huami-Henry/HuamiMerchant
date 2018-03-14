package com.huami.merchant.activity.task.model;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by henry on 2018/1/24.
 */

public class TaskFailureReasonModelImp implements TaskFailureReasonModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getFailureReason(String url, String task_id, InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null || url == null || task_id == null) {
            throw new Exception("Some Parameters is Empty");
        }
        biz.getMainDataGet(url+"?task_id="+task_id,url);
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
            listener.loadFailure(tag,ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.NET_FAILURE);
    }
}
