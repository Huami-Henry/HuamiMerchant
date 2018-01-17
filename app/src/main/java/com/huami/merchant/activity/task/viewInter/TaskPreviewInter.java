package com.huami.merchant.activity.task.viewInter;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseViewInter;

/**
 * Created by Henry on 2018/1/8.
 */

public interface TaskPreviewInter extends BaseViewInter {
    void viewLoadSuccess(Object tag,String json);
    void viewLoadFailure(Object tag, ErrorCode errorCode);
}
