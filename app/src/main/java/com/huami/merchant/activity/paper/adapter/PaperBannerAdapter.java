package com.huami.merchant.activity.paper.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huami.merchant.R;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.CheckCaseIdListInfo;
import com.huami.merchant.listener.OnRecycleItemClickListener;
import com.huami.merchant.util.AuditUtil;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Henry on 2018/1/16.
 */

public class PaperBannerAdapter extends RecyclerView.Adapter<PaperBannerAdapter.PaperBannerHolder> {
    private List<CheckCaseIdListInfo> checkCaseIdListInfos;
    private OnRecycleItemClickListener listener;
    public PaperBannerAdapter(List<CheckCaseIdListInfo> checkCaseIdListInfos, OnRecycleItemClickListener listener) {
        this.checkCaseIdListInfos = checkCaseIdListInfos;
        this.listener = listener;
    }
    @Override
    public PaperBannerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paper_banner, parent, false);
        PaperBannerHolder holder = new PaperBannerHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(PaperBannerHolder holder, final int position) {
        CheckCaseIdListInfo info = checkCaseIdListInfos.get(position);
        holder.paper_title.setText(AuditUtil.getState(info.getCheckTimes(),info.getState()));
        if (info.isCheck()) {
            holder.paper_title.setBackgroundColor(Color.parseColor("#F3F3F3"));
        } else {
            holder.paper_title.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.paper_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }
    /**
     * 获取审核状态
     * @param state
     * @return
     */
    public String getState(int state){
        switch (state) {
            case 1:
                return "待审";
            case 2:
                return "通过";
            case 3:
                return "不通过";
            default:
                return "错误";
        }
    }
    /**
     * 转化成大写
     * @param number
     * @return
     */
    public String upCase(int number){
        switch (number) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            default:
                return "N";

        }
    }
    @Override
    public int getItemCount() {
        return checkCaseIdListInfos.size();
    }

    public class PaperBannerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.paper_title)
        TextView paper_title;
        public PaperBannerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
