package com.huami.merchant.imagepicker.ui.adapter;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huami.merchant.R;
import com.huami.merchant.imagepicker.bean.ImageItem;
import com.huami.merchant.imagepicker.utils.PickerConfig;
import com.huami.merchant.imagepicker.utils.RxBus;
import com.huami.merchant.imagepicker.utils.RxPickerManager;
import com.huami.merchant.imagepicker.utils.T;
import java.util.ArrayList;
import java.util.List;

public class PickerFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int CAMERA_TYPE = 0;
  private static final int NORMAL_TYPE = 1;

  private View.OnClickListener cameraClickListener;

  private int imageWidth;
  private PickerConfig config;

  private List<ImageItem> datas;
  private List<ImageItem> checkImage;

  public PickerFragmentAdapter(int imageWidth) {
    config = RxPickerManager.getInstance().getConfig();
    this.imageWidth = imageWidth;
    checkImage = new ArrayList<>();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (CAMERA_TYPE == viewType) {
      return new CameraViewHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_camera, parent, false));
    } else {
      return new PickerViewHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picker, parent, false));
    }
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof CameraViewHolder) {
      holder.itemView.setOnClickListener(cameraClickListener);
      return;
    }
    int dataPosition = config.isShowCamera() ? position - 1 : position;

    final ImageItem imageItem = datas.get(dataPosition);
    PickerViewHolder pickerViewHolder = (PickerViewHolder) holder;
    pickerViewHolder.bind(imageItem);

    pickerViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (config.isSingle()) {
          RxBus.singleton().post(imageItem);
        } else {
          int maxValue = config.getMaxValue();
          if (checkImage.size() == maxValue && !checkImage.contains(imageItem)) {
            T.show(holder.itemView.getContext(),holder.itemView.getContext().getString(R.string.max_select, config.getMaxValue()));
            return;
          }
          boolean b = checkImage.contains(imageItem) ? checkImage.remove(imageItem)
              : checkImage.add(imageItem);
          notifyItemChanged(holder.getAdapterPosition());
        }
      }
    });
  }

  @Override public int getItemCount() {
    if (datas != null && config.isShowCamera()) {
      return datas.size() + 1;
    } else if (datas != null) {
      return datas.size();
    }
    return 0;
  }

  @Override public int getItemViewType(int position) {
    if (config.isShowCamera() && position == 0) {
      return CAMERA_TYPE;
    } else {
      return NORMAL_TYPE;
    }
  }

  public void setData(List<ImageItem> data) {
    this.datas = data;
  }

  public void setCameraClickListener(View.OnClickListener cameraClickListener) {
    this.cameraClickListener = cameraClickListener;
  }

  public ArrayList<ImageItem> getCheckImage() {
    return (ArrayList<ImageItem>) checkImage;
  }

  private class PickerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private AppCompatCheckBox cbCheck;

    private PickerViewHolder(View itemView) {
      super(itemView);
      imageView = (ImageView) itemView.findViewById(R.id.iv_image);
      cbCheck = (AppCompatCheckBox) itemView.findViewById(R.id.cb_check);
    }

    private void bind(ImageItem imageItem) {
      ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
      layoutParams.width = imageWidth;
      layoutParams.height = imageWidth;
      imageView.setLayoutParams(layoutParams);

      RxPickerManager.getInstance().display(imageView, imageItem.getPath(), imageWidth, imageWidth);
      cbCheck.setVisibility(config.isSingle() ? View.GONE : View.VISIBLE);
      cbCheck.setChecked(checkImage.contains(imageItem));
    }
  }

  private class CameraViewHolder extends RecyclerView.ViewHolder {

    private CameraViewHolder(View itemView) {
      super(itemView);
    }
  }
}


