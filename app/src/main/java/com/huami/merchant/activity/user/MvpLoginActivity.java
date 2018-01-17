package com.huami.merchant.activity.user;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.main.MainActivity;
import com.huami.merchant.activity.user.presenter.LoginPresenter;
import com.huami.merchant.activity.user.viewInter.LoginViewInter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.KeyBoardManager;
import com.huami.merchant.util.SPCache;
import butterknife.BindView;
import butterknife.OnClick;
public class MvpLoginActivity extends MvpBaseActivity<LoginPresenter, MvpLoginActivity> implements LoginViewInter {
    @BindView(R.id.login_edt_user)
    EditText login_edt_user;
    @BindView(R.id.login_edt_pswd)
    EditText login_edt_pswd;
    private boolean need_jump=true;
    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        need_jump = getIntent().getBooleanExtra("need_jump", true);
    }

    @Override
    protected void initView() {
        String hint = SPCache.getString(BaseConsts.USER_CENTER.USER_EMAIL, "");
        if (!TextUtils.isEmpty(hint)) {
            login_edt_user.setText(hint);
            login_edt_user.setSelection(hint.length());
        }
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("登录");
    }

    @Override
    public void doSuccess() {
        endLoading();
        showToast("登录成功");
        KeyBoardManager.hideSoftInputKeyBoard(this, login_edt_user.getWindowToken());
        if (need_jump) {
            startActivity(this, MainActivity.class);
        } else {
            setResult(RESULT_OK);
        }
        finish();
    }

    @Override
    public void doFailure(String tag, ErrorCode code) {
        endLoading();
        switch (code) {
            case ACTION_FAILURE:
                showToast("用户名或者密码错误。");
                break;
            case NET_FAILURE:
                showToast("网络异常请稍后再试。");
                break;
            case TRY_CATCH:
                showToast("服务器出错请稍后尝试。");
                break;
        }
    }

    @OnClick(R.id.del_login_phone)
    public void emptyUser() {
        login_edt_user.setText("");
    }

    @OnClick(R.id.back_bar)
    public void back() {
        KeyBoardManager.hideSoftInputKeyBoard(this, login_edt_user.getWindowToken());
        finish();
    }

    @OnClick(R.id.login)
    public void login() {
        String userName = login_edt_user.getText().toString();
        String password = login_edt_pswd.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        showLoading();
        presenter.goLogin(userName, password);
    }
}
