package com.huami.merchant.activity.service.model;

import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/23.
 */

public interface ValueServiceModelInter extends BaseModelInter{
    void getValueService(String url, InterLoadListener listener) throws Exception;

    void getValueRightService(String url,String packageType,String page,String number,InterLoadListener listener) throws Exception;
}
