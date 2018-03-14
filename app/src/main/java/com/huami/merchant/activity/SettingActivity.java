package com.huami.merchant.activity;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.activity.web.AgentWebActivity;
import com.huami.merchant.mvpbase.ActivityManager;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.SPCache;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.OnClick;
public class SettingActivity extends MvpBaseActivity implements BaseNetDataBiz.RequestListener{
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("设置");
    }
    @OnClick(R.id.clear_memory)
    public void clearMemory(){
        showToast("缓存清除成功");
    }
    @OnClick(R.id.about_us)
    public void aboutUs(){
        startActivity(this, AgentWebActivity.class,"url",BaseConsts.USER_HELP);
    }
    @OnClick(R.id.version_check)
    public void versionCheck(){

    }
    @OnClick(R.id.login_out)
    public void loginOut(){
        showLoading();
        String[] keys = new String[]{"uuid","merUserId"};
        String[] values = new String[]{BaseApplication.UU_TOKEN,BaseApplication.UUID};
        biz.getMainThread(BaseConsts.BASE_URL_LOGIN_OUT,keys,values,BaseConsts.BASE_URL_LOGIN_OUT);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        endLoading();
        String json = model.getJson();
        try {
            JSONObject object = new JSONObject(json);
            if (object.getInt("code") == 0) {
                showToast("退出成功");
                SPCache._removePersonDetail();
                startActivity(this, MvpLoginActivity.class);
                ActivityManager.Instance.finishAllActivity();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnFailure(Request r, IOException o) {
        endLoading();
        showToast("退出失败,您已登出");
    }
}

