package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.task.TaskEditActivity;
import com.huami.merchant.activity.task.TaskPreviewActivity;
import com.huami.merchant.activity.task.model.TaskEditModelImp;
import com.huami.merchant.activity.task.model.TaskPreviewModelImp;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.bean.TaskPublishBase;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by Henry on 2018/1/8.
 */
public class TaskEditPresenter extends BasePresenter<TaskEditActivity,TaskEditModelImp> {
    @Override
    protected TaskEditModelImp getModel() {
        return new TaskEditModelImp();
    }

    /**
     * 获取任务详情
     * @param task_id
     * @throws Exception
     */
    public void getEditTask(String task_id) throws Exception{
        model.getEditTask(task_id,new InterLoadListener() {
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
    /**
     * 发布任务
     * @param url
     * @param publish
     * @throws Exception
     */
    public void publishTask(String url, TaskPublishBase publish) throws Exception{
        model.publishTask(url, publish, new InterLoadListener() {
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
