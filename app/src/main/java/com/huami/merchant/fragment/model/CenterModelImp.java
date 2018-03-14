package com.huami.merchant.fragment.model;
import android.text.TextUtils;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
/**
 * Created by Henry on 2018/1/4.
 */
public class CenterModelImp implements CenterModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        String tag = model.getTag();
        try {
            JSONObject object = new JSONObject(json);
            int code = object.getInt("code");
            if (code == 0) {
                listener.loadSuccess(tag,json);
            } else if (code == 666) {
                listener.loadFailure(tag, ErrorCode.CODE_ERROR);
            }
        } catch (JSONException e) {
            listener.loadFailure(tag,ErrorCode.TRY_CATCH);
        }
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(),ErrorCode.NET_FAILURE);
    }
    @Override
    public void getCenterInfo(String uuid,String merUserId, InterLoadListener listener) {
        this.listener = listener;
        if (TextUtils.isEmpty(uuid)) {
            listener.loadFailure(BaseConsts.BASE_URL_USER_CENTER,ErrorCode.PARAMA_EMPTY);
            return;
        }
        if (TextUtils.isEmpty(merUserId)) {
            listener.loadFailure(BaseConsts.BASE_URL_USER_CENTER,ErrorCode.PARAMA_EMPTY);
            return;
        }
        String[] keys = new String[]{"uuid", "merUserId"};
        String[] values=new String[]{BaseApplication.UU_TOKEN,BaseApplication.UUID};
        biz.getMainThread(BaseConsts.BASE_URL_USER_CENTER,keys,values,BaseConsts.BASE_URL_USER_CENTER);
    }
}
