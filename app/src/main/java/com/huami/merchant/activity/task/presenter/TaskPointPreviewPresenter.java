package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.task.TaskPointReviewActivity;
import com.huami.merchant.activity.task.model.TaskPointModelImp;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

import java.util.List;

/**
 * Created by henry on 2018/2/6.
 */

public class TaskPointPreviewPresenter extends BasePresenter<TaskPointReviewActivity,TaskPointModelImp> {
    @Override
    protected TaskPointModelImp getModel() {
        return new TaskPointModelImp();
    }
    /**
     * 获取门店列表
     * @param shops
     * @param uuid
     * @param page
     */
    public void getTaskShops(List<TaskPointInfo> shops, String url, String uuid, String taskId, int page){
        model.getShop(shops,url, uuid,taskId, page, new InterLoadListener() {
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
