package com.huami.merchant.fragment.listener;

import com.huami.merchant.code.ErrorCode;

/**
 * Created by Henry on 2018/1/4.
 */

public interface InterLoadListener {
    void loadSuccess(Object tag,String json);
    void loadFailure(Object tag, ErrorCode error_code);
}
