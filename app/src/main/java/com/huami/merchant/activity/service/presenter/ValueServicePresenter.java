package com.huami.merchant.activity.service.presenter;

import com.huami.merchant.activity.service.ValueServiceActivity;
import com.huami.merchant.activity.service.model.ValueServiceModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueServicePresenter extends BasePresenter<ValueServiceActivity,ValueServiceModelImp>{
    @Override
    protected ValueServiceModelImp getModel() {
        return new ValueServiceModelImp();
    }
    /**
     * 获取左边的导航列表
     * @param url
     * @throws Exception
     */
    public void getLeftValue(String url) throws Exception{
        model.getValueService(url, new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().success((String) tag, json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().failure((String) tag, error_code);
                }
            }
        });
    }
    /**
     * 获取右边的导航列表
     * @param url
     * @throws Exception
     */
    public void getRightValue(String url,String packageType) throws Exception{
        model.getValueRightService(url,packageType,"1","1000",new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().success((String) tag, json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().failure((String) tag, error_code);
                }
            }
        });
    }
}
