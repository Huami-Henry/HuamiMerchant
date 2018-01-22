package com.huami.merchant.activity.task.model;

import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/19.
 */

public interface TaskPublishModelInter extends BaseModelInter{
    void uploadTaskIcon(String path) throws Exception;
}
