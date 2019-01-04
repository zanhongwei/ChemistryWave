package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.ThreeDection;
import com.zhw.chemistrywave.bean.ThreeDectionS;
import com.zhw.chemistrywave.utils.NetConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/11/25.
 */

public class ThreeDetectionAdapter extends BaseAdapter {


    private Context context;
    private List<ThreeDectionS.DataBean> mList;


    public ThreeDetectionAdapter(Context context, List<ThreeDectionS.DataBean> mList) {
        this.context = context;
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
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_detection_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(NetConfig.baseurl + mList.get(position).getSuper_url()).apply(MyApplication.options).into(holder.ivDetection);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_detection)
        ImageView ivDetection;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
