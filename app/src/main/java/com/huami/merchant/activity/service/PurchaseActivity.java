package com.huami.merchant.activity.service;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.activity.service.presenter.PurchasePresenter;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.fragment.viewInter.TaskViewInter;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.MvpBaseActivity;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.BindView;
import butterknife.OnClick;

public class PurchaseActivity extends MvpBaseActivity<PurchasePresenter,PurchaseActivity> implements TaskViewInter{
    private String packageId;
    private String number;
    @BindView(R.id.pay)
    TextView pay;
    @BindView(R.id.pay_price)
    TextView pay_price;
    @BindView(R.id.account_balance)
    TextView account_balance;
    @BindView(R.id.user_name)
    EditText user_name;
    @BindView(R.id.phone_number)
    EditText phone_number;
    @Override
    protected PurchasePresenter getPresenter() {
        return new PurchasePresenter();
    }
    @Override
    protected int initLayout() {
        return R.layout.activity_purchase;
    }

    @Override
    protected void initData() {
        packageId = getIntent().getStringExtra("packageId");
        number = getIntent().getStringExtra("number");
        try {
            presenter.getPrice(
                    BaseConsts.BASE_URL_SINGLE_VALUE_SERVICE_PRICE,
                    BaseApplication.UU_TOKEN,BaseApplication.UUID,packageId,number);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("数据异常请稍后尝试");
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setToolBar(TextView t_name, TextView t_menu) {
        t_name.setText("增值服务购买");
    }

    private boolean can_pay = true;
    @Override
    public void doSuccess(Object tag, String json) {
        if (BaseConsts.BASE_URL_SINGLE_VALUE_SERVICE_PRICE.equals(tag)) {
            try {
                JSONObject object = new JSONObject(json);
                JSONObject data = object.getJSONObject("data");
                if (data != null) {
                    double totalAmount = data.getDouble("totalAmount");
                    pay_price.setText(totalAmount+"元");
                    double balance = data.getDouble("balance");
                    account_balance.setText(balance+"元");
                    if (totalAmount > balance) {
                        pay.setBackgroundColor(getResources().getColor(R.color.theme_daren));
                        can_pay = false;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                showToast("数据异常,请稍后再试");
            }
        } else {
            try {
                JSONObject object = new JSONObject(json);
                int code = object.getInt("code");
                if (code == 0) {
                    showToast("增值服务购买成功.");
                    finishActivity(this);
                    finishActivity(ValueDetailActivity.class);
                    finishActivity(SingleValueActivity.class);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                showToast("数据异常,请稍后再试");
            }
        }
    }
    @Override
    public void doFailure(Object tag, ErrorCode code) {
        showToast("网络异常,请稍后再试");
    }
    @OnClick(R.id.pay)
    public void payService(){
        if (!can_pay) {
            showToast("您的余额不足,请联系客服进行充值。");
            return;
        }
        String name = user_name.getText().toString();
        String phone = phone_number.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("联系人不能为空.");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("手机号不能为空.");
            return;
        }
        try {
            presenter.payService(
                    BaseConsts.PAY_SERVICE,
                    BaseApplication.UU_TOKEN,
                    BaseApplication.UUID,phone,name,packageId,number);
        } catch (Exception e) {
            e.printStackTrace();
            showToast("参数异常,请稍后再试.");
        }
    }
}
