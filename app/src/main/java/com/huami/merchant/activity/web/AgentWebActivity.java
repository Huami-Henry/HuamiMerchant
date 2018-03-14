package com.huami.merchant.activity.web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.huami.merchant.R;
import com.just.library.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AgentWebActivity extends AppCompatActivity {
    @BindView(R.id.web_content)
    LinearLayout web_content;
    private AgentWeb mAgentWeb;
    private String html_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);
        ButterKnife.bind(this);
        html_url = getIntent().getStringExtra("url");
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(web_content, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .closeProgressBar()// 使用默认进度条
                .setWebChromeClient(null)
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(html_url);
    }
}
