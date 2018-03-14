package com.huami.merchant.activity.service.presenter;

import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.activity.service.model.ValueServiceModelImp;
import com.huami.merchant.activity.task.model.SingleValueServiceModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/24.
 */

public class SingleValuePresenter extends BasePresenter<SingleValueActivity,SingleValueServiceModelImp> {
    @Override
    protected SingleValueServiceModelImp getModel() {
        return new SingleValueServiceModelImp();
    }
    public void getValueList(String url, String packageType, String page, String number) throws Exception{
        model.getValueRightService(url, packageType, page, number, new InterLoadListener() {
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

