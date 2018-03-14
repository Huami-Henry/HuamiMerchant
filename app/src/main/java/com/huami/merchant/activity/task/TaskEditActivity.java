package com.huami.merchant.activity.task;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.presenter.TaskEditPresenter;
import com.huami.merchant.bean.NetTaskBean;
import com.huami.merchant.bean.TaskCondition;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.bean.TaskPublishBase;
import com.huami.merchant.bean.TaskShop;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.ConditionUtil;
import com.huami.merchant.util.TimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.huami.merchant.R.id.end;
import static com.huami.merchant.R.id.number;
import static com.huami.merchant.R.id.task_info;

public class TaskEditActivity extends MvpBaseActivity<TaskEditPresenter,TaskEditActivity> implements TaskViewInter,View.OnClickListener{
    @BindView(R.id.task_icon)
    ImageView task_icon;
    @BindView(R.id.task_web_info)
    WebView task_web_info;
    @BindView(R.id.task_iv_attention)
    TextView task_iv_attention;
    @BindView(R.id.task_paper_iv)
    TextView task_paper_iv;
    @BindView(R.id.task_set_count_price_iv)
    TextView task_set_count_price_iv;
    @BindView(R.id.train_paper_iv)
    TextView train_paper_iv;
    @BindView(R.id.task_name)
    TextView task_name;
    @BindView(R.id.change_task_icon)
    TextView change_task_icon;
    @BindView(R.id.task_set)
    TextView task_set;
    @BindView(R.id.task_attention_edit)
    TextView task_attention_edit;
    @BindView(R.id.task_info_edit)
    TextView task_info_edit;
    @BindView(R.id.task_paper_edit)
    TextView task_paper_edit;
    @BindView(R.id.train_paper_edit)
    TextView train_paper_edit;
    @BindView(R.id.task_set_count_price)
    TextView task_set_count_price;
    @BindView(R.id.publish_liner)
    LinearLayout publish_liner;
    @BindView(R.id.task_accept_time)
    TextView task_accept_time;
    @BindView(R.id.task_accept_end_date)
    TextView task_accept_end_date;
    @BindView(R.id.task_in_shop_time)
    TextView task_in_shop_time;
    @BindView(R.id.task_condition)
    TextView task_condition;
    @BindView(R.id.publish_task)
    TextView publish_task;
    @BindView(R.id.task_price)
    TextView task_price;
    @BindView(R.id.task_count)
    TextView task_count;
    private TaskInfo taskInfo;
    private List<TaskShop> shops = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private final int TASK_ICON_CHANGE_REQUEST_CODE = 1001;
    private final int TASK_ATTENTION_CHANGE_REQUEST_CODE = 1002;
    private final int TASK_POINT_MANAGER_REQUEST_CODE = 1003;
    private final int TASK_POINT_SHOP_REQUEST_CODE = 1004;
    private boolean edit;//非编辑的话要请求网络获取数据
    private String taskId;
    private TaskPublishBase publish = new TaskPublishBase();
    private List<String> taskAttention = new ArrayList<>();
    private int total_count;
    private int total_money;

    @Override
    protected TaskEditPresenter getPresenter() {
        return new TaskEditPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_edit;
    }

