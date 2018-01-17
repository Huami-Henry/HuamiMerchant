package com.huami.merchant.activity.user.model;
import com.huami.merchant.code.ErrorCode;
import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.mvpbase.BaseConsts;
import com.huami.merchant.mvpbase.BaseNetDataBiz;
import com.huami.merchant.util.SPCache;
import com.squareup.okhttp.Request;
import org.json.JSONObject;
import java.io.IOException;
/**
 * Created by henry on 2017/9/21.
 */
public class LoginModelImp implements LoginModelInter, BaseNetDataBiz.RequestListener {
    private BaseNetDataBiz biz = new BaseNetDataBiz(this);
    private LoginListener listener;
    private String random;
    private String phone;
    /**
     * 登录的业务处理
     * @param name
     * @param pass
     * @param listener
     */
    @Override
    public void login(String name, String pass, LoginListener listener) {
        this.phone = name;
        this.listener = listener;
//        if (random == null) {
//            random = EncryptUtil.getRandom(16);
//        }
//        String enc_user = EncryptUtil.AESEncrypt(random, name);
//        String enc_pass = EncryptUtil.AESEncrypt(random, pass);
//        String head_enc = EncryptUtil.RSAEncrypt(random);
//        biz.getMainThreadWithHead(key, values, head_enc,BaseConsts.BASE_URL_USER);
        String[] values = new String[]{name, pass};
        String[] key= new String[]{"email", "password"};
        biz.getMainThread(BaseConsts.BASE_URL_USER,key,values,BaseConsts.BASE_URL_USER);
//        biz.getHomeData(BaseConsts.BASE_URL_USER+"?email="+name+"&password="+pass,BaseConsts.BASE_URL_USER);
    }
    @Override
    public void onResponse(BaseNetDataBiz.Model model) {
        //{"code":0,"msg":"登录成功","data":{"merUserId":261,"uuid":"e321e815e28748c085ba5f3c3f7a5299"}}
        String tag = model.getTag();
        if (BaseConsts.BASE_URL_USER.equals(tag)) {
            String json = model.getJson();
//            String s = EncryptUtil.AESDecrypt(random, json);
            try {
                JSONObject object=new JSONObject(json);
                if (object.getInt("code") == 0) {
                    JSONObject data = object.getJSONObject("data");
                    setBaseAppInfo(data.getString("merUserId"),data.getString("uuid"));
                    listener.success();
                } else {
                    listener.Failure(tag,ErrorCode.ACTION_FAILURE);
                }
            } catch (Exception e) {
                listener.Failure(tag, ErrorCode.TRY_CATCH);
            }
        }
    }
    /**
     * 保存用户的信息
     */
    private void setBaseAppInfo(String merUserId,String uuid) {
        SPCache.putString(BaseConsts.USER_CENTER.USER_ID,merUserId);
        SPCache.putString(BaseConsts.USER_CENTER.USER_TOKEN,uuid);
        SPCache.putString(BaseConsts.USER_CENTER.USER_EMAIL,phone);
        BaseApplication.UUID = merUserId;
        BaseApplication.UU_TOKEN = uuid;
    }
    @Override
    public void OnFailure(Request r, IOException o) {
        listener.Failure(r.tag(), ErrorCode.NET_FAILURE);
    }
}
