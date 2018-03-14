package com.huami.merchant.activity.task;
import android.view.View;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import butterknife.OnClick;

public class TaskReleaseFinishActivity extends MvpBaseActivity {

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_release_finish;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        back_main_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(TaskEditActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_menu.setText("确认");
        t_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(TaskEditActivity.class);
                finish();
            }
        });
    }

    /**
     * 加速审批
     */
    @OnClick(R.id.complete_approve)
    public void completeApproval(){
        startActivity(this, SingleValueActivity.class,"entryId","2");
    }
    /**
     * 加速审批
     */
    @OnClick(R.id.purchase)
    public void purchase(){
        startActivity(this, SingleValueActivity.class,"entryId","12");
    }
}
