package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.MyService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12.
 */

public class MyServiceAdapter extends BaseAdapter {

    private Context context;
    private List<MyService.DataBean.ListBean> mList;
    private int size = 0;

    public MyServiceAdapter(Context context, List<MyService.DataBean.ListBean> mList) {
        this.context = context;
        this.mList = mList;
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
            view = View.inflate(context, R.layout.item_my_service_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvApplyTime.setText(mList.get(i).getCreate_time());
        holder.tvCompanyName.setText(mList.get(i).getCom_name());
        holder.tvCompanyAddress.setText(mList.get(i).getCom_addr());
        holder.tvPhone.setText(mList.get(i).getContact_phone());
        holder.tvTelephone.setText(mList.get(i).getContact_phone());
        holder.tvEmail.setText(mList.get(i).getMailbox());
        holder.tvRemark.setText(mList.get(i).getPostscript());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_apply_time)
        TextView tvApplyTime;
        @BindView(R.id.tv_company_name)
        TextView tvCompanyName;
        @BindView(R.id.tv_company_address)
        TextView tvCompanyAddress;
        @BindView(R.id.tv_telephone)
        TextView tvTelephone;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_email)
        TextView tvEmail;
        @BindView(R.id.tv_remark)
        TextView tvRemark;
        @BindView(R.id.tv_sort)
        TextView tvSort;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
