package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.SySMsg;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class SysMsgLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<SySMsg.DataBean.ListBean> mList;

    public SysMsgLvAdapter(Context mContext, List<SySMsg.DataBean.ListBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_sysmsg, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemsysmsgContent.setText(mList.get(position).getMsg_content());
        String time = mList.get(position).getMsg_time();
        if (TextUtils.isEmpty(time)) {
            holder.tvItemsysmsgDate.setText("");
        } else {
            holder.tvItemsysmsgDate.setText(time);
        }
        if (!TextUtils.isEmpty(mList.get(position).getMsg_publisher())) {
            holder.tvItemsysmsgTitle.setText(mList.get(position).getMsg_publisher());
        } else {
            holder.tvItemsysmsgTitle.setText("");
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_itemsysmsg_title)
        TextView tvItemsysmsgTitle;
        @BindView(R.id.tv_itemsysmsg_content)
        TextView tvItemsysmsgContent;
        @BindView(R.id.tv_itemsysmsg_date)
        TextView tvItemsysmsgDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
