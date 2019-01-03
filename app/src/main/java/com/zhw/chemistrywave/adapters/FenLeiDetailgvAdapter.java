package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.graphics.Paint;
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

public class FenLeiDetailgvAdapter extends BaseAdapter {
    private Context mContext;
    private List<HotSaleProduct.DataBean.ListBean> mList;

    public FenLeiDetailgvAdapter(Context mContext, List<HotSaleProduct.DataBean.ListBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv_shopdetail, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String picture = (String) mList.get(position).getProduct_picture();
        if (TextUtils.isEmpty(picture)) {
            holder.ivShopdetailLogo.setImageResource(R.drawable.goodsone);
        } else {
            Glide.with(mContext).load(NetConfig.baseurl + picture).into(holder.ivShopdetailLogo);
        }
        holder.ivShopdetailGoodsname.setText(mList.get(position).getGoods_name() != null ? mList.get(position).getGoods_name() : "");
        holder.ivShopdetailGuige.setText(mList.get(position).getSpecification() != null ? "Specification：" + mList.get(position).getSpecification() : "");
        holder.ivShopdetailChundu.setText(mList.get(position).getGoods_purity() != null ? "Purity：" + mList.get(position).getGoods_purity() : "");
        holder.ivShopdetailShichangjia.setText(mList.get(position).getMarket_price() != null ? "Market price：$" + mList.get(position).getMarket_price() : "");
        holder.ivShopdetailShichangjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.ivShopdetailShangchengjia.setText(mList.get(position).getCurrent_price() != null ? "Shop price：$" + mList.get(position).getCurrent_price() : "");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_shopdetail_logo)
        ImageView ivShopdetailLogo;
        @BindView(R.id.iv_shopdetail_goodsname)
        TextView ivShopdetailGoodsname;
        @BindView(R.id.iv_shopdetail_guige)
        TextView ivShopdetailGuige;
        @BindView(R.id.iv_shopdetail_chundu)
        TextView ivShopdetailChundu;
        @BindView(R.id.iv_shopdetail_shichangjia)
        TextView ivShopdetailShichangjia;
        @BindView(R.id.iv_shopdetail_shangchengjia)
        TextView ivShopdetailShangchengjia;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
