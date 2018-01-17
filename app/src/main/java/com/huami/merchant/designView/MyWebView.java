package com.huami.merchant.designView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Henry on 2017/7/20.
 */

public class MyWebView extends WebView {
    ScrollInterface web;
    private WebSettings settings;

    public MyWebView(Context context) {
        super(context);
        initWebView();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebView();
    }
    private void initWebView() {
        settings = getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new WebViewClient());
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");//设置默认为utf-8
        settings.setLoadsImagesAutomatically(true);
    }
    public void setWebUrl(String url){
        if (isNetworkConnected(getContext())) {
            loadUrl(url);
        }
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
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        web.onSChanged(l, t, oldl, oldt);
    }
    public void setOnCustomScroolChangeListener(ScrollInterface t){
        this.web=t;
    }
    /**
     * 定义滑动接口
     */
    public interface ScrollInterface {
        void onSChanged(int l, int t, int oldl, int oldt) ;
    }
}
