package com.huami.merchant.designView.stateView.helper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huami.merchant.designView.stateView.holder.EmptyViewHolder;
import com.huami.merchant.designView.stateView.holder.ErrorViewHolder;
import com.huami.merchant.designView.stateView.holder.LoadingViewHolder;
import com.huami.merchant.designView.stateView.holder.NoNetworkViewHolder;
import com.huami.merchant.designView.stateView.holder.TimeOutViewHolder;

public class ViewHelper {
    public static final int ERROR = 1;
    public static final int EMPTY = 2;
    public static final int TIMEOUT = 3;
    public static final int NOT_NETWORK = 4;
    public static final int LOADING = 5;
    public TextView getTvTip(int type, View view) {
        switch (type) {
            case ERROR:
                return ((ErrorViewHolder) view.getTag()).tvTip;
            case EMPTY:
                return ((EmptyViewHolder) view.getTag()).tvTip;
            case TIMEOUT:
                return ((TimeOutViewHolder) view.getTag()).tvTip;
            case NOT_NETWORK:
                return ((NoNetworkViewHolder) view.getTag()).tvTip;
            case LOADING:
                return ((LoadingViewHolder) view.getTag()).tvTip;
            default:
                return null;
        }
    }
    public ImageView getImg(int type, View view) {
        switch (type) {
            case ERROR:
                return ((ErrorViewHolder) view.getTag()).ivImg;
            case EMPTY:
                return ((EmptyViewHolder) view.getTag()).ivImg;
            case TIMEOUT:
                return ((TimeOutViewHolder) view.getTag()).ivImg;
            case NOT_NETWORK:
                return ((NoNetworkViewHolder) view.getTag()).ivImg;
            default:
                return null;
        }
    }


}
