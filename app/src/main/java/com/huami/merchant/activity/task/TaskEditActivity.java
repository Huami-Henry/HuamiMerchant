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
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.bean.TaskShop;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

import static com.huami.merchant.R.id.task_info;

public class TaskEditActivity extends MvpBaseActivity<TaskEditPresenter,TaskEditActivity> implements TaskViewInter,View.OnClickListener{
    String json = "[\n" +
            "  {'level': 1, 'name': '白丁', 'need_exp': 1000, 'total_exp': 0},\n" +
            "  {'level': 2, 'name': '从九品', 'need_exp': 1000, 'total_exp': 1000},\n" +
            "  {'level': 3, 'name': '正九品', 'need_exp': 2000, 'total_exp': 2000},\n" +
            "  {'level': 4, 'name': '从八品', 'need_exp': 3000, 'total_exp': 4000},\n" +
            "  {'level': 5, 'name': '正八品', 'need_exp': 5000, 'total_exp': 7000},\n" +
            "  {'level': 6, 'name': '从七品', 'need_exp': 8000, 'total_exp': 12000},\n" +
            "  {'level': 7, 'name': '正七品', 'need_exp': 13000, 'total_exp': 20000},\n" +
            "  {'level': 8, 'name': '从六品', 'need_exp': 21000, 'total_exp': 33000},\n" +
            "  {'level': 9, 'name': '正六品', 'need_exp': 34000, 'total_exp': 54000},\n" +
            "  {'level': 10, 'name': '从五品', 'need_exp': 55000, 'total_exp': 88000},\n" +
            "  {'level': 11, 'name': '正五品', 'need_exp': 89000, 'total_exp': 143000},\n" +
            "  {'level': 12, 'name': '从四品', 'need_exp': 144000, 'total_exp': 232000},\n" +
            "  {'level': 13, 'name': '正四品', 'need_exp': 233000, 'total_exp': 376000},\n" +
            "  {'level': 14, 'name': '从三品', 'need_exp': 377000, 'total_exp': 609000},\n" +
            "  {'level': 15, 'name': '正三品', 'need_exp': 610000, 'total_exp': 986000},\n" +
            "  {'level': 16, 'name': '从二品', 'need_exp': 987000, 'total_exp': 1596000},\n" +
            "  {'level': 17, 'name': '正二品', 'need_exp': 1597000, 'total_exp': 2583000},\n" +
            "  {'level': 18, 'name': '从一品', 'need_exp': 2584000, 'total_exp': 4180000},\n" +
            "  {'level': 19, 'name': '正一品', 'need_exp': 4181000, 'total_exp': 6764000}\n" +
            "]";
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
    private boolean edit;//非编辑的话要请求网络获取数据
    private String taskId;
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
        if (!edit) {
            try {
                presenter.getEditTask(taskId);
            } catch (Exception e) {
                showToast(e.getMessage());
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
            if (!TextUtils.isEmpty(taskInfo.getTask_icon())) {
                Glide.with(task_icon.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(task_icon);
            }
            task_name.setText(taskInfo.getTask_name());
            if (taskInfo.getAccept_begin_date() != 0) {
                task_accept_time.setText(format.format(new Date(taskInfo.getAccept_begin_date())));
            }
            if (taskInfo.getAccept_end_date() != 0) {
                task_accept_end_date.setText(format.format(new Date(taskInfo.getAccept_end_date())));
            }
            task_in_shop_time.setText((taskInfo.getRequire_shop_time() * 60)+"分钟");
            try {
                JSONArray array = new JSONArray(json);
                List<TaskInfo.TaskCondition> condition = taskInfo.getTaskCondition();
                if (condition != null) {
                    List<String> condition_list = new ArrayList<>();
                    for (TaskInfo.TaskCondition con : condition) {
                        switch (con.getCondition_id()) {
                            case 1:
                                condition_list.add(con.getParam_text());
                                break;
                            case 2:
                                condition_list.add(con.getParam1() + "及以上");
                                break;
                            case 3:
                                condition_list.add(con.getParam1() + "及以下");
                                break;
                            case 4:
                                condition_list.add(con.getParam1() + "-" + con.getParam2() + "岁");
                                break;
                            case 5:
                                for (int i = 0; i < array.length(); i++) {
                                    if (con.getParam1() == array.getJSONObject(i).getInt("level")) {
                                        condition_list.add(array.getJSONObject(i).getString("name") + "以上");
                                    }
                                }
                                break;
                            case 6:
                                if (con.getParam1() == 1) {
                                    condition_list.add("男");
                                } else if (con.getParam1() == 2) {
                                    condition_list.add("女");
                                }
                                break;
                        }
                    }
                    String condition_str = new Gson().toJson(condition_list);
                    task_condition.setText(condition_str);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if (!TextUtils.isEmpty(taskInfo.getTaskAttention())) {
                    List<String> task_attention= new Gson().fromJson(taskInfo.getTaskAttention(),new TypeToken<List<String>>(){}.getType());
                    String taskAtt = "";
                    int i=1;
                    for (String s : task_attention) {
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
        Log.e("根据id获取的结果", json);
        NetTaskBean bean = new Gson().fromJson(json, NetTaskBean.class);
        if (bean.getCode() == 0) {
            NetTaskBean.NetTaskData data = bean.getData();
            taskInfo.setAccept_begin_date(data.getAccept_begin_date());
            taskInfo.setAccept_end_date(data.getAccept_end_date());
            taskInfo.setCreate_date(data.getCreate_date());
            taskInfo.setLast_mod(data.getLast_mod());
            taskInfo.setMerchant_id(data.getMerchant_id());
            taskInfo.setOperator_id(data.getOperator_id());
            taskInfo.setShop_count(data.getShop_count());
            taskInfo.setTrainpaper_id(data.getTrainpaper_id());
            taskInfo.setTrain_name(data.getTrain_name());
            taskInfo.setTaskpaper_id(data.getTaskpaper_id());
            taskInfo.setTask_total_count(data.getTask_total_count());
            taskInfo.setTask_price(data.getTask_price());
            taskInfo.setTask_paper_name(data.getTask_paper_name());
            taskInfo.setTask_name(data.getTask_name());
            taskInfo.setTask_info(data.getTask_info());
            taskInfo.setTask_id(data.getTask_id());
            taskInfo.setTask_icon(data.getTask_icon());
            taskInfo.setTask_end_date(data.getTask_end_date());
            taskInfo.setTask_desc(data.getTask_desc());
            taskInfo.setState(data.getState());
            taskInfo.setRequire_shop_time(data.getRequire_shop_time());
            taskInfo.setTaskCondition(data.getTaskCondition());
            refreshData();
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == TASK_ICON_CHANGE_REQUEST_CODE && resultCode == RESULT_OK) {
                taskInfo = (TaskInfo) data.getSerializableExtra("taskInfo");
            } else if (requestCode == TASK_ATTENTION_CHANGE_REQUEST_CODE && resultCode == RESULT_OK) {
                taskInfo = (TaskInfo) data.getSerializableExtra("taskInfo");
            } else if (requestCode == TASK_POINT_MANAGER_REQUEST_CODE && resultCode == RESULT_OK) {

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
                startActivity(intent);
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
                startActivityForResult(this, TaskPaperListActivity.class, new String[]{"taskInfo","task"}, new Object[]{taskInfo,true},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.train_paper_edit:
                startActivityForResult(this, TaskPaperListActivity.class, new String[]{"taskInfo","task"}, new Object[]{taskInfo,false},TASK_ATTENTION_CHANGE_REQUEST_CODE);
                break;
            case R.id.task_set_count_price:
                startActivityForResult(this, TaskPointConfigureActivity.class,new String[]{"taskId"},new String[]{taskId},TASK_POINT_MANAGER_REQUEST_CODE);
                break;
            case R.id.publish_task:
                break;
        }
    }
}
