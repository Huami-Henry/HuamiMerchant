package com.huami.merchant.activity.task.adapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huami.merchant.R;
import com.huami.merchant.bean.AlreadyService.AlreadyServiceData;
import com.huami.merchant.util.TimeUtil;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by henry on 2018/2/7.
 */
public class AlreadyBuyAdapter extends RecyclerView.Adapter<AlreadyBuyAdapter.AlreadyBuyHolder>{
    private List<AlreadyServiceData> datas;
    public AlreadyBuyAdapter(List<AlreadyServiceData> datas) {
        this.datas = datas;
    }
    @Override
    public AlreadyBuyAdapter.AlreadyBuyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_already_buy, parent, false);
        AlreadyBuyHolder holder = new AlreadyBuyHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(AlreadyBuyAdapter.AlreadyBuyHolder holder, int position) {
        AlreadyServiceData alreadyService = datas.get(position);
        holder.value_name.setText(alreadyService.getName());
        holder.value_desc.setText(alreadyService.getIntro());
        if (alreadyService.getCreate_date() != null) {
            String time = TimeUtil.stampToDate(alreadyService.getCreate_date().getTime());
            holder.value_time.setText(time);
        }
        holder.value_count.setText("x"+alreadyService.getNum());
        holder.value_money.setText(alreadyService.getAmount()+"元");
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }
    public class AlreadyBuyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.value_name)
        TextView value_name;
        @BindView(R.id.value_desc)
        TextView value_desc;
        @BindView(R.id.value_time)
        TextView value_time;
        @BindView(R.id.value_count)
        TextView value_count;
        @BindView(R.id.value_money)
        TextView value_money;
        public AlreadyBuyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
