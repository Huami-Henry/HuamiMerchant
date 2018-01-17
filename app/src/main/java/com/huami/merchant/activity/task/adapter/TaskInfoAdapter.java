package com.huami.merchant.activity.task.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.bean.TaskInfoHtml;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Henry on 2018/1/11.
 */

public class TaskInfoAdapter extends RecyclerView.Adapter<TaskInfoAdapter.TaskInfoHolder> {
    private List<TaskInfoHtml> task_info;
    private OnRecycleItemClickListener listener;
    public TaskInfoAdapter(List<TaskInfoHtml> task_info, OnRecycleItemClickListener listener) {
        this.task_info = task_info;
        this.listener = listener;
    }

    @Override
    public TaskInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_info_tv, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_info_iv, parent, false);
        }
        TaskInfoHolder holder = new TaskInfoHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        return task_info.get(position).getUrl()==null?0:1;
    }

    @Override
    public void onBindViewHolder(TaskInfoHolder holder, final int position) {
        TaskInfoHtml html = task_info.get(position);
        if (!TextUtils.isEmpty(html.getText())) {
            holder.task_info.setText(html.getText());
        } else {
            Glide.with(holder.task_info_iv.getContext()).load(html.getUrl()).asBitmap().into(holder.task_info_iv);
        }
        holder.text_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        holder.text_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return task_info.size();
    }
    public class TaskInfoHolder extends RecyclerView.ViewHolder {
        private TextView task_info;
        private ImageView task_info_iv;
        private ImageView text_edit;
        private ImageView text_del;
        public TaskInfoHolder(View itemView) {
            super(itemView);
            task_info = (TextView) itemView.findViewById(R.id.task_info);
            task_info_iv = (ImageView) itemView.findViewById(R.id.task_info_iv);
            text_edit = (ImageView) itemView.findViewById(R.id.text_edit);
            text_del = (ImageView) itemView.findViewById(R.id.text_del);
        }
    }
}
