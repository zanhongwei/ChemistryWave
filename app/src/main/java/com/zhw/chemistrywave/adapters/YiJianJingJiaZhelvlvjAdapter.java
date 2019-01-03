package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.JingBiaoZheDetailActivity;
import com.zhw.chemistrywave.bean.NewBaoJia;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class YiJianJingJiaZhelvlvjAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewBaoJia> mList;

    public YiJianJingJiaZhelvlvjAdapter(Context mContext, List<NewBaoJia> mList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_jingbaioz_bottom, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.tvItemlvcyjbzGoodsname.setText(mList.get(position).getName());
//        holder.tvItemlvcyjbzHuoqi.setText(mList.get(position).getPrice());
        holder.tvItemlvcyjbzChakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, JingBiaoZheDetailActivity.class).putExtra("type", "yijianjingjiazhelvlvadapter"));
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_itemlvcyjbz_goodsname)
        TextView tvItemlvcyjbzGoodsname;
        @BindView(R.id.tv_itemlvcyjbz_jingbiaojia)
        TextView tvItemlvcyjbzJingbiaojia;
        @BindView(R.id.tv_itemlvcyjbz_chundu)
        TextView tvItemlvcyjbzChundu;
        @BindView(R.id.tv_itemlvcyjbz_huoqi)
        TextView tvItemlvcyjbzHuoqi;
        @BindView(R.id.tv_itemlvcyjbz_xuanzeta)
        TextView tvItemlvcyjbzXuanzeta;
        @BindView(R.id.tv_itemlvcyjbz_chakan)
        TextView tvItemlvcyjbzChakan;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
