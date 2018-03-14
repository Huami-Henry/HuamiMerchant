package com.huami.merchant.activity.task;
import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.just.library.AgentWeb;

import butterknife.BindView;
import butterknife.OnClick;

public class DataStatisticsActivity extends MvpBaseActivity{
    private String taskId;
    @BindView(R.id.data_content)
    LinearLayout data_content;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_data_statistics;
    }

    @Override
    protected void initData() {
        taskId = getIntent().getStringExtra("task_id");
    }

    @Override
    protected void initView() {
        String html = BaseConsts.DATA_STATISTICS + taskId;
        showLoading();
        AgentWeb.with(this)//传入Activity
                .setAgentWebParent(data_content, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .closeProgressBar()// 使用默认进度条
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(html);

    }
    private WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
    };
    private WebViewClient mWebViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            endLoading();
        }
    };
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("数据统计");
    }
    @OnClick(R.id.download_data)
    public void downloadData(){
        startActivity(this, SingleValueActivity.class,"entryId","5");
    }
    @OnClick(R.id.data_statistics)
    public void dataStatistics(){
        startActivity(this, SingleValueActivity.class,"entryId","6");
    }
}
