package com.huami.merchant.activity.paper;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.paper.adapter.NewExaminationAdapter;
import com.huami.merchant.activity.paper.adapter.PaperBannerAdapter;
import com.huami.merchant.activity.task.AuditLastStepActivity;
import com.huami.merchant.activity.task.presenter.PaperPendingPresenter;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.bean.AlreadyBean;
import com.huami.merchant.bean.AuditResult;
import com.huami.merchant.bean.PaperAlreadyAuditing;
import com.huami.merchant.bean.PaperAlreadyAuditing.PaperAlreadyAuditingData.PaperAlreadyAuditingTaskTitle;
import com.huami.merchant.bean.QuestionSinglePostil;
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
import com.huami.merchant.designView.recycle.MyGridLayoutManager;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.AuditUtil;
import com.huami.merchant.util.StringUtil;
import com.huami.merchant.util.TagUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 待审核问卷页面
 */
public class PaperPendingDetailActivity extends MvpBaseActivity<PaperPendingPresenter, PaperPendingDetailActivity> implements TaskViewInter, OnRecycleItemClickListener {
    private String usercase_id;
    private String isHistory;//1 - 历史问卷 2 - 当前问卷
    private String uca_check_usercase_id;
    private String pass;
    private String shop_id_str,shop_name_str,shop_price_str,shop_region_str,shop_address_str;
    @BindView(R.id.total_postil)
    TextView total_postil;
    @BindView(R.id.bottom_pending)
    LinearLayout bottom_pending;
    @BindView(R.id.task_paper_recycle)
    XRecyclerView task_paper_recycle;
    @BindView(R.id.tv_paper_result)
    TextView tv_paper_result;
    TextView user_case_id;
    TextView shop_id;
    TextView shop_name;
    TextView shop_price;
    TextView shop_region;
    TextView shop_address;
    TextView sign_in_time;
    TextView sign_out_time;
    TextView submit_time;
    TextView check_time;
    RecyclerView check_time_recycle;
    private LinearLayoutManager manager_paper;
    private LinearLayoutManager manager_banner;
    private List<CheckCaseIdListInfo> banner = new ArrayList<>();
    private PaperBannerAdapter bannerAdapter;
    private NewExaminationAdapter adapter;
    private List<ExaminationInner> inners = new ArrayList<>();
    private List<TaskAnswer> answers = new ArrayList<>();
    private List<TaskPaperAlreadyPendingHistoryPostil> postils = new ArrayList<>();
    private TaskTitle taskTitle;
    private String auditDeadline;
    private int checkTimes;
    private int checkState;
    private String taskPaperId;
    private AlreadyBean bean=new AlreadyBean();
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
        taskPaperId = getIntent().getStringExtra("taskPaperId");
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
        boolean already = getIntent().getBooleanExtra("already", false);
        bean.setAlready(already);
    }

    @Override
    protected void initView() {
        manager_paper = new LinearLayoutManager(this);
        task_paper_recycle.setLayoutManager(manager_paper);
        task_paper_recycle.setPullRefreshEnabled(false);
        task_paper_recycle.setLoadingMoreEnabled(false);
        View view = LayoutInflater.from(this).inflate(R.layout.pending_header, task_paper_recycle, false);
        initHeader(view);
        if (!bean.isAlready()) {
            manager_banner = new LinearLayoutManager(this);
            manager_banner.setOrientation(LinearLayoutManager.HORIZONTAL);
            check_time_recycle.setLayoutManager(manager_banner);
            bannerAdapter = new PaperBannerAdapter(banner, this);
            check_time_recycle.setAdapter(bannerAdapter);
            adapter = new NewExaminationAdapter(inners, answers,postils, bean,this);
        } else {
            check_time_recycle.setVisibility(View.GONE);
            bottom_pending.setVisibility(View.GONE);
            total_postil.setVisibility(View.VISIBLE);
            tv_paper_result.setText(AuditUtil.getState(checkTimes,checkState));
            adapter = new NewExaminationAdapter(inners, answers, postils,bean, this);
        }

        task_paper_recycle.addHeaderView(view);
        task_paper_recycle.setAdapter(adapter);
        showLoading();
        if (bean.isAlready()) {
            presenter.getAlreadyPendingDetail(BaseConsts.BASE_URL_TASK_ReviewResult, usercase_id, uca_check_usercase_id, pass);
        } else {
            presenter.getPendingDetail(BaseConsts.BASE_URL_TASK_ReviewPending, usercase_id,uca_check_usercase_id, isHistory);
        }
    }
    /**
     * 初始化头部
     * @param view
     */
    private void initHeader(View view) {
        user_case_id= (TextView) view.findViewById(R.id.user_case_id);
        shop_id= (TextView) view.findViewById(R.id.shop_id);
        shop_name= (TextView) view.findViewById(R.id.shop_name);
        shop_price= (TextView) view.findViewById(R.id.shop_price);
        shop_region= (TextView) view.findViewById(R.id.shop_region);
        shop_address= (TextView) view.findViewById(R.id.shop_address);
        sign_in_time= (TextView) view.findViewById(R.id.sign_in_time);
        sign_out_time= (TextView) view.findViewById(R.id.sign_out_time);
        submit_time= (TextView) view.findViewById(R.id.submit_time);
        check_time= (TextView) view.findViewById(R.id.check_time);
        check_time_recycle= (RecyclerView) view.findViewById(R.id.check_time_recycle);
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
                postils.clear();
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
            } else if (BaseConsts.BASE_URL_TASK_ReviewResult.equals(tag)){
                PaperAlreadyAuditing paperAlreadyAuditing = gson.fromJson(json, PaperAlreadyAuditing.class);
                if (paperAlreadyAuditing.getCode() == 0) {
                    setAlreadyPending(paperAlreadyAuditing);
                }
            }
            setOtherContent();
        } catch (Exception e) {
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
        tv_paper_result.setText("审核截止时间：" + StringUtil.subStringTime(auditDeadline));
        setTitle(taskTitle);
        int i = 0;
        if (position == banner.size() - 1) {
            banner.clear();
            inners.clear();
        }
        for (CheckCaseIdListInfo info : checkCaseIdList) {
            if (i == checkCaseIdList.size()-1) {
                info.setCheck(true);
            }
            banner.add(info);
            i++;
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
        shop_id.setText("店铺编号:000"+shop_id_str);
        shop_name.setText(shop_name_str);
        shop_price.setText("奖金:"+shop_price_str+"元");
        shop_region.setText(shop_region_str);
        shop_address.setText(shop_address_str);
        user_case_id.setText("问卷编号:000"+usercase_id);
    }

    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(PaperAlreadyAuditingTaskTitle title) {
        if (title != null) {
            sign_in_time.setText(StringUtil.subStringTime(title.getSignInTime()));
            sign_out_time.setText(StringUtil.subStringTime(title.getSignOutTime()));
            submit_time.setText(StringUtil.subStringTime(title.getSubmitTime()));
            if (title.getAuditStage() == 1) {
                check_time.setText(StringUtil.subStringTime(title.getAuditTime()));
            } else {
                check_time.setText("");
            }
        }
    }
    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(TaskTitle title) {
        if (title != null) {
            sign_in_time.setText(StringUtil.subStringTime(title.getSignInTime()));
            sign_out_time.setText(StringUtil.subStringTime(title.getSignOutTime()));
            submit_time.setText(StringUtil.subStringTime(title.getSubmitTime()));
            if (title.getAuditStage() == 1) {
                check_time.setText(StringUtil.subStringTime(title.getAuditTime()));
            } else {
                check_time.setText("");
            }
        }
    }
    /**
     * 设置头部信息
     *
     * @param title
     */
    public void setTitle(TaskPaperAlreadyPendingTaskTitle title) {
        if (title != null) {
            sign_in_time.setText(StringUtil.subStringTime(title.getSignInTime()));
            sign_out_time.setText(StringUtil.subStringTime(title.getSignOutTime()));
            submit_time.setText(StringUtil.subStringTime(title.getSubmitTime()));
            if (title.getAuditStage() == 1) {
                check_time.setText(StringUtil.subStringTime(title.getAuditTime()));
            } else {
                check_time.setText("");
            }
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("访问出错,请稍后尝试.");
    }
    private int position;
    @Override
    public void onItemClick(View v, int position) {
        this.position = position;
        switch (v.getId()) {
            case R.id.paper_title:
                CheckCaseIdListInfo info = banner.get(position);
                if (!info.isCheck()) {//已经点击了此按钮则不管
                    if (info.getState() == 1) {
                        isHistory = "2";
                        bean.setAlready(false);
                        bottom_pending.setVisibility(View.VISIBLE);
                        total_postil.setVisibility(View.GONE);
                    } else {
                        bean.setAlready(true);
                        isHistory = "1";
                        bottom_pending.setVisibility(View.INVISIBLE);
                        total_postil.setVisibility(View.VISIBLE);
                    }
                    presenter.getPendingDetail(BaseConsts.BASE_URL_TASK_ReviewPending, usercase_id,String.valueOf(info.getCheckCaseId()),isHistory);
                    for (CheckCaseIdListInfo ch : banner) {
                        ch.setCheck(false);
                    }
                    info.setCheck(true);
                }
                break;
        }
    }
    private AuditResult result=new AuditResult();
    private List<QuestionSinglePostil> post = new ArrayList<>();
    @OnClick(R.id.pass_tv)
    public void taskPass(){
        setAuditResult(true);
    }

    private void setAuditResult(boolean pass) {
        if (TextUtils.isEmpty(BaseApplication.UU_TOKEN) || TextUtils.isEmpty(BaseApplication.UUID)) {
            showToast("系统检测您的登陆过期。");
            startActivity(this, MvpLoginActivity.class);
            finish();
            return;
        }
        for (ExaminationInner inner : inners) {
            if (!TextUtils.isEmpty(inner.getPostil())) {
                QuestionSinglePostil p = new QuestionSinglePostil();
                p.setCheckCaseId(Integer.valueOf(uca_check_usercase_id));
                p.setQuestionContent(inner.getPostil());
                p.setQuestionId(inner.getqId());
                p.setTaskPaperId(Integer.valueOf(taskPaperId));
                p.setUserCaseId(Integer.valueOf(usercase_id));
                post.add(p);
            }

        }
        if (!pass) {
            if (post.size() == 0) {
                showToast("不通过至少添加一条批注");
                return;
            }
        }
        result.setCheckCaseId(uca_check_usercase_id);
        result.setMerUserId(BaseApplication.UUID);
        result.setUuid(BaseApplication.UU_TOKEN);
        result.setPostil(post);
        startActivityForResult(this, AuditLastStepActivity.class,new String[]{"auditResult","pass"},new Object[]{result,pass},10001);
    }

    @OnClick(R.id.no_pass_tv)
    public void taskNoPass(){
        setAuditResult(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
