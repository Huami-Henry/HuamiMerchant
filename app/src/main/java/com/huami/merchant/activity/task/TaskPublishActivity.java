package com.huami.merchant.activity.task;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.presenter.TaskPublishPresenter;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.imagepicker.RxPicker;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class TaskPublishActivity extends MvpBaseActivity<TaskPublishPresenter,TaskPublishActivity> implements TaskViewInter{
    @BindView(R.id.iv_task)
    ImageView iv_task;
    @BindView(R.id.del_task_name)
    ImageView del_task_name;
    @BindView(R.id.change_tv)
    TextView change_tv;
    @BindView(R.id.task_name)
    EditText task_name;
    private TaskInfo taskInfo;
    private boolean edit;
    @Override
    protected TaskPublishPresenter getPresenter() {
        return new TaskPublishPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_publish;
    }

    @Override
    protected void initData() {
        taskInfo= (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        edit= getIntent().getBooleanExtra("edit",false);
        if (taskInfo == null) {
            taskInfo = new TaskInfo();
        }
    }

    @Override
    protected void initView() {
        if (!TextUtils.isEmpty(taskInfo.getTask_icon())) {
            Glide.with(iv_task.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(iv_task);
        }
        task_name.setText(taskInfo.getTask_name());
        if (!TextUtils.isEmpty(taskInfo.getTask_name())) {
            task_name.setSelection(task_name.getText().length());
        }
    }
    @OnClick(R.id.change_tv)
    public void changeTaskIcon(){
        RxPicker.of()
                .single(true)
                .camera(true)
                .limit(1, 5)
                .start(this)
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(@NonNull final List<ImageItem> imageItems){
                        if (imageItems.size() != 0) {
                            String path = imageItems.get(0).getPath();
                            taskInfo.setTask_icon(path);
                            Glide.with(iv_task.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(iv_task);
                            try {
                                presenter.uploadTaskIcon(path);
                            } catch (Exception e) {
                                showToast(e.getMessage());
                            }

                        }
                    }
                });
    }
    @OnClick(R.id.del_task_name)
    public void delTaskName(){
        task_name.setText("");
        taskInfo.setTask_name("");
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_menu.setText("确定");
        t_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskInfo.setTask_name(task_name.getText().toString());
                Intent intent = new Intent(TaskPublishActivity.this, TaskEditActivity.class);
                intent.putExtra("taskInfo", taskInfo);
                if (edit) {
                    setResult(RESULT_OK, intent);
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void doSuccess(Object tag, String json) {

    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }
}
