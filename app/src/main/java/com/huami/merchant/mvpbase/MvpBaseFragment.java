package com.huami.merchant.mvpbase;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.huami.merchant.R;
import com.kaopiz.kprogresshud.KProgressHUD;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MvpBaseFragment<T extends BasePresenter,V extends BaseViewInter> extends Fragment {
    protected T presenter;
    private long lastToastTime;
    protected KProgressHUD hud;
    private Context mContext;
    private View mRootView;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
        }
        unbinder = ButterKnife.bind(this,mRootView);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.attach((V)this);
        }
        initData();
    }
    protected abstract T getPresenter();
    protected abstract int initLayout();
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("我的执行", "onDestroyView");
        if (presenter != null) {
            presenter.deAttach();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    /**
     * 检查网络和id是否为空
     * @return
     */
    public boolean checkSendNetRequestWithId() {
        if (isNetworkConnected(getActivity())&& !TextUtils.isEmpty(BaseApplication.UUID)) {
            return true;
        }
        return false;
    }
    /**
     * 检查网络链接状态
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    protected void showAnimation(View view,int centerX,int centerY,float startRadius,float endRadius,int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal =  ViewAnimationUtils.createCircularReveal(
                    view,
                    centerX,
                    centerY,
                    startRadius, endRadius);
            circularReveal.setDuration(duration);
            circularReveal.setInterpolator(new AccelerateInterpolator());
            circularReveal.start();
        }
    }
    public AnimationSet getInAnimation(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }

    public AnimationSet getOutAnimation(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }
    /**
     * 显示dialog
     */
    public void showLoading() {
        if (isNetworkConnected(getActivity())) {
            if (hud == null) {
                hud = KProgressHUD.create(getActivity())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("正在执行,请稍等...")
                        .setCancellable(true);
                hud.show();
            } else {
                if (!hud.isShowing()) {
                    hud.show();
                }
            }
        }
    }
    /**
     * 显示dialog
     */
    public void endLoading() {
        if (hud != null) {
            hud.dismiss();
        }
    }
    private String lastToast=null;

    /**
     * 带有样式的弹出Toast
     * @param message 要显示的信息
     * @param icon 没有可以填 0
     * @param duration 显示时长
     */
    public void showToastWithStyle(String message,int icon,int duration){
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.toast_view, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv)).setImageResource(icon);
                    (view.findViewById(R.id.icon_iv)).setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(getActivity());
                toast.setView(view);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, TDevide.dip2px(84, getActivity()));
                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }
    /**
     * 普通 toast
     * @param str
     */
    public void showToast(String str,int duration){
        Toast toast = Toast.makeText(getActivity(), str,duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 普通 toast
     * @param str
     */
    public void showToast(String str){
        BaseToast.showToast(getActivity(), str);
    }
    /**
     * 普通 toast
     */
    protected void showToast(int resId){
        BaseToast.showToast(getActivity(), resId);
    }
    /**
     * 不带参数的跳转
     * @param activity
     * @param cla
     */
    public void startActivity(Activity activity, Class cla){
        Intent intent=new Intent(activity,cla);
        startActivity(intent);
    }
    /**
     * 只允许 String Boolean Integer Float 类型传递过来
     * @param activity
     * @param cla
     * @param key
     * @param extras
     */
    public void startActivity(Activity activity,Class cla,String key,Object extras){
        Intent intent=new Intent(activity,cla);
        if (extras instanceof String) {
            intent.putExtra(key, (String) extras);
        }else if (extras instanceof Boolean) {
            intent.putExtra(key, (Boolean) extras);
        }else if (extras instanceof Integer) {
            intent.putExtra(key, (Integer) extras);
        }else if (extras instanceof Float) {
            intent.putExtra(key, (Float) extras);
        }
        startActivity(intent);
    }
    /**
     * 只允许 String Boolean Integer Float 类型传递过来
     * @param activity
     * @param cla
     * @param keys
     * @param extras
     */
    public void startActivity(Activity activity,Class cla,String[] keys,Object[] extras){
        Intent intent=new Intent(activity,cla);
        for (int i=0;i<keys.length;i++){
            if (extras[i] instanceof String) {
                intent.putExtra(keys[i], (String) extras[i]);
            }else if (extras[i] instanceof Boolean) {
                intent.putExtra(keys[i], (Boolean) extras[i]);
            }else if (extras[i] instanceof Integer) {
                intent.putExtra(keys[i], (Integer) extras[i]);
            }else if (extras[i] instanceof Float) {
                intent.putExtra(keys[i], (Float) extras[i]);
            }
        }
        startActivity(intent);
    }
    /**
     * intent不带参数待返回的跳转
     * @param activity
     * @param cla
     */
    public void startActivityForResult(Activity activity,Class cla,int requestCode){
        Intent intent=new Intent(activity,cla);
        startActivityForResult(intent, requestCode);
    }
    /**
     * intent带参数待返回的跳转
     * @param activity
     * @param cla
     */
    public void startActivityForResult(Activity activity,Class cla,String key,String extras,int requestCode){
        Intent intent=new Intent(activity,cla);
        intent.putExtra(key, extras);
        startActivityForResult(intent, requestCode);
    }
    /**
     * intent带参数待返回的跳转
     * @param activity
     * @param cla
     */
    public void startActivityForResult(Activity activity, Class cla, String keys[], Object extras[], int requestCode){
        Intent intent=new Intent(activity,cla);
        for (int i=0;i<keys.length;i++){
            if (extras[i] instanceof String) {
                intent.putExtra(keys[i], (String) extras[i]);
            }else if (extras[i] instanceof Boolean) {
                intent.putExtra(keys[i], (Boolean) extras[i]);
            }else if (extras[i] instanceof Integer) {
                intent.putExtra(keys[i], (Integer) extras[i]);
            }else if (extras[i] instanceof Float) {
                intent.putExtra(keys[i], (Float) extras[i]);
            }
        }
        startActivityForResult(intent, requestCode);
    }
}
