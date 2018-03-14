package com.huami.merchant.activity.task.model;

import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/24.
 */

public interface TaskFailureReasonModelInter extends BaseModelInter{
    void getFailureReason(String url, String task_id, InterLoadListener listener) throws Exception;
}
