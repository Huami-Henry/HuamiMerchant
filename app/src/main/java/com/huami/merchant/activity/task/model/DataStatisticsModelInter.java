package com.huami.merchant.activity.task.model;

import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/25.
 */
public interface DataStatisticsModelInter extends BaseModelInter{
    void getTaskDataStatistics(String url, int taskId, InterLoadListener listener) throws Exception;
}
