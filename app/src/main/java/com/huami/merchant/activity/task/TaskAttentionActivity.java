package com.huami.merchant.activity.task;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskAttentionAdapter;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.designView.recycle.MyGridLayoutManager;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TaskAttentionActivity extends MvpBaseActivity implements OnRecycleItemClickListener{
    @BindView(R.id.template_liner)
    LinearLayout template_liner;
    @BindView(R.id.task_attention_recycle)
    RecyclerView task_attention_recycle;
    @BindView(R.id.use_template)
    TextView use_template;
    @BindView(R.id.complete)
    TextView complete;
    @BindView(R.id.tip_one)
    TextView tip_one;
    @BindView(R.id.tip_two)
    TextView tip_two;
    @BindView(R.id.tip_three)
    TextView tip_three;
    @BindView(R.id.tip_four)
    TextView tip_four;
    @BindView(R.id.tip_five)
    TextView tip_five;
    @BindView(R.id.tip_six)
    TextView tip_six;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.task_attention_ed)
    EditText task_attention_ed;
    private TaskInfo taskInfo;
    private String taskAttention;
    private List<String> task_attention = new ArrayList<>();
    private MyGridLayoutManager manager;
    private TaskAttentionAdapter adapter;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_task_attention;
    }
    @Override
    protected void initData() {
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        if (taskInfo == null) {
            taskInfo = new TaskInfo();
        }
        try {
            taskAttention = taskInfo.getTaskAttention();
            if (!TextUtils.isEmpty(taskAttention)) {
                task_attention= new Gson().fromJson(taskAttention,new TypeToken<List<String>>(){}.getType());
            }
        } catch (Exception e) {
        }
        adapter = new TaskAttentionAdapter(task_attention, this);
    }

    @Override
    protected void initView() {
        manager = new MyGridLayoutManager(this, 1);
        task_attention_recycle.setLayoutManager(manager);
        task_attention_recycle.setAdapter(adapter);
    }
    @OnClick(R.id.complete)
    public void complete(){
        if (TextUtils.isEmpty(task_attention_ed.getText().toString())) {
            return;
        }
        task_attention.add(task_attention_ed.getText().toString());
        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.clear_attention)
    public void clearAttention(){
        task_attention_ed.setText("");
    }
    public void hideSoftInputKeyBoard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
    }
    @OnClick(R.id.use_template)
    public void useTemplate(){
        template_liner.setVisibility(template_liner.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        hideSoftInputKeyBoard();
    }
    @OnClick(R.id.close)
    public void close(){
        closeTemp();
    }
    public void closeTemp(){
        useTemplate();
    }
    @OnClick(R.id.tip_one)
    public void tipOne(){
        setText(tip_one.getText().toString());
        closeTemp();
    }
    @OnClick(R.id.tip_two)
    public void tipTwo(){
        setText(tip_two.getText().toString());
        closeTemp();
    }
    @OnClick(R.id.tip_three)
    public void tipThree(){
        setText(tip_three.getText().toString());
        closeTemp();
    }
    @OnClick(R.id.tip_four)
    public void tipFour(){
        setText(tip_four.getText().toString());
        closeTemp();
    }
    @OnClick(R.id.tip_five)
    public void tipFive(){
        setText(tip_five.getText().toString());
        closeTemp();
    }
    @OnClick(R.id.tip_six)
    public void tipSix(){
        setText(tip_six.getText().toString());
        closeTemp();
    }
    /**
     * 设置文本框显示
     * @param text
     */
    public void setText(String text){
        if (!TextUtils.isEmpty(text)) {
            task_attention_ed.setText(text);
            task_attention_ed.setSelection(text.length());
        }
    }
    /**
     * 增加新模板
     * @param attention
     */
    public void addAttention(String attention){
        if (task_attention.size() >= 10) {
            showToast("最多添加10条");
            return;
        }
        task_attention.add(task_attention.size()-1,attention);
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("注意事项");
        t_menu.setText("确定");
        t_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskInfo.setTaskAttention(new Gson().toJson(task_attention));
                Intent intent = new Intent(TaskAttentionActivity.this, TaskEditActivity.class);
                intent.putExtra("taskInfo", taskInfo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (v.getId()) {
            case R.id.del_attention:
                if (task_attention.size() == 1) {
                    showToast("不可删除");
                    return;
                }
                task_attention.remove(position);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
