package com.huami.merchant.activity.user.model;

import com.huami.merchant.code.ErrorCode;

/**
 * Created by Administrator on 2017/9/21.
 */

public interface LoginListener {
    void success();
    /**
     * @param tag 对应的是请求的标记
     * @param error_code 对应的是请求返回的code编码 1用户名密码错误,2异常错误
     */
    void Failure(Object tag, ErrorCode error_code);
}
