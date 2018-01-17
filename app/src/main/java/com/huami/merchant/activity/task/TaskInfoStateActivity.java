package com.huami.merchant.activity.task;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.presenter.TaskEditPresenter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class TaskInfoStateActivity extends MvpBaseActivity<TaskEditPresenter,TaskInfoStateActivity> implements TaskViewInter, View.OnClickListener{
    @BindView(R.id.change_task_icon)
    TextView change_task_icon;
    @BindView(R.id.task_state)
    TextView task_state;
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

    private int check_state;
    @Override
    protected TaskEditPresenter getPresenter() {
        return new TaskEditPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_info_state;
    }

    @Override
    protected void initData() {
        check_state = getIntent().getIntExtra("check_state", 0);
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
        examine.setOnClickListener(this);
        check_result.setOnClickListener(this);
        data_statistics.setOnClickListener(this);
        look_result.setOnClickListener(this);
        edit_task.setOnClickListener(this);
        finish_check_result.setOnClickListener(this);
        finish_data_statistics.setOnClickListener(this);
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("任务详细");
    }

    @Override
    public void doSuccess(Object tag, String json) {

    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.examine:
                break;
            case R.id.check_result:
                break;
            case R.id.data_statistics:
                break;
            case R.id.look_result:
                break;
            case R.id.edit_task:
                break;
            case R.id.finish_check_result:
                break;
            case R.id.finish_data_statistics:
                break;
        }
    }
}
