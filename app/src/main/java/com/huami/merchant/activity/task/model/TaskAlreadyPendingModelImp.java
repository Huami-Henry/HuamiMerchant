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

public class TaskAlreadyPendingModelImp implements TaskAlreadyPendingModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    private List<TaskPreviewData> tasks;
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(json);
            int code = object.getInt("code");
            if (BaseConsts.BASE_URL_ALREADY_REVIEW_TASK.equals(model.getTag())) {
                if (code == 0) {
                    TaskPreviewBean bean = gson.fromJson(json, TaskPreviewBean.class);
                    for (TaskPreviewData data : bean.getData()) {
                        tasks.add(data);
                    }
                }
            }
            listener.loadSuccess(model.getTag(), json);
        } catch (JSONException e) {
            listener.loadFailure(model.getTag(), ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.ACTION_FAILURE);
    }
    @Override
    public void getPreviewTask(List<TaskPreviewData> tasks,String url, String uuid, String task_id, String page, String check_times,String task_name,String pass_state, InterLoadListener listener) {
        this.listener = listener;
        if (TextUtils.isEmpty(uuid)) {
            listener.loadFailure(BaseConsts.BASE_URL_ALREADY_REVIEW_TASK, ErrorCode.PARAMA_EMPTY);
            return;
        }
        this.tasks = tasks;
        String[] keys = new String[]{"uuid", "page","task_id","pass_state","check_times","name","number"};
        String[] values = new String[]{uuid, page,task_id,pass_state,check_times,task_name,"10"};
        biz.getMainThread(url,keys,values,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK);
    }

    @Override
    public void getMaxAudit(String url, InterLoadListener listener) {
        this.listener = listener;
        biz.getHomeData(url,url);
    }
}
