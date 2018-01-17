package com.huami.merchant.mvpbase;

import java.lang.ref.WeakReference;

/**
 * Created by Real_Man on 2016/8/27.
 */
public abstract class BasePresenter<T extends BaseViewInter, M extends BaseModelInter> {
    private WeakReference<T> weakReference;
    protected M model;
    public void attach(T t) {
        weakReference = new WeakReference<>(t);
        model = getModel();
    }

    public void deAttach() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    public boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    public T getView() {
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
    protected abstract M getModel();
}
