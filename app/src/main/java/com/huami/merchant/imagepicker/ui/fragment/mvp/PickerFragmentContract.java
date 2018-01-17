package com.huami.merchant.imagepicker.ui.fragment.mvp;

import android.content.Context;

import com.huami.merchant.imagepicker.base.BasePresenter;
import com.huami.merchant.imagepicker.base.BaseView;
import com.huami.merchant.imagepicker.bean.ImageFolder;

import java.util.List;

/**
 * @author Smile
 * @time 2017/4/19  上午10:38
 * @desc ${TODD}
 */
public interface PickerFragmentContract {

  interface View extends BaseView {
    void showAllImage(List<ImageFolder> datas);
  }

  public abstract class Presenter extends BasePresenter<View> {
    public abstract void loadAllImage(Context context);
  }
}
