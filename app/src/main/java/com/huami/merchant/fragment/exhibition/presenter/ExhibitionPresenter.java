package com.huami.merchant.fragment.exhibition.presenter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.exhibition.ExhibitionHomeFragment;
import com.huami.merchant.fragment.exhibition.model.ExhibitionBannerModelImp;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BasePresenter;
import java.util.List;
/**
 * Created by henry on 2018/3/15.
 */
public class ExhibitionPresenter extends BasePresenter<ExhibitionHomeFragment,ExhibitionBannerModelImp> {
    @Override
    protected ExhibitionBannerModelImp getModel() {
        return new ExhibitionBannerModelImp();
    }
    public void getBanner(String url, List<String> banners) throws Exception {
        model.getBanner(url, banners, new InterLoadListener() {
            @Override
            public void loadSuccess(Object tag, String json) {
                if (isViewAttached()) {
                    getView().loadSuccess();
                }
            }
            @Override
            public void loadFailure(Object tag, ErrorCode error_code) {
                if (isViewAttached()) {
                    getView().loadFailure();
                }
            }
        });
    }
}
