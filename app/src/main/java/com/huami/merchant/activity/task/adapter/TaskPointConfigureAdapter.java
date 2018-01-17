package com.huami.merchant.activity.task.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPointInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Henry on 2018/1/15.
 */

public class TaskPointConfigureAdapter extends RecyclerView.Adapter<TaskPointConfigureAdapter.TaskPointHolder> {
    private List<TaskPointInfo> shops;
    private OnRecycleItemClickListener listener;
    private Map<Integer, Integer> map;
    private boolean edit;
    public TaskPointConfigureAdapter(List<TaskPointInfo> shops, Map<Integer, Integer> map,boolean edit, OnRecycleItemClickListener listener) {
        this.shops = shops;
        this.listener = listener;
        this.map = map;
        this.edit = edit;
    }
    @Override
    public TaskPointConfigureAdapter.TaskPointHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_shop_configure, parent, false);
        TaskPointHolder holder = new TaskPointHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TaskPointHolder holder, final int position) {
        final TaskPointInfo info = shops.get(position);
        holder.shop_cb.setChecked(info.isCheck());
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
        holder.task_count.setText(""+info.getTotal_num());
        holder.task_count.setText(""+info.getMer_price());
        if (edit) {
            holder.shop_cb.setVisibility(View.VISIBLE);
            if (map.containsKey(position)) {
                holder.shop_cb.setChecked(true);
            } else {
                holder.shop_cb.setChecked(false);
            }
        }
    }
    @Override
    public int getItemCount() {
        return shops.size();
    }
    public class TaskPointHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_cb)
        CheckBox shop_cb;
        @BindView(R.id.shop_num)
        TextView shop_num;
        @BindView(R.id.shop_name)
        TextView shop_name;
        @BindView(R.id.shop_region)
        TextView shop_region;
        @BindView(R.id.shop_address)
        TextView shop_address;
        @BindView(R.id.task_price)
        TextView task_price;
        @BindView(R.id.task_count)
        TextView task_count;
        @BindView(R.id.task_shop)
        View task_shop;
        public TaskPointHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            shop_cb.setVisibility(View.GONE);
        }
    }
}
