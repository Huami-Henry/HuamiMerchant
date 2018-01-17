package com.huami.merchant.acceptNet;

import android.text.TextUtils;
import android.util.Log;

import com.huami.merchant.mvpbase.BaseApplication;
import com.huami.merchant.util.BitmapUtil;
import com.huami.merchant.util.FileCache;
import com.huami.merchant.util.request.ProgressHelper;
import com.huami.merchant.util.request.UIProgressRequestListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class OkHttp {
    private static final String TAG = "OkHttp";
    public static final OkHttpClient mOkHttpClient = new OkHttpClient();
    public static final int NET_STATE=0;
    /**
     * 设置的缓存大小
     */
    private static int cacheSize = 30 * 1024 * 1024; // 20 MiB
    private static final MediaType MEDIA_TYPE_FILE = MediaType.parse("file/file");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_GIF = MediaType.parse("image/gif");
    private static final MediaType MEDIA_TYPE_VIDEO = MediaType.parse("video/mp4");
    // timeout
    static {
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(10, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient.setCookieHandler(new CookieManager(new PersistentCookieStore(BaseApplication.getContext()), CookiePolicy.ACCEPT_ALL));
        /**
         * 当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息
         */
        mOkHttpClient.setCache(new Cache(BaseApplication.getContext().getExternalCacheDir(), cacheSize));
    }

    /**
     * 不使用异步线程。
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    private static void enqueue(Request request, Callback responseCallback) {

        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 同步Get(一般不使用)
     * @param url
     * @return String
     */
    public static String syncGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = execute(request);
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        return response.body().string();
    }

    /**
     * 异步get
     * @param url
     * @param callback
     * @return
     */
    public static void asyncGet(String url,String tag,Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }
    /**
     * 异步get
     * @param url
     * @param callback
     * @return
     */
    public static void asyncGet(String url,Object tag,Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }
    // post without file with tag
    public static void asyncPost(String url, String tag, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }
    /**
     * @param url 你服务器的地址
     * @param body 你要拼接的参数 一个个对应 key  value
     * @param callback 数据请求的回传 可以直接 new Callback(){}传进来
     */
    // post without file
    public static void asyncPost(String url, Map<String,String> body, Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                formEncodingBuilder.add(key, body.get(key));
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }
    /**
     * @param url 你服务器的地址
     * @param callback 数据请求的回传 可以直接 new Callback(){}传进来
     */
    // post without file
    public static void asyncPostWithJson(String url,String json,String tag,Callback callback) {
        RequestBody formBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }

    /**
     * @param url 你服务器的地址
     * @param body 你要拼接的参数 一个个对应 key  value
     * @param callback 数据请求的回传 可以直接 new Callback(){}传进来
     */
    // post without file
    public static void asyncPostWithHead(String url, Map<String,String> body,OkHttpHead httpHead,String tag,Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                formEncodingBuilder.add(key, body.get(key));
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .addHeader(httpHead.getKey(),httpHead.getValue())
                .build();
        enqueue(request, callback);
    }

    /**
     * 封装head和token的header传递
     * @param url
     * @param body
     * @param tag
     * @param callback
     */
    public static void asyncPostWithHead(String url, Map<String,String> body,String[] encrypt,String tag,Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                formEncodingBuilder.add(key, body.get(key));
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .addHeader("Auth-head",encrypt[0])
                .addHeader("access_token",encrypt[1])
                .build();
        enqueue(request, callback);
    }

    /**
     * 带有tag标签的文件上传
     * @param url
     * @param body
     * @param tag
     * @param callback
     */
    // post without file with tag
    public static void asyncPost(String url, Map<String, String> body, String tag,String head, Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                formEncodingBuilder.add(key, body.get(key));
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .addHeader("Auth-head",head)
                .build();
        enqueue(request, callback);
    }

    /**
     * 带有tag标签的文件上传
     * @param url
     * @param body
     * @param tag
     * @param callback
     */
    // post without file with tag
    public static void asyncPost(String url, Map<String, String> body, String tag, Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                formEncodingBuilder.add(key, body.get(key));
            }
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }

    /**
     * 带有tag标签登出系统
     *
     * @param url
     * @param tag
     * @param callback
     */
    // post without file with tag
    public static void asyncDel(String url,String tag,Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .tag(tag)
                .build();
        enqueue(request, callback);
    }

    /**
     * 带图片文件的上传
     * @param url
     * @param body
     * @param file
     * @param callback
     */
    public static void asyncPost(String url, Map<String, String> body, File file, Callback callback) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                multipartBuilder.addFormDataPart(key, body.get(key));
            }
        }
        if (file != null && file.exists()) {
            multipartBuilder.addFormDataPart("headpic","image", RequestBody.create(MEDIA_TYPE_PNG, BitmapUtil.getSmallBitmap(file.getAbsolutePath(),400,800,80)));
        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }
    /**
     * 带图片文件的上传
     * @param url
     * @param file
     * @param callback
     */
    public static void asyncPostUploadHead(KProgressHUD hud, String url, String file, String tag, Callback callback) {
        try {
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(MultipartBuilder.FORM);
            multipartBuilder.addFormDataPart("image","image.png", RequestBody.create(MEDIA_TYPE_PNG, BitmapUtil.getSmallBitmap(file,400,800,80)));
            RequestBody formBody = multipartBuilder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .tag(tag)
                    .build();
            enqueue(request, callback);
        } catch (Exception e) {
            if (hud != null) {
                hud.dismiss();
            }
        }
    }

    /**
     * 带gif的图片上传
     *
     * @param url
     * @param body
     * @param file
     * @param tag
     * @param callback
     */
    //	 post with gif
    public static void asyncPostWithGif(String url, Map<String, String> body, File file, String tag, Callback callback) {
        try {
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(MultipartBuilder.FORM);
            for (String key : body.keySet()) {
                if (!TextUtils.isEmpty(body.get(key))) {
                    multipartBuilder.addFormDataPart(key, body.get(key));
                }
            }
            if (file != null && file.exists()) {
                byte[] bytes = getVideoBytes(file.getAbsolutePath());
                multipartBuilder.addFormDataPart("img", file.getName(), RequestBody.create(MEDIA_TYPE_GIF, bytes));
            }
            RequestBody formBody = multipartBuilder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .tag(tag)
                    .post(formBody)
                    .build();
            enqueue(request, callback);
        } catch (Exception e) {

        }
    }
    private static String guessMimeType(String path){
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null){
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    /**
     * 多文件上传 主要图片和视频
     * @param url
     * @param body
     * @param files
     * @param callback
     */
    public static void asyncPost(String url, Map<String, String> body, List<File> files, Callback callback) {
        int index=0;
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        for (String key : body.keySet()) {
            if (!TextUtils.isEmpty(body.get(key))) {
                multipartBuilder.addFormDataPart(key, body.get(key));
            }
        }
        for (File file : files) {
            String file_name = file.getName();
            index++;
            if (file != null && file.exists()) {
                if (!file_name.endsWith(".mp4")&&!file_name.endsWith(".avi")&&!file_name.endsWith(".rmvb")&&!file_name.endsWith("." +
                        "rm")) {
                    if (file_name.endsWith(".gif")) {
                        byte[] bytes = getVideoBytes(file.getAbsolutePath());
                        multipartBuilder.addFormDataPart("img" + index, file_name, RequestBody.create(MEDIA_TYPE_GIF, bytes));
                    } else {
                        multipartBuilder.addFormDataPart("img" + index, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, BitmapUtil.getSmallBitmap(file.getAbsolutePath(),400,800,80)));
                    }
                } else {
                    byte[] bytes=getVideoBytes(file.getAbsolutePath());
                    multipartBuilder.addFormDataPart("video" + index,file_name, RequestBody.create(MEDIA_TYPE_VIDEO, bytes));
                }
            }
        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }
    /**
     * 多文件上传 主要图片和视频
     * @param url
     * @param files
     * @param callback
     */
    public static void asyncPost(KProgressHUD hud, String url, List<File> files, Callback callback) {
        int index=0;
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        int errorPic = 0;
        for (File file : files) {
            String file_name = file.getName();
            index++;
            if (file != null && file.exists()) {
                if (!file_name.endsWith(".mp4")&&!file_name.endsWith(".avi")&&!file_name.endsWith(".rmvb")&&!file_name.endsWith("." +
                        "rm")) {
                    if (file_name.endsWith(".gif")) {
                        byte[] bytes = getVideoBytes(file.getAbsolutePath());
                        multipartBuilder.addFormDataPart("img" + index, file_name, RequestBody.create(MEDIA_TYPE_GIF, bytes));
                    } else {
                        byte[] small_byte = BitmapUtil.getSmallBitmap(file.getAbsolutePath(),400,800,80);
                        if (small_byte != null) {
                            multipartBuilder.addFormDataPart("img" + index, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, small_byte));
                        } else {
                            errorPic++;
                        }
                    }
                } else {
                    byte[] bytes=getVideoBytes(file.getAbsolutePath());
                    multipartBuilder.addFormDataPart("video" + index,file_name, RequestBody.create(MEDIA_TYPE_VIDEO, bytes));
                }
            }
        }
        if (errorPic == files.size()) {
            if (hud != null) {
                hud.dismiss();
            }
            return;
        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }
    /**
     * 多文件上传 主要图片
     * @param url
     * @param files
     * @param callback
     */
    public static void asyncPostWithoutPress(KProgressHUD hud, String url, List<File> files, int type, Callback callback) {
        int index=0;
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        int errorPic = 0;
        for (File file : files) {
            index++;
            if (file != null && file.exists()) {
                byte[] small_byte=null;
                switch (type) {
                    case 0:
                        small_byte = BitmapUtil.getSmallBitmap(file.getAbsolutePath(),400,800,80);
                        break;
                    case 1:
                        small_byte = BitmapUtil.getBitmapSize(file.getAbsolutePath(),95);
                        break;
                }
                if (small_byte != null) {
                    multipartBuilder.addFormDataPart("img" + index, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, small_byte));
                } else {
                    errorPic++;
                }
            }
        }
        if (errorPic == files.size()) {
            if (hud != null) {
                hud.dismiss();
            }
            return;
        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }

    /**
     * 上传单个视频到进度条的
     *
     * @param url
     * @param listener
     * @param video
     * @param tag
     * @param callback
     */
    public static void asyncPost(String url, UIProgressRequestListener listener, File video, String tag, Callback callback) {
        try {
            MultipartBuilder multipartBuilder = new MultipartBuilder();
            multipartBuilder.type(MultipartBuilder.FORM);
            String video_name = video.getName();
            FileCache fileCache = new FileCache();
            byte[] bytes_video = fileCache.getFileByte(video.getAbsolutePath());
            multipartBuilder.addFormDataPart("video", video_name, RequestBody.create(MEDIA_TYPE_VIDEO, bytes_video));
            RequestBody formBody = multipartBuilder.build();
            if (listener != null) {
                Request request = new Request.Builder()
                        .url(url)
                        .tag(tag)
                        .post(ProgressHelper.addProgressRequestListener(tag, formBody, listener))
                        .build();
                enqueue(request, callback);
            } else {
                Request request = new Request.Builder()
                        .url(url)
                        .tag(tag)
                        .post(formBody)
                        .build();
                enqueue(request, callback);
            }
        } catch (Exception e) {

        }
    }
    /**
     * 上传gif和视频
     * @param url
     * @param audio
     * @param callback
     */
    public static void asyncPost(String url,File audio, String tag, Callback callback) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.FORM);
        String video_name = audio.getName();
        byte[] bytes_video=getVideoBytes(audio.getAbsolutePath());
        multipartBuilder.addFormDataPart("video",video_name, RequestBody.create(MEDIA_TYPE_VIDEO, bytes_video));
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }
    /**
     * 获得指定文件的byte数组
     */
    private static byte[] getVideoBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(2*1024*1024);
            byte[] b = new byte[2*1024*1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
