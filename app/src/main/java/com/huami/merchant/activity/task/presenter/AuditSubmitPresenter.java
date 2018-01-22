package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.task.AuditStarActivity;
import com.huami.merchant.activity.task.model.AuditSubmitModelImp;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/18.
 */

public class AuditSubmitPresenter extends BasePresenter<AuditStarActivity,AuditSubmitModelImp> implements InterLoadListener{

    @Override
    protected AuditSubmitModelImp getModel() {
        return new AuditSubmitModelImp();
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
            getView().doFailure(tag, error_code);
        }
    }

    /**
     * 提交最后的审核结果
     * @param url
     * @param result
     * @throws Exception
     */
    public void submitAudit(String url, AuditResult result) throws Exception{
        model.setDataCallback(this);
        if (result == null) {
            throw new Exception("parameter is null");
        }
        model.submitAudit(url, result);
    }
}
