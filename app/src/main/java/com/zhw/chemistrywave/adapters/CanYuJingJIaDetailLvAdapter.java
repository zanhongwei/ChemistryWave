package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.JingJiaDetailWoFaQiActivity;
import com.zhw.chemistrywave.bean.CanYuJingJiaBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class CanYuJingJIaDetailLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<CanYuJingJiaBean.DataBean.ListBean.ParticipationBeansBean> mlist;

    public CanYuJingJIaDetailLvAdapter(Context mContext, ArrayList<CanYuJingJiaBean.DataBean.ListBean.ParticipationBeansBean> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @Override
    public int getCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mlist != null && mlist.size() > 0) {
            return mlist.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_jingjiaitem, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, JingJiaDetailWoFaQiActivity.class).putExtra("cargo_id", mlist.get(position).getCargo_id() + "")
                        .putExtra("type", "canyu"));
            }
        });
        holder.tvGoodsName.setText(mlist.get(position).getCargo_name() + "");
        holder.tvGoodsCount.setText(mlist.get(position).getCargo_num() + "");
        holder.tvGoodsChundu.setText(mlist.get(position).getCargo_purity() + "");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_goods)
        ImageView ivGoods;
        @BindView(R.id.tv_goods_state)
        TextView tvGoodsState;
        @BindView(R.id.tv_canyujingbiao)
        TextView tvCanyujingbiao;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_goods_count)
        TextView tvGoodsCount;
        @BindView(R.id.tv_goods_chundu)
        TextView tvGoodsChundu;
        @BindView(R.id.tv_goods_renshu)
        TextView tvGoodsRenshu;
        @BindView(R.id.ll)
        LinearLayout ll;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
