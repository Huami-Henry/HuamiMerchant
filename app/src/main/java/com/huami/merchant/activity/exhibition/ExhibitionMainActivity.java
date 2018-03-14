package com.huami.merchant.activity.exhibition;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import butterknife.BindView;

public class ExhibitionMainActivity extends MvpBaseActivity {
    @BindView(R.id.bottom_nav)
    RadioGroup bottom_nav;
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_exhibition_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        bottom_nav.check(R.id.home);

    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }
}
