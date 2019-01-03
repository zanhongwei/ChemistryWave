package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.Kuaixun;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by axehome on 2017/11/17.
 */

public class KuaiXunLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<Kuaixun.DataBean.ListBean> mList;

    public KuaiXunLvAdapter(Context mContext, List<Kuaixun.DataBean.ListBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_kuaixun, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemkuaixunDate.setText(mList.get(position).getCreate_time() != null ? mList.get(position).getCreate_time() : "");
        holder.tvItemkuaixunTitle.setText(mList.get(position).getNews_title() != null ? mList.get(position).getNews_title() : "");
        holder.tvItemkuaixunYuedunum.setText("read : "+mList.get(position).getRead_times() != null ? mList.get(position).getRead_times() : "");
        Glide.with(mContext).load(NetConfig.baseurl+mList.get(position).getImg_src()).apply(MyApplication.options).into(holder.ivItemkuaixunLogo);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.iv_itemkuaixun_logo)
        ImageView ivItemkuaixunLogo;
        @BindView(R.id.tv_itemkuaixun_title)
        TextView tvItemkuaixunTitle;
        @BindView(R.id.tv_itemkuaixun_date)
        TextView tvItemkuaixunDate;
        @BindView(R.id.tv_itemkuaixun_yuedunum)
        TextView tvItemkuaixunYuedunum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
