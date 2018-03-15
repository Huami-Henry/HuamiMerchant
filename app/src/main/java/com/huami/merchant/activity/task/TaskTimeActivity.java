package com.huami.merchant.activity.task;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.bean.TaskCondition;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.TimeUtil;
import org.feezu.liuli.timeselector.TimeSelector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
public class TaskTimeActivity extends MvpBaseActivity {
    @BindView(R.id.accept_begin_time)
    TextView accept_begin_time;
    @BindView(R.id.set_accept_begin_time)
    ImageView set_accept_begin_time;
    @BindView(R.id.accept_end_time)
    TextView accept_end_time;
    @BindView(R.id.set_accept_end_time)
    ImageView set_accept_end_time;
    @BindView(R.id.in_shop_time)
    EditText in_shop_time;
    @BindView(R.id.from_age)
    EditText from_age;
    @BindView(R.id.to_age)
    EditText to_age;
    @BindView(R.id.user_sex)
    Spinner user_sex;
    @BindView(R.id.user_level)
    TextView user_level;
    @BindView(R.id.make_sure)
    TextView make_sure;
    private TaskInfo taskInfo;
    private String accept_begin_time_str;
    private String accept_end_time_str;
    private String in_shop_time_str;
    private String from_age_str;
    private String to_age_str;
    private String user_sex_str;
    private String user_level_str;
    private List<TaskCondition> conditions = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_time;
    }

    @Override
    protected void initData() {
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("taskInfo");
    }
    @OnClick(R.id.make_sure)
    public void makeSure(){
        in_shop_time_str = in_shop_time.getText().toString();
        if (TextUtils.isEmpty(in_shop_time_str)) {
            taskInfo.setRequire_shop_time(0);
        } else {
            taskInfo.setRequire_shop_time(Integer.valueOf(in_shop_time_str));
        }
        TaskCondition condition_level = new TaskCondition();
        condition_level.setCondition_id(5);
        condition_level.setParam1(2);
        conditions.add(condition_level);
        String s = accept_begin_time.getText().toString();
        String e = accept_end_time.getText().toString();
        if (TextUtils.isEmpty(s)||TextUtils.isEmpty(e)) {
            showToast("任务开始和结束时间不能为空");
            return;
        }

        taskInfo.setAccept_begin_date(s);
        taskInfo.setAccept_end_date(e);

        from_age_str = from_age.getText().toString();
        to_age_str = to_age.getText().toString();
        if (!TextUtils.isEmpty(from_age_str) && !TextUtils.isEmpty(to_age_str)) {
            TaskCondition condition_age = new TaskCondition();
            condition_age.setCondition_id(4);
            condition_age.setParam1(Integer.valueOf(from_age_str));
            condition_age.setParam2(Integer.valueOf(to_age_str));
            conditions.add(condition_age);
        }
        if (user_sex.getSelectedItemPosition() != 0) {
            TaskCondition condition_sex = new TaskCondition();
            condition_sex.setCondition_id(6);
            condition_sex.setParam1(user_sex.getSelectedItemPosition());
            conditions.add(condition_sex);
        }
        taskInfo.setTaskCondition(conditions);
        Intent intent = new Intent(this, TaskEditActivity.class);
        intent.putExtra("taskInfo", taskInfo);
        setResult(RESULT_OK,intent);
        finish();
    }
    @OnClick(R.id.set_accept_begin_time)
    public void acceptBeginTime(){
        Calendar c = Calendar.getInstance();//首先要获取日历对象
        int year = c.get(Calendar.YEAR);
        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                accept_begin_time.setText(time+":00");
                taskInfo.setAccept_begin_date(time+":00");
            }
        },year+"-01-01 00:00",(year+100)+"-12-12 00:00");
        timeSelector.show();
    }
    @OnClick(R.id.set_accept_end_time)
    public void acceptEndTime(){
        Calendar c = Calendar.getInstance();//首先要获取日历对象
        int year = c.get(Calendar.YEAR);
        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                accept_end_time.setText(time+":00");
                taskInfo.setAccept_end_date(time+":00");
            }
        },year+"-01-01 00:00",(year+100)+"-12-12 00:00");
        timeSelector.show();
    }
    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(taskInfo.getAccept_begin_date())) {
            accept_end_time.setText(taskInfo.getAccept_end_date());
        } else {
            String end_day = TimeUtil.getCurrentMonthDate();
            accept_end_time.setText(end_day);
        }

        if (!TextUtils.isEmpty(taskInfo.getAccept_begin_date())) {
            accept_begin_time.setText(taskInfo.getAccept_begin_date());
        } else {
            String currentDate = TimeUtil.stampToDate();
            accept_begin_time.setText(currentDate);
        }
        if (taskInfo.getRequire_shop_time() != 0) {
            in_shop_time.setText(""+taskInfo.getRequire_shop_time());
            in_shop_time.setSelection(in_shop_time.getText().length());
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("时间设置");
    }
}
