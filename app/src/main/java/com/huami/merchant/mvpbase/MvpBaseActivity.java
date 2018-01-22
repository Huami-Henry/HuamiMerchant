package com.huami.merchant.mvpbase;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.huami.merchant.R;
import com.huami.merchant.util.DisplayUtil;

import java.io.Serializable;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
public abstract class MvpBaseActivity<T extends BasePresenter,V extends BaseViewInter> extends AppCompatActivity {
    protected T presenter;
    private View progress;
    protected LayoutInflater inflater;
    private LinearLayout progress_container;
    private View loadView;
    private long lastToastTime;
    protected TextView tv_name,tv_menu;
    protected KProgressHUD hud;
    protected ImageView back_main_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.Instance.addActivity(this);
        inflater = LayoutInflater.from(this);
        setContentView(initLayout());
        ButterKnife.bind(this);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.attach((V)this);
        }
        initData();
        back_main_bar = getView(R.id.back_bar);
        if (back_main_bar != null) {
            back_main_bar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        tv_name = getView(R.id.activity_title);
        tv_menu = getView(R.id.activity_right);
        if (tv_name != null) {
            setToolBar(tv_name, tv_menu);
        }
        initView();
    }

    protected abstract T getPresenter();
    protected abstract int initLayout();
    protected abstract void initData();
    protected abstract void initView();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.deAttach();
        }
    }
    /**
     * 检查网络和id是否为空
     * @return
     */
    public boolean checkSendNetRequestWithId() {
        if (isNetworkConnected(this)&& !TextUtils.isEmpty(BaseApplication.UUID)) {
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
    protected abstract void setToolBar(TextView t_name, TextView t_menu);

    protected int dis_width = DisplayUtil.getScreenWidthPixels();
    protected int dis_height = DisplayUtil.getScreenHeightPixels();
    private FrameLayout container;
    /**
     * 开始动画
     * @param view 需要被替换的view  例如recycleView
     */
    public void setProgress(View view) {
        try {
            if (progress != null) {
                return;
            }
            loadView = view;
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            ViewParent parent = view.getParent();
            container = new FrameLayout(BaseApplication.getContext());
            ViewGroup group = (ViewGroup) parent;
            int index = group.indexOfChild(view);
            group.removeView(view);
            group.addView(container, index, lp);
            container.addView(view);
            if (inflater != null) {
                progress = inflater.inflate(R.layout.loading_dialog, null);
                progress_container = (LinearLayout) progress.findViewById(R.id.progress_container);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dis_width, dis_height);
                progress_container.setLayoutParams(params);
                container.addView(progress);
                progress_container.setTag(view);
                view.setVisibility(View.GONE);
            }
            group.postInvalidate();
        } catch (Exception e) {

        }
    }
    /**
     * 开始动画
     * @param view 需要被替换的view  例如recycleView
     */
    public void setProgressView(View view) {
        try {
            if (progress != null) {
                return;
            }
            loadView = view;
            container = (FrameLayout) view.getParent();
            container.removeView(view);
            if (inflater != null) {
                progress = inflater.inflate(R.layout.loading_dialog, null);
                progress_container = (LinearLayout) progress.findViewById(R.id.progress_container);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(dis_width, dis_height);
                progress_container.setLayoutParams(params);
                container.addView(progress,0);
            }
        } catch (Exception e) {

        }
    }
    /**
     * 开始动画
     * @param view 需要被替换的view  例如recycleView
     */
    public void setInputProgress(View view) {
        if (progress != null) {
            return;
        }
        loadView = view;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        ViewParent parent = view.getParent();
        FrameLayout container = new FrameLayout(BaseApplication.getContext());
        ViewGroup group = (ViewGroup) parent;
        int index = group.indexOfChild(view);
        group.removeView(view);
        group.addView(container, index, lp);
        container.addView(view);
        if (inflater != null) {
            progress = inflater.inflate(R.layout.loading_input, null);
            progress_container = (LinearLayout) progress.findViewById(R.id.progress_container);
            container.addView(progress);
            progress_container.setTag(view);
            view.setVisibility(View.GONE);
        }
        group.postInvalidate();
    }
    /**
     * 隐藏标题栏 全屏展示
     */
    protected void clearTop(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 显示标题栏
     */
    protected void showTop(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    /**
     * 结束动画
     */
    public void endProgress() {
        if (progress_container != null) {
            //这个与progress_container为Tag关系的是ListView对象，即progress_container.getTag()为ListView对象
            ((View) progress_container.getTag()).setVisibility(View.VISIBLE);
            progress_container.setVisibility(View.GONE);
            progress.setVisibility(View.GONE);
            progress_container = null;
            progress = null;
        }
    }
    /**
     * 结束动画
     */
    public void endProgressView() {
        if (progress_container != null) {
            //这个与progress_container为Tag关系的是ListView对象，即progress_container.getTag()为ListView对象
            container.removeViewAt(0);
            container.addView(loadView,0);
            progress = null;
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
    @Override
    public Resources getResources() {
        Resources res = BaseApplication.getContext().getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
    /**
     * 显示dialog
     */
    public void showLoading() {
        if (isNetworkConnected(this)) {
            if (hud == null) {
                hud = KProgressHUD.create(this)
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
    protected void showProgressLoading() {
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.PIE_DETERMINATE)
                .setLabel("Please wait");
        hud.show();
    }
    /**
     * 显示dialog
     */
    protected void endProgressLoading() {
        if (hud != null) {
            hud.dismiss();
            hud = null;
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
    /**
     * 退出的dialog
     */
    public void showExitDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.color_dialog).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(R.layout.dialog_tip_exit_app);
        dialog.findViewById(R.id.content_top).setAnimation(getInAnimation(this));
        dialog.findViewById(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.Instance.finishAllActivity();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
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
                Toast toast = new Toast(this);
                toast.setView(view);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, TDevide.dip2px(84, this));
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
        Toast toast = Toast.makeText(this, str,duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    /**
     * 普通 toast
     * @param str
     */
    public void showToast(String str){
        BaseToast.showToast(this, str);
    }
    /**
     * 普通 toast
     */
    protected void showToast(int resId){
        BaseToast.showToast(this, resId);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.Instance.finishActivity(this);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    /**
     * 这个稀疏数组，网上说的是提高效率的
     */
    private final SparseArray<View> views = new SparseArray<>();
    /**
     * 返回一个具体的view对象
     * 这个就是借鉴的base-adapter-helper中的方法
     * @param viewId
     * @param <T>
     * @return
     */
    public  <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
    public  <T extends View> T getView(int viewId,Dialog group) {
        View view = group.findViewById(viewId);
        return (T) view;
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
            } else if (extras[i] instanceof Float) {
                intent.putExtra(keys[i], (Float) extras[i]);
            } else {
                intent.putExtra(keys[i],(Serializable)extras[i]);
            }
        }
        startActivityForResult(intent, requestCode);
    }
    /**
     * 验证手机格式
     */
    protected boolean isMobile(String mobile) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][23456879]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobile))
            return false;
        else
            return mobile.matches(telRegex);
    }
    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    protected int getVersion() {
        PackageManager packageManager=getPackageManager();
        PackageInfo packageInfo;
        int versionCode=0;
        try {
            packageInfo=packageManager.getPackageInfo(getPackageName(),0);
            versionCode=packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
        return versionCode;
    }
    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    protected String getVersionName() {
        PackageManager packageManager=getPackageManager();
        PackageInfo packageInfo;
        String versionCode;
        try {
            packageInfo=packageManager.getPackageInfo(getPackageName(),0);
            versionCode=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
        return versionCode;
    }
}
