package com.huami.merchant.activity.service.viewInter;

import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseViewInter;

/**
 * Created by henry on 2018/1/23.
 */

public interface ValueServiceViewInter extends BaseViewInter {
    void success(String tag,String json);
    void failure(String tag, ErrorCode code);
}
