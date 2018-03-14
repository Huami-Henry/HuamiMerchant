package com.huami.merchant.activity.task.model;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by henry on 2018/1/25.
 */

public class DataStaticsModelImp implements DataStatisticsModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getTaskDataStatistics(String url, int taskId, InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null) {
            throw new Exception("CallBack is null!");
        }
        biz.getMainDataGet(url+"taskId="+taskId,url);
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
