package com.huami.merchant.fragment.model;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;
import java.util.List;
/**
 * Created by Henry on 2018/1/4.
 */

public interface TaskListModelInter extends BaseModelInter {
    void getTask(List<TaskInfo> tasks, String merUserId ,String check_state,String taskName,InterLoadListener listener);
}
