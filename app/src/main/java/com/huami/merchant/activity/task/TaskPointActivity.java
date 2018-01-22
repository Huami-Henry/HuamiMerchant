package com.huami.merchant.activity.task;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskPointAdapter;
import com.huami.merchant.activity.task.presenter.TaskPointPresenter;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class TaskPointActivity extends MvpBaseActivity<TaskPointPresenter,TaskPointActivity> implements XRecyclerView.LoadingListener,TaskViewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_shop_recycle)
    XRecyclerView task_shop_recycle;
    private LinearLayoutManager manager;
    private TaskPointAdapter adapter;
    private List<TaskPointInfo> shops = new ArrayList<>();
    private List<TaskPointInfo> taskPointInfo;
    private String taskPointInfoStr;
    private int page = 1;
    @Override
    protected TaskPointPresenter getPresenter() {
        return new TaskPointPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_point;
    }

    @Override
    protected void initData() {
        try {
            taskPointInfoStr =getIntent().getStringExtra("taskPointInfoStr");
            taskPointInfo=new Gson().fromJson(taskPointInfoStr, new TypeToken<List<TaskPointInfo>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        task_shop_recycle.setLayoutManager(manager);
        adapter = new TaskPointAdapter(shops, this);
        task_shop_recycle.setAdapter(adapter);
        getMoreShop();
    }
    public void getMoreShop(){
        if (TextUtils.isEmpty(BaseApplication.UUID)) {
            showToast("未登录,请先登录再尝试.");
            return;
        }
        showLoading();
        presenter.getTaskShops(shops, BaseConsts.BASE_URL_TASK_POINT_SHOP, BaseApplication.UUID,"", page);
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu){
        t_name.setText("任务点配置");
        t_menu.setText("添加");
        t_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    taskPointInfo.clear();
                    for (TaskPointInfo info : shops) {
                        if (info.isCheck()) {
                            taskPointInfo.add(info);
                        }
                    }
                    String json = new Gson().toJson(taskPointInfo);
                    Intent intent = new Intent(TaskPointActivity.this, TaskPointConfigureActivity.class);
                    intent.putExtra("taskPointInfoStr", json);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        if (page == 1) {
            task_shop_recycle.refreshComplete();
        } else {
            task_shop_recycle.loadMoreComplete();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("数据异常,请稍后尝试");
    }

    @Override
    public void onItemClick(View v, int position) {
        TaskPointInfo info = shops.get(position);
        info.setCheck(!info.isCheck());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        page = 1;
        getMoreShop();
    }

    @Override
    public void onLoadMore() {
        page++;
        getMoreShop();
    }
}
