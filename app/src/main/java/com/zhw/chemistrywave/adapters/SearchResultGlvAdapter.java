package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.GoodsDetailActivity;
import com.zhw.chemistrywave.activity.ShopDetailActivity;
import com.zhw.chemistrywave.bean.SearchGoods;
import com.zhw.chemistrywave.utils.NetConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/17.
 */

public class SearchResultGlvAdapter extends BaseAdapter {

    private Context context;
    private List<SearchGoods.DataBean.ListBean> mList;
    private int size = 0;

    public SearchResultGlvAdapter(Context context, List<SearchGoods.DataBean.ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            size = mList.size();
        }
        return size;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_search_goods_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Glide.with(context).load(NetConfig.baseurl + mList.get(i).getProduct_picture()).apply(MyApplication.options).into(holder.ivGoodsPic);
        holder.tvGoodsName.setText(mList.get(i).getGoods_name_en());
        holder.tvPrice.setText(mList.get(i).getCurrent_price());
        if (mList.get(i).getShopBean() == null) {
            holder.tvShopName.setText("化浪自营商店");
        } else
            holder.tvShopName.setText(mList.get(i).getShopBean().getCom_name());
        holder.tvGotoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = mList.get(i).getUser_id() + "";
                if (mList.get(i).getShopBean() != null)
                    context.startActivity(new Intent(context, ShopDetailActivity.class).putExtra("user_id", userId));
            }
        });

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, GoodsDetailActivity.class)
                        .putExtra("mer_id", mList.get(i).getGoods_id()));
            }
        });

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_goods_pic)
        ImageView ivGoodsPic;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.btn_buy)
        Button btnBuy;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_goto_shop)
        TextView tvGotoShop;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
