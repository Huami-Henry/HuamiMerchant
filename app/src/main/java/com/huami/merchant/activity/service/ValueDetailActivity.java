package com.huami.merchant.activity.service;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.presenter.ValuePresenter;
import com.huami.merchant.activity.web.AgentWebActivity;
import com.huami.merchant.bean.ValueDetail;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
public class ValueDetailActivity extends MvpBaseActivity<ValuePresenter,ValueDetailActivity> implements TaskViewInter {
    @BindView(R.id.web_content)
    WebView web_content;
    private int packageId;
    @BindView(R.id.purchase_count)
    EditText purchase_count;
    @Override
    protected ValuePresenter getPresenter() {
        return new ValuePresenter();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_value_detail;
    }

    @Override
    protected void initData() {
        packageId = getIntent().getIntExtra("packageId", 0);
        try {
            presenter.getDetai(BaseConsts.BASE_URL_VALUE_SERVICE_DETAIL+packageId);
        } catch (Exception e) {
            showToast("数据出错,请稍后再尝试。");
        }
    }
    @Override
    protected void initView() {
        purchase_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    int count_service = Integer.parseInt(s.toString());
                    String next = String.valueOf(count_service);
                    if (next.length() != s.length()) {
                        purchase_count.setText(count_service+"");
                        purchase_count.setSelection(next.length());
                    }
                } else {
                    purchase_count.setText(0+"");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("增值服务");
    }

    @Override
    public void doSuccess(Object tag, String json) {
        Gson gson = new Gson();
        try {
            ValueDetail detail = gson.fromJson(json, ValueDetail.class);
            if (!TextUtils.isEmpty(detail.getData().getDescs())) {
                web_content.loadDataWithBaseURL(null,detail.getData().getDescs(), "text/html" , "utf-8", null);
            }
        } catch (JsonSyntaxException e) {
            showToast("数据格式出错,请稍后尝试。");
        }
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        showToast("网络数据出错,请稍后尝试");
    }
    @OnClick(R.id.purchase)
    public void purchase(){
        String count= purchase_count.getText().toString();
        if (TextUtils.isEmpty(count)) {
            showToast("请先选择购买数量");
            return;
        }
        startActivity(this,
                PurchaseActivity.class,
                new String[]{"number","packageId"},
                new String[]{count,String.valueOf(packageId)});
    }
    @OnClick(R.id.minus_purchase)
    public void minusCount(){
        String count= purchase_count.getText().toString();
        if (!TextUtils.isEmpty(count)) {
            int purchase_count_number = Integer.valueOf(count) - 1;
            purchase_count.setText(purchase_count_number + "");
            purchase_count.setSelection(purchase_count.getText().length());
        }
    }
    @OnClick(R.id.plus_purchase)
    public void plusCount(){
        String count= purchase_count.getText().toString();
        if (!TextUtils.isEmpty(count)) {
            int purchase_count_number = Integer.valueOf(count)+1;
            purchase_count.setText(purchase_count_number+"");
        }else {
            purchase_count.setText("1");
        }
        purchase_count.setSelection(purchase_count.getText().length());
    }
    @OnClick(R.id.custom_service)
    public void  customService(){
        startActivity(this, AgentWebActivity.class,"url",BaseConsts.USER_HELP);
    }
}
