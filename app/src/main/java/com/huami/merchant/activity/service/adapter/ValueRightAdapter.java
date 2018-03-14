package com.huami.merchant.activity.service.adapter;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.bean.ValueRightInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueRightAdapter extends RecyclerView.Adapter<ValueRightAdapter.ValueRightHolder> {
    private List<ValueRightInfo> infos;
    private OnRecycleItemClickListener listener;

    public ValueRightAdapter(List<ValueRightInfo> infos, OnRecycleItemClickListener listener) {
        this.infos = infos;
        this.listener = listener;
    }

    @Override
    public ValueRightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_value, parent, false);
        return new ValueRightHolder(view);
    }

    @Override
    public void onBindViewHolder(ValueRightHolder holder, final int position) {
        ValueRightInfo info = infos.get(position);
        holder.value_name.setText(info.getName());
        holder.value_name.setTextColor(Color.parseColor("#000000"));
        holder.value_name.setTextSize(20f);
        holder.description.setText(info.getIntro());
        holder.value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return infos.size();
    }

    public class ValueRightHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.value_name)
        TextView value_name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.value)
        LinearLayout value;
        public ValueRightHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
