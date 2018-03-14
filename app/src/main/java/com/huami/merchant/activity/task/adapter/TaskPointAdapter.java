package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Henry on 2018/1/15.
 */
public class TaskPointAdapter extends RecyclerView.Adapter<TaskPointAdapter.TaskPointHolder> {
    private List<TaskPointInfo> shops;
    private OnRecycleItemClickListener listener;
    public TaskPointAdapter(List<TaskPointInfo> shops, OnRecycleItemClickListener listener) {
        this.shops = shops;
        this.listener = listener;
    }
    @Override
    public TaskPointHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_shop, parent, false);
        TaskPointHolder holder = new TaskPointHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TaskPointHolder holder, final int position) {
        final TaskPointInfo info = shops.get(position);
        holder.shop_cb.setImageResource(info.isCheck()?R.mipmap.multiple_choice_sel:R.mipmap.multiple_choice);
        holder.shop_num.setText(info.getShop_num());
        holder.shop_region.setText(info.getRegion());
        holder.shop_name.setText(info.getShort_name());
        holder.shop_address.setText(info.getShop_address());
        holder.task_shop.setTag(info.getShop_id());
        holder.task_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return shops.size();
    }
    public class TaskPointHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_cb)
        ImageView shop_cb;
        @BindView(R.id.shop_num)
        TextView shop_num;
        @BindView(R.id.shop_name)
        TextView shop_name;
        @BindView(R.id.shop_region)
        TextView shop_region;
        @BindView(R.id.shop_address)
        TextView shop_address;
        @BindView(R.id.task_shop)
        View task_shop;
        public TaskPointHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
