package com.huami.merchant.util;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by henry 2017.5.3
 */
public class BitmapUtil {
    /**
     * 图片缩放处理
     * @param bitmap 原图片
     * @param scale 缩放倍数
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        if (scale == 1.0f) {
            return bitmap;
        } else {
            return Bitmap.createScaledBitmap(bitmap,
                    (int) (scale * (float) bitmap.getWidth()),
                    (int) (scale * (float) bitmap.getHeight()), true);
        }
    }

    /**
     * 图片缩放
     * @param filePath 图片路径
     * @param scale 缩放倍数
     * @return
     */
    public static Bitmap scaleBitmap(String filePath, float scale) {
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if (bitmap != null)
            return scaleBitmap(bitmap, scale);
        return null;
    }

    /**
     * 获取一个带圆角的图片
     * @param bitmap
     * @param roundPX
     * @return
     */
    public static Bitmap getRoundedCornerBitmapBig(Bitmap bitmap,float roundPX) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }

    public static Bitmap getRoundHeader(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_4444);

        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        //--CROP THE IMAGE
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2 - 1, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree 旋转的角度
     */

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return Bitmap
     */
    public static Bitmap rotateImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 根据路径获取质量压缩的图片
     * @param filePath 图片的路径
     * @param quality （50-98）
     * @return
     */
    public static byte[] getBitmapSize(String filePath,int quality){
        ByteArrayOutputStream baos = null;
        Bitmap bitmap = null;
        byte[] bytes = null;
        try {
            bitmap = BitmapFactory.decodeFile(filePath);
            if (bitmap == null) {
                return null;
            }
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
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
     * 按照比例和质量进行图片压缩
     * @param filePath 图片的路径
     * @param reqWidth 压缩的宽的基准值  400
     * @param reqHeight 压缩高的基准值  800
     * @param quality 压缩图片的质量 80
     * @return
     */
    public static byte[] getSmallBitmap(String filePath,int reqWidth,int reqHeight,int quality) {
        ByteArrayOutputStream bao = null;
        Bitmap bitmap = null;
        Bitmap bitmapCache = null;
        byte[] bytes = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            options.inJustDecodeBounds = false;
            bitmapCache = BitmapFactory.decodeFile(filePath, options);
            if (bitmapCache == null) {
                return null;
            }
            bitmap = BitmapUtil.rotateImageView(BitmapUtil.readPictureDegree(filePath), bitmapCache);
            bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bao);
            bytes = bao.toByteArray();
        } catch (Exception ignored) {

        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
            if (bitmapCache != null) {
                bitmapCache.recycle();
            }
            if (bao != null) try {
                bao.flush();
                bao.close();
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
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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
