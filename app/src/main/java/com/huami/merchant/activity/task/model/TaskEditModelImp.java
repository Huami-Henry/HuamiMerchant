package com.huami.merchant.activity.task.model;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.huami.merchant.bean.TaskPreviewBean;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Henry on 2018/1/8.
 */
public class TaskEditModelImp implements TaskEditModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getEditTask(String task_id,InterLoadListener listener) throws Exception{
        this.listener = listener;
        if (TextUtils.isEmpty(task_id)) {
            listener.loadFailure(BaseConsts.BASE_URL_TASK_DETAIL_INFO, ErrorCode.PARAMA_EMPTY);
            return;
        }
        String[] keys = new String[]{"taskId"};
        String[] values = new String[]{task_id};
        biz.getMainThread(BaseConsts.BASE_URL_TASK_DETAIL_INFO,keys,values,BaseConsts.BASE_URL_TASK_DETAIL_INFO);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        try {
            JSONObject object = new JSONObject(json);
            int code = object.getInt("code");
            if (code == 0) {
                listener.loadSuccess(model.getTag(), json);
            } else {
                listener.loadFailure(model.getTag(), ErrorCode.CODE_ERROR);
            }
        } catch (JSONException e) {
            listener.loadFailure(model.getTag(), ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.ACTION_FAILURE);
    }
}
