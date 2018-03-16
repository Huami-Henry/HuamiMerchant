package com.huami.merchant.fragment.exhibition;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.activity.exhibition.ExhibitionMainActivity;
import com.huami.merchant.activity.task.TaskEditActivity;
import com.huami.merchant.fragment.exhibition.presenter.ExhibitionPresenter;
import com.huami.merchant.fragment.exhibition.view.BannerViewInter;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseFragment;
import com.huami.merchant.util.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by henry on 2018/3/14.
 */

public class ExhibitionHomeFragment extends MvpBaseFragment<ExhibitionPresenter,ExhibitionHomeFragment> implements BannerViewInter{
    @BindView(R.id.ad_banner)
    BGABanner ad_banner;
    @BindView(R.id.order_now)
    LinearLayout order_now;
    @BindView(R.id.value_service)
    LinearLayout value_service;
    @BindView(R.id.sheet_form)
    LinearLayout sheet_form;
    private List<String> bannerList=new ArrayList<>();
    @Override
    protected ExhibitionPresenter getPresenter() {
        return new ExhibitionPresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.exhibition_home_fragment;
    }
    @Override
    protected void initData() {
        showLoading();
        float width = DisplayUtil.getScreenWidthPixels()*1.0f;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, (int) (width / 5*2.5));
        ad_banner.setLayoutParams(params);
        try {
            presenter.getBanner(BaseConsts.MAIN_TOPIC,bannerList);
        } catch (Exception e) {
            e.printStackTrace();
            endLoading();
        }
    }
    @Override
    public void loadSuccess() {
        endLoading();
        ad_banner.setData(bannerList,new ArrayList<String>());
        ad_banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(itemView.getContext())
                        .load(model)
                        .placeholder(R.mipmap.theme_seize_pic)
                        .error(R.mipmap.theme_seize_pic)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
    }
    @Override
    public void loadFailure() {
        endLoading();
        showToast("网络数据异常。");
    }
    @OnClick(R.id.sheet_form)
    public void goSheet(){
        ((ExhibitionMainActivity)getActivity()).sheet.toggle();
    }
    @OnClick(R.id.value_service)
    public void goValueService(){
        ((ExhibitionMainActivity)getActivity()).service.toggle();
    }
    @OnClick(R.id.order_now)
    public void goOrder(){
        startActivity(getActivity(), TaskEditActivity.class);
    }
}
