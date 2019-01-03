package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.NotificationMsg;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class UserMsgLvLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<NotificationMsg.DataBean.ListBean> mList;

    public UserMsgLvLvAdapter(Context mContext, List<NotificationMsg.DataBean.ListBean> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvItemfragmentMsgcontent.setText(mList.get(position).getContent());
        if (!TextUtils.isEmpty(mList.get(position).getCreate_time())) {
            holder.tvItemfragmentMsgdate.setText(mList.get(position).getCreate_time());
        } else {
            holder.tvItemfragmentMsgdate.setText("");
        }
        if (!TextUtils.isEmpty(mList.get(position).getMsg_type())) {
            holder.tvItemfragmentMsgtype.setText(mList.get(position).getCreate_time());
        } else {
            holder.tvItemfragmentMsgtype.setText("");
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_itemfragment_msgtype)
        TextView tvItemfragmentMsgtype;
        @BindView(R.id.tv_itemfragment_msgcontent)
        TextView tvItemfragmentMsgcontent;
        @BindView(R.id.tv_itemfragment_msgdate)
        TextView tvItemfragmentMsgdate;
        @BindView(R.id.tv_itemfragment_msgdetail)
        TextView tvItemfragmentMsgdetail;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
