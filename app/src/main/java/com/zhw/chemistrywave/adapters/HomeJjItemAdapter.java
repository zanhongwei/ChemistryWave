package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.CanYuJingJiaActivity;
import com.zhw.chemistrywave.bean.SdcgAllOrder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/11/20.
 */

public class HomeJjItemAdapter extends BaseAdapter {
    private List<SdcgAllOrder.DataBean.ListBean.CargoBeansBean> strings;
    private Context context;
    private String sponsor_id;
    private String spon_serl;


    public String getSpon_serl() {
        return spon_serl;
    }

    public void setSpon_serl(String spon_serl) {
        this.spon_serl = spon_serl;
    }

    public String getSponsor_id() {
        return sponsor_id;
    }

    public void setSponsor_id(String sponsor_id) {
        this.sponsor_id = sponsor_id;
    }

    public HomeJjItemAdapter(Context context, List<SdcgAllOrder.DataBean.ListBean.CargoBeansBean> strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        if (strings != null) {

            return strings.size();
        }
        return 0;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.hjjitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvLvlvitemGojing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, CanYuJingJiaActivity.class).putExtra("seller_id", sponsor_id + "")
                        .putExtra("cargo_id", strings.get(position).getCargo_id() + "").putExtra("spon_serl", spon_serl + ""));
            }
        });
        Log.e("aaa", "----strings.size()+strings.toString()----->" + strings.size() + strings.toString());
        if (!TextUtils.isEmpty(strings.get(position).getCargo_name())) {

            viewHolder.tvLvlvitemName.setText(strings.get(position).getCargo_name());
        } else {
            viewHolder.tvLvlvitemName.setText("");
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_lvlvitem_name)
        TextView tvLvlvitemName;
        @BindView(R.id.tv_lvlvitem_gojing)
        TextView tvLvlvitemGojing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
