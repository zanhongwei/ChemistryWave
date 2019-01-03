package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.ShopCar;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class QuerendingdanmLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShopCar.DataBean.ListBean.CartBeansBean> mList;

    public QuerendingdanmLvAdapter(Context mContext, List<ShopCar.DataBean.ListBean.CartBeansBean> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mlv_vertifyorder, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvProductName.setText(mList.get(position).getGoods_name() != null ? mList.get(position).getGoods_name() : "");

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_product_img)
        ImageView ivProductImg;
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_product_params)
        TextView tvProductParams;
        @BindView(R.id.tv_product_unit_price)
        TextView tvProductUnitPrice;
        @BindView(R.id.tv_product_unit_num)
        TextView tvProductUnitNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
