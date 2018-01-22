package com.huami.merchant.activity.task.model;

import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by henry on 2018/1/18.
 */

public interface AuditSubmitModelInter extends BaseModelInter{
    void submitAudit(String url,AuditResult result) throws Exception;
}
