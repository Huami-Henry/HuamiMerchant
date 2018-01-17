package com.huami.merchant.util.request;

/**
 * Created by Henry on 2017/7/6.
 */

public interface ProgressRequestListener {
    void onRequestProgress(String req_tag, long bytesWritten, long contentLength, boolean done);
}
