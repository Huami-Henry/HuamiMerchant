package com.huami.merchant.activity.task.model;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;
/**
 * Created by Henry on 2018/1/8.
 */
public interface TaskEditModelInter extends BaseModelInter {
    void getEditTask(String task_id,InterLoadListener listener) throws Exception;
}
