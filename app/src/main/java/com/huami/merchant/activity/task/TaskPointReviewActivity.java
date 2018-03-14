package com.huami.merchant.activity.task;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskPointConfigureAdapter;
import com.huami.merchant.activity.task.presenter.TaskPointPreviewPresenter;
import com.huami.merchant.bean.AlreadyBean;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class TaskPointReviewActivity extends MvpBaseActivity<TaskPointPreviewPresenter,TaskPointReviewActivity> implements TaskViewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_point_recycle)
    RecyclerView task_point_recycle;
    private List<TaskPointInfo> shops = new ArrayList<>();
    private LinearLayoutManager manager;
    private TaskPointConfigureAdapter adapter;
    private String taskId;
    @Override
    protected TaskPointPreviewPresenter getPresenter() {
        return new TaskPointPreviewPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_task_point_review;
    }

    @Override
    protected void initData() {
        taskId = getIntent().getStringExtra("taskId");
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        task_point_recycle.setLayoutManager(manager);
        adapter = new TaskPointConfigureAdapter(shops, new AlreadyBean(), this);
        task_point_recycle.setAdapter(adapter);
        try {
            showLoading();
            presenter.getTaskShops(shops, BaseConsts.BASE_URL_TASK_POINT, BaseApplication.UUID, taskId,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("任务点");
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

    @Override
    public void onItemClick(View v, int position) {

    }
}
