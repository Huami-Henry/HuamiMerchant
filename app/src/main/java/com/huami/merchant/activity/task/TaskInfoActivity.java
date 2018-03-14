package com.huami.merchant.activity.task;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskInfoAdapter;
import com.huami.merchant.activity.task.presenter.TaskInfoPresenter;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.bean.TaskInfoHtml;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.imagepicker.RxPicker;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class TaskInfoActivity extends MvpBaseActivity<TaskInfoPresenter,TaskInfoActivity> implements OnRecycleItemClickListener,TaskViewInter{
    @BindView(R.id.html_recycle)
    RecyclerView html_recycle;
    @BindView(R.id.text_edit)
    LinearLayout link_edit;
    @BindView(R.id.link_edit)
    LinearLayout text_edit;
    @BindView(R.id.photo_edit)
    LinearLayout photo_edit;
    @BindView(R.id.camera_edit)
    LinearLayout camera_edit;
    private TaskInfo taskInfo;
    private List<TaskInfoHtml> taskInfoHtmls = new ArrayList<>();
    private LinearLayoutManager manager;
    private TaskInfoAdapter adapter;
    @Override
    protected TaskInfoPresenter getPresenter() {
        return new TaskInfoPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_task_info;
    }
    @Override
    protected void initData() {
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("taskInfo");
    }
    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        html_recycle.setLayoutManager(manager);
        adapter = new TaskInfoAdapter(taskInfoHtmls, this);
        html_recycle.setAdapter(adapter);
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("任务详情");
        tv_menu.setText("确定");
        tv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html=createHtml();
                taskInfo.setTask_info(html);
                Intent intent = new Intent(TaskInfoActivity.this, TaskEditActivity.class);
                intent.putExtra("taskInfo", taskInfo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public String createHtml(){
        String htmlStr = "";
        for (TaskInfoHtml html : taskInfoHtmls) {
            if (!TextUtils.isEmpty(html.getText())) {
                htmlStr += ("<p>" + html.getText() + "</p>");
            } else {
                htmlStr += ("<p><img src='" + html.getUrl() + "'/></p>");
            }
        }
        return htmlStr;
    }
    private TaskInfoHtml html;
    @OnClick(R.id.text_edit)
    public void editText(){
        html = new TaskInfoHtml();
        showTextEditDialog(html,false);
    }
    @OnClick(R.id.link_edit)
    public void editLink(){
        showToast("暂未开放");
    }
    @OnClick(R.id.photo_edit)
    public void editPhoto(){
        RxPicker.of()
                .single(true)
                .camera(true)
                .limit(1,1)
                .start(this)
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(@NonNull final List<ImageItem> imageItems) throws Exception {
                        if (imageItems.size() != 0) {
                            showLoading();
                            for (ImageItem imageItem : imageItems) {
                                String path = imageItem.getPath();
                                File file = new File(path);
                                if (file != null) {
                                    if (file.length() > 0) {
                                        presenter.updateTaskInfoImage(imageItem.getPath());
                                    } else {
                                        showToast("图片损坏或不存在.");
                                    }
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    @OnClick(R.id.camera_edit)
    public void editCamera(){
        RxPicker.of()
                .single(true)
                .camera(true)
                .limit(1,1)
                .start(this)
                .subscribe(new Consumer<List<ImageItem>>() {
                    @Override
                    public void accept(@NonNull final List<ImageItem> imageItems) throws Exception {
                        if (imageItems.size() != 0) {
                            showLoading();
                            for (ImageItem imageItem : imageItems) {
                                presenter.updateTaskInfoImage(imageItem.getPath());
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    /**
     * 打开输入文字dialog
     * @param h
     */
    public void showTextEditDialog(final TaskInfoHtml h, final boolean update){
        final AlertDialog dialog = new AlertDialog.Builder(this,R.style.color_dialog).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setView(inflater.inflate(R.layout.dialog_input_text, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialog_input_text);
        final TextView commit = (TextView) dialog.findViewById(R.id.commit);
        TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss);
        final EditText input_area = (EditText) dialog.findViewById(R.id.input_area);
        if (!TextUtils.isEmpty(h.getText())) {
            input_area.setText(h.getText());
            input_area.setSelection(h.getText().length());
        }
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = input_area.getText().toString();
                if (!TextUtils.isEmpty(str)) {
                    h.setText(str);
                    if (!update) {
                        taskInfoHtmls.add(html);
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onItemClick(View v, final int position) {
        switch (v.getId()) {
            case R.id.text_del:
                taskInfoHtmls.remove(position);
                adapter.notifyDataSetChanged();
                break;
            case R.id.text_edit:
                final TaskInfoHtml h = taskInfoHtmls.get(position);
                if (!TextUtils.isEmpty(h.getText())) {
                    showTextEditDialog(h,true);
                } else {
                    RxPicker.of()
                            .single(true)
                            .camera(true)
                            .limit(1,5)
                            .start(this)
                            .subscribe(new Consumer<List<ImageItem>>() {
                                @Override
                                public void accept(@NonNull final List<ImageItem> imageItems) throws Exception {
                                    if (imageItems.size() != 0) {
                                        for (ImageItem imageItem : imageItems) {
                                            h.setUrl(imageItem.getPath());
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            });
                }
                break;
        }
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        try {
            JSONObject object = new JSONObject(json);
            String picPath = object.getString("picPath");
            html = new TaskInfoHtml();
            html.setUrl(picPath);
            taskInfoHtmls.add(html);
        } catch (JSONException e) {
           showToast("上传失败.");
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        showToast("上传失败");
    }
}
