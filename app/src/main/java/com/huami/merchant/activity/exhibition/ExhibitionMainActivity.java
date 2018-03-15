package com.huami.merchant.activity.exhibition;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.fragment.exhibition.ExhibitionCenterFragment;
import com.huami.merchant.fragment.exhibition.ExhibitionHomeFragment;
import com.huami.merchant.fragment.exhibition.ExhibitionServiceFragment;
import com.huami.merchant.fragment.exhibition.ExhibitionSheetFragment;
import com.huami.merchant.mvpbase.BasePresenter;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import butterknife.BindView;
public class ExhibitionMainActivity extends MvpBaseActivity {
    @BindView(R.id.bottom_nav)
    public RadioGroup bottom_nav;
    @BindView(R.id.service)
    public RadioButton service;
    @BindView(R.id.sheet)
    public RadioButton sheet;
    public ExhibitionHomeFragment home;
    public ExhibitionServiceFragment service_fm;
    public ExhibitionSheetFragment sheet_fm;
    public ExhibitionCenterFragment center;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int index_fragment;
    private Fragment from_fragment;
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
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        index_fragment = getIntent().getIntExtra("index_fragment", 1);
        switch (index_fragment) {
            case 1:
                home = new ExhibitionHomeFragment();
                from_fragment = home;
                break;
            case 2:
                service_fm = new ExhibitionServiceFragment();
                from_fragment = service_fm;
                break;
            case 3:
                sheet_fm = new ExhibitionSheetFragment();
                from_fragment = sheet_fm;
                break;
            case 4:
                center = new ExhibitionCenterFragment();
                from_fragment = center;
                break;
        }
        transaction.add(R.id.fragment_content, from_fragment);
        transaction.commit();
    }

    @Override
    protected void initView() {
        bottom_nav.check(R.id.home);
        fullScreen(this);
        bottom_nav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        home = new ExhibitionHomeFragment();
                        switchFragment(home);
                        break;
                    case R.id.service:
                        service_fm = new ExhibitionServiceFragment();
                        switchFragment(service_fm);
                        break;
                    case R.id.sheet:
                        sheet_fm = new ExhibitionSheetFragment();
                        switchFragment(sheet_fm);
                        break;
                    case R.id.center:
                        center = new ExhibitionCenterFragment();
                        switchFragment(center);
                        break;
                }
            }
        });
    }
    public void switchFragment(Fragment fragment){
        transaction=manager.beginTransaction();
        transaction.replace(R.id.fragment_content, fragment);
        transaction.commit();
    }
    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {

    }
}
