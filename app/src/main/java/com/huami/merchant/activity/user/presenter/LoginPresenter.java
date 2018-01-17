package com.huami.merchant.activity.user.presenter;

import com.huami.merchant.activity.user.MvpLoginActivity;
import com.huami.merchant.activity.user.model.LoginListener;
import com.huami.merchant.activity.user.model.LoginModelImp;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BasePresenter;

/**
 * Created by Henry on 2017/9/21.
 */
public class LoginPresenter extends BasePresenter<MvpLoginActivity,LoginModelImp> {
    @Override
    protected LoginModelImp getModel() {
        return new LoginModelImp();
    }
    public void goLogin(String userName, String passWord) {
        model.login(userName, passWord, new LoginListener() {
            @Override
            public void success() {
                if (isViewAttached()) {
                    getView().doSuccess();
                }
            }
            @Override
            public void Failure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().doFailure((String) tag,error_code);
                }
            }
        });
    }
}
