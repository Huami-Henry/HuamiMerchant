package com.huami.merchant.mvpbase;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.squareup.okhttp.Request;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
/**
 * Created by henry on 2018/1/31.
 */

public class BaseDetailModelImp implements BaseModelInter,BaseNetDataBiz.RequestListener{
    private InterLoadListener listener;
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    public void getDetail(String url, InterLoadListener listener) throws Exception{
        this.listener = listener;
        if (listener == null) {
            throw new NullPointerException("CallBack is null");
        }
        biz.getHomeData(url,url);
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        String tag = model.getTag();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                listener.loadSuccess(tag,json);
            } else {
                listener.loadFailure(tag, ErrorCode.ACTION_FAILURE);
            }
        } catch (JSONException e) {
            listener.loadFailure(tag, ErrorCode.TRY_CATCH);
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
