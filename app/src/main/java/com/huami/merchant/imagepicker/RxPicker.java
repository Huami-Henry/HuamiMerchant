package com.huami.merchant.imagepicker;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.imagepicker.ui.RxPickerActivity;
import com.huami.merchant.imagepicker.ui.fragment.ResultHandlerFragment;
import com.huami.merchant.imagepicker.utils.PickerConfig;
import com.huami.merchant.imagepicker.utils.RxPickerImageLoader;
import com.huami.merchant.imagepicker.utils.RxPickerManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * @author Smile
 * @time 2017/4/18  下午6:01
 * @desc ${TODD}
 */
public class RxPicker {

    private RxPicker(PickerConfig config) {
        RxPickerManager.getInstance().setConfig(config);
    }
    /**
     * init RxPicker
     */
    public static void init(RxPickerImageLoader imageLoader) {
        RxPickerManager.getInstance().init(imageLoader);
    }


    /**
     * Using the custom config
     */

    static RxPicker of(PickerConfig config) {
        return new RxPicker(config);
    }

    /**
     * Using the default config
     */
    public static RxPicker of() {
        return new RxPicker(new PickerConfig());
    }

    /**
     * Set the selection mode
     */
    public RxPicker single(boolean single) {
        RxPickerManager.getInstance().setMode(single ? PickerConfig.SINGLE_IMG : PickerConfig.MULTIPLE_IMG);
        return this;
    }

    /**
     * Set the show  Taking pictures;
     */
    public RxPicker camera(boolean showCamera) {
        RxPickerManager.getInstance().showCamera(showCamera);
        return this;
    }
    /**
     * Set the show  Taking pictures;
     */
    public RxPicker showCrop(boolean showCamera) {
        RxPickerManager.getInstance().showCamera(showCamera);
        return this;
    }

    /**
     * Set the select  image limit
     */
    public RxPicker limit(int minValue, int maxValue) {
        RxPickerManager.getInstance().limit(minValue, maxValue);
        return this;
    }

    /**
     * start picker from activity
     */
    public Observable<List<ImageItem>> start(Activity activity) {
        return start(activity.getFragmentManager());
    }

    /**
     * start picker from fragment
     */
    public Observable<List<ImageItem>> start(Fragment fragment) {
        return start(fragment.getFragmentManager());
    }

    /**
     * start picker from fragment
     */
    private Observable<List<ImageItem>> start(FragmentManager fragmentManager) {
        ResultHandlerFragment fragment = (ResultHandlerFragment) fragmentManager.findFragmentByTag(ResultHandlerFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = ResultHandlerFragment.newInstance();
            fragmentManager.beginTransaction().add(fragment, fragment.getClass().getSimpleName()).commit();
        } else if (fragment.isDetached()) {
            fragmentManager.beginTransaction().attach(fragment).commit();
        }
        return getListItem(fragment);
    }


    private Observable<List<ImageItem>> getListItem(final ResultHandlerFragment finalFragment) {
        return finalFragment.getAttachSubject().filter(new Predicate<Boolean>() {
            @Override
            public boolean test(@NonNull Boolean aBoolean) throws Exception {
                return aBoolean;
            }
        }).flatMap(new Function<Boolean, ObservableSource<List<ImageItem>>>() {
            @Override
            public ObservableSource<List<ImageItem>> apply(@NonNull Boolean aBoolean) throws Exception {
                Intent intent = new Intent(finalFragment.getActivity(), RxPickerActivity.class);
                finalFragment.startActivityForResult(intent, ResultHandlerFragment.REQUEST_CODE);
                return finalFragment.getResultSubject();

            }
        }).take(1);
    }


}
