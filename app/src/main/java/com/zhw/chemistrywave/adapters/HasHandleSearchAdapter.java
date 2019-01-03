package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.HasHandleSearchInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12.
 */

public class HasHandleSearchAdapter extends BaseAdapter {

    private int size = 0;

    private List<HasHandleSearchInfo> mList;
    private Context context;

    public HasHandleSearchAdapter(List<HasHandleSearchInfo> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {

        if (mList != null) {
            size = mList.size();
        }
        return size;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null) {
            view = View.inflate(context, R.layout.item_has_handle_search_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_company_address)
        TextView tvCompanyAddress;
        @BindView(R.id.tv_telephone)
        TextView tvTelephone;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_mail)
        TextView tvMail;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_apply_time)
        TextView tvApplyTime;
        @BindView(R.id.tv_handle_state)
        TextView tvHandleState;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
