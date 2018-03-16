package com.huami.merchant.fragment.exhibition;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.ValueDetailActivity;
import com.huami.merchant.activity.service.adapter.ValueLeftAdapter;
import com.huami.merchant.activity.service.adapter.ValueRightAdapter;
import com.huami.merchant.activity.service.presenter.ValueServicePresenter;
import com.huami.merchant.activity.service.viewInter.ValueServiceViewInter;
import com.huami.merchant.bean.ValueLeft;
import com.huami.merchant.bean.ValueLeftData;
import com.huami.merchant.bean.ValueRight;
import com.huami.merchant.bean.ValueRightInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.designView.stateView.StateLayoutView;
import com.huami.merchant.fragment.present.ExhibitionServicePresenter;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by henry on 2018/3/14.
 */

public class ExhibitionServiceFragment extends MvpBaseFragment<ExhibitionServicePresenter,ExhibitionServiceFragment> implements ValueServiceViewInter,OnRecycleItemClickListener {
    @BindView(R.id.value_left_recycle)
    RecyclerView value_left_recycle;
    @BindView(R.id.value_right_recycle)
    RecyclerView value_right_recycle;
    private LinearLayoutManager manager_left;
    private LinearLayoutManager manager_right;
    private ValueLeftAdapter adapter_left;
    private ValueRightAdapter adapter_right;
    private List<ValueRightInfo> valueRight = new ArrayList<>();
    private List<ValueLeftData> valueLeft = new ArrayList<>();
    private boolean first=true;
    @BindView(R.id.top_bar)
    TextView top_bar;
    @BindView(R.id.state_layout)
    StateLayoutView state_layout;
    @Override
    protected ExhibitionServicePresenter getPresenter() {
        return new ExhibitionServicePresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.exhibition_service_fragment;
    }
    @Override
    protected void initData() {
        initView();
    }
    protected void initView() {
        int height = getStatusBarHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        top_bar.setLayoutParams(params);
        top_bar.setBackgroundColor(getResources().getColor(R.color.app_theme));
        manager_left = new LinearLayoutManager(getActivity());
        value_left_recycle.setLayoutManager(manager_left);
        adapter_left = new ValueLeftAdapter(valueLeft, this);
        value_left_recycle.setAdapter(adapter_left);

        manager_right = new LinearLayoutManager(getActivity());
        value_right_recycle.setLayoutManager(manager_right);
        adapter_right = new ValueRightAdapter(valueRight, this);
        value_right_recycle.setAdapter(adapter_right);
        try {
            presenter.getLeftValue(BaseConsts.BASE_URL_VALUE_SERVICE_TYPE);
        } catch (Exception e) {
            showToast("数据请求出错,请稍后尝试.");
        }
    }
    @Override
    public void success(String tag, String json) {
        Gson gson = new Gson();
        if (BaseConsts.BASE_URL_VALUE_SERVICE_TYPE.equals(tag)) {
            try {
                ValueLeft left = gson.fromJson(json, ValueLeft.class);
                List<ValueLeftData> data = left.getData();
                for (ValueLeftData valueLeftData : data) {
                    valueLeft.add(valueLeftData);
                }
                if (first) {
                    if (valueLeft.size() > 0) {
                        valueLeft.get(0).setCheck(true);
                        int id = valueLeft.get(0).getId();
                        presenter.getRightValue(BaseConsts.BASE_URL_VALUE_SERVICE,String.valueOf(id));
                    }
                }

            } catch (Exception e) {
                showToast("网络数据出错。");
            }
        } else if (BaseConsts.BASE_URL_VALUE_SERVICE.equals(tag)) {
            ValueRight left = gson.fromJson(json, ValueRight.class);
            List<ValueRightInfo> data = left.getData().getList();
            valueRight.clear();
            for (ValueRightInfo rightInfo : data) {
                valueRight.add(rightInfo);
            }
            adapter_right.notifyDataSetChanged();
        }
        adapter_left.notifyDataSetChanged();
        first = false;
        if (valueLeft.size() == 0 && valueRight.size() == 0) {
            state_layout.showEmptyView("暂时没有数据.",R.mipmap.empty_view);
        }
    }

    @Override
    public void failure(String tag, ErrorCode code) {
        showToast("请求出错,请稍后尝试。");
    }

    @Override
    public void onItemClick(View v, int position) {
        String tag = (String) v.getTag();
        if ("left".equals(tag)) {
            for (ValueLeftData data : valueLeft) {
                data.setCheck(false);
            }
            valueLeft.get(position).setCheck(true);

            int id = valueLeft.get(position).getId();
            try {
                presenter.getRightValue(BaseConsts.BASE_URL_VALUE_SERVICE, String.valueOf(id));
            } catch (Exception e) {
                showToast("数据异常。");
            }
        } else {
            startActivity(getActivity(), ValueDetailActivity.class,"packageId",valueRight.get(position).getPackageId());
        }
    }
}
