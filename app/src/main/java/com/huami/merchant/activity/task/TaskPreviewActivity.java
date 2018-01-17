package com.huami.merchant.activity.task;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.TaskPreviewAdapter;
import com.huami.merchant.activity.task.presenter.TaskPreviewPresenter;
import com.huami.merchant.activity.task.viewInter.TaskPreviewInter;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class TaskPreviewActivity extends MvpBaseActivity<TaskPreviewPresenter, TaskPreviewActivity> implements TaskPreviewInter, XRecyclerView.LoadingListener,OnRecycleItemClickListener{
    private List<TaskPreviewData> datas = new ArrayList<>();
    private LinearLayoutManager manager;
    private TaskPreviewAdapter adapter;
    @BindView(R.id.task_preview_recycle)
    XRecyclerView task_preview_recycle;
    private int page=1;
    private String task_name;
    private String task_id;
    @Override
    protected TaskPreviewPresenter getPresenter() {
        return new TaskPreviewPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_task_preview;
    }
    @Override
    protected void initData() {
        task_id = getIntent().getStringExtra("task_id");
        if (TextUtils.isEmpty(task_id)) {
            showToast("数据出错,请稍后操作");
            finish();
        }
        manager = new LinearLayoutManager(this);
        task_preview_recycle.setLayoutManager(manager);
        adapter = new TaskPreviewAdapter(datas,false,this);
        task_preview_recycle.setAdapter(adapter);
        showLoading();
        presenter.getTaskPreviewList(datas,task_id,page,task_name);
    }
    @Override
    protected void initView() {

    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }
    @Override
    public void viewLoadSuccess(Object tag, String json) {
        if (tag.equals(BaseConsts.BASE_URL_PREVIEW_TASK)) {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void viewLoadFailure(Object tag, ErrorCode errorCode) {
        endLoading();
        switch (errorCode) {
            case PARAMA_EMPTY:
                showToast("用户未登录");
                startActivity(this, MvpLoginActivity.class);
                finish();
                break;
            case TRY_CATCH:
                showToast("数据出错,请稍后尝试");
                break;
            case ACTION_FAILURE:

                break;
            case NET_FAILURE:
                showToast("网络出错,请检查网络后尝试");
                break;
        }
    }
    @Override
    public void onRefresh() {
        page = 1;
        datas.clear();
        presenter.getTaskPreviewList(datas,task_id,page,task_name);
    }
    @Override
    public void onLoadMore() {
        page++;
        presenter.getTaskPreviewList(datas,task_id,page,task_name);
    }
    @Override
    public void onItemClick(View v, int position) {

    }
}
