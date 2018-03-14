package com.huami.merchant.fragment;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huami.merchant.R;
import com.huami.merchant.activity.SettingActivity;
import com.huami.merchant.activity.service.ValueServiceActivity;
import com.huami.merchant.activity.task.AlreadyBuyActivity;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.activity.web.AgentWebActivity;
import com.huami.merchant.bean.UserInfo;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.present.CenterPresenter;
import com.huami.merchant.fragment.viewInter.CenterViewInter;
import com.huami.merchant.mvpbase.ActivityManager;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseFragment;
import com.huami.merchant.util.SPCache;

import butterknife.BindView;
import butterknife.OnClick;

public class CenterFragment extends MvpBaseFragment<CenterPresenter,CenterFragment> implements CenterViewInter{
    @BindView(R.id.user_icon)
    ImageView user_icon;
    @BindView(R.id.user_company)
    TextView user_company;
    @BindView(R.id.company_des)
    TextView company_des;
    @BindView(R.id.account_balance)
    TextView account_balance;
    @Override
    protected CenterPresenter getPresenter() {
        return new CenterPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.fragment_center;
    }
    @Override
    protected void initData() {
        presenter.getCenterInfo(BaseApplication.UU_TOKEN,BaseApplication.UUID);
    }
    @OnClick(R.id.personal_edition)
    public void personalService(){
        startActivity(getActivity(), ValueServiceActivity.class);
    }
    @OnClick(R.id.use_help)
    public void userHelp(){
        startActivity(getActivity(), AgentWebActivity.class,"url", BaseConsts.USER_HELP);
    }
    @Override
    public void doSuccess(Object tag, String json) {
        if (BaseConsts.BASE_URL_USER_CENTER.equals(tag)) {
            Gson gson = new Gson();
            UserInfo info = gson.fromJson(json, UserInfo.class);
            updateUser(info);
        }
    }

    private void updateUser(UserInfo info) {
        try {
            UserInfo.UserData.UserDetail detail = info.getData().getUserInfo();
            if (!TextUtils.isEmpty(detail.getIcon())) {
                Glide.with(user_icon.getContext()).load(detail.getIcon()).asBitmap().placeholder(R.mipmap.icon_user).error(R.mipmap.icon_user).into(user_icon);
            }
            user_company.setText(detail.getName());
            company_des.setText(detail.getMerchar_desc());
            account_balance.setText(""+detail.getBalance()+"元");
        } catch (Exception e) {
            showToast("用户信息获取失败。");
        }
    }

    @Override
    public void doFailure(Object tag, ErrorCode code) {
        endLoading();
        switch (code) {
            case PARAMA_EMPTY:
                showToast("用户未登录。");
                break;
            case CODE_ERROR:
                showToast("您的账户信息已过期或者在别的设备上登录。");
                SPCache._removePersonDetail();
                startActivity(getActivity(), MvpLoginActivity.class);
                ActivityManager.Instance.finishAllActivity();
                break;
        }
    }
    @OnClick(R.id.setting)
    public void setting(){
        startActivity(getActivity(), SettingActivity.class);
    }
    @OnClick(R.id.already_buy)
    public void alreadyBuy(){
        startActivity(getActivity(), AlreadyBuyActivity.class);
    }
}
