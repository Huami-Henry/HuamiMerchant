package com.huami.merchant.activity.task.model;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.huami.merchant.bean.TaskPreviewBean;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.bean.TaskPublishBase;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.orhanobut.logger.Logger;
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
    public void publishTask(String url, TaskPublishBase publish,InterLoadListener listener) throws Exception{
        this.listener = listener;
        if (listener == null) {
            throw new Exception("CallBack is null");
        }
        Gson gson = new Gson();
        String[] keys = new String[]{"uuid",
                "merUserId", "oldTaskId",
                "traInfoId", "taskPaperId",
                "taskInfo", "taskShop",
                "taskCondition", "taskAttention",
                "automaticAddType"};
        String taskInfo = gson.toJson(publish.getTaskinfo());
        String taskShop = gson.toJson(publish.getTaskShop());
        String taskCondition = gson.toJson(publish.getTaskCondition());
        String taskAttention = gson.toJson(publish.getTaskAttention());
        String[] values = new String[]{
                BaseApplication.UU_TOKEN,
                BaseApplication.UUID,
                publish.getOldTaskId(),
                String.valueOf(publish.getTraInfoId()),
                String.valueOf(publish.getTaskPaperId()),
                taskInfo,
                taskShop,
                taskCondition,
                taskAttention, "0"};
        biz.getMainThread(url, keys, values, url);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        Logger.json(json);
        try {
            JSONObject object = new JSONObject(json);
            int code = object.getInt("code");
            if (code == 0) {
                listener.loadSuccess(model.getTag(), json);
            } else {
                listener.loadFailure(model.getTag(), ErrorCode.CODE_ERROR);
            }
        } catch (JSONException e) {
            Logger.d("我的tag",e.getMessage());
            listener.loadFailure(model.getTag(), ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.ACTION_FAILURE);
    }
}
