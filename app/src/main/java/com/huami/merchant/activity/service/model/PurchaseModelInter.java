package com.huami.merchant.activity.service.model;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/2/5.
 */

public interface PurchaseModelInter extends BaseModelInter{
    void getPrice(String url,String token, String uuid, String packageId, String number, InterLoadListener listener) throws Exception;
    void payService(String url, String token, String uuid, String cellPhone, String contact, String packageId, String number, InterLoadListener interLoadListener) throws Exception;
}
