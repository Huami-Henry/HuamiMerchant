package com.huami.merchant.util.request;

import com.squareup.okhttp.RequestBody;

/**
 * Created by Administrator on 2017/7/6.
 */

public class ProgressHelper {
    /**
     * 包装请求体用于上传文件的回调
     * @param requestBody 请求体RequestBody
     * @param progressRequestListener 进度回调接口
     * @return 包装后的进度回调请求体
     */
    public static ProgressRequestBody addProgressRequestListener(String tag,RequestBody requestBody, ProgressRequestListener progressRequestListener){
        //包装请求体
        return new ProgressRequestBody(tag,requestBody,progressRequestListener);
    }
}
