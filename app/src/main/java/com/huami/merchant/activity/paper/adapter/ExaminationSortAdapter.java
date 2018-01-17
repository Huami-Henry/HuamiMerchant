package com.huami.merchant.activity.paper.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskPaper.ExaminationInner.Body.Options;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskAnswer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry on 2017/5/22.
 */

public class ExaminationSortAdapter extends RecyclerView.Adapter<ExaminationSortAdapter.ExaminationSortHolder>{
    private List<Options> optionInfos;
    private TaskAnswer answerInfo;
    private List<Integer> answer_index=new ArrayList<>();
    public ExaminationSortAdapter(List<Options> optionInfos,TaskAnswer answerInfo) {
        this.optionInfos = optionInfos;
        this.answerInfo = answerInfo;
    }
    @Override
    public ExaminationSortHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sort, parent, false);
        ExaminationSortHolder holder = new ExaminationSortHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ExaminationSortHolder holder, final int position) {
        if (TextUtils.isEmpty(answerInfo.getAnswer())) {
            for (int i = 0; i < optionInfos.size(); i++) {
                answer_index.add(i + 1);
            }
        } else {
            String[] split = answerInfo.getAnswer().split(",");
            for (int i = 0; i < optionInfos.size(); i++) {
                answer_index.add(Integer.valueOf(split[i]));
            }
        }
        Options option = optionInfos.get(answer_index.get(position)-1);
        holder.exam_sort_number.setText(""+answer_index.get(position));

        holder.exam_sort_number.setTag(answer_index.get(position));

        holder.exam_sort_title.setText(option.getContent());
    }

    @Override
    public int getItemCount() {
        return optionInfos.size();
    }

    private int index;
    public void setPosition(int position){
        this.index = position;
    }
    public class ExaminationSortHolder extends RecyclerView.ViewHolder{
        private TextView exam_sort_number,exam_sort_title;
        private ImageView exam_sort_sort;
        public ExaminationSortHolder(View itemView) {
            super(itemView);
            exam_sort_number = (TextView) itemView.findViewById(R.id.exam_sort_number);
            exam_sort_title = (TextView) itemView.findViewById(R.id.exam_sort_title);
            exam_sort_sort = (ImageView) itemView.findViewById(R.id.exam_sort_sort);
        }
    }
}
