package com.huami.merchant.designView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.huami.merchant.R;

/**
 * Created by Henry on 2017/9/20.
 */
public class CheckBoxImage extends AppCompatImageView implements View.OnClickListener{
    private boolean ischeck;
    public CheckBoxImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }
    public CheckBoxImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (ischeck) {
            setImageResource(R.mipmap.multiple_choice);
            ischeck = false;
        } else {
            setImageResource(R.mipmap.multiple_choice_yes);
            ischeck = true;
        }
    }
    public boolean isCheck(){
        return ischeck;
    }
}
