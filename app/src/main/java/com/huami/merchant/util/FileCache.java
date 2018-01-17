package com.huami.merchant.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Henry on 2017/7/7.
 */

public class FileCache {
    public byte[] getFileByte(String filePath){
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
    public  byte[] getSmallBitmap(String filePath) {
        ByteArrayOutputStream baos = null;
        Bitmap bitmap = null;
        Bitmap bitmapCache = null;
        byte[] bytes = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = calculateInSampleSize(options, 400, 800);
            options.inJustDecodeBounds = false;
            bitmapCache = BitmapFactory.decodeFile(filePath, options);
            if (bitmapCache == null) {
                return null;
            }
            bitmap = BitmapUtil.rotateImageView(BitmapUtil.readPictureDegree(filePath), bitmapCache);
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            bytes = baos.toByteArray();
        } catch (Exception ignored) {

        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (baos != null) try {
                baos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (baos != null) try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 获取图片的缩放比
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
