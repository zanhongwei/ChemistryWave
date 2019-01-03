package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.HotSaleProduct;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by axehome on 2017/11/17.
 */

public class HomeXhgyGvAdapter extends BaseAdapter {
    private Context mContext;
    private List<HotSaleProduct.DataBean.ListBean> mList;

    public HomeXhgyGvAdapter(Context mContext, List<HotSaleProduct.DataBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_homexhgy, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemhomexhgyGoodsname.setText(mList.get(position).getGoods_name() != null ? mList.get(position).getGoods_name() : "");
        holder.tvItemhomexhgyGuige.setText(mList.get(position).getSpecification() != null ? "" + mList.get(position).getSpecification() : "");
        holder.tvItemhomexhgyChundu.setText(mList.get(position).getGoods_purity() != null ? "" + mList.get(position).getGoods_purity() : "");
        holder.tvItemhomexhgyScj.setText(mList.get(position).getCurrent_price() != null ? "¥" + mList.get(position).getCurrent_price() : "");
        String picture = (String) mList.get(position).getProduct_picture();
        if (TextUtils.isEmpty(picture)) {
            holder.ivItemhomexhgyLogo.setImageResource(R.drawable.goodsone);
        } else {
            Glide.with(mContext).load(NetConfig.baseurl + picture).into(holder.ivItemhomexhgyLogo);
        }

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_itemhomexhgy_logo)
        ImageView ivItemhomexhgyLogo;
        @BindView(R.id.tv_itemhomexhgy_goodsname)
        TextView tvItemhomexhgyGoodsname;
        @BindView(R.id.tv_itemhomexhgy_guige)
        TextView tvItemhomexhgyGuige;
        @BindView(R.id.tv_itemhomexhgy_chundu)
        TextView tvItemhomexhgyChundu;
        @BindView(R.id.tv_itemhomexhgy_scj)
        TextView tvItemhomexhgyScj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
