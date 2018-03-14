package com.huami.merchant.activity.task.model;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huami.merchant.bean.TaskPaperInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Henry on 2018/1/12.
 */
public class TaskPaperModelImp implements TaskPaperModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    private List<TaskPaperInfo> papers;
    private int page;
    @Override
    public void getTaskPaper(List<TaskPaperInfo> papers,String url, String uuid, int page, InterLoadListener listener) {
        this.page = page;
        this.papers = papers;
        this.listener = listener;
        if (papers == null) {
            this.papers = new ArrayList<>();
        }
        try {
            String[] keys = new String[]{"merUserId", "page", "number"};
            String[] values = new String[]{BaseApplication.UUID,String.valueOf(page), "1000"};
            biz.getMainThread(url,keys,values,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<TaskPaperInfo> json_paper;
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        Log.e("我的问卷是啥", json);
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                JSONArray data = object.getJSONArray("data");
                String json_data = data.toString();
                json_paper= gson.fromJson(json_data,new TypeToken<List<TaskPaperInfo>>(){}.getType());
                if (json_paper != null) {
                    if (page == 1) {
                        papers.clear();
                    }
                    for (TaskPaperInfo info : json_paper) {
                        papers.add(info);
                    }
                }
                listener.loadSuccess(model.getTag(), json);
            } else {
                listener.loadFailure(model.getTag(), ErrorCode.ACTION_FAILURE);
            }
        } catch (JSONException e) {
            listener.loadFailure(model.getTag(), ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
