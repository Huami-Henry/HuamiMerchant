package com.huami.merchant.activity.exhibition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.activity.exhibition.view.ExhibitionViewInter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

public class ExhibitionPublishActivity extends MvpBaseActivity implements ExhibitionViewInter {

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_exhibition_publish;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }

    @Override
    public void uploadSuccess(String json, String tag) {

    }

    @Override
    public void uploadFailure(String tag, ErrorCode code) {

    }
}