    @Override
    protected void initData() {
        taskInfo= (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        if (taskInfo == null) {
            taskInfo = new TaskInfo();
        }
        edit=getIntent().getBooleanExtra("edit",false);
        taskId = getIntent().getStringExtra("task_id");
        total_count = getIntent().getIntExtra("total_count",0);
        total_money = getIntent().getIntExtra("total_money",0);
        if (edit) {
            try {
                showLoading();
                presenter.getEditTask(taskId);
            } catch (Exception e) {
                showToast(""+e.getMessage());
            }
        }
    }

    @Override
    protected void initView() {
        task_set.setOnClickListener(this);
        change_task_icon.setOnClickListener(this);
        task_attention_edit.setOnClickListener(this);
        task_info_edit.setOnClickListener(this);
        task_paper_edit.setOnClickListener(this);
        train_paper_edit.setOnClickListener(this);
        task_set_count_price.setOnClickListener(this);
        publish_task.setOnClickListener(this);
        refreshData();
    }
    public void refreshData(){
        if (taskInfo != null) {
            try {
                task_count.setText(total_count+"单");
                task_price.setText(total_money+"元");
                if (!TextUtils.isEmpty(taskInfo.getTask_icon())) {
                    Glide.with(task_icon.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(task_icon);
                }
                task_name.setText(taskInfo.getTask_name());
                if (!TextUtils.isEmpty(taskInfo.getAccept_begin_date())) {
                    task_accept_time.setText(taskInfo.getAccept_begin_date());
                }
                if (!TextUtils.isEmpty(taskInfo.getAccept_end_date())) {
                    task_accept_end_date.setText(taskInfo.getAccept_end_date());
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
                    taskAttention.clear();
                    if (taskInfo.getTaskAttention()!=null) {
                        List<String> task_attention= taskInfo.getTaskAttention();
                        String taskAtt = "";
                        int i=1;
                        for (String s : task_attention) {
                            taskAttention.add(s);
                            taskAtt = taskAtt+"\n"+i+":"+s ;
                            i++;
                        }
                        task_iv_attention.setText(taskAtt);
                    }
                } catch (Exception e) {
                    Log.e("我的刀", e.getMessage());
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
        } else {
            taskInfo = new TaskInfo();
        }
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("编辑任务");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        if (BaseConsts.BASE_URL_TASK_DETAIL_INFO.equals(tag)) {
            NetTaskBean bean = new Gson().fromJson(json, NetTaskBean.class);
            if (bean.getCode() == 0) {
                setTaskInfo(bean);
            }
        } else if (BaseConsts.BASE_URL_TASK_PUBLISH.equals(tag)) {
            try {
                JSONObject object = new JSONObject(json);
                int code = object.getInt("code");
                if (code == 0) {
                    showToast("发布成功");
                    startActivity(this,TaskReleaseFinishActivity.class);
                    finish();
                } else if (code == -666) {
                    showToast("您的登录信息已过期或者在其他设备上登录,请重新登录。");
                }
            } catch (JSONException e) {
                showToast("数据格式出错,请稍后尝试。");
            }
        }

    }
    /**
     * 数据转换刷新页面
     * @param bean
     */
    private void setTaskInfo(NetTaskBean bean) {
        NetTaskBean.NetTaskData data = bean.getData();
        taskInfo.setAccept_begin_date(TimeUtil.stampToDate(data.getAccept_begin_date()));
        taskInfo.setAccept_end_date(TimeUtil.stampToDate(data.getAccept_end_date()));
        taskInfo.setCreate_date(data.getCreate_date());
        taskInfo.setLast_mod(data.getLast_mod());
        taskInfo.setMerchant_id(data.getMerchant_id());
        taskInfo.setOperator_id(data.getOperator_id());
        taskInfo.setShop_count(data.getShop_count());
        taskInfo.setTrainpaper_id(data.getTrainpaper_id());
        taskInfo.setTrain_name(data.getTrain_name());
        taskInfo.setTask_total_count(data.getTask_total_count());
        taskInfo.setTask_price(data.getTask_price());
        taskInfo.setTask_name(data.getTask_name());
        taskInfo.setTask_info(data.getTask_info());
        taskInfo.setTask_id(data.getTask_id());
        taskInfo.setTask_icon(data.getTask_icon());
        taskInfo.setTask_end_date(data.getTask_end_date());
        taskInfo.setTask_desc(data.getTask_desc());
        taskInfo.setState(data.getState());
        taskInfo.setRequire_shop_time(data.getRequire_shop_time());
        taskInfo.setTaskCondition(data.getTaskCondition());
        taskInfo.setTrain_id(data.getTrain_id());
        taskInfo.setTaskAttention(data.getTaskAttention());
        refreshData();
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("网络异常,请稍后尝试。");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == TASK_ICON_CHANGE_REQUEST_CODE && resultCode == RESULT_OK) {
                taskInfo = (TaskInfo) data.getSerializableExtra("taskInfo");
            } else if (requestCode == TASK_ATTENTION_CHANGE_REQUEST_CODE && resultCode == RESULT_OK) {
                taskInfo = (TaskInfo) data.getSerializableExtra("taskInfo");
            } else if (requestCode == TASK_POINT_SHOP_REQUEST_CODE && resultCode == RESULT_OK) {
                String shop = data.getStringExtra("task_shop");
                List<TaskPointInfo> taskShops = new Gson().fromJson(shop, new TypeToken<List<TaskPointInfo>>() {
                }.getType());
                total_count = 0;
                total_money = 0;
                for (TaskPointInfo info : taskShops) {
                    TaskShop taskShop = new TaskShop();
                    taskShop.setMer_price(info.getMer_price());
                    taskShop.setShop_id(info.getShop_id());
                    taskShop.setTotal_num(info.getTotal_num());
                    taskShop.setPrice(info.getMer_price());
                    shops.add(taskShop);
                    total_count += info.getTotal_num();
                    total_money += (info.getTotal_num() * info.getMer_price());
                }
                taskInfo.setShop_count(shops.size());
                task_set_count_price_iv.setText("任务点:(" + taskInfo.getShop_count()+")");
            }
        }
        refreshData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.task_set:
                Intent intent = new Intent(this, TaskTimeActivity.class);
                intent.putExtra("taskInfo", taskInfo);
                startActivityForResult(intent,TASK_ICON_CHANGE_REQUEST_CODE);
                break;
            case R.id.change_task_icon:
                startActivityForResult(this, TaskPublishActivity.class, new String[]{"taskInfo"}, new Object[]{taskInfo},TASK_ICON_CHANGE_REQUEST_CODE);
                break;
            case R.id.task_attention_edit:
                startActivityForResult(this, TaskAttentionActivity.class, new String[]{"taskInfo"}, new Object[]{taskInfo},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.task_info_edit:
                startActivityForResult(this, TaskInfoActivity.class, new String[]{"taskInfo"}, new Object[]{taskInfo},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.task_paper_edit:
                startActivityForResult(this, TaskPaperListActivity.class, new String[]{"taskInfo"}, new Object[]{taskInfo},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.train_paper_edit:
                startActivityForResult(this, TrainActivity.class, new String[]{"taskInfo"}, new Object[]{taskInfo},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.task_set_count_price:
                startActivityForResult(this, TaskPointConfigureActivity.class,new String[]{"taskId"},new String[]{taskId},TASK_POINT_SHOP_REQUEST_CODE);
                break;
            case R.id.publish_task:
                publishTask();
                break;
        }
    }
    /**
     * 发布任务
     */
    private void publishTask() {
        if (TextUtils.isEmpty(taskInfo.getTask_name())) {
            showToast("任务名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(taskInfo.getAccept_begin_date())) {
            showToast("任务开始领取时间必须设置");
            return;
        }
        if (TextUtils.isEmpty(taskInfo.getAccept_end_date())) {
            showToast("任务结束领取时间必须设置");
            return;
        }
        if (TextUtils.isEmpty(taskInfo.getTask_info())) {
            showToast("任务详情必须设置");
            return;
        }
        if (TextUtils.isEmpty(taskInfo.getTask_paper_name())) {
            showToast("调研问卷必须设置");
            return;
        }
        if (TextUtils.isEmpty(taskInfo.getTrain_name())) {
            showToast("任务培训问卷必须设置");
            return;
        }
        if (shops.size() == 0) {
            showToast("任务门店必须勾选");
            return;
        }
        if (TextUtils.isEmpty(BaseApplication.UU_TOKEN)) {
            showToast("您暂时还未登录,请先登录");
            return;
        }
        publish.setUuid(BaseApplication.UU_TOKEN);
        publish.setMerUserId(Integer.valueOf(BaseApplication.UUID));
        publish.setOldTaskId(taskId);
        publish.setTaskPaperId(taskInfo.getTaskpaper_id());
        publish.setTraInfoId(taskInfo.getTrain_id());
        taskInfo.setTask_id(null);
        publish.setTaskinfo(taskInfo);
        publish.setTaskShop(shops);
        publish.setTaskCondition(taskInfo.getTaskCondition());
        publish.setTaskAttention(taskAttention);
        showLoading();
        try {
            presenter.publishTask(BaseConsts.BASE_URL_TASK_PUBLISH,publish);
        } catch (Exception e) {
            showToast("数据格式出错。");
        }
    }
}
