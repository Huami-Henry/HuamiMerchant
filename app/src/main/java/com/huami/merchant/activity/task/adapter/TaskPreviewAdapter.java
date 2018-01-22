package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPreviewBean.TaskPreviewData;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.util.AuditUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Henry on 2018/1/8.
 */

public class TaskPreviewAdapter extends RecyclerView.Adapter<TaskPreviewAdapter.TaskPreviewHolder> {
    private List<TaskPreviewData> datas;
    private OnRecycleItemClickListener listener;
    private boolean already;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public TaskPreviewAdapter(List<TaskPreviewData> datas, boolean already,OnRecycleItemClickListener listener) {
        this.datas = datas;
        this.listener = listener;
        this.already = already;
    }
    @Override
    public TaskPreviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_preview, parent, false);
        return new TaskPreviewHolder(view);
    }
    @Override
    public void onBindViewHolder(TaskPreviewHolder holder, final int position) {
        if (already) {
            holder.bottom_preview.setVisibility(View.GONE);
        }
        TaskPreviewData data = datas.get(position);
        String check_state= AuditUtil.getState(data.getCheck_times(),data.getState());
        holder.task_preview_count.setText(check_state);
        holder.user_case_id.setText("000"+data.getUsercase_id()+"");
        holder.shop_id.setText("000"+position);
        holder.shop_name.setText(data.getShop_name());
        holder.task_price.setText(""+data.getPrice()+"元");
        holder.task_address.setText(""+data.getShop_address());
        holder.task_region.setText(data.getRegion_name());
        if (data.getLast_mod() != 0) {
            holder.task_upload_time.setText(format.format(new Date(data.getLast_mod())));
        }
        if (data.getCheck_end_date() != 0) {
            holder.task_end_preview_date.setText(format.format(new Date(data.getCheck_end_date())));
        }
        holder.pending_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class TaskPreviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bottom_preview)
        LinearLayout bottom_preview;
        @BindView(R.id.pending_task)
        TextView pending_task;
        @BindView(R.id.task_preview_count)
        TextView task_preview_count;
        @BindView(R.id.user_case_id)
        TextView user_case_id;
        @BindView(R.id.task_upload_time)
        TextView task_upload_time;
        @BindView(R.id.shop_id)
        TextView shop_id;
        @BindView(R.id.shop_name)
        TextView shop_name;
        @BindView(R.id.task_price)
        TextView task_price;
        @BindView(R.id.task_region)
        TextView task_region;
        @BindView(R.id.task_address)
        TextView task_address;
        @BindView(R.id.task_end_preview_date)
        TextView task_end_preview_date;
        View view;
        public TaskPreviewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
