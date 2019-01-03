package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.PriceExpressBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/14.
 */

public class PriceExpressAdapter extends BaseAdapter {

    private List<PriceExpressBean.DataBean.ListBean> mList;
    private Context context;
    private LayoutInflater layoutInflater;
    private int size = 0;


    public PriceExpressAdapter(List<PriceExpressBean.DataBean.ListBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i % mList.size());
    }

    @Override
    public long getItemId(int i) {
        return i % mList.size();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_price_express_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        int mPositon = i % mList.size();
        holder.tvPriceNum.setText("$" + mList.get(mPositon).getGoods_price());
        holder.tvPrivceContent.setText(mList.get(mPositon).getGoods_name());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_price_num)
        TextView tvPriceNum;
        @BindView(R.id.tv_privce_content)
        TextView tvPrivceContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
