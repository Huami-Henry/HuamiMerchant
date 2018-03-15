package com.huami.merchant.fragment.model;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.huami.merchant.bean.TaskBean;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONObject;
import java.io.IOException;
import java.util.List;
/**
 * Created by Henry on 2018/1/4.
 */
public class TaskListModelImp implements TaskListModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    private List<TaskInfo> tasks;
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                TaskBean bean = gson.fromJson(json, TaskBean.class);
                tasks.clear();
                for (TaskBean.TaskData.TaskInfo info : bean.getData().getTask()) {
                    tasks.add(info);
                }
            } else {
                listener.loadFailure(model.getTag(),ErrorCode.ACTION_FAILURE);
            }
            listener.loadSuccess(model.getTag(),json);
        } catch (Exception e) {
            listener.loadFailure(model.getTag(),ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.NET_FAILURE);
    }
    @Override
    public void getTask(List<TaskInfo> tasks, String merUserId, String check_state, String taskName, InterLoadListener listener) {
        this.listener = listener;
        this.tasks = tasks;
        if (TextUtils.isEmpty(merUserId)) {
            listener.loadFailure(BaseConsts.BASE_URL_TASK, ErrorCode.PARAMA_EMPTY);
            return;
        }
        String[] keys = new String[]{"merUserId", "check_state", "task_name"};
        String[] values = new String[]{merUserId,check_state,taskName};
        biz.getMainThread(BaseConsts.BASE_URL_TASK,keys,values,BaseConsts.BASE_URL_TASK);
    }
}
