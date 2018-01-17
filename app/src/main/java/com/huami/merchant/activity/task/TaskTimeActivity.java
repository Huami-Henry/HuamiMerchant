package com.huami.merchant.activity.task;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.designView.DateTimePicker;
import com.huami.merchant.listener.OnTimePickListener;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.TimeUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    Spinner user_level;
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
        Intent intent = new Intent(this, TaskEditActivity.class);
        intent.putExtra("taskInfo", taskInfo);
        setResult(RESULT_OK,intent);
        finish();
    }
    @OnClick(R.id.set_accept_begin_time)
    public void acceptBeginTime(){
        DateTimePicker.Builder builder = new DateTimePicker.Builder();
        builder.init(this).
                canceledOnTouchOutside(false).
                cancelable(false).
                pickListener(new OnTimePickListener() {
                    @Override
                    public void timePick(String time) {
                        try {
                            accept_begin_time.setText(time);
                            taskInfo.setAccept_begin_date(TimeUtil.dateToStamp(time));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }).builder().show();
    }
    @OnClick(R.id.set_accept_end_time)
    public void acceptEndTime(){
        DateTimePicker.Builder builder = new DateTimePicker.Builder();
        builder.init(this).
                canceledOnTouchOutside(false).
                cancelable(false).
                pickListener(new OnTimePickListener() {
                    @Override
                    public void timePick(String time) {
                        accept_end_time.setText(time);
                    }
                }).builder().show();
    }
    @Override
    protected void initView() {
        if (taskInfo.getAccept_begin_date() != 0) {
            accept_end_time.setText(format.format(new Date(taskInfo.getAccept_end_date())));
        }
        if (taskInfo.getAccept_begin_date() != 0) {
            accept_begin_time.setText(format.format(new Date(taskInfo.getAccept_begin_date())));
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
