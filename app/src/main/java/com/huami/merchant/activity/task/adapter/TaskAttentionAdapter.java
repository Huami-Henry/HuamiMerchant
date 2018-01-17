package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Henry on 2018/1/10.
 */
public class TaskAttentionAdapter extends RecyclerView.Adapter<TaskAttentionAdapter.TaskAttentionHolder> {
    private List<String> task_attention;
    private OnRecycleItemClickListener listener;
    public TaskAttentionAdapter(List<String> task_attention, OnRecycleItemClickListener listener) {
        this.task_attention = task_attention;
        this.listener = listener;
    }

    @Override
    public TaskAttentionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_attetion, parent, false);
        TaskAttentionHolder holder = new TaskAttentionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final TaskAttentionHolder holder, final int position) {
        holder.del_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        });
        holder.task_attention.setText((position+1)+":"+task_attention.get(position));
    }
    @Override
    public int getItemCount() {
        return task_attention.size();
    }
    public class TaskAttentionHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_attention)
        TextView task_attention;
        @BindView(R.id.del_attention)
        ImageView del_attention;
        View bottom;
        public TaskAttentionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
