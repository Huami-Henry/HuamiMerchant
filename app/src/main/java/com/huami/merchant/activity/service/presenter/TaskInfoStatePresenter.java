package com.huami.merchant.activity.service.presenter;

import com.huami.merchant.activity.task.TaskInfoStateActivity;
import com.huami.merchant.activity.task.model.TaskEditModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by henry on 2018/1/26.
 */

public class TaskInfoStatePresenter extends BasePresenter<TaskInfoStateActivity,TaskEditModelImp> {
    @Override
    protected TaskEditModelImp getModel() {
        return new TaskEditModelImp();
    }
    /**
     * 获取任务详情
     *
     * @param task_id
     * @throws Exception
     */
    public void getTaskInfo(String task_id) throws Exception {
        model.getEditTask(task_id, new InterLoadListener() {
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
