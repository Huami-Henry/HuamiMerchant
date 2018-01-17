package com.huami.merchant.fragment.present;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.TaskFragment;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.fragment.model.TaskListModelImp;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BasePresenter;
import java.util.List;

/**
 * Created by Henry on 2018/1/4.
 */

public class TaskListPresenter extends BasePresenter<TaskFragment,TaskListModelImp> {
    @Override
    protected TaskListModelImp getModel() {
        return new TaskListModelImp();
    }
    /**
     * 获取任务审核列表
     */
    public void getTaskList(List<TaskInfo> tasks,String merUserId,String checkState,String taskName){
        model.getTask(tasks,merUserId,checkState,taskName,new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().doSuccess(tag,json);
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
