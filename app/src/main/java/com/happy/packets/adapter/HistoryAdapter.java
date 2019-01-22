package com.happy.packets.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.packets.HappyConstants;
import com.happy.packets.R;
import com.happy.packets.entity.RedPackage;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHodler> {

    private List<RedPackage> redPackages;

    public HistoryAdapter(List<RedPackage> redPackages) {
        this.redPackages = redPackages;
    }


    @Override
    public HistoryViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        HistoryViewHodler holder = new HistoryViewHodler(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_history, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(HistoryViewHodler holder, int position) {
        holder.tv_sender.setText("来自：" + redPackages.get(position).getSender());
        holder.tv_time.setText(redPackages.get(position).getTime());
        holder.tv_money.setText("获得 " + new DecimalFormat("#0.00").format(redPackages.get(position).getMoney()) + " 元");

        switch (redPackages.get(position).getChannel()) {
            case HappyConstants.TAG_WEIXIN:
                holder.iv_weixin.setVisibility(View.VISIBLE);
                holder.iv_dingding.setVisibility(View.GONE);
                holder.iv_qq.setVisibility(View.GONE);
                holder.iv_work.setVisibility(View.GONE);

                break;
            case HappyConstants.TAG_DINGDING:
                holder.iv_weixin.setVisibility(View.GONE);
                holder.iv_dingding.setVisibility(View.VISIBLE);
                holder.iv_qq.setVisibility(View.GONE);
                holder.iv_work.setVisibility(View.GONE);
                break;
            case HappyConstants.TAG_QQ:
                holder.iv_weixin.setVisibility(View.GONE);
                holder.iv_dingding.setVisibility(View.GONE);
                holder.iv_qq.setVisibility(View.VISIBLE);
                holder.iv_work.setVisibility(View.GONE);
                break;
            case HappyConstants.TAG_WORK_WEIXIN:
                holder.iv_weixin.setVisibility(View.GONE);
                holder.iv_dingding.setVisibility(View.GONE);
                holder.iv_qq.setVisibility(View.GONE);
                holder.iv_work.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return redPackages.size();
    }

    class HistoryViewHodler extends RecyclerView.ViewHolder {

        TextView tv_sender;
        TextView tv_time;
        TextView tv_money;
        ImageView iv_weixin;
        ImageView iv_dingding;
        ImageView iv_qq;
        ImageView iv_work;

        public HistoryViewHodler(View view) {
            super(view);
            tv_sender = view.findViewById(R.id.tv_name);
            tv_time = view.findViewById(R.id.tv_time);
            tv_money = view.findViewById(R.id.tv_money);
            iv_weixin = view.findViewById(R.id.iv_weixin);
            iv_dingding = view.findViewById(R.id.iv_dingding);
            iv_qq = view.findViewById(R.id.iv_qq);
            iv_work = view.findViewById(R.id.iv_work);

        }
    }
}
