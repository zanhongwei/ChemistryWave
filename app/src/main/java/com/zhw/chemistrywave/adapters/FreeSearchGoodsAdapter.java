package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.XTXPOrder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/27.
 */

public class FreeSearchGoodsAdapter extends BaseAdapter {

    private Context context;
    private List<XTXPOrder.DataBean.ListBean> mList;
    private int size = 0;

    public FreeSearchGoodsAdapter(Context context, List<XTXPOrder.DataBean.ListBean> mList) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_free_search_goods_lv, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String state = mList.get(position).getGoods_state();
        if (!TextUtils.isEmpty(state)) {
            switch (state) {
                case "wait_audit":
                    holder.tvState.setText("Pending");
                    break;
                case "up":
                    holder.tvState.setText("Processed");
                    break;
            }
        }
        holder.tvTime.setText(mList.get(position).getCreate_time() != null ? mList.get(position).getCreate_time() : "");
        holder.tvDetectionId.setText((mList.get(position).getGoods_id() != 0 ? mList.get(position).getGoods_id() : 0)+"");
        holder.tvDetection.setText(mList.get(position).getGoods_name() != null ? mList.get(position).getGoods_name() : "");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_detection_id)
        TextView tvDetectionId;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_detection)
        TextView tvDetection;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setList(List<XTXPOrder.DataBean.ListBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
}
