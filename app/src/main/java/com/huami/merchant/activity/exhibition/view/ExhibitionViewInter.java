package com.huami.merchant.activity.exhibition.view;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseViewInter;
/**
 * Created by henry on 2018/3/16.
 */

public interface ExhibitionViewInter extends BaseViewInter {
    void uploadSuccess(String json,String tag);
    void uploadFailure(String tag, ErrorCode code);
}
