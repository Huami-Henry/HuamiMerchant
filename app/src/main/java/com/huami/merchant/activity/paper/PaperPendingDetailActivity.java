package com.huami.merchant.activity.paper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.paper.adapter.NewExaminationAdapter;
import com.huami.merchant.activity.paper.adapter.PaperBannerAdapter;
import com.huami.merchant.activity.task.presenter.PaperPendingPresenter;
import com.huami.merchant.bean.PaperAlreadyAuditing;
import com.huami.merchant.bean.PaperAlreadyAuditing.PaperAlreadyAuditingData.PaperAlreadyAuditingTaskTitle;
import com.huami.merchant.bean.TaskPaperAlreadyPending;
import com.huami.merchant.bean.TaskPaperAlreadyPending.TaskPaperAlreadyPendingData.TaskPaperAlreadyPendingTaskAnswer;
import com.huami.merchant.bean.TaskPaperAlreadyPending.TaskPaperAlreadyPendingData.TaskPaperAlreadyPendingTaskTitle;
import com.huami.merchant.bean.TaskPaperAlreadyPending.TaskPaperAlreadyPendingData.TaskPaperAlreadyPendingHistoryPostil;
import com.huami.merchant.bean.TaskPaperPendingBean;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskTitle;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskAnswer;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.CheckCaseIdListInfo;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskPaper.ExaminationInner;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.FullyLinearLayoutManager;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class PaperPendingDetailActivity extends MvpBaseActivity<PaperPendingPresenter, PaperPendingDetailActivity> implements TaskViewInter, OnRecycleItemClickListener {
    private String usercase_id;
    private String isHistory;//1 - 历史问卷 2 - 当前问卷
    private String uca_check_usercase_id;
    private String pass;
    private String shop_id_str,shop_name_str,shop_price_str,shop_region_str,shop_address_str;
    @BindView(R.id.no_pass_tv)
    TextView no_pass_tv;
    @BindView(R.id.total_postil)
    TextView total_postil;
    @BindView(R.id.pass_tv)
    TextView pass_tv;
    @BindView(R.id.bottom_pending)
    LinearLayout bottom_pending;
    @BindView(R.id.task_paper_recycle)
    RecyclerView task_paper_recycle;
    @BindView(R.id.user_case_id)
    TextView user_case_id;
    @BindView(R.id.shop_id)
    TextView shop_id;
    @BindView(R.id.tv_paper_result)
    TextView tv_paper_result;
    @BindView(R.id.shop_name)
    TextView shop_name;
    @BindView(R.id.shop_price)
    TextView shop_price;
    @BindView(R.id.shop_region)
    TextView shop_region;
    @BindView(R.id.shop_address)
    TextView shop_address;
    @BindView(R.id.check_time_recycle)
    RecyclerView check_time_recycle;
    @BindView(R.id.sign_in_time)
    TextView sign_in_time;
    @BindView(R.id.sign_out_time)
    TextView sign_out_time;
    @BindView(R.id.submit_time)
    TextView submit_time;
    @BindView(R.id.check_time)
    TextView check_time;
    private FullyLinearLayoutManager manager_papaer;
    private FullyLinearLayoutManager manager_banner;
    private List<CheckCaseIdListInfo> banner = new ArrayList<>();
    private PaperBannerAdapter bannerAdapter;
    private boolean already;//已审
    private NewExaminationAdapter adapter;
    private List<ExaminationInner> inners = new ArrayList<>();
    private List<TaskAnswer> answers = new ArrayList<>();
    private List<TaskPaperAlreadyPendingHistoryPostil> postils = new ArrayList<>();
    private TaskTitle taskTitle;
    private String auditDeadline;
    private int checkTimes;
    private int checkState;
    @Override
    protected PaperPendingPresenter getPresenter() {
        return new PaperPendingPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_paperpending_detail;
    }

    @Override
    protected void initData() {
        usercase_id = getIntent().getStringExtra("usercase_id");
        isHistory = getIntent().getStringExtra("isHistory");
        uca_check_usercase_id = getIntent().getStringExtra("uca_check_usercase_id");
        pass = getIntent().getStringExtra("pass");
        shop_id_str = getIntent().getStringExtra("shop_id_str");
        shop_name_str = getIntent().getStringExtra("shop_name_str");
        shop_price_str = getIntent().getStringExtra("shop_price_str");
        shop_region_str = getIntent().getStringExtra("shop_region_str");
        shop_address_str = getIntent().getStringExtra("shop_address_str");
        checkTimes = getIntent().getIntExtra("checkTimes",0);
        checkState = getIntent().getIntExtra("checkState",0);
        already = getIntent().getBooleanExtra("already", false);
    }

    @Override
    protected void initView() {
        if (!already) {
            manager_banner = new FullyLinearLayoutManager(this);
            check_time_recycle.setLayoutManager(manager_banner);
            manager_banner.setOrientation(LinearLayoutManager.HORIZONTAL);
            bannerAdapter = new PaperBannerAdapter(banner, this);
            check_time_recycle.setAdapter(bannerAdapter);
            adapter = new NewExaminationAdapter(inners, answers, this);
        } else {
            total_postil.setVisibility(View.VISIBLE);
            String checkTime = upCase(checkTimes);
            String state = getState(checkState);
            tv_paper_result.setText(checkTime+state);
            adapter = new NewExaminationAdapter(inners, answers, postils, this);
        }
        manager_papaer = new FullyLinearLayoutManager(this);
        task_paper_recycle.setLayoutManager(manager_papaer);

        task_paper_recycle.setAdapter(adapter);
        if (already) {
            presenter.getAlreadyPendingDetail(BaseConsts.BASE_URL_TASK_ReviewResult, usercase_id, uca_check_usercase_id, pass);
        } else {
            presenter.getPendingDetail(BaseConsts.BASE_URL_TASK_ReviewPending, usercase_id, isHistory);
        }
        setOtherContent();
    }
    /**
     * 获取审核状态
     * @param state
     * @return
     */
    public String getState(int state){
        switch (state) {
            case 1:
                return "待审";
            case 2:
                return "通过";
            case 3:
                return "不通过";
            default:
                return "错误";
        }
    }
    /**
     * 转化成大写
     * @param number
     * @return
     */
    public String upCase(int number){
        switch (number) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            default:
                return "N";

        }
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("问卷");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        Gson gson = new Gson();
        try {
            if (BaseConsts.BASE_URL_TASK_ReviewPending.equals(tag)) {
                answers.clear();
                if ("2".equals(isHistory)) {//当前问卷
                    TaskPaperPendingBean bean = gson.fromJson(json, TaskPaperPendingBean.class);
                    if (bean.getCode() == 0) {
                        setCurrentPaper(bean);
                    }
                } else {
                    TaskPaperAlreadyPending alreadyPending = gson.fromJson(json, TaskPaperAlreadyPending.class);
                    if (alreadyPending.getCode() == 0) {
                        setCurrentAlready(alreadyPending);
                    }
                }
                adapter.notifyDataSetChanged();
                bannerAdapter.notifyDataSetChanged();
            } else {
                PaperAlreadyAuditing paperAlreadyAuditing = gson.fromJson(json, PaperAlreadyAuditing.class);
                if (paperAlreadyAuditing.getCode() == 0) {
                    setAlreadyPending(paperAlreadyAuditing);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置已经完成的数据
     * @param paperAlreadyAuditing
     */
    private void setAlreadyPending(PaperAlreadyAuditing paperAlreadyAuditing) {
        PaperAlreadyAuditingTaskTitle taskTitle = paperAlreadyAuditing.getData().getTaskTitle();
        String generalPostil = paperAlreadyAuditing.getData().getGeneralPostil();
        List<ExaminationInner> paperData = paperAlreadyAuditing.getData().getTaskPaper().getPaperData();
        List<TaskAnswer> questionAnswer = paperAlreadyAuditing.getData().getQuestionAnswer();
        List<TaskPaperAlreadyPendingHistoryPostil> singlePostil = paperAlreadyAuditing.getData().getSinglePostil();
        setTitle(taskTitle);
        total_postil.setText(generalPostil);
        for (ExaminationInner inner : paperData) {
            inners.add(inner);
        }
        for (TaskAnswer answer : questionAnswer) {
            answers.add(answer);
        }
        for (TaskPaperAlreadyPendingHistoryPostil p : singlePostil) {
            postils.add(p);
        }
        adapter.notifyDataSetChanged();
        bannerAdapter.notifyDataSetChanged();
    }

    /**
     * 设置待审中已审
     * @param alreadyPending
     */
    private void setCurrentAlready(TaskPaperAlreadyPending alreadyPending) {
        List<TaskPaperAlreadyPendingTaskAnswer> taskAnswer = alreadyPending.getData().getTaskAnswer();
        TaskPaperAlreadyPendingTaskTitle taskTitle = alreadyPending.getData().getTaskTitle();
        setTitle(taskTitle);
        for (TaskPaperAlreadyPendingTaskAnswer answer : taskAnswer) {
            TaskAnswer t_answer = new TaskAnswer();
            t_answer.setAnswer(answer.getAnswer());
            t_answer.setBody_id(answer.getBody_id());
            answers.add(t_answer);
        }
        List<TaskPaperAlreadyPendingHistoryPostil> historyPostil = alreadyPending.getData().getHistoryPostil();
        for (TaskPaperAlreadyPendingHistoryPostil p : historyPostil) {
            postils.add(p);
        }
        total_postil.setText(taskTitle.getGeneralPostil());
    }

    /**
     * 设置待审
     * @param bean
     */
    private void setCurrentPaper(TaskPaperPendingBean bean) {
        List<CheckCaseIdListInfo> checkCaseIdList = bean.getData().getCheckCaseIdList();
        List<TaskAnswer> taskAnswer = bean.getData().getTaskAnswer();
        List<ExaminationInner> paperData = bean.getData().getTaskPaper().getPaperData();
        taskTitle = bean.getData().getTaskTitle();
        auditDeadline = taskTitle.getAuditDeadline();
        tv_paper_result.setText(auditDeadline);
        setTitle(taskTitle);
        for (CheckCaseIdListInfo info : checkCaseIdList) {
            banner.add(info);
        }
        for (TaskAnswer answer : taskAnswer) {
            answers.add(answer);
        }
        for (ExaminationInner inner : paperData) {
            inners.add(inner);
        }
    }

    /**
     * 其他信息
     */
    private void setOtherContent() {
        shop_id.setText("000"+shop_id_str);
        shop_name.setText(shop_name_str);
        shop_price.setText(shop_price_str);
        shop_region.setText(shop_region_str);
        shop_address.setText(shop_address_str);
        user_case_id.setText("000"+usercase_id);
    }

    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(PaperAlreadyAuditingTaskTitle title) {
        if (title != null) {
            sign_in_time.setText(title.getSignInTime());
            sign_out_time.setText(title.getSignOutTime());
            submit_time.setText(title.getSubmitTime());
            check_time.setText(title.getAuditTime());
        }
    }
    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(TaskTitle title) {
        if (title != null) {
            sign_in_time.setText(title.getSignInTime());
            sign_out_time.setText(title.getSignOutTime());
            submit_time.setText(title.getSubmitTime());
            check_time.setText(title.getAuditTime());
        }
    }
    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(TaskPaperAlreadyPendingTaskTitle title) {
        if (title != null) {
            sign_in_time.setText(title.getSignInTime());
            sign_out_time.setText(title.getSignOutTime());
            submit_time.setText(title.getSubmitTime());
            check_time.setText(title.getAuditTime());
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("访问出错,请稍后尝试.");
    }

    @Override
    public void onItemClick(View v, int position) {
        switch (v.getId()) {
            case R.id.paper_title:
                CheckCaseIdListInfo info = banner.get(position);
                if (!info.isCheck()) {//已经点击了此按钮则不管
                    if (info.getState() == 1) {
                        isHistory = "2";
                        bottom_pending.setVisibility(View.VISIBLE);
                        total_postil.setVisibility(View.GONE);
                        presenter.getPendingDetail(BaseConsts.BASE_URL_TASK_ReviewPending, usercase_id, isHistory);
                    } else {
                        isHistory = "1";
                        bottom_pending.setVisibility(View.INVISIBLE);
                        total_postil.setVisibility(View.VISIBLE);
                        presenter.getPendingDetail(BaseConsts.BASE_URL_TASK_ReviewPending, usercase_id,uca_check_usercase_id,isHistory);
                    }
                    for (CheckCaseIdListInfo ch : banner) {
                        ch.setCheck(false);
                    }
                    info.setCheck(true);
                }
                break;
        }
    }
}
