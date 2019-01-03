package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.CanYuJingJiaActivity;
import com.zhw.chemistrywave.activity.JingJiaDetailWoFaQiActivity;
import com.zhw.chemistrywave.bean.Cargo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/11/21.
 */

public class JingJiaItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cargo.DataBean.CargoBeansBean> mlist;
    private String sponsor_id;
    private String spon_serl;

    public String getSpon_serl() {
        return spon_serl;
    }

    public void setSpon_serl(String spon_serl) {
        this.spon_serl = spon_serl;
    }

    public JingJiaItemAdapter(Context context, ArrayList<Cargo.DataBean.CargoBeansBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_jingjiaitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Cargo.DataBean.CargoBeansBean cargoBean = mlist.get(position);
        viewHolder.tvGoodsName.setText(cargoBean.getCargo_name() + "");
        viewHolder.tvGoodsCount.setText(cargoBean.getCargo_num() + "");
        viewHolder.tvGoodsChundu.setText(cargoBean.getCargo_purity() + "");
        final String cargo_id = cargoBean.getCargo_id() + "";
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, JingJiaDetailWoFaQiActivity.class).putExtra("type", "canyujingjia").putExtra("cargo_id", cargo_id)
                        .putExtra("spon_serl", spon_serl).putExtra("seller_id", sponsor_id));
            }
        });
        viewHolder.tvCanyujingbiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.setNtype("4");
                Log.e("aaa",
                        "(JingJiaItemAdapter.java:88)" + sponsor_id);
                context.startActivity(new Intent(context, CanYuJingJiaActivity.class).putExtra("seller_id", sponsor_id)
                        .putExtra("cargo_id", cargo_id).putExtra("spon_serl", spon_serl));
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
