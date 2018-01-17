package com.huami.merchant.mvpbase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;

import com.huami.merchant.acceptNet.OkHttp;
import com.huami.merchant.acceptNet.OkHttpHead;
import com.huami.merchant.util.request.UIProgressRequestListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by henry on 2017/5/3
 */
public class BaseNetDataBiz {

    private Map<String, String> parama = new HashMap<>();

    public BaseNetDataBiz(){}

    private RequestListener listener;

    public BaseNetDataBiz(RequestListener listener) {
        this.listener = listener;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    listener.onResponse((Model) msg.obj);
                    break;
                case 1:
                    listener.OnFailure((Request) msg.obj, null);
                    break;
            }
        }
    };
    private void showNetError() {
        BaseToast.showToast(BaseApplication.getContext(),"网络异常,请检查网络之后重试");
    }
    /**
     * get请求获取参数
     * @param url
     * @param tag
     */
    public void getMainDataGet(String url,String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncGet(url, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }
                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setTag((String) response.request().tag());
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * post
     * @param url
     * @param tag
     */
    public void sendJson(String url,String json,String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncPostWithJson(url, json, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setTag((String) response.request().tag());
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * test
     * @param keys
     * @param values
     */
    public void getMainThread(String[] keys, String[] values, final String tag) {
        if (isNetworkConnected()) {
            for (int i = 0; i < keys.length; i++) {
                parama.put(keys[i], values[i]);
            }
            OkHttp.asyncPost(BaseConsts.BASE_URL_USER, parama, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }

    /**
     * test
     *
     * @param keys
     * @param values
     */
    public void getMainThread(String url, String[] keys, String[] values, final String tag) {
        if (isNetworkConnected()) {

            parama.clear();
            for (int i = 0; i < keys.length; i++) {
                parama.put(keys[i], values[i]);
            }
            OkHttp.asyncPost(url, parama, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    if (listener != null) {
                        Message message = new Message();
                        message.obj = request;
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (listener != null) {
                        if (response.isSuccessful()) {
                            String json = response.body().string();
                            Model model = new Model();
                            model.setTag((String) response.request().tag());
                            model.setJson(json);
                            model.setCode(response.code());
                            Message message = new Message();
                            message.obj = model;
                            message.what = 0;
                            handler.sendMessage(message);
                        }
                    }
                }
            });

        } else {
            showNetError();
        }
    }

    /**
     * 用户登出功能
     */
    public void pushUser(String url,String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncDel(url, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setTag((String) response.request().tag());
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * test
     * @param keys
     * @param values
     */
    public void getMainThreadWithHead(String[] keys, String[] values,String head,final String tag) {
        if (isNetworkConnected()) {
            for (int i = 0; i < keys.length; i++) {
                parama.put(keys[i], values[i]);
            }
            OkHttp.asyncPost(BaseConsts.BASE_URL_USER, parama, tag, head, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setJson(json);
                        model.setTag((String) response.request().tag());
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * @param keys
     * @param values
     */
    public void getMainThreadWithHead(String url,String[] keys, String[] values,String head,final String tag) {
        if (isNetworkConnected()) {
            for (int i = 0; i < keys.length; i++) {
                parama.put(keys[i], values[i]);
            }
            OkHttp.asyncPost(url, parama, tag, head, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what =1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setJson(json);
                        model.setTag((String) response.request().tag());
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * test
     * @param keys
     * @param values
     */
    public void getMainThreadWithHead(String url,String[] keys, String[] values,String[] encrypt,final String tag) {
        if (isNetworkConnected()) {
            for (int i = 0; i < keys.length; i++) {
                parama.put(keys[i], values[i]);
            }
            OkHttp.asyncPostWithHead(url, parama, encrypt, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setJson(json);
                        model.setTag((String) response.request().tag());
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }

    public void getHomeData(String url, String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncGet(url, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setJson(json);
                        model.setTag((String) response.request().tag());
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }

    /**
     * 带有头部参数的http post请求
     * @param keys
     * @param values
     * @param head
     * @param tag
     */
    public void httpSendWithHead(String[] keys, String[] values, OkHttpHead head, final String tag) {
        for (int i = 0; i < keys.length; i++) {
            parama.put(keys[i], values[i]);
        }
        OkHttp.asyncPostWithHead(BaseConsts.BASE_URL_USER, parama,head,tag,new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Message message = new Message();
                message.obj = request;
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Model model = new Model();
                    model.setTag((String) response.request().tag());
                    model.setJson(json);
                    Message message = new Message();
                    message.obj = model;
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    /**
     * 上传一个视频
     *
     * @param url
     * @param req_listener
     * @param video
     * @param tag
     */
    public void getMainThreadUploadFile(String url, UIProgressRequestListener req_listener, File video, final String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncPost(url, req_listener, video, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setTag((String) response.request().tag());
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }

    /**
     * @param url
     * @param audio
     * @param tag
     */
    public void getMainThreadUploadFile(String url,File audio, final String tag) {
        OkHttp.asyncPost(url,null,audio,tag,new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Message message = new Message();
                message.obj = request;
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Model model = new Model();
                    model.setTag((String) response.request().tag());
                    model.setJson(json);
                    Message message = new Message();
                    message.obj = model;
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }
    /**
     *
     * @param keys
     * @param values
     * @param image 一个图片
     * @param tag
     */
    public void getMainThreadUploadFile(String[] keys, String[] values, File image, final String tag) {
        for (int i = 0; i < keys.length; i++) {
            parama.put(keys[i], values[i]);
        }
        OkHttp.asyncPostWithGif(BaseConsts.BASE_URL_USER, parama, image, tag,new Callback() {
            @Override
            public void onFailure (Request request, IOException e){
                Message message = new Message();
                message.obj = request;
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onResponse (Response response)throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Model model = new Model();
                    model.setTag((String) response.request().tag());
                    model.setJson(json);
                    model.setCode(response.code());
                    Message message = new Message();
                    message.obj = model;
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        });
    }

    /**
     * 上传图片
     * @param url
     * @param image
     * @param tag
     */
    public void getMainThreadUploadHead(KProgressHUD hud,String url, String image, String tag) {
        if (isNetworkConnected()) {
            OkHttp.asyncPostUploadHead(hud, url, image, tag, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Message message = new Message();
                    message.obj = request;
                    message.what = 1;
                    handler.sendMessage(message);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = response.body().string();
                        Model model = new Model();
                        model.setTag((String) response.request().tag());
                        model.setJson(json);
                        model.setCode(response.code());
                        Message message = new Message();
                        message.obj = model;
                        message.what = 0;
                        handler.sendMessage(message);
                    }
                }
            });
        } else {
            showNetError();
        }
    }
    /**
     * 检查网络链接状态
     * @return
     */
    public boolean isNetworkConnected() {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        return false;
    }
    public interface RequestListener{
        void onResponse(Model model);
        void OnFailure(Request r, IOException o);
    }

    public class Model{

        private String tag;

        private String json;
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }
    }
}
