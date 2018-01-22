package com.huami.merchant.fragment;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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
import com.huami.merchant.designView.recycle.XRecyclerView;
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
public class TaskFragment extends MvpBaseFragment<TaskListPresenter, TaskFragment> implements XRecyclerView.LoadingListener, TaskViewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_search)
    EditText task_search;//搜索框
    @BindView(R.id.wait_preview)
    TextView wait_preview;//待审核个数
    @BindView(R.id.only_look_preview)
    TextView only_look_preview;//只查看待审核
    @BindView(R.id.help_review)
    TextView help_review;//帮助审核
    @BindView(R.id.state_layout)
    StateLayoutView state_layout;//空状态页面
    @BindView(R.id.task_preview)
    XRecyclerView task_preview;//任务列表
    private TaskAdapter adapter;
    private List<TaskInfo> tasks = new ArrayList<>();
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
        task_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    taskName = "";
                    getTaskList();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        task_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    //写点击搜索键后的操作
                    taskName = task_search.getText().toString();
                    if (!TextUtils.isEmpty(taskName)) {
                        getTaskList();
                    }
                    return true;
                }
                return false;
            }
        });

        task_preview.setLoadingListener(this);
        task_preview.setLoadingMoreEnabled(false);
        task_preview.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter(tasks, this);
        task_preview.setAdapter(adapter);
        showLoading();
        getTaskList();
    }

    private String taskName;
    /**
     * 获取任务列表
     */
    public void getTaskList() {
        if (isNetworkConnected(getActivity())) {
            presenter.getTaskList(tasks,BaseApplication.UUID,checkState,taskName);
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
            only_look_preview.setTextColor(Color.parseColor("#bb0012"));
        } else {
            only_look_preview.setTextColor(Color.parseColor("#464646"));
            checkState = "";
        }
        getTaskList();
    }
    @Override
    public void doSuccess(Object tag,String json) {
        if (tag.equals(BaseConsts.BASE_URL_TASK)) {
            endLoading();
            try {
                task_preview.refreshComplete();
                Gson gson = new Gson();
                TaskBean bean = gson.fromJson(json, TaskBean.class);
                int count = bean.getData().getCount();
                wait_preview.setText(String.valueOf(count));
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
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

    @Override
    public void onRefresh() {
        getTaskList();
    }

    @Override
    public void onLoadMore() {

    }
}
