package com.huami.merchant.activity.task.presenter;
import com.huami.merchant.activity.task.AlreadyBuyActivity;
import com.huami.merchant.activity.task.model.AlreadyBuyModelImp;
import com.huami.merchant.bean.AlreadyService.AlreadyServiceData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by henry on 2018/2/7.
 */
public class AlreadyBuyPresenter extends BasePresenter<AlreadyBuyActivity,AlreadyBuyModelImp>{
    @Override
    protected AlreadyBuyModelImp getModel() {
        return new AlreadyBuyModelImp();
    }
    public void alreadyBuy(String url, String uuid, String merUserId,List<AlreadyServiceData> services) throws Exception {
        model.alreadyBuy(url, uuid, merUserId,services, new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().doSuccess(tag, json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().doFailure(tag, error_code);
                }
            }
        });
    }
}
