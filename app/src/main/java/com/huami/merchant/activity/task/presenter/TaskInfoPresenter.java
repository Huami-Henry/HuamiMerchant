package com.huami.merchant.activity.task.presenter;
import com.huami.merchant.activity.task.TaskInfoActivity;
import com.huami.merchant.activity.task.model.TaskPublishModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;
/**
 * Created by henry on 2018/1/26.
 */
public class TaskInfoPresenter extends BasePresenter<TaskInfoActivity,TaskPublishModelImp> implements InterLoadListener{
    @Override
    protected TaskPublishModelImp getModel() {
        return new TaskPublishModelImp();
    }
    public void updateTaskInfoImage(String path) throws Exception{
        model.setCallBack(this);
        model.uploadTaskIcon(path);
    }

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
}
