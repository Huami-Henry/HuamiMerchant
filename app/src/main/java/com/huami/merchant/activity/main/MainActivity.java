package com.huami.merchant.activity.main;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.TaskPublishActivity;
import com.huami.merchant.fragment.CenterFragment;
import com.huami.merchant.fragment.TaskFragment;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.BaseViewInter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
public class MainActivity extends MvpBaseActivity implements BaseViewInter{
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.tab_center)
    RadioButton tab_center;
    @BindView(R.id.tab_task)
    RadioButton tab_task;
    @BindView(R.id.bottom_nav)
    RadioGroup bottom_nav;
    private TaskFragment taskFragment;
    private CenterFragment centerFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int fragmentIndex=1;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        taskFragment = new TaskFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.content,taskFragment);
        transaction.commit();
    }
    @OnClick(R.id.release_task)//发布任务
    public void releaseTask(){
        startActivity(this, TaskPublishActivity.class,"edit",false);
    }
    @OnClick(R.id.tab_task)
    public void clickTask(){
        if (fragmentIndex != 1) {
            fragmentIndex = 1;
            taskFragment = new TaskFragment();
            switchFragment(centerFragment,taskFragment);
        }
    }
    @OnClick(R.id.tab_center)
    public void clickCenter(){
        if (fragmentIndex != 2) {
            fragmentIndex = 2;
            centerFragment = new CenterFragment();
            switchFragment(taskFragment,centerFragment);
        }
    }
    @Override
    protected void initView() {
        bottom_nav.check(R.id.tab_task);
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (taskFragment != null) {
            taskFragment.getTaskList();
        }
    }

    /**
     * 切换页面的重载，优化了fragment的切换
     */
    public void switchFragment(Fragment from_first, Fragment to) {
        if (from_first == null || to == null)
            return;
        transaction = manager.beginTransaction();
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(from_first).add(R.id.content, to).commitAllowingStateLoss();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from_first).show(to).commitAllowingStateLoss();
        }
    }

}
