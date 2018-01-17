package com.huami.merchant.activity.task.model;

import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

import java.util.List;

/**
 * Created by Henry on 2018/1/15.
 */

public interface TaskPointModelInter extends BaseModelInter {
    void getShop(List<TaskPointInfo> shops, String url,String uuid,String taskId, int page, InterLoadListener listener);
}
