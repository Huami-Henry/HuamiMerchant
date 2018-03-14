package com.huami.merchant.activity.task.model;

import com.huami.merchant.bean.AlreadyService.AlreadyServiceData;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

import java.util.List;

/**
 * Created by henry on 2018/2/7.
 */

public interface AlreadyBuyModelInter extends BaseModelInter{
    void alreadyBuy(String url, String uuid, String merUserId, List<AlreadyServiceData> services, InterLoadListener listener) throws Exception;
}
