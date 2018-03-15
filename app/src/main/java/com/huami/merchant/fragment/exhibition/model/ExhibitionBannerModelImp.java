package com.huami.merchant.fragment.exhibition.model;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by henry on 2018/3/15.
 */

public class ExhibitionBannerModelImp implements ExhibitionBannerModelInter,BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private InterLoadListener listener;
    private WeakReference<List<String>> weak_banner;

    @Override
    public void getBanner(String url, List<String> banners, InterLoadListener listener) throws Exception {
        if (banners == null) {
            throw new Exception("banners should not null");
        }
        if (listener == null) {
            throw new Exception("listener should not null");
        }
        if (this.listener == null) {
            this.listener = listener;
        }
        weak_banner = new WeakReference<>(banners);
        biz.getHomeData(url,"banner");
    }

    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        String tag = model.getTag();
        String json = model.getJson();
        try {
            List<String> banner = weak_banner.get();
            if (banner == null) {
                listener.loadFailure(tag, ErrorCode.PARAMA_EMPTY);
                return;
            }
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            JSONArray recommendList = data.getJSONArray("recommendList");
            for (int i=0;i<recommendList.length();i++) {
                String showImage = recommendList.getJSONObject(i).getString("showImage");
                banner.add(showImage);
            }
            listener.loadSuccess(tag,json);
        } catch (Exception e) {
            listener.loadFailure(tag, ErrorCode.TRY_CATCH);
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        if (listener != null) {
            listener.loadFailure(r.tag(), ErrorCode.NET_FAILURE);
        }
    }
}
