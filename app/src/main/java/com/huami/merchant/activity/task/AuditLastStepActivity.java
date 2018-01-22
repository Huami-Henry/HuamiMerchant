package com.huami.merchant.activity.task;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.model.TaskTag;
import com.huami.merchant.activity.task.presenter.AuditLastStepPresenter;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.tagview.TagContainerLayout;
import com.huami.merchant.designView.tagview.TagView;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class AuditLastStepActivity extends MvpBaseActivity<AuditLastStepPresenter,AuditLastStepActivity> implements TaskViewInter,TagView.OnTagClickListener{
    @BindView(R.id.content_postil)
    EditText content_postil;
    @BindView(R.id.tag_container)
    TagContainerLayout tag_container;
    @BindView(R.id.postil)
    TextView postil;
    private AuditResult auditResult;
    private List<TaskTag.TaskTagInfo> infos;
    private List<String> tags=new ArrayList<>();
    private boolean pass;
    private String type="1";
    @Override
    protected AuditLastStepPresenter getPresenter() {
        return new AuditLastStepPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_audit_last_step;
    }

    @Override
    protected void initData() {
        auditResult = (AuditResult) getIntent().getSerializableExtra("auditResult");
        pass = getIntent().getBooleanExtra("pass",false);
        if (pass) {
            type = "1";
        } else {
            type = "2";
        }
        if (auditResult == null) {
            showToast("数据传递有误请重新尝试。");
            finish();
        }
    }

    @Override
    protected void initView() {
        tag_container.setOnTagClickListener(this);
        try {
            showLoading();
            presenter.getAuditTag(BaseConsts.BASE_URL_TASK_TAG,type);
        } catch (Exception e) {
            endLoading();
            showToast("网路数据出错,请稍后尝试。");
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("批注");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        try {
            Gson gson = new Gson();
            TaskTag taskTag = gson.fromJson(json, TaskTag.class);
            infos = taskTag.getData();
            if (infos != null) {
                for (TaskTag.TaskTagInfo info : infos) {
                    tags.add(info.getDescs());
                }
                tag_container.setTags(tags);
            } else {
                showToast("数据结构出错。");
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("网络数据出错,请稍后尝试。");
    }
    @OnClick(R.id.make_sure)
    public void makeSure(){
        String postil = content_postil.getText().toString();
        if (auditResult == null) {
            showToast("数据格式出错,请稍后尝试。");
            return;
        }
        auditResult.setContent(postil);
        if (TextUtils.isEmpty(tag_audit)) {
            showToast("请先选择审核标签。");
            return;
        }
        if (infos == null) {
            showToast("数据格式出错,请稍后尝试。");
            return;
        }
        TaskTag.TaskTagInfo info = infos.get(position);
        int id = info.getId();
        auditResult.setResultId(String.valueOf(id));
        startActivityForResult(this,AuditStarActivity.class,new String[]{"result"},new Object[]{auditResult},10001);
    }

    private int position;
    private String tag_audit;
    @Override
    public void onTagClick(int position, String text) {
        this.position = position;
        postil.setVisibility(View.VISIBLE);
        postil.setText(tags.get(position));
        tag_audit = tags.get(position);
    }
    @OnClick(R.id.postil)
    public void delTag(){
        postil.setVisibility(View.GONE);
        tag_audit = "";
    }
    @Override
    public void onTagLongClick(int position, String text) {

    }

    @Override
    public void onTagCrossClick(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
