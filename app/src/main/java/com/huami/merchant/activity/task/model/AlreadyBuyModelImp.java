package com.huami.merchant.activity.task.model;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.huami.merchant.bean.AlreadyService;
import com.huami.merchant.bean.AlreadyService.AlreadyServiceData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

/**
 * Created by henry on 2018/2/7.
 */

public class AlreadyBuyModelImp implements AlreadyBuyModelInter,BaseNetDataBiz.RequestListener {
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    private List<AlreadyServiceData> services;
    @Override
    public void alreadyBuy(String url, String uuid, String merUserId, List<AlreadyServiceData> services, InterLoadListener listener) throws Exception {
        this.listener = listener;
        if (listener == null) {
            throw new Exception("callBack is null");
        }
        if (TextUtils.isEmpty(uuid)) {
            throw new Exception("用户没有登录");
        }
        if (TextUtils.isEmpty(merUserId)) {
            throw new Exception("用户没有登录");
        }
        this.services = services;
        String[] key = new String[]{"uuid", "merUserId"};
        String[] values = new String[]{uuid,merUserId};
        biz.getMainThread(url,key,values,url);
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        try {
            String json = model.getJson();
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                listener.loadSuccess(model.getTag(),json);
                AlreadyService bean = new Gson().fromJson(json,AlreadyService.class);
                List<AlreadyServiceData> data = bean.getData();
                for (AlreadyServiceData d : data) {
                    if (services != null) {
                        services.add(d);
                    }
                }
            } else {
                listener.loadFailure(model.getTag(), ErrorCode.CODE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            listener.loadFailure(model.getTag(),ErrorCode.TRY_CATCH);
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.NET_FAILURE);
    }
}
