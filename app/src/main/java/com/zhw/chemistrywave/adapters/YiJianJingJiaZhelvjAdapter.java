package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.NewBaoJia;
import com.zhw.chemistrywave.view.CircleImageView;
import com.zhw.chemistrywave.view.MyListView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class YiJianJingJiaZhelvjAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewBaoJia> mList;
    private List<NewBaoJia> mChildList;
    private YiJianJingJiaZhelvlvjAdapter mAdapter;

    public YiJianJingJiaZhelvjAdapter(Context mContext, List<NewBaoJia> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_yijianjingjiazhe, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tvItemlvcyjbzUsernametop.setText(mList.get(position).getName());

        mChildList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            NewBaoJia newBaoJia = new NewBaoJia("4,4'-二正十二烷氧基氧化偶氮苯", "20天");
//            mChildList.add(newBaoJia);
//        }
        mAdapter = new YiJianJingJiaZhelvlvjAdapter(mContext, mChildList);
        holder.mlvItemlvyijianjingjiaz.setAdapter(mAdapter);

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.civ_itemlvcyjbz_headimage)
        CircleImageView civItemlvcyjbzHeadimage;
        @BindView(R.id.tv_itemlvcyjbz_usernametop)
        TextView tvItemlvcyjbzUsernametop;
        @BindView(R.id.tv_itemlvcyjbz_usernamebottom)
        TextView tvItemlvcyjbzUsernamebottom;
        @BindView(R.id.mlv_itemlvyijianjingjiaz)
        MyListView mlvItemlvyijianjingjiaz;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
