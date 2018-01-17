package com.huami.merchant.util;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.acceptNet.OkHttp;
import com.huami.merchant.mvpbase.BaseApplication;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;

/**
 * Created by Henry on 2017/6/16.
 */

public class SingleDownLoadService implements Callback{
    private SoftReference<Dialog> softReference;
    private String app_url;
    private String FILE_DOWN_PATH = Environment
            .getExternalStoragePublicDirectory("com.huami.app.DownLoad")
            .getAbsolutePath() + File.separator;
    public SingleDownLoadService(Dialog dialog,String app_url) {
        this.app_url = app_url;
        softReference = new SoftReference<>(dialog);
        progress_tv= (TextView) dialog.findViewById(R.id.progress_tv);
        my_progress= (ProgressBar) dialog.findViewById(R.id.my_progress);
    }
    /**
     * 先初始化下载的配置
     * @param directory
     * @param fileName
     */
    public void setDownloadPath(String directory,String fileName){
        try {
            File file_directory = new File(FILE_DOWN_PATH);
            if (!file_directory.exists()) {
                file_directory.mkdirs();
            }
            FILE_DOWN_PATH = FILE_DOWN_PATH + fileName;
            File file = new File(FILE_DOWN_PATH);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            Log.e("我的异常", e.getMessage());
        }
    }

    /**
     * 开始下载文件
     */
    public void startDownApp(){
        Request request = new Request.Builder().url(app_url).build();
        OkHttp.mOkHttpClient.newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Request request, IOException e) {
        handler.sendEmptyMessage(DOWN_ERROR);
    }

    private TextView progress_tv;
    private ProgressBar my_progress;
    private Dialog dialog_progress;
    @Override
    public void onResponse(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        // 储存下载文件的目录
        try {
            is = response.body().byteStream();
            long total = response.body().contentLength();
            File file = new File(FILE_DOWN_PATH);
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                int progress = (int) (sum * 1.0f / total * 100);
                // 下载中
                Message message = new Message();
                message.arg1 = progress;
                message.what = DOWN_PROGRESS;
                handler.sendMessage(message);
            }
            fos.flush();
            // 下载完成
            handler.sendEmptyMessage(DOWN_SUCCESS);
        } catch (Exception e) {
            handler.sendEmptyMessage(DOWN_ERROR);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }
        }
    }
    private final int DOWN_PROGRESS = 0;
    private final int DOWN_ERROR = 1;
    private final int DOWN_SUCCESS = 2;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWN_PROGRESS:
                    dialog_progress = softReference.get();
                    if (dialog_progress != null) {
                        if (msg.arg1 <= 100) {
                            progress_tv.setText("下载进度" + msg.arg1 + "%");
                            my_progress.setProgress(msg.arg1);
                        }
                    }
                    break;
                case DOWN_SUCCESS:
                    Dialog dialog_success = softReference.get();
                    if (dialog_success != null) {
                        openFile(new File(FILE_DOWN_PATH));
                    }
                    break;
                case DOWN_ERROR:
                    Dialog dialog = softReference.get();
                    if (dialog != null) {
                        TextView textView = (TextView) dialog.findViewById(R.id.progress_tv);
                        textView.setText("文件下载失败.");
                    }
                    break;
            }
        }
    };
    /**
     * 通过文件安装apk
     *
     * @param file
     */
    private void openFile(File file) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            Uri data;
            // 判断版本大于等于7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                data = FileProvider.getUriForFile(BaseApplication.getContext(), "com.mystery.fileProvider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                data = Uri.fromFile(file);
            }
            intent.setDataAndType(data,
                    "application/vnd.android.package-archive");
            BaseApplication.getContext().startActivity(intent);
        } catch (Exception e) {
        }
    }
}
