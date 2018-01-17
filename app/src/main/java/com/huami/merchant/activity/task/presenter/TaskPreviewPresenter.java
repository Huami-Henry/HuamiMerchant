package com.huami.merchant.activity.task.presenter;
import com.huami.merchant.activity.task.TaskPreviewActivity;
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
public class TaskPreviewPresenter extends BasePresenter<TaskPreviewActivity,TaskPreviewModelImp> {
    @Override
    protected TaskPreviewModelImp getModel() {
        return new TaskPreviewModelImp();
    }
    public void getTaskPreviewList(List<TaskPreviewData> tasks,String task_id,int page, String taskName){
        model.getPreviewTask(tasks, BaseApplication.UUID, task_id,String.valueOf(page), taskName, new InterLoadListener() {
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
