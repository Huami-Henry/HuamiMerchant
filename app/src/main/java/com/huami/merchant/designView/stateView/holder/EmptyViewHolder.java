package com.huami.merchant.designView.stateView.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huami.merchant.R;

public class EmptyViewHolder extends BaseHolder {

    public ImageView ivImg;

    public EmptyViewHolder(View view) {
        tvTip = (TextView) view.findViewById(R.id.tv_message);
        ivImg = (ImageView) view.findViewById(R.id.iv_img);
    }


}
