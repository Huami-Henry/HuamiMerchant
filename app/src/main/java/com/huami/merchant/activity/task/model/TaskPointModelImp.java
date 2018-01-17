package com.huami.merchant.activity.task.model;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.huami.merchant.bean.TaskPointBean;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Henry on 2018/1/15.
 */
public class TaskPointModelImp implements TaskPointModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private List<TaskPointInfo> shops;
    private InterLoadListener listener;
    private int page;
    @Override
    public void getShop(List<TaskPointInfo> shops,String url,String uuid,String taskId, int page, InterLoadListener listener) {
        this.shops=this.shops== null ? new ArrayList<TaskPointInfo>():shops;
        this.listener = listener;
        this.page = page;
        String[] keys = null;
        String[] values = null;
        if (TextUtils.isEmpty(taskId)) {
            keys = new String[]{"uuid", "page", "number"};
            values = new String[]{BaseApplication.UUID, String.valueOf(page), "20000"};
        } else {
            keys = new String[]{"taskId"};
            values = new String[]{taskId};
        }
        biz.getMainThread(url,keys,values,url);
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        String tag = model.getTag();
        Gson gson = new Gson();
        try {
            TaskPointBean taskPointBean = gson.fromJson(json, TaskPointBean.class);
            if (taskPointBean.getCode() == 0) {
                List<TaskPointInfo> info = taskPointBean.getData();
                if (page == 1) {
                    shops.clear();
                }
                for (TaskPointInfo in : info) {
                    shops.add(in);
                }
            } else {
                listener.loadFailure(tag, ErrorCode.ACTION_FAILURE);
            }
        } catch (Exception e) {
            listener.loadFailure(tag,ErrorCode.TRY_CATCH);
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        if (listener != null) {
            listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
        }
    }
}
