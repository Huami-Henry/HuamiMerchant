package com.huami.merchant.activity.task;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskPointConfigureAdapter;
import com.huami.merchant.activity.task.presenter.TaskPointConfigurePresenter;
import com.huami.merchant.activity.task.presenter.TaskPointPresenter;
import com.huami.merchant.bean.AlreadyBean;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
public class TaskPointConfigureActivity extends MvpBaseActivity<TaskPointConfigurePresenter,TaskPointConfigureActivity> implements OnRecycleItemClickListener,TaskViewInter{
    @BindView(R.id.task_point_recycle)
    RecyclerView task_point_recycle;
    @BindView(R.id.task_single_count)
    EditText task_single_count;
    @BindView(R.id.task_single_price)
    EditText task_single_price;
    @BindView(R.id.shop_count)
    TextView shop_count;
    @BindView(R.id.total_money)
    TextView total_money;
    @BindView(R.id.manage_shop_liner)
    LinearLayout manage_shop_liner;
    @BindView(R.id.manage_shop_cb)
    CheckBox manage_shop_cb;
    @BindView(R.id.manage_shop_del)
    TextView manage_shop_del;
    @BindView(R.id.del_task_point)
    TextView del_task_point;
    @BindView(R.id.add_task_point)
    TextView add_task_point;
    private String taskId;
    private List<TaskPointInfo> shops = new ArrayList<>();
    private LinearLayoutManager manager;
    private TaskPointConfigureAdapter adapter;
    private final int ADD_TASK_SHOP_CODE = 1001;
    private Map<Integer, Integer> map = new HashMap<>();
    private AlreadyBean edit=new AlreadyBean();
    @Override
    protected TaskPointConfigurePresenter getPresenter() {
        return new TaskPointConfigurePresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_task_point_configure;
    }
    public void getMoreShop(){
        if (TextUtils.isEmpty(BaseApplication.UUID)) {
            showToast("未登录,请先登录再尝试.");
            return;
        }
        if (!TextUtils.isEmpty(taskId)) {
            showLoading();
            presenter.getTaskShops(shops,BaseConsts.BASE_URL_TASK_POINT, BaseApplication.UUID, taskId,1);
        }
    }
    @Override
    protected void initData() {
        taskId = getIntent().getStringExtra("taskId");
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        task_point_recycle.setLayoutManager(manager);
        adapter = new TaskPointConfigureAdapter(shops,map,edit,this);
        task_point_recycle.setAdapter(adapter);
        getMoreShop();
        initListener();
    }

    private void initListener() {
        task_single_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (TaskPointInfo info : shops) {
                    info.setTotal_num(Integer.valueOf(s.toString()));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        task_single_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (TaskPointInfo info : shops) {
                    info.setMer_price(Integer.valueOf(s.toString()));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("任务点配置");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {

        }
    }
    @OnClick(R.id.make_sure_task)
    public void makeSureTask(){

    }
    @Override
    public void onItemClick(View v, int position) {
        if (map.containsKey(position)) {
            map.remove(position);
        } else {
            map.put(position, position);
        }
        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.add_task_point)
    public void addTaskPoint(){
        map.clear();
        String json = new Gson().toJson(shops);
        startActivityForResult(this,TaskPointActivity.class,new String[]{"taskPointInfoStr"},new String[]{json},ADD_TASK_SHOP_CODE);
    }
    @OnClick(R.id.del_task_point)
    public void delTaskPoint(){
        map.clear();
        if (edit.isAlready()) {
            edit.setAlready(false);
            del_task_point.setText("管理");
            add_task_point.setVisibility(View.VISIBLE);
            manage_shop_liner.setVisibility(View.GONE);
        } else {
            edit.setAlready(true);
            del_task_point.setText("完成");
            add_task_point.setVisibility(View.GONE);
            manage_shop_liner.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.manage_shop_del)
    public void delCheckShop(){
        for (int index : map.keySet()) {
            shops.remove(index);
        }
    }
    @OnClick(R.id.manage_shop_cb)
    public void checkAll(){
        int i=0;
        for (TaskPointInfo info : shops) {
            map.put(i, i);
            i++;
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("用户数据获取异常,请稍后再尝试。");
    }
}
