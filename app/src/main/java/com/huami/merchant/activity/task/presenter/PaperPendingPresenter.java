package com.huami.merchant.activity.task.presenter;

import com.huami.merchant.activity.paper.PaperPendingDetailActivity;
import com.huami.merchant.activity.task.model.PaperPendingModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by Henry on 2018/1/16.
 */

public class PaperPendingPresenter extends BasePresenter<PaperPendingDetailActivity,PaperPendingModelImp> {
    @Override
    protected PaperPendingModelImp getModel() {
        return new PaperPendingModelImp();
    }
    /**
     * 获取待审任务详情
     * @param url
     * @param usercase_id
     * @param isHistory
     */
    public void getPendingDetail(String url,String usercase_id, String isHistory) {
        model.getPendingDetail(url, usercase_id, isHistory, new InterLoadListener() {
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
    /**
     * 获取待审任务详情
     * @param url
     * @param usercase_id
     * @param isHistory
     */
    public void getPendingDetail(String url,String usercase_id,String check_case_id, String isHistory) {
        model.getPendingDetail(url, usercase_id, isHistory, new InterLoadListener() {
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
    /**
     * 获取已审任务详情
     * @param url
     * @param userCaseId
     * @param uca_check_usercase_id
     * @param pass
     * @param listener
     */
    public void getAlreadyPendingDetail(String url,String userCaseId, String uca_check_usercase_id, String pass) {
        model.getAlreadyPendingDetail(url, userCaseId, uca_check_usercase_id,pass, new InterLoadListener() {
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
