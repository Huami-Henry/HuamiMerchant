package com.huami.merchant.activity.task;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskPaperAdapter;
import com.huami.merchant.activity.task.presenter.TaskPaperPresenter;
import com.huami.merchant.activity.task.viewInter.TaskPreviewInter;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.bean.TaskInfo;
import com.huami.merchant.bean.TaskPaperInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.MvpBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskPaperListActivity extends MvpBaseActivity<TaskPaperPresenter,TaskPaperListActivity> implements XRecyclerView.LoadingListener, TaskPreviewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_paper_recycle)
    XRecyclerView task_paper_recycle;
    private LinearLayoutManager manager;
    private TaskPaperAdapter adapter;
    private List<TaskPaperInfo> papers=new ArrayList<>();
    private TaskInfo taskInfo;
    private int taskpaper_id;
    private int trainpaper_id;
    private int page=1;
    private boolean task;
    @Override
    protected TaskPaperPresenter getPresenter() {
        return new TaskPaperPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_paper_list;
    }

    @Override
    protected void initData() {
        taskInfo = (TaskInfo) getIntent().getSerializableExtra("taskInfo");
        if (taskInfo != null) {
            taskpaper_id = taskInfo.getTaskpaper_id();
            trainpaper_id = taskInfo.getTrainpaper_id();
        }
        task = getIntent().getBooleanExtra("task", false);
    }
    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        task_paper_recycle.setLayoutManager(manager);
        adapter = new TaskPaperAdapter(papers, this);
        task_paper_recycle.setAdapter(adapter);
        if (isNetworkConnected(this)) {
            if (!TextUtils.isEmpty(BaseApplication.UUID)) {
                showLoading();
                presenter.getTaskPaper(papers, BaseApplication.UUID, page);
            }
        }
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("问卷选择");
    }
    @Override
    public void viewLoadSuccess(Object tag, String json) {
        endLoading();
        if (page == 1) {
            task_paper_recycle.refreshComplete();
        } else {
            task_paper_recycle.loadMoreComplete();
        }
        if (taskpaper_id != 0||trainpaper_id!=0) {
            for (TaskPaperInfo info : papers) {
                if (task) {
                    if (info.getTaskpaper_id() == taskpaper_id) {
                        info.setCheck(true);
                    }
                } else {
                    if (info.getTrainpaper_id() == trainpaper_id) {
                        info.setCheck(true);
                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void viewLoadFailure(Object tag, ErrorCode errorCode) {
        endLoading();
        showToast("数据请求失败,请稍后尝试.");
    }

    @Override
    public void onItemClick(View v, int position) {
        TaskPaperInfo info = papers.get(position);
        if (task) {
            taskInfo.setTaskpaper_id(info.getTaskpaper_id());
            taskInfo.setTask_paper_name(info.getName());
        } else {
            taskInfo.setTrainpaper_id(info.getTrainpaper_id());
            taskInfo.setTrain_name(info.getName());
        }
        Intent intent = new Intent(this, TaskEditActivity.class);
        intent.putExtra("taskInfo", taskInfo);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onRefresh() {
        page = 1;
        presenter.getTaskPaper(papers,BaseApplication.UUID,page);
    }

    @Override
    public void onLoadMore() {
        page++;
        presenter.getTaskPaper(papers,BaseApplication.UUID,page);
    }
}
