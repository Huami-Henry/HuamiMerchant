package com.huami.merchant.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import com.huami.merchant.R;

/**
 * Created by young chan on 2017/5/22
 * 对话框创建工具
 */

public class DialogUtil {

    public static Dialog createMessageDialog(Context context,
                                             String title,
                                             String message,
                                             String btnName,
                                             DialogInterface.OnClickListener listener)
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        //设置对话框消息
        builder.setMessage(message);
        //设置按钮
        builder.setPositiveButton(btnName, listener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createConfirmDialog(Context context,
                                             String title,
                                             String message,
                                             String positiveBtnName,
                                             String negativeBtnName,
                                             DialogInterface.OnClickListener positiveBtnListener,
                                             DialogInterface.OnClickListener negativeBtnListener )
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        //设置对话框消息
        builder.setMessage(message);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置取消按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createSingleChoiceDialog(Context context,
                                                  String title,
                                                  String[] itemsString,
                                                  String positiveBtnName,
                                                  String negativeBtnName,
                                                  DialogInterface.OnClickListener positiveBtnListener,
                                                  DialogInterface.OnClickListener negativeBtnListener,
                                                  DialogInterface.OnClickListener itemClickListener)
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        //设置单选选项, 参数0: 默认第一个单选按钮被选中
        builder.setSingleChoiceItems(itemsString, 0, itemClickListener);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createMultiChoiceDialog(Context context,
                                                 String title,
                                                 String[] itemsString,
                                                 String positiveBtnName,
                                                 String negativeBtnName,
                                                 DialogInterface.OnClickListener positiveBtnListener,
                                                 DialogInterface.OnClickListener negativeBtnListener,
                                                 DialogInterface.OnMultiChoiceClickListener itemClickListener)
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        //设置选项
        builder.setMultiChoiceItems(itemsString, null, itemClickListener);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createListDialog(Context context,
                                          String title,
                                          String[] itemsString,
                                          String negativeBtnName,
                                          DialogInterface.OnClickListener negativeBtnListener,
                                          DialogInterface.OnClickListener itemClickListener)
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        //设置列表选项
        builder.setItems(itemsString, itemClickListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createRandomDialog(Context context,
                                            String title,
                                            String positiveBtnName,
                                            String negativeBtnName,
                                            DialogInterface.OnClickListener positiveBtnListener,
                                            DialogInterface.OnClickListener negativeBtnListener,
                                            View view)
    {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //设置对话框标题
        builder.setTitle(title);
        builder.setView(view);
        //设置确定按钮
        builder.setPositiveButton(positiveBtnName, positiveBtnListener);
        //设置确定按钮
        builder.setNegativeButton(negativeBtnName, negativeBtnListener);
        //创建一个消息对话框
        dialog = builder.create();
        return dialog;
    }

    public static Dialog createHuamiDialog(Context context, int layout){
        Dialog dialog = new Dialog(context,R.style.huami_style_dialog);
        View view = LayoutInflater.from(context).inflate(layout, null);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        return dialog;
    }

    /**
     * 创建一个Dialog
     * @param context
     * @param themeResId dialog的显示theme
     * @param layoutId dialog的 view
     * @param animationResId dialog需要显示动画的id
     * @return
     */
    public static Dialog getModelDialog(Context context,int themeResId,int layoutId,int animationResId){
        AlertDialog dialog = new AlertDialog.Builder(context,themeResId).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setContentView(layoutId);
        dialog.findViewById(animationResId).setAnimation(getInAnimation(context));
        return dialog;
    }
    public static AnimationSet getInAnimation(Context context) {
        AnimationSet out = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(150);
        ScaleAnimation scale = new ScaleAnimation(0.6f, 1.0f, 0.6f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(150);
        out.addAnimation(alpha);
        out.addAnimation(scale);
        return out;
    }
}
