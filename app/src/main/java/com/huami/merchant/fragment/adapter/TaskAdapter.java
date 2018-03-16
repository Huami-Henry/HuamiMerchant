package com.huami.merchant.fragment.adapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huami.merchant.R;
import com.huami.merchant.bean.TaskBean.TaskData.TaskInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by Henry on 2018/1/4.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<TaskInfo> tasks;
    private OnRecycleItemClickListener listener;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public TaskAdapter(List<TaskInfo> tasks, OnRecycleItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }
    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1--待审 2--审核通过 3--审核不同过
        View view = null;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_pending,parent,false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_release,parent,false);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_pend_failure,parent,false);
                break;
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_finish, parent, false);
                break;
        }
        return new TaskHolder(view);
    }
    @Override
    public int getItemViewType(int position) {
        TaskInfo info = tasks.get(position);
        long endTime = info.getTask_end_date();
        long current = System.currentTimeMillis();
        if (current > endTime) {
            if (info.getCheck_state() == 2) {
                info.setCheck_state(4);
            }
        }
        return info.getCheck_state();
    }
    @Override
    public void onBindViewHolder(TaskHolder holder, final int position) {
        try {
            TaskInfo info = tasks.get(position);
            int taskId = info.getTask_id();
            String new_task_id = generate(String.valueOf(taskId));
            holder.task_id.setText("NO:"+new_task_id);
            int itemViewType = getItemViewType(position);
            String task_result = "审批中";
            switch (itemViewType) {
                case 2:
                    task_result = "已发布";
                    break;
                case 3:
                    task_result = "审核失败";
                    break;
                case 4:
                    task_result = "已结束";
                    break;
            }
            holder.task_result.setText(task_result);
            Glide.with(holder.task_icon.getContext()).load(info.getTask_icon()).asBitmap().centerCrop().into(holder.task_icon);
            setText(holder.task_name, info.getTask_name());
            setText(holder.task_count,info.getTask_total_count()+"单");
            setText(holder.task_money,info.getTask_price()+"元");
            long accept_begin_date = info.getAccept_begin_date();
            long accept_end_date = info.getAccept_end_date();
            setText(holder.accept_begin_time, format.format(new Date(accept_begin_date)));
            setText(holder.accept_end_time, format.format(new Date(accept_end_date)));
            if (holder.check_result != null) {
                holder.check_result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
            if (holder.examine != null) {
                holder.examine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
            if (holder.data_statistics != null) {
                holder.data_statistics.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
            if (holder.look_result != null) {
                holder.look_result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
            if (holder.edit_task != null) {
                holder.edit_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(v, position);
                    }
                });
            }
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v,position);
                }
            });
        } catch (Exception e) {
        }
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    public class TaskHolder extends RecyclerView.ViewHolder {
        private TextView task_id;
        private TextView task_result;
        private TextView task_count;
        private TextView task_name;
        private TextView task_money;
        private TextView accept_begin_time;
        private TextView accept_end_time;
        private TextView data_statistics;
        private TextView check_result;
        private TextView look_result;
        private TextView edit_task;
        private TextView examine;
        private ImageView task_icon;
        private View view;
        public TaskHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            task_id =  itemView.findViewById(R.id.task_id);
            task_name = itemView.findViewById(R.id.task_name);
            task_result =  itemView.findViewById(R.id.task_result);
            task_count = itemView.findViewById(R.id.task_count);
            task_money = itemView.findViewById(R.id.task_money);
            accept_begin_time =  itemView.findViewById(R.id.accept_begin_time);
            accept_end_time = itemView.findViewById(R.id.accept_end_time);
            data_statistics = itemView.findViewById(R.id.data_statistics);
            check_result = itemView.findViewById(R.id.check_result);
            look_result =  itemView.findViewById(R.id.look_result);
            edit_task =  itemView.findViewById(R.id.edit_task);
            examine =  itemView.findViewById(R.id.examine);
            task_icon =  itemView.findViewById(R.id.task_icon);
        }
    }
    /**
     * 生成编号id
     * @param taskId
     */
    public String generate(String taskId) {
        if (TextUtils.isEmpty(taskId)) {
            return "00000";
        }
        StringBuffer buffer = new StringBuffer();
        if (taskId.length() > 5) {
            for (int i = 0; i < taskId.length()-5; i++) {
                buffer.append("0");
            }
        } else {
            for (int i = 0; i < 5 - taskId.length(); i++) {
                buffer.append("0");
            }
        }
        buffer.append(taskId);
        return buffer.toString();
    }
    public void setText(TextView textView,Object o){
        textView.setText(String.valueOf(o));
    }
}
