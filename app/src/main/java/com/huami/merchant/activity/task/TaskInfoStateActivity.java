package com.huami.merchant.activity.task;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.activity.service.presenter.TaskInfoStatePresenter;
import com.huami.merchant.activity.task.presenter.TaskEditPresenter;
import com.huami.merchant.activity.web.AgentWebActivity;
import com.huami.merchant.activity.web.HtmlActivity;
import com.huami.merchant.bean.NetTaskBean;
import com.huami.merchant.bean.TaskCondition;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.ConditionUtil;
import com.huami.merchant.util.TimeUtil;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskInfoStateActivity extends MvpBaseActivity<TaskInfoStatePresenter,TaskInfoStateActivity> implements TaskViewInter, View.OnClickListener{
    @BindView(R.id.task_state)
    TextView task_state;
    @BindView(R.id.task_set_count_price)
    TextView task_set_count_price;
    @BindView(R.id.task_failure_liner)
    LinearLayout task_failure_liner;
    @BindView(R.id.task_release_liner)
    LinearLayout task_release_liner;
    @BindView(R.id.task_finish_liner)
    LinearLayout task_finish_liner;
    @BindView(R.id.examine)
    TextView examine;
    @BindView(R.id.check_result)
    TextView check_result;
    @BindView(R.id.data_statistics)
    TextView data_statistics;
    @BindView(R.id.look_result)
    TextView look_result;
    @BindView(R.id.edit_task)
    TextView edit_task;
    @BindView(R.id.finish_check_result)
    TextView finish_check_result;
    @BindView(R.id.finish_data_statistics)
    TextView finish_data_statistics;
    @BindView(R.id.task_icon)
    ImageView task_icon;
    @BindView(R.id.task_name)
    TextView task_name;
    @BindView(R.id.task_accept_time)
    TextView task_accept_time;
    @BindView(R.id.task_accept_end_date)
    TextView task_accept_end_date;
    @BindView(R.id.task_in_shop_time)
    TextView task_in_shop_time;
    @BindView(R.id.task_condition)
    TextView task_condition;
    @BindView(R.id.task_iv_attention)
    TextView task_iv_attention;
    @BindView(R.id.task_set_count_price_iv)
    TextView task_set_count_price_iv;
    @BindView(R.id.total_count_tv)
    TextView total_count_tv;
    @BindView(R.id.task_web_info)
    WebView task_web_info;
    @BindView(R.id.task_paper_iv)
    TextView task_paper_iv;
    @BindView(R.id.train_paper_iv)
    TextView train_paper_iv;
    @BindView(R.id.value_service)
    CardView value_service;
    private int check_state;
    private int total_count;
    private int total_money;
    private String task_id;
    @Override
    protected TaskInfoStatePresenter getPresenter() {
        return new TaskInfoStatePresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_info_state;
    }

    @Override
    protected void initData() {
        check_state = getIntent().getIntExtra("check_state", 0);
        total_count = getIntent().getIntExtra("total_count", 0);
        total_money = getIntent().getIntExtra("total_money", 0);
        task_id = getIntent().getStringExtra("task_id");
        switch (check_state) {
            case 2:
                task_release_liner.setVisibility(View.VISIBLE);
                break;
            case 3:
                task_failure_liner.setVisibility(View.VISIBLE);
                break;
            case 4:
                task_finish_liner.setVisibility(View.VISIBLE);
                break;
        }
    }
    @Override
    protected void initView() {
        switch (check_state) {
            case 1:
                task_state.setText("待审核");
                value_service.setVisibility(View.VISIBLE);
                break;
            case 2:
                task_state.setText("已发布");
                break;
            case 3:
                task_state.setText("发布失败");
                break;
            case 4:
                task_state.setText("已结束");
                break;
        }
        total_count_tv.setText(total_count+"单");
        task_set_count_price.setText(total_money+"元");
        examine.setOnClickListener(this);
        check_result.setOnClickListener(this);
        data_statistics.setOnClickListener(this);
        look_result.setOnClickListener(this);
        edit_task.setOnClickListener(this);
        finish_check_result.setOnClickListener(this);
        finish_data_statistics.setOnClickListener(this);
        try {
            presenter.getTaskInfo(task_id);
        } catch (Exception e) {
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("任务详细");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        try {
            Gson gson = new Gson();
            NetTaskBean bean = gson.fromJson(json, NetTaskBean.class);
            setTaskInfo(bean);
        } catch (JsonSyntaxException e) {
            showToast("数据异常");
        }
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        showToast("网络异常,请稍后再试。");
    }
    /**
     * 数据转换刷新页面
     * @param bean
     */
    private void setTaskInfo(NetTaskBean bean) {
        NetTaskBean.NetTaskData data = bean.getData();
        this.data=data;
        refreshData(data);
    }

    private NetTaskBean.NetTaskData data;
    public void refreshData(NetTaskBean.NetTaskData taskInfo){
        try {
            if (!TextUtils.isEmpty(taskInfo.getTask_icon())) {
                Glide.with(task_icon.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(task_icon);
            }
            task_name.setText(taskInfo.getTask_name());
            if (taskInfo.getAccept_begin_date()!=0) {
                task_accept_time.setText(TimeUtil.stampToDate(taskInfo.getAccept_begin_date()));
            }
            if (taskInfo.getAccept_end_date()!=0) {
                task_accept_end_date.setText(TimeUtil.stampToDate(taskInfo.getAccept_end_date()));
            }
            task_in_shop_time.setText(taskInfo.getRequire_shop_time()+"分钟");
            try {
                List<TaskCondition> condition = taskInfo.getTaskCondition();
                if (condition != null) {
                    StringBuffer buffer = new StringBuffer();
                    for (TaskCondition con : condition) {
                        String con_result = ConditionUtil.getCondition(con);
                        buffer.append(con_result+",");
                    }
                    String con = "";
                    if (!TextUtils.isEmpty(buffer.toString())) {
                        con = buffer.substring(0, buffer.length() - 1);
                    } else {
                        con = buffer.toString();
                    }
                    task_condition.setText(con);
                }
            } catch (Exception e) {
            }
            try {
                if (taskInfo.getTaskAttention()!=null) {
                    String taskAtt = "";
                    int i=1;
                    for (String s : taskInfo.getTaskAttention()) {
                        taskAtt = taskAtt+"\n"+i+":"+s ;
                        i++;
                    }
                    task_iv_attention.setText(taskAtt);
                }
            } catch (Exception e) {
                task_iv_attention.setText("尚未添加");
            }
            if (!TextUtils.isEmpty(taskInfo.getTask_info())) {
                task_web_info.loadDataWithBaseURL(null, taskInfo.getTask_info(), "text/html", "utf-8", null);
            }
            if (!TextUtils.isEmpty(taskInfo.getTask_paper_name())) {
                task_paper_iv.setText(taskInfo.getTask_paper_name());
            }
            if (!TextUtils.isEmpty(taskInfo.getTrain_name())) {
                train_paper_iv.setText(taskInfo.getTrain_name());
            }
            task_set_count_price_iv.setText("任务点:(" + taskInfo.getShop_count()+")");
        } catch (Exception e) {
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.examine:
                startActivity(this,
                        TaskPreviewActivity.class,
                        new String[]{"task_id"},new Object[]{task_id});
                break;
            case R.id.check_result:
                startActivity(this,
                        TaskAlreadyPendingActivity.class,
                        new String[]{"task_id"},
                        new String[]{task_id});
                break;
            case R.id.data_statistics:
                startActivity(this, DataStatisticsActivity.class,new String[]{"task_id"},new String[]{task_id});
                break;
            case R.id.look_result:
                startActivity(this,
                        TaskFailureReasonActivity.class,
                        new String[]{"task_id"},
                        new String[]{task_id});
                break;
            case R.id.edit_task:
                startActivity(this,
                        TaskEditActivity.class,
                        new String[]{"task_id","edit"},
                        new Object[]{task_id,true});
                break;
            case R.id.finish_check_result:
                startActivity(this,
                        TaskAlreadyPendingActivity.class,
                        new String[]{"task_id"},
                        new String[]{task_id});
                break;
            case R.id.finish_data_statistics:
                startActivity(this, DataStatisticsActivity.class,new String[]{"task_id"},new String[]{task_id});
                break;
        }
    }
    @OnClick(R.id.task_web_content)
    public void webInfo(){
        startActivity(this, HtmlActivity.class,"html",data.getTask_info());
    }
    @OnClick(R.id.task_paper_iv)
    public void checkTaskPaper(){
        startActivity(this, AgentWebActivity.class,"url", BaseConsts.TASK_PAPER_PREVIEW +data.getTaskpaper_id()+"&temp=0");
    }
    @OnClick(R.id.train_paper_iv)
    public void checkTrain(){
        startActivity(this, AgentWebActivity.class,"url",BaseConsts.TRAIN_PREVIEW +data.getTrain_id());
    }
    @OnClick(R.id.task_set_count_price_iv)
    public void getTaskPoint(){
        startActivity(this, TaskPointReviewActivity.class, "taskId", task_id);
    }
    /**
     * 加速审批
     */
    @OnClick(R.id.value_service)
    public void completeApproval(){
        startActivity(this, SingleValueActivity.class,"entryId","2");
    }
}
