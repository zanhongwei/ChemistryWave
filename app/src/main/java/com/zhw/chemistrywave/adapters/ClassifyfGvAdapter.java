package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.GoodsSortsBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class ClassifyfGvAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsSortsBean.DataBean.ListBean> mList;

    public ClassifyfGvAdapter(Context mContext, List<GoodsSortsBean.DataBean.ListBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_classyfyf, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvClassifyfitemName.setText(mList.get(position).getText());

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_classifyfitem_name)
        TextView tvClassifyfitemName;
        @BindView(R.id.ll_classifyfitem_bj)
        LinearLayout llClassifyfitemBj;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
