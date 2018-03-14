package com.huami.merchant.activity.task.presenter;
import com.huami.merchant.activity.task.TaskPaperListActivity;
import com.huami.merchant.activity.task.model.TaskPaperModelImp;
import com.huami.merchant.bean.TaskPaperInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by Henry on 2018/1/12.
 */

public class TaskPaperPresenter extends BasePresenter<TaskPaperListActivity,TaskPaperModelImp> {
    @Override
    protected TaskPaperModelImp getModel() {
        return new TaskPaperModelImp();
    }
    public void getTaskPaper(List<TaskPaperInfo> papers,String url, String uuid, int page){
        model.getTaskPaper(papers, url,uuid, page, new InterLoadListener() {
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
