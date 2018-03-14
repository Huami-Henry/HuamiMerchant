package com.huami.merchant.fragment.model;

import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by Henry on 2018/1/4.
 */

public interface CenterModelInter extends BaseModelInter {
    void getCenterInfo(String uuid,String merUserId,InterLoadListener listener);
}
