package com.huami.merchant.util;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.imagepicker.utils.RxPickerImageLoader;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GlideImageLoader implements RxPickerImageLoader {
    @Override
    public void display(ImageView imageView, String path, int width, int height) {
        Glide.with(imageView.getContext())
                .load(path)
                .error(R.drawable.ic_preview_image)
                .centerCrop()
                .override(width, height)
                .into(imageView);
    }
}
