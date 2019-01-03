package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.JingJiaBean;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/20.
 */

public class HomeJingJiaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JingJiaBean> mlist;

    public HomeJingJiaAdapter(Context context, ArrayList<JingJiaBean> jingjiaList) {
        this.context = context;
        this.mlist = jingjiaList;
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.homelv_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_jjitem_ordnum)
        TextView tvJjitemOrdnum;
        @BindView(R.id.lv_jignjiaitem)
        ListView lvJignjiaitem;
        @BindView(R.id.tv_jjitem_checkdetail)
        TextView tvJjitemCheckdetail;
        @BindView(R.id.tv_jjitem_onkeyjing)
        TextView tvJjitemOnkeyjing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
