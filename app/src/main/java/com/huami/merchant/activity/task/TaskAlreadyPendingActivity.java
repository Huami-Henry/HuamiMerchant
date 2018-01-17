package com.huami.merchant.activity.task;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.paper.PaperPendingDetailActivity;
import com.huami.merchant.activity.task.adapter.TaskPreviewAdapter;
import com.huami.merchant.activity.task.presenter.TaskAlreadyPresenter;
import com.huami.merchant.activity.task.viewInter.TaskPreviewInter;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.bean.AuditTime;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.AuditUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class TaskAlreadyPendingActivity extends MvpBaseActivity<TaskAlreadyPresenter,TaskAlreadyPendingActivity> implements TaskPreviewInter,XRecyclerView.LoadingListener,OnRecycleItemClickListener{
    private List<TaskPreviewData> datas = new ArrayList<>();
    private LinearLayoutManager manager;
    private TaskPreviewAdapter adapter;
    @BindView(R.id.task_preview_recycle)
    XRecyclerView task_preview_recycle;
    @BindView(R.id.sp_check_time)
    Spinner sp_check_time;
    @BindView(R.id.sp_check_result)
    Spinner sp_check_result;
    private int page = 1;
    private String task_name;
    private String task_id;
    private String checkTimes;
    private String passState;
    private ArrayAdapter<String> adapter_audit;
    private ArrayAdapter<String> adapter_pass;
    private List<String> auditTimes=new ArrayList<>();
    private List<String> pass=new ArrayList<>();
    @Override
    protected TaskAlreadyPresenter getPresenter() {
        return new TaskAlreadyPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_task_already_pending;
    }
    @Override
    protected void initData() {
        task_id = getIntent().getStringExtra("task_id");
        if (TextUtils.isEmpty(task_id)) {
            showToast("数据出错,请稍后操作");
            finish();
        }
        manager = new LinearLayoutManager(this);
        task_preview_recycle.setLayoutManager(manager);
        adapter = new TaskPreviewAdapter(datas,true,this);
        task_preview_recycle.setAdapter(adapter);
        showLoading();
        presenter.getTaskAlreadyPending(datas,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK,task_id,page,checkTimes,passState,task_name);
        presenter.getMaxAudit(BaseConsts.BASE_URL_TASK_getMaxAudit);
    }
    @Override
    protected void initView() {
        sp_check_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showLoading();
                checkTimes = String.valueOf(position + 1);
                presenter.getTaskAlreadyPending(datas,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK,task_id,page,checkTimes,passState,task_name);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_check_result.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showLoading();
                passState = String.valueOf(position + 2);
                presenter.getTaskAlreadyPending(datas,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK,task_id,page,checkTimes,passState,task_name);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }
    @Override
    public void viewLoadSuccess(Object tag, String json) {
        endLoading();
        if (tag.equals(BaseConsts.BASE_URL_ALREADY_REVIEW_TASK)) {
            adapter.notifyDataSetChanged();
        } else {
            try {
                JSONObject object = new JSONObject(json);
                int code = object.getInt("code");
                if (code == 0) {
                    int maxAudit = object.getInt("maxAudit");
                    for (int i=0;i<maxAudit;i++) {
                        String s = AuditUtil.upCase(i+1);
                        auditTimes.add(s);
                    }
                    pass.add("通过");
                    pass.add("不通过");
                    adapter_audit = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,auditTimes);
                    adapter_audit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_check_time.setAdapter(adapter_audit);

                    adapter_pass = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,pass);
                    adapter_pass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_check_result.setAdapter(adapter_pass);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void viewLoadFailure(Object tag, ErrorCode errorCode) {
        endLoading();
        switch (errorCode) {
            case PARAMA_EMPTY:
                showToast("用户未登录");
                startActivity(this, MvpLoginActivity.class);
                finish();
                break;
            case TRY_CATCH:
                showToast("数据出错,请稍后尝试");
                break;
            case ACTION_FAILURE:

                break;
            case NET_FAILURE:
                showToast("网络出错,请检查网络后尝试");
                break;
        }
    }
    @Override
    public void onRefresh(){
        page = 1;
        datas.clear();
        presenter.getTaskAlreadyPending(datas,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK,task_id,page,checkTimes,passState,task_name);
    }
    @Override
    public void onLoadMore() {
        page++;
        presenter.getTaskAlreadyPending(datas,BaseConsts.BASE_URL_ALREADY_REVIEW_TASK,task_id,page,checkTimes,passState,task_name);
    }
    @Override
    public void onItemClick(View v, int position) {
        switch (v.getId()) {
            case R.id.pending_task:
                TaskPreviewData data = datas.get(position);
                startActivity(this,PaperPendingDetailActivity.class,
                        new String[]{
                                "taskPaperId",
                                "usercase_id",
                                "isHistory",
                                "uca_check_usercase_id",
                                "pass",
                                "shop_id_str","shop_name_str",
                                "shop_price_str",
                                "shop_region_str",
                                "shop_address_str",
                                "checkTimes","checkState","already"},
                        new Object[]{
                                String.valueOf(data.getTaskpaper_id()),
                                String.valueOf(data.getUsercase_id()),
                                "2",
                                "",
                                "",
                                String.valueOf(data.getShop_id()),
                                data.getShop_name(),
                                String.valueOf(data.getPrice()),
                                data.getRegion_name(),
                                data.getShop_address(),
                                data.getCheck_times(),
                                data.getState(),
                                false
                                });
                break;
        }
    }
}
