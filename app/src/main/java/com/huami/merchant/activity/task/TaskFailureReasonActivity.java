package com.huami.merchant.activity.task;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.activity.task.presenter.TaskFailureReasonPresenter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskFailureReasonActivity extends MvpBaseActivity<TaskFailureReasonPresenter,TaskFailureReasonActivity> implements TaskViewInter{
    private String task_id;
    @BindView(R.id.failure_reason)
    TextView failure_reason;
    @Override
    protected TaskFailureReasonPresenter getPresenter() {
        return new TaskFailureReasonPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_failure_reson;
    }

    @Override
    protected void initData() {
        task_id=getIntent().getStringExtra("task_id");
        try {
            presenter.getFailureReason(BaseConsts.BASE_URL_TASK_FAILURE_REASON,task_id);
        } catch (Exception e) {
            showToast("数据异常");
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("失败原因");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        try {
            JSONObject object = new JSONObject(json);
            String content = object.getString("content");
            failure_reason.setText(content);
        } catch (JSONException e) {
           showToast("数据异常");
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }
    @OnClick(R.id.value_service)
    public void valueService(){
        startActivity(this, SingleValueActivity.class, "entryId", "3");
    }
}
