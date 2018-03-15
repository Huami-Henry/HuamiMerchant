package com.huami.merchant.activity.service;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.adapter.ValueRightAdapter;
import com.huami.merchant.activity.service.presenter.SingleValuePresenter;
import com.huami.merchant.bean.SingleValueBean;
import com.huami.merchant.bean.ValueRightInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
public class SingleValueActivity extends MvpBaseActivity<SingleValuePresenter,SingleValueActivity> implements TaskViewInter,OnRecycleItemClickListener{
    @BindView(R.id.value_right_recycle)
    RecyclerView value_right_recycle;
    private ValueRightAdapter adapter_right;
    private List<ValueRightInfo> valueRight = new ArrayList<>();
    private LinearLayoutManager manager;
    private String entryId;
    @Override
    protected SingleValuePresenter getPresenter() {
        return new SingleValuePresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_single_value;
    }

    @Override
    protected void initData() {
        entryId = getIntent().getStringExtra("entryId");
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        value_right_recycle.setLayoutManager(manager);
        adapter_right = new ValueRightAdapter(valueRight, this);
        value_right_recycle.setAdapter(adapter_right);
        try {
            presenter.getValueList(BaseConsts.BASE_URL_SINGLE_VALUE_SERVICE, entryId,null,null);
        } catch (Exception e) {
            showToast("数据出错.");
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("增值服务");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        Gson gson = new Gson();
        SingleValueBean left = gson.fromJson(json, SingleValueBean.class);
        List<ValueRightInfo> data = left.getData();
        valueRight.clear();
        for (ValueRightInfo rightInfo : data) {
            valueRight.add(rightInfo);
        }
        adapter_right.notifyDataSetChanged();
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        showToast("数据异常,请稍后再试");
    }

    @Override
    public void onItemClick(View v, int position) {
        startActivity(this,ValueDetailActivity.class,"packageId",valueRight.get(position).getPackageId());
    }
}
