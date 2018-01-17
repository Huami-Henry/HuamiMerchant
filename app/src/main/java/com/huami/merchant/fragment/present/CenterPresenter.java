package com.huami.merchant.fragment.present;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.CenterFragment;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.fragment.model.CenterModelImp;
import com.huami.merchant.mvpbase.BasePresenter;
/**
 * Created by Henry on 2018/1/4.
 */
public class CenterPresenter extends BasePresenter<CenterFragment,CenterModelImp> {
    @Override
    protected CenterModelImp getModel() {
        return new CenterModelImp();
    }
    public void getCenterInfo(String uuid){
        model.getCenterInfo(uuid, new InterLoadListener() {
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
