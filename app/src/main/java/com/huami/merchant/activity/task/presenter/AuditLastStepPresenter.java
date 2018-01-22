package com.huami.merchant.activity.task.presenter;
import com.huami.merchant.activity.task.AuditLastStepActivity;
import com.huami.merchant.activity.task.model.AuditLastStepModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/18.
 */

public class AuditLastStepPresenter extends BasePresenter<AuditLastStepActivity,AuditLastStepModelImp> implements InterLoadListener{
    @Override
    protected AuditLastStepModelImp getModel() {
        return new AuditLastStepModelImp();
    }
    public void getAuditTag(String url,String type) throws Exception {
        model.setCallbackListener(this);
        model.getAuditTag(url,type);
    }

    @Override
    public void loadSuccess(Object tag, String json) {
        if (isViewAttached()) {
            getView().doSuccess(tag, json);
        }
    }

    @Override
    public void loadFailure(Object tag, ErrorCode error_code) {
        if (isViewAttached()) {
            getView().doFailure(tag,error_code);
        }
    }
}
