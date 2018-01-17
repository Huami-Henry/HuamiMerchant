package com.huami.merchant.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.huami.merchant.mvpbase.BaseApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Henry on 2017/7/5.
 */
public class VideoPlayManager {
    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath();//内存卡路径
    private static String video_path;
    private static String video_inner_path ="examination";
    static{
        video_path = BASE_PATH + File.separator+ BaseApplication.getContext().getPackageName()+File.separator;
        File file = new File(video_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        video_path=video_path+video_inner_path +File.separator;
        File f = new File(video_path);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    /**
     * 复制视频文件
     * @param target 需要复制的文件
     * @param file_name 对应的文件名称
     */
    public static void copyVideo(String target,String folder,String file_name) {
        File file = new File(video_path + folder + File.separator);
        if (!file.exists()) {
            file.mkdirs();
        }
        copyFile(target, video_path + folder+File.separator+file_name);
    }
    private static boolean copyFile(String oldPath, String newPath) {
        try {
            int byteSum = 0;
            int byteRead = 0;
            File oldFile = new File(oldPath);
            if (oldFile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteRead = inStream.read(buffer)) != -1) {
                    byteSum += byteRead; //字节数 文件大小
                    fs.write(buffer,0, byteRead);
                }
                inStream.close();
            }
        }catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static void deleteFile(String taskId,String filePath) {
        File file = new File(video_path + taskId + File.separator+filePath);
        Log.e("我的文件路径", file.getPath());
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }
    /**
     * 删除目标文件夹下所有文件
     * @param taskId
     */
    public static void delFolder(String taskId){
        File file = new File(video_path + taskId + File.separator);
        if (file.exists()) {
            recursionDeleteFile(file);
        }
    }

    /**
     * 获取缩略图
     * @param taskId
     * @param name
     * @return
     */
    public static Bitmap getThumbnail(String taskId,String name) {
        String file_path = video_path + taskId + File.separator + name;
        Log.e("我的文件路径", file_path);
        File file = new File(file_path);
        if (file.exists()) {
            Bitmap bitmap = null;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                retriever.setDataSource(file_path);
                bitmap = retriever.getFrameAtTime();
            }catch(Exception e) {
                return null;
            }
            try {
                retriever.release();
            }catch (RuntimeException e) {
                return null;
            }
            return bitmap;
        } else {
            return null;
        }
    }
    /**
     * 获取缩略图
     * @param taskId
     * @param name
     * @return
     */
    public static String getVideoPath(String taskId,String name) {
        String file_path = video_path + taskId + File.separator + name;
        File file = new File(file_path);
        if (file.exists()) {
            return file_path;
        } else {
            return null;
        }
    }
    /**
     * 获取缩略图
     * @return
     */
    public static String exist(String net_url) {
        String file_path = SPCache.getString(net_url, "");
        if (TextUtils.isEmpty(file_path)) {
            return null;
        }
        File file = new File(file_path);
        if (file.exists()) {
            return file_path;
        } else {
            return null;
        }
    }
    /**
     * 递归删除文件夹及其下面所有文件
     * @param file
     */
    private static void recursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                recursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 调用系统播放器播放视频
     */
    public static void playVideo(Context context,String taskId, String fileName,String url) {
        String path = video_path + taskId + File.separator + fileName;
        File file = new File(path);
        if (file.exists()) {
            url = path;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "video/mp4");
        context.startActivity(intent);
    }

    /**
     * 调用系统播放器播放视频
     */
    public static void playVideo(Context context,String url,String net_url) {
        File file = new File(url);
        if (!file.exists()) {
            url = net_url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(url), "video/mp4");
        context.startActivity(intent);
    }
}
