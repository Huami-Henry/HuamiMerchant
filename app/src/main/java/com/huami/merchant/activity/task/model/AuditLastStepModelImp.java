package com.huami.merchant.activity.task.model;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;
import java.io.IOException;
/**
 * Created by henry on 2018/1/17.
 */

public class AuditLastStepModelImp implements AuditLastStepModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    public void setCallbackListener(InterLoadListener listener){
        this.listener = listener;
    }
    @Override
    public void getAuditTag(String url,String type) {
        biz.getHomeData(url + "?type=" + type, url);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        int code = model.getCode();
        if (code == 0) {
            String json = model.getJson();
            if (listener != null) {
                listener.loadSuccess(model.getTag(), json);
            }
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {

    }
}
