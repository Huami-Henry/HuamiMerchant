package com.huami.merchant.activity.task.model;

import com.google.gson.Gson;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.bean.QuestionSinglePostil;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.List;

/**
 * Created by henry on 2018/1/18.
 */

public class AuditSubmitModelImp implements AuditSubmitModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;

    public void setDataCallback(InterLoadListener listener) {
        this.listener = listener;
    }
    @Override
    public void submitAudit(String url, AuditResult result) throws Exception{
        if (listener == null) {
            throw new Exception("DataCallBack is null,Please set first");
        }
        try {
            Gson gson = new Gson();
            List<QuestionSinglePostil> postil = result.getPostil();
            String json = gson.toJson(postil);
            String[] keys = new String[]{"merUserId", "uuid", "checkCaseId", "resultId", "content", "score", "postil"};
            String[] values=new String[]{result.getMerUserId(),result.getUuid(),result.getCheckCaseId(),result.getResultId(),result.getContent(),String.valueOf(result.getScore()),json};
            biz.getMainThread(url,keys,values,url);
        } catch (Exception e) {
            throw new Exception("parameter is bad!");
        }
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        listener.loadSuccess(model.getTag(),model.getJson());
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
