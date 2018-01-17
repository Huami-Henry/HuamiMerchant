package com.huami.merchant.activity.user.model;

import com.huami.merchant.mvpbase.BaseModelInter;

/**
 * Created by Henry on 2017/9/21.
 */

public interface LoginModelInter extends BaseModelInter {
    void login(String name, String pass, LoginListener listener);
}
