package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.NewBaoJia;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.view.CircleImageView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class MianFeiZhaoHuolLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewBaoJia.DataBean> mList;
    private int size = 0;

    public MianFeiZhaoHuolLvAdapter(Context mContext, List<NewBaoJia.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            size = mList.size();
        }
        Log.e("aaa",
                "(MianFeiZhaoHuolLvAdapter.java:39)<---->" + size);
        return size;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_mianfeizhaohuo, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemmianfeizhaohuoUsername.setText("#"+mList.get(position).getCom_name()+"#");//没有明确的用户信息
        Glide.with(mContext).load(NetConfig.baseurl + mList.get(position).getUser_photo()).apply(MyApplication.options).into(holder.civItemmianfeizhaohuoHeadimage);
        holder.tvItemmianfeizhaohuoGoodsname.setText(mList.get(position).getGoods_name());
        holder.tvItemmianfeizhaohuoTime.setText(mList.get(position).getCreate_time().split(" ")[1]);

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.civ_itemmianfeizhaohuo_headimage)
        CircleImageView civItemmianfeizhaohuoHeadimage;
        @BindView(R.id.tv_itemmianfeizhaohuo_username)
        TextView tvItemmianfeizhaohuoUsername;
        @BindView(R.id.tv_itemmianfeizhaohuo_goodsname)
        TextView tvItemmianfeizhaohuoGoodsname;
        @BindView(R.id.tv_itemmianfeizhaohuo_time)
        TextView tvItemmianfeizhaohuoTime;
        @BindView(R.id.tv_itemmianfeizhaohuo_state)
        TextView tvItemmianfeizhaohuoState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
