package com.huami.merchant.fragment.viewInter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseViewInter;

/**
 * Created by Henry on 2018/1/4.
 */
public interface TaskViewInter extends BaseViewInter {
    void doSuccess(Object tag,String json);
    void doFailure(Object tag, ErrorCode code);
}
