package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public void onBindViewHolder(TaskPaperHolder holder, int position) {
//        TaskPaperInfo info = papers.get(position);
//        if (!TextUtils.isEmpty(info.getName())) {
//            holder.task_paper_name.setText(info.getName());
//        }
//        if (info.isCheck()) {
//            holder.task_paper_radio.setChecked(true);
//        } else {
//            holder.task_paper_radio.setChecked(false);
//        }
    }
    @Override
    public int getItemCount() {
        return 10;
    }
    public class TaskPaperHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.task_paper_radio)
        RadioButton task_paper_radio;
        @BindView(R.id.task_paper_name)
        TextView task_paper_name;
        public TaskPaperHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
