package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.SupplierSearchInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12.
 */

public class SupplierSearchAdapter extends BaseAdapter {

    private int size = 0;

    private List<SupplierSearchInfo.DataBean> mList;
    private Context context;

    public SupplierSearchAdapter(List<SupplierSearchInfo.DataBean> mList, Context context) {
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
            view = View.inflate(context, R.layout.item_supplier_search_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvCompanyName.setText(mList.get(i).getCom_name());
        holder.tvProductName.setText(mList.get(i).getGoods_name());
        holder.tvTelephone.setText(mList.get(i).getCom_contacts());
        holder.tvPhone.setText(mList.get(i).getCom_number());
        holder.tvMail.setText(mList.get(i).getCom_mailbox());
        holder.tvRemark.setText(mList.get(i).getPostscript());
        holder.tvApplyTime.setText(mList.get(i).getCreate_time());

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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
