package com.huami.merchant.fragment.model;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;
import java.io.IOException;
/**
 * Created by Henry on 2018/1/4.
 */
public class CenterModelImp implements CenterModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {

    }
    @Override
    public void OnFailure(Request r, IOException o) {

    }
    @Override
    public void getCenterInfo(String uuid, InterLoadListener listener) {

    }
}
