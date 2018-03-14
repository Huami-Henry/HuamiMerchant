package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPaperInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Henry on 2018/1/12.
 */
public class TaskPaperAdapter extends RecyclerView.Adapter<TaskPaperAdapter.TaskPaperHolder> {
    private List<TaskPaperInfo> papers;
    private OnRecycleItemClickListener listener;
    public TaskPaperAdapter(List<TaskPaperInfo> papers, OnRecycleItemClickListener listener) {
        this.papers = papers;
        this.listener = listener;
    }
    @Override
    public TaskPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_paper, parent, false);
        TaskPaperHolder holder = new TaskPaperHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(TaskPaperHolder holder, final int position) {
        TaskPaperInfo info = papers.get(position);
        if (!TextUtils.isEmpty(info.getName())) {
            holder.task_paper_name.setText(info.getName());
        }
        holder.task_paper_radio.setImageResource(info.isCheck() ? R.mipmap.multiple_choice_sel : R.mipmap.multiple_choice);
        holder.paper_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        holder.task_paper_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return papers.size();
    }
    public class TaskPaperHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_paper_radio)
        ImageView task_paper_radio;
        @BindView(R.id.task_paper_name)
        TextView task_paper_name;
        @BindView(R.id.paper_content)
        LinearLayout paper_content;
        public TaskPaperHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
