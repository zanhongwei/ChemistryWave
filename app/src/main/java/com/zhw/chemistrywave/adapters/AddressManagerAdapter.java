package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.AddressDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/25.
 */

public class AddressManagerAdapter extends BaseAdapter {

    private Context context;
    private List<AddressDetailBean> mList;
    private int size = 0;

    private OnMakeAddressListener listener;

    public AddressManagerAdapter(Context context, List<AddressDetailBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            size = mList.size();
        }
        return 3;
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
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder = null;

        if (view == null) {
            view = View.inflate(context, R.layout.item_address_detail_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final ViewHolder finalHolder = holder;
        holder.tvDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDefault(position);
                finalHolder.ivDefault.setImageResource(R.drawable.dizhimorenxuanze);
            }
        });

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEdit(position);
            }
        });
        holder.tvDelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelect(position);
            }
        });
        return view;
    }


    public interface OnMakeAddressListener {
        void onDelect(int position);

        void onEdit(int position);

        void onDefault(int position);
    }

    public void setOnMakeAddressListener(OnMakeAddressListener listener) {
        this.listener = listener;
    }

    static class ViewHolder {
        @BindView(R.id.iv_default)
        ImageView ivDefault;
        @BindView(R.id.tv_default)
        TextView tvDefault;
        @BindView(R.id.tv_address_detail)
        TextView tvAddressDetail;
        @BindView(R.id.tv_edit)
        TextView tvEdit;
        @BindView(R.id.tv_delect)
        TextView tvDelect;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
