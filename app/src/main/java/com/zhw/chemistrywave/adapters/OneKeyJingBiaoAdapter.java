package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.Cargo;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/21.
 */

public class OneKeyJingBiaoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Cargo.DataBean.CargoBeansBean> mlist;

    public OneKeyJingBiaoAdapter(Context context, ArrayList<Cargo.DataBean.CargoBeansBean> mlist) {
        this.context = context;
        this.mlist = mlist;
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
            convertView = View.inflate(context, R.layout.item_onekey, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cargo.DataBean.CargoBeansBean cargoBean = mlist.get(position);
        viewHolder.tvName.setText(cargoBean.getCargo_name() + "");
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_edit)
        TextView tvEdit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
