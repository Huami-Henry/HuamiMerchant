package com.huami.merchant.activity.task.model;
import com.huami.merchant.bean.TaskPaperInfo;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;
import java.util.List;

/**
 * Created by Henry on 2018/1/12.
 */

public interface TaskPaperModelInter extends BaseModelInter {
    void getTaskPaper(List<TaskPaperInfo> papers, String uuid, int page,InterLoadListener listener);
}
