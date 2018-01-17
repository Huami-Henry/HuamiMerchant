package com.huami.merchant.activity.task.model;

import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by Henry on 2018/1/16.
 */

public interface PaperPendingModelInter extends BaseModelInter {
    void getPendingDetail(String url,String usercase_id, String isHistory, InterLoadListener listener);
    void getPendingDetail(String url,String usercase_id, String checkCaseId,String isHistory, InterLoadListener listener);
    void getAlreadyPendingDetail(String url,String userCaseId, String uca_check_usercase_id,String pass, InterLoadListener listener);
}
