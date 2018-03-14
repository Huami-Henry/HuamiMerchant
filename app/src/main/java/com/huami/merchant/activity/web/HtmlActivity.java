package com.huami.merchant.activity.web;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import butterknife.BindView;

public class HtmlActivity extends MvpBaseActivity {
    @BindView(R.id.web_activity)
    WebView web_activity;
    private String html;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_html;
    }

    @Override
    protected void initData() {
        html = getIntent().getStringExtra("html");
    }

    @Override
    protected void initView() {
        try {
            if (!TextUtils.isEmpty(html)) {
                web_activity.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
            } else {
                showToast("数据异常。");
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast("数据异常。");
            finish();
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("详情");
    }
}
