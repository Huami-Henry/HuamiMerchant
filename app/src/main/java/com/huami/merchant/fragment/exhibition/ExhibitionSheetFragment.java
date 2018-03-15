package com.huami.merchant.fragment.exhibition;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.SingleValueActivity;
import com.huami.merchant.activity.task.DataStatisticsActivity;
import com.huami.merchant.activity.task.TaskAlreadyPendingActivity;
import com.huami.merchant.activity.task.TaskEditActivity;
import com.huami.merchant.activity.task.TaskFailureReasonActivity;
import com.huami.merchant.activity.task.TaskInfoStateActivity;
import com.huami.merchant.activity.task.TaskPreviewActivity;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.fragment.adapter.TaskAdapter;
import com.huami.merchant.fragment.exhibition.presenter.ExhibitionSheetPresenter;
import com.huami.merchant.fragment.exhibition.view.SheetViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.MvpBaseFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
/**
 * Created by henry on 2018/3/14.
 */
public class ExhibitionSheetFragment extends MvpBaseFragment<ExhibitionSheetPresenter,ExhibitionSheetFragment> implements SheetViewInter,OnRecycleItemClickListener{
    @BindView(R.id.task_group)
    RadioGroup task_group;
    @BindView(R.id.task_search)
    EditText task_search;
    @BindView(R.id.task_recycle)
    RecyclerView task_recycle;
    private TaskAdapter adapter;
    private List<TaskInfo> tasks = new ArrayList<>();
    private String checkState;
    private String taskName;
    @BindView(R.id.wait_preview)
    RadioButton wait_preview;
    @Override
    protected ExhibitionSheetPresenter getPresenter() {
        return new ExhibitionSheetPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.exhibition_sheet_fragment;
    }
    @Override
    protected void initData() {
        task_group.check(R.id.wait_preview);
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
                        //搜索本地数据


                    }
                    return true;
                }
                return false;
            }
        });
        task_recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter(tasks, this);
        task_recycle.setAdapter(adapter);
        getTaskList();
        task_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.wait_preview:
                        checkState = "";
                        getTaskList();
                        break;
                    case R.id.only_wait_preview:
                        checkState = "1";
                        getTaskList();
                        break;
                    case R.id.help_preview:
                        startActivity(getActivity(), SingleValueActivity.class,"entryId","1");
                        break;
                }
            }
        });
    }
    /**
     * 获取任务列表
     */
    public void getTaskList(){
        if (isNetworkConnected(getActivity())) {
            showLoading();
            try {
                presenter.getTaskList(tasks, BaseApplication.UUID, checkState, taskName);
            } catch (Exception e) {
                endLoading();
            }
        } else {
            showToast("网络异常");
        }
    }
    private int i = -1;
    @Override
    public void taskSuccess() {
        endLoading();
        adapter.notifyDataSetChanged();
        //获取本地数据的待审核数量
        if (i == -1) {
            i = 0;
            for (TaskInfo info : tasks) {
                if (info.getCheck_state() == 1) {
                    i++;
                }
            }
            wait_preview.setText("待审核("+i+")");
        }
    }

    @Override
    public void taskFailure() {
        endLoading();
        showToast("网络异常,请稍后再试。");
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
            case R.id.look_result:
                startActivity(getActivity(), TaskFailureReasonActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            case R.id.data_statistics:
                startActivity(getActivity(), DataStatisticsActivity.class,new String[]{"task_id"},new String[]{String.valueOf(tasks.get(position).getTask_id())});
                break;
            case R.id.edit_task:
                startActivity(getActivity(),
                        TaskEditActivity.class,
                        new String[]{"task_id","total_count","total_money","edit"},
                        new Object[]{
                                String.valueOf(tasks.get(position).getTask_id()),
                                tasks.get(position).getTask_total_count(),
                                tasks.get(position).getTask_price(),
                                true});
                break;
            default:
                startActivity(getActivity(),
                        TaskInfoStateActivity.class,
                        new String[]{"task_id","check_state","total_count","total_money"},
                        new Object[]{
                                String.valueOf(tasks.get(position).getTask_id()),
                                tasks.get(position).getCheck_state(),
                                tasks.get(position).getTask_total_count(),
                                tasks.get(position).getTask_price()
                        });
                break;
        }
    }
}
