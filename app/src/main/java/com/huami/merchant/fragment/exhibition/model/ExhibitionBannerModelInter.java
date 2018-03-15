package com.huami.merchant.fragment.exhibition.model;
import com.huami.merchant.fragment.listener.InterLoadListener;
import com.huami.merchant.mvpbase.BaseModelInter;
import java.util.List;
/**
 * Created by henry on 2018/3/15.
 */

public interface ExhibitionBannerModelInter extends BaseModelInter{
    void getBanner(String url, List<String> banners, InterLoadListener listener) throws Exception;
}
