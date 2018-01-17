package com.huami.merchant.activity.user.viewInter;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseViewInter;

/**
 * Created by Henry on 2017/9/21.
 */

public interface LoginViewInter extends BaseViewInter {
    void doSuccess();
    void doFailure(String tag, ErrorCode code);
}
