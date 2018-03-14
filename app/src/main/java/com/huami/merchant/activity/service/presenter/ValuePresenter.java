package com.huami.merchant.activity.service.presenter;

import com.huami.merchant.activity.service.ValueDetailActivity;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseDetailModelImp;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/31.
 */

public class ValuePresenter extends BasePresenter<ValueDetailActivity,BaseDetailModelImp> {
    @Override
    protected BaseDetailModelImp getModel() {
        return new BaseDetailModelImp();
    }
    public void getDetai(String url) throws Exception{
        model.getDetail(url, new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().doSuccess(tag,json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().doFailure(tag,error_code);
                }
            }
        });
    }
}
