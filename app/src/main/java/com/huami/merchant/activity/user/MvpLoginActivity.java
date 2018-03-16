package com.huami.merchant.activity.user;
import android.graphics.Paint;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huami.merchant.R;
import com.huami.merchant.activity.ExhibitionImageView;
import com.huami.merchant.activity.exhibition.ExhibitionMainActivity;
import com.huami.merchant.activity.main.MainActivity;
import com.huami.merchant.activity.user.presenter.LoginPresenter;
import com.huami.merchant.activity.user.viewInter.LoginViewInter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import com.huami.merchant.util.KeyBoardManager;
import com.huami.merchant.util.SPCache;


import org.feezu.liuli.timeselector.Utils.TextUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
public class MvpLoginActivity extends MvpBaseActivity<LoginPresenter, MvpLoginActivity> implements LoginViewInter {
    @BindView(R.id.login_edt_user)
    EditText login_edt_user;
    @BindView(R.id.login_edt_pswd)
    EditText login_edt_pswd;
    @BindView(R.id.login_background)
    ExhibitionImageView login_background;
    @BindView(R.id.login)
    Button login_button;
    @BindView(R.id.forget)
    TextView login_forget;
    private boolean need_jump=true;
    int i = 0;
    int[] iamgeArray = {R.mipmap.exhibition_login_back_first,R.mipmap.exhibition_login_back_second,R.mipmap.exhibition_login_back_third};
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
        fullScreen(this);
        need_jump = getIntent().getBooleanExtra("need_jump", true);
        if (!TextUtils.isEmpty(BaseApplication.UU_TOKEN)) {
            startActivity(this, ExhibitionMainActivity.class);
            finish();
        }
        login_forget.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        a();
        c();
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
            startActivity(this, ExhibitionMainActivity.class);
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

//    @OnClick(R.id.del_login_phone)
//    public void emptyUser() {
//        login_edt_user.setText("");
//    }

//    @OnClick(R.id.back_bar)
//    public void back() {
//        KeyBoardManager.hideSoftInputKeyBoard(this, login_edt_user.getWindowToken());
//        finish();
//    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 新添加的东西
     */
    public void a(){

        login_background.setImages(iamgeArray).setIsCircle(true).startTask();
    }

    public void b(){

        String userName = login_edt_user.getText().toString();
        String password = login_edt_pswd.getText().toString();
        if (!TextUtil.isEmpty(userName)&&!TextUtil.isEmpty(password)){

            login_button.setBackgroundResource(R.mipmap.exhibition_login);
        }else {

            login_button.setBackgroundResource(R.mipmap.exhibition_nologin);

        }
    }

    public void c(){

        setEditTextInhibitInputSpeChat(login_edt_pswd);

        login_edt_user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int j, int i1, int i2) {

                b();
                if (charSequence.toString().contains(" ")) {
                    String[] str = charSequence.toString().split(" ");
                    String str1 = "";
                    for (int i = 0; i < str.length; i++) {
                        str1 += str[i];
                    }
                    login_edt_user.setText(str1);
                    login_edt_user.setSelection(i1);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        login_edt_pswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int j, int i1, int i2) {

                b();
                if (charSequence.toString().contains(" ")) {
                    String[] str = charSequence.toString().split(" ");
                    String str1 = "";
                    for (int i = 0; i < str.length; i++) {
                        str1 += str[i];
                    }
                    login_edt_pswd.setText(str1);
                    login_edt_pswd.setSelection(i1);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 禁止EditText输入空格
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText){
        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(" ")) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText){

        InputFilter filter=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if(matcher.find())return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }
}
