package com.huami.merchant.activity.task.model;

import com.google.gson.Gson;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import java.io.IOException;

/**
 * Created by Henry on 2018/1/16.
 */

public class PaperPendingModelImp implements PaperPendingModelInter ,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void getPendingDetail(String url,String usercase_id, String isHistory, InterLoadListener listener) {
        this.listener = listener;
        String[] key = new String[]{"usercase_id", "isHistory"};
        String[] values = new String[]{usercase_id, isHistory};
        biz.getMainThread(url, key, values, url);
    }
    @Override
    public void getPendingDetail(String url,String usercase_id,String check_case_id, String isHistory, InterLoadListener listener) {
        this.listener = listener;
        String[] key = new String[]{"usercase_id", "isHistory","checkCaseId"};
        String[] values = new String[]{usercase_id, isHistory,check_case_id};
        biz.getMainThread(url, key, values, url);
    }
    @Override
    public void getAlreadyPendingDetail(String url,String userCaseId, String uca_check_usercase_id, String pass, InterLoadListener listener) {
        this.listener = listener;
        String[] key = new String[]{"userCaseId", "uca_check_usercase_id","pass"};
        String[] values = new String[]{userCaseId, uca_check_usercase_id,pass};
        biz.getMainThread(url, key, values, url);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        try {
            listener.loadSuccess(model.getTag(), model.getJson());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        try {
            listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
