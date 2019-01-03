package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.ZiYingGoods;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class HomezkzqGvAdapter extends BaseAdapter {
    private Context mContext;
    private List<ZiYingGoods.DataBean.ListBean> mList;

    public HomezkzqGvAdapter(Context mContext, List<ZiYingGoods.DataBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            if (mList.size() > 3) {
                return 3;
            } else {

                return mList.size();
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null && mList.size() > 0) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_zkzq, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (mList != null && mList.size() > 0) {

            holder.tvItemhomezkzqGoodsname.setText(mList.get(position).getCargo_name() != null ? mList.get(position).getCargo_name() : "");
            holder.ivItemlvzkzqGuige.setText(mList.get(position).getCargo_specification() != null ? mList.get(position).getCargo_specification() : "");
            holder.ivItemlvzkzqChundu.setText(mList.get(position).getCargo_purity() != null ? mList.get(position).getCargo_purity() : "");
            holder.tvItemhomezkzqShicj.setText(mList.get(position).getMarket_price() != null ? "¥" + mList.get(position).getMarket_price() : "");
            holder.tvItemhomezkzqShicj.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvItemhomezkzqScj.setText(mList.get(position).getCurrent_price() != null ? "¥" + mList.get(position).getCurrent_price() : "");
            String picture = (String) mList.get(position).getProduct_picture();
            if (TextUtils.isEmpty(picture)) {
                holder.ivItemhomezkzqLogo.setImageResource(R.color.color_f6);
            } else {
                Glide.with(mContext).load(NetConfig.baseurl + picture).apply(MyApplication.options).into(holder.ivItemhomezkzqLogo);
            }

        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_itemhomezkzq_logo)
        ImageView ivItemhomezkzqLogo;
        @BindView(R.id.tv_itemhomezkzq_goodsname)
        TextView tvItemhomezkzqGoodsname;
        @BindView(R.id.iv_itemlvzkzq_guige)
        TextView ivItemlvzkzqGuige;
        @BindView(R.id.iv_itemlvzkzq_chundu)
        TextView ivItemlvzkzqChundu;
        @BindView(R.id.tv_itemhomezkzq_scj)
        TextView tvItemhomezkzqScj;
        @BindView(R.id.tv_itemhomezkzq_shicj)
        TextView tvItemhomezkzqShicj;
        @BindView(R.id.ll_bbb)
        LinearLayout llBbb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
