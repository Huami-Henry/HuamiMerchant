package com.huami.merchant.activity.task;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.presenter.AuditSubmitPresenter;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.AppManager;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class AuditStarActivity extends MvpBaseActivity<AuditSubmitPresenter,AuditStarActivity> implements TaskViewInter{
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    private AuditResult result;
    @Override
    protected AuditSubmitPresenter getPresenter() {
        return new AuditSubmitPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_audit_star;
    }

    @Override
    protected void initData() {
        result = (AuditResult) getIntent().getSerializableExtra("result");
    }

    @Override
    protected void initView() {
        back_main_bar.setVisibility(View.GONE);
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_menu.setText("提交");
        t_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                if (rating == 0) {
                    showToast("请先给用户评分再提交");
                } else {
                    try {
                        showLoading();
                        presenter.submitAudit(BaseConsts.BASE_URL_TASK_PASS, result);
                    } catch (Exception e) {
                        showToast("" + e.getMessage());
                        endLoading();
                    }
                }
            }
        });
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                showToast("问卷审核成功！");
                setResult(RESULT_OK);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("网络异常,请稍后再试。");
    }
}
