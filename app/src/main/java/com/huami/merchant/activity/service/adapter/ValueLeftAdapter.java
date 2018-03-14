package com.huami.merchant.activity.service.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.ValueLeft;
import com.huami.merchant.bean.ValueLeftData;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueLeftAdapter extends RecyclerView.Adapter<ValueLeftAdapter.ValueLeftHolder> {
    private List<ValueLeftData> values;
    private OnRecycleItemClickListener listener;
    public ValueLeftAdapter(List<ValueLeftData> values, OnRecycleItemClickListener listener) {
        this.values = values;
        this.listener = listener;
    }
    @Override
    public ValueLeftAdapter.ValueLeftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value, parent, false);
        ValueLeftHolder holder = new ValueLeftHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ValueLeftAdapter.ValueLeftHolder holder, final int position) {
        ValueLeftData data = values.get(position);
        holder.value_name.setText(data.getName());
        holder.value_name.setTag("left");
        holder.value_name.setTextColor(Color.parseColor("#000000"));
        if (data.isCheck()) {
            holder.value_name.setBackgroundColor(Color.parseColor("#F3F3F3"));
        } else {
            holder.value_name.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.value_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        holder.description.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    public class ValueLeftHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.value_name)
        TextView value_name;
        @BindView(R.id.description)
        TextView description;
        public ValueLeftHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
