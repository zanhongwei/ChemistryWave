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
import com.zhw.chemistrywave.bean.Cargo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by axehome on 2017/11/17.
 */

public class DetailLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<Cargo.DataBean.CargoBeansBean> mList;
    private String sponsor_id;
    private String spon_serl;

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public String getSpon_serl() {
        return spon_serl;
    }

    public void setSpon_serl(String spon_serl) {
        this.spon_serl = spon_serl;
    }

    public DetailLvAdapter(Context mContext, List<Cargo.DataBean.CargoBeansBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_jingjiaitem, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvCanyujingbiao.setVisibility(View.GONE);
        final Cargo.DataBean.CargoBeansBean cargoBean = mList.get(position);
        holder.tvGoodsName.setText(cargoBean.getCargo_name() + "");
        holder.tvGoodsCount.setText(cargoBean.getCargo_num() + "");
        holder.tvGoodsChundu.setText(cargoBean.getCargo_purity() + "");
        final String cargo_id = cargoBean.getCargo_id() + "";
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, JingJiaDetailWoFaQiActivity.class).putExtra("type", "faqi").putExtra("cargo_id", cargo_id)
                        .putExtra("spon_serl", spon_serl).putExtra("seller_id", sponsor_id));
            }
        });
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
