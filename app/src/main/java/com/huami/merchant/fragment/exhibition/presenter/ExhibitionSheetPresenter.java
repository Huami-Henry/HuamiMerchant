package com.huami.merchant.fragment.exhibition.presenter;

import com.huami.merchant.bean.TaskBean;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.TaskFragment;
import com.huami.merchant.fragment.exhibition.ExhibitionSheetFragment;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.fragment.model.TaskListModelImp;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by henry on 2018/3/15.
 */

public class ExhibitionSheetPresenter extends BasePresenter<ExhibitionSheetFragment,TaskListModelImp> {
    @Override
    protected TaskListModelImp getModel() {
        return new TaskListModelImp();
    }
    /**
     * 获取任务审核列表
     */
    public void getTaskList(List<TaskBean.TaskData.TaskInfo> tasks, String merUserId, String checkState, String taskName){
        model.getTask(tasks,merUserId,checkState,taskName,new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().taskSuccess();
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().taskFailure();
                }
            }
        });
    }
}