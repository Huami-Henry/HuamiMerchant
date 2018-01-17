package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.task.TaskAlreadyPendingActivity;
import com.huami.merchant.activity.task.TaskPreviewActivity;
import com.huami.merchant.activity.task.model.TaskAlreadyPendingModelImp;
import com.huami.merchant.activity.task.model.TaskPreviewModelImp;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by Henry on 2018/1/8.
 */
public class TaskAlreadyPresenter extends BasePresenter<TaskAlreadyPendingActivity,TaskAlreadyPendingModelImp> {
    @Override
    protected TaskAlreadyPendingModelImp getModel() {
        return new TaskAlreadyPendingModelImp();
    }
    public void getTaskAlreadyPending(List<TaskPreviewData> tasks,String url,String task_id,int page,String checkTimes,String passState, String taskName){
        model.getPreviewTask(tasks,url, BaseApplication.UUID, task_id,String.valueOf(page),checkTimes, taskName,passState,new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().viewLoadSuccess(tag,json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().viewLoadFailure(tag,error_code);
                }
            }
        });
    }
    /**
     * 获取最大审核次数
     * @param url
     */
    public void getMaxAudit(String url){
        model.getMaxAudit(url, new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().viewLoadSuccess(tag,json);
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().viewLoadFailure(tag,error_code);
                }
            }
        });
    }
}
