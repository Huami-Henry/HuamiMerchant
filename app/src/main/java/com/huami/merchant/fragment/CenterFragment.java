package com.huami.merchant.fragment;
import com.huami.merchant.R;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.present.CenterPresenter;
import com.huami.merchant.fragment.viewInter.CenterViewInter;
import com.huami.merchant.mvpbase.MvpBaseFragment;

public class CenterFragment extends MvpBaseFragment<CenterPresenter,CenterFragment> implements CenterViewInter{
    @Override
    public void doSuccess(Object tag, String json) {

    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {

    }

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

    }
}
