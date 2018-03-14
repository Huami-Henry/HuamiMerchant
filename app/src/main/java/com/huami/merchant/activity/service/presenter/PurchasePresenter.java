package com.huami.merchant.activity.service.presenter;

import android.text.TextUtils;

import com.huami.merchant.activity.service.PurchaseActivity;
import com.huami.merchant.activity.service.model.PurchaseModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.util.StringUtil;

/**
 * Created by henry on 2018/2/5.
 */

public class PurchasePresenter extends BasePresenter<PurchaseActivity,PurchaseModelImp> {
    @Override
    protected PurchaseModelImp getModel() {
        return new PurchaseModelImp();
    }
    public void getPrice(String url, String token, String uuid, String packageId, String number) throws Exception {
        model.getPrice(url, token, uuid, packageId, number, new InterLoadListener() {
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
    public void payService(String url, String token, String uuid, String cellPhone,String contact, String packageId, String number) throws Exception{
        model.payService(url, token, uuid,cellPhone,contact, packageId, number, new InterLoadListener() {
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
