package com.huami.merchant.activity.task.model;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
/**
 * Created by henry on 2018/1/19.
 */
public class TaskPublishModelImp implements TaskPublishModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;

    public void setCallBack(InterLoadListener listener) {
        this.listener = listener;
    }
    @Override
    public void uploadTaskIcon(String path) throws Exception{
        if (listener == null) {
            throw new Exception("CallBack is null!");
        }
        File file = new File(path);
        if (!file.exists()) {
            throw new Exception("没有选择图片或者选择的文件损坏");
        }
        biz.getMainThreadUploadHead(null, BaseConsts.BASE_URL_IMAGE,path,BaseConsts.BASE_URL_IMAGE);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String json = model.getJson();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                listener.loadSuccess(model.getTag(), json);
            } else {
                listener.loadFailure(model.getTag(), ErrorCode.ACTION_FAILURE);
            }
        } catch (JSONException e) {
            listener.loadSuccess(model.getTag(), json);
        }

    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
