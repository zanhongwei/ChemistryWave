package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.AddressManagerActivity;
import com.zhw.chemistrywave.bean.AddressDetailBean;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by zhw on 2017/8/10.
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<AddressDetailBean.DataBean.ListBean> mList;


    public AddressAdapter(Context context, List<AddressDetailBean.DataBean.ListBean> mList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // 页面
        final ViewHolder holder;

        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.address_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AddressDetailBean.DataBean.ListBean addressBean = mList.get(position);
        holder.tvItemaddressUsername.setText(addressBean.getConsignee().equals("null") ? "" : addressBean.getConsignee());
        holder.tvItemaddressUserphone.setText(addressBean.getMobile());
        holder.tvItemaddressAddress.setText((addressBean.getProvince().equals("null") ? "" : addressBean.getProvince()) + (addressBean.getCity().equals("null") ? "" : addressBean.getCity()) + (addressBean.getArea().equals("null") ? "" : addressBean.getArea()) + (addressBean.getDetail().equals("null") ? "" : addressBean.getDetail()));
        if (addressBean.isIs_default()) {
            holder.rbAddress.setChecked(true);
        } else {
            holder.rbAddress.setChecked(false);
        }
        holder.rbAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post()
                        .url(NetConfig.defaultaddress)
                        .addParams("addr_id", addressBean.getAddr_id() + "")
                        .addParams("is_default", "1")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa",
                                        "(AddressAdapter.java:112)" + response);
                                if (response.contains("success")) {
                                    for (AddressDetailBean.DataBean.ListBean addressbean : mList) {
                                        addressbean.setIs_default(false);
                                    }
                                    mList.get(position).setIs_default(true);
                                    notifyDataSetChanged();
                                }
                            }
                        });
            }
        });
        holder.tvItemaddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.post()
                        .url(NetConfig.deleteaddress)
                        .addParams("addr_id", addressBean.getAddr_id() + "")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if (response.contains("success")) {
                                    mList.remove(position);
                                    notifyDataSetChanged();
                                }

                            }
                        });
            }
        });
        holder.tvItemaddressEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(context, AddressManagerActivity.class);
                ii.putExtra("addr_id", addressBean.getAddr_id() + "");
                ii.putExtra("consignee", addressBean.getConsignee() + "");
                ii.putExtra("province", addressBean.getProvince() + "");
                ii.putExtra("city", addressBean.getCity() + "");
                ii.putExtra("area", addressBean.getArea() + "");
                ii.putExtra("detail", addressBean.getDetail() + "");
                ii.putExtra("mobile", addressBean.getMobile() + "");
                ii.putExtra("landline", addressBean.getLandline() + "");
                ii.putExtra("is_default", addressBean.isIs_default() + "");
                context.startActivity(ii);
            }
        });

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_itemaddress_username)
        TextView tvItemaddressUsername;
        @BindView(R.id.tv_itemaddress_userphone)
        TextView tvItemaddressUserphone;
        @BindView(R.id.tv_itemaddress_address)
        TextView tvItemaddressAddress;
        @BindView(R.id.rb_address)
        RadioButton rbAddress;
        @BindView(R.id.tv_itemaddress_edit)
        TextView tvItemaddressEdit;
        @BindView(R.id.tv_itemaddress_delete)
        TextView tvItemaddressDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
