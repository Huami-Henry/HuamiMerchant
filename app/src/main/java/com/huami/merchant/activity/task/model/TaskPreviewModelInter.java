package com.huami.merchant.activity.task.model;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;
import java.util.List;
/**
 * Created by Henry on 2018/1/8.
 */
public interface TaskPreviewModelInter extends BaseModelInter {
    void getPreviewTask(List<TaskPreviewData> tasks, String uuid,String task_id,String page,String task_name,InterLoadListener listener);
}
