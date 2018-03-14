package com.huami.merchant.activity.task;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.task.adapter.AlreadyBuyAdapter;
import com.huami.merchant.activity.task.presenter.AlreadyBuyPresenter;
import com.huami.merchant.bean.AlreadyService.AlreadyServiceData;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.recycle.XRecyclerView;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class AlreadyBuyActivity extends MvpBaseActivity<AlreadyBuyPresenter,AlreadyBuyActivity> implements TaskViewInter{
    @BindView(R.id.already_buy_recycle)
    XRecyclerView already_buy_recycle;
    private LinearLayoutManager manager;
    private List<AlreadyServiceData> services=new ArrayList<>();
    private AlreadyBuyAdapter adapter;
    @Override
    protected AlreadyBuyPresenter getPresenter() {
        return new AlreadyBuyPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_already_buy;
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        already_buy_recycle.setLayoutManager(manager);
        already_buy_recycle.setPullRefreshEnabled(false);
        already_buy_recycle.setLoadingMoreEnabled(false);
        adapter = new AlreadyBuyAdapter(services);
        already_buy_recycle.setAdapter(adapter);
        try {
            showLoading();
            presenter.alreadyBuy(BaseConsts.ALREADY_BUY, BaseApplication.UU_TOKEN,BaseApplication.UUID,services);
        } catch (Exception e) {
            e.printStackTrace();
            endLoading();
            showToast(e.getMessage());
        }
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("已购服务");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        endLoading();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        showToast("网络异常,请稍后在尝试.");
    }
}
