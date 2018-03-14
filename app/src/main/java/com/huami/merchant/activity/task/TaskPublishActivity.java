package com.huami.merchant.activity.task;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.activity.task.presenter.TaskPublishPresenter;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.imagepicker.RxPicker;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.mvpbase.ActivityManager;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
                .limit(1, 1)
                .start(this)
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(@NonNull final List<ImageItem> imageItems){
                        if (imageItems.size() != 0) {
                            String path = imageItems.get(0).getPath();
                            File file = new File(path);
                            long length = file.length();
                            if (length > 0) {
                                taskInfo.setTask_icon(path);
                                Glide.with(iv_task.getContext()).load(taskInfo.getTask_icon()).asBitmap().error(R.mipmap.rectangle_seize_pic).placeholder(R.mipmap.rectangle_seize_pic).into(iv_task);
                                try {
                                    showLoading();
                                    presenter.uploadTaskIcon(path);
                                } catch (Exception e) {
                                    showToast(e.getMessage());
                                }
                            } else {
                                showToast("文件损坏,或不存在.");
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
                String task_name_str=task_name.getText().toString();
                if (TextUtils.isEmpty(task_name_str)) {
                    showToast("任务名称不能为空");
                    return;
                }
                taskInfo.setTask_name(task_name.getText().toString());
                Intent intent = new Intent(TaskPublishActivity.this, TaskEditActivity.class);
                intent.putExtra("taskInfo", taskInfo);
                intent.putExtra("edit", edit);
                if (edit) {
                    setResult(RESULT_OK, intent);
                } else {
                    startActivity(intent);
                }
                finishActivity(TaskPublishActivity.this);
            }
        });
    }
    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        try {
            JSONObject object = new JSONObject(json);
            String picPath = object.getString("picPath");
            taskInfo.setTask_icon(picPath);
        } catch (JSONException e) {
            showToast("图片上传失败");
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("图片上传失败");
    }
    @OnClick(R.id.value_search)
    public void valueSearch(){
        startActivity(this, SingleValueActivity.class,"entryId","7");
    }
    @OnClick(R.id.value_proxy)
    public void valueProxy(){
        startActivity(this, SingleValueActivity.class,"entryId","3");
    }
}
