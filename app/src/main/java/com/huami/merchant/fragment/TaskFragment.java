package com.huami.merchant.fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.TaskAlreadyPendingActivity;
import com.huami.merchant.activity.task.TaskEditActivity;
import com.huami.merchant.activity.task.TaskInfoStateActivity;
import com.huami.merchant.activity.task.TaskPreviewActivity;
import com.huami.merchant.bean.TaskBean;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.stateView.StateLayoutView;
import com.huami.merchant.fragment.adapter.TaskAdapter;
import com.huami.merchant.fragment.present.TaskListPresenter;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseFragment;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
public class TaskFragment extends MvpBaseFragment<TaskListPresenter, TaskFragment> implements TaskViewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_search)
    TextView task_search;//搜索框
    @BindView(R.id.wait_preview)
    TextView wait_preview;//待审核个数
    @BindView(R.id.only_look_preview)
    TextView only_look_preview;//只查看待审核
    @BindView(R.id.help_review)
    TextView help_review;//帮助审核
    @BindView(R.id.state_layout)
    StateLayoutView state_layout;//空状态页面
    @BindView(R.id.task_preview)
    RecyclerView task_preview;//任务列表
    private TaskAdapter adapter;
    private List<TaskInfo> tasks = new ArrayList<>();
    @BindView(R.id.circle_refresh)
    CircleRefreshLayout circle_refresh;
    private String checkState;
    @Override
    protected TaskListPresenter getPresenter() {
        return new TaskListPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.fragment_task;
    }

    @Override
    protected void initData() {
        task_preview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter(tasks, this);
        task_preview.setAdapter(adapter);
        getTaskList();
        circle_refresh.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        showToast("执行操作");
                        try {
                            getTaskList();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void completeRefresh() {
                        showToast("结束执行");
                        try {
                            endLoading();
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 获取任务列表
     */
    private void getTaskList() {
        if (isNetworkConnected(getActivity())) {
            showLoading();
            tasks.clear();
            presenter.getTaskList(tasks,BaseApplication.UUID,checkState,null);
        } else {
            state_layout.showNoNetworkView();
        }
    }

    @OnClick(R.id.help_review)
    public void helpReview(){
//        startActivity(this,);
    }
    @OnClick(R.id.only_look_preview)//待审核
    public void onlyLookPreview(){
        if (TextUtils.isEmpty(checkState)) {
            checkState = "1";
        } else {
            checkState = "";
        }
        getTaskList();
    }
    @Override
    public void doSuccess(Object tag,String json) {
        if (tag.equals(BaseConsts.BASE_URL_TASK)) {
            try {
                Gson gson = new Gson();
                TaskBean bean = gson.fromJson(json, TaskBean.class);
                int count = bean.getData().getCount();
                wait_preview.setText(String.valueOf(count));
                circle_refresh.finishRefreshing();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        switch (code) {
            case ACTION_FAILURE:
                showToast("请求异常");
                break;
            case PARAMA_EMPTY:
                showToast("参数为空");
                break;
            case TRY_CATCH:
                showToast("数据格式异常");
                break;
            case NET_FAILURE:
                showToast("网络异常,请稍后再试");
                break;
        }
    }
    @Override
    public void onItemClick(View v, int position) {
        switch (v.getId()) {
            case R.id.examine:
                startActivity(getActivity(), TaskPreviewActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            case R.id.check_result:
                startActivity(getActivity(), TaskAlreadyPendingActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            case R.id.data_statistics:
                startActivity(getActivity(), TaskEditActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            case R.id.edit_task:
                startActivity(getActivity(), TaskEditActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            default:
                startActivity(getActivity(), TaskInfoStateActivity.class,new String[]{"task_id","check_state"},new String[]{String.valueOf(tasks.get(position).getTask_id()), String.valueOf(tasks.get(position).getCheck_state())});
                break;
        }
    }
}
