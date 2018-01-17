package com.huami.merchant.activity.task.model;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/17.
 */

public interface AuditLastStepModelInter extends BaseModelInter{
    void getAuditTag(String url,String type);
}
