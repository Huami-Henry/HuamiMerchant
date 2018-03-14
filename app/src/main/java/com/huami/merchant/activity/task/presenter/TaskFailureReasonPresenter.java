package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.task.TaskFailureReasonActivity;
import com.huami.merchant.activity.task.model.TaskFailureReasonModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/24.
 */

public class TaskFailureReasonPresenter extends BasePresenter<TaskFailureReasonActivity,TaskFailureReasonModelImp>{
    @Override
    protected TaskFailureReasonModelImp getModel() {
        return new TaskFailureReasonModelImp();
    }
    public void getFailureReason(String url,String task_id) throws Exception{
        model.getFailureReason(url, task_id, new InterLoadListener() {
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
