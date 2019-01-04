package com.zhw.chemistrywave.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.OrderCompactActivity;
import com.zhw.chemistrywave.activity.ThreeDetectionActivity;
import com.zhw.chemistrywave.bean.Order;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by axehome on 2017/11/17.
 */

public class OrderfLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<Order.DataBean.ListBean> mList;
    private qvXiaoClick qvXiaoClick;

    public OrderfLvAdapter(Context mContext, List<Order.DataBean.ListBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void setQvXiaoClick(OrderfLvAdapter.qvXiaoClick qvXiaoClick) {
        this.qvXiaoClick = qvXiaoClick;
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

    @SuppressLint("NewApi")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_orderf, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /**
         * 查看合同
         */
        holder.tvItemorderfChakanhetong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, OrderCompactActivity.class));
            }
        });

        //订单状态（1待支付、2待发货、3已收货 、4已完成）
        String state = mList.get(position).getStatus();
        if (!TextUtils.isEmpty(state)) {
            switch (state) {
                case "0":
                    holder.tvItemorderfOne.setText("Canceled");
                    holder.tvItemorderfTwo.setVisibility(View.GONE);
                    holder.tvItemorderfThree.setVisibility(View.GONE);
                    break;
                case "1":
                    holder.tvItemorderfOrderstate.setText("Wait pay");
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "$" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setText("Cancel");
                    final View finalConvertView = convertView;
                    final ViewHolder finalHolder = holder;
                    holder.tvItemorderfTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            qvXiaoClick.onclick(position);
                        }
                    });

                    holder.tvItemorderfTwo.setTextColor(mContext.getResources().getColor(R.color.a58));
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.shengqingshouhou_null));
                    holder.tvItemorderfThree.setText("Pay");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    holder.llItemorderfSanfang.setVisibility(View.INVISIBLE);
                    break;
                case "2":
                    Log.e("aaa",
                            "(OrderfLvAdapter.java:121)<--进入待发货-->");
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Wait deliver");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfThree.setVisibility(View.GONE);
                    holder.tvItemorderfThree.setText("Cancel");
                    holder.tvItemorderfThree.setTextColor(mContext.getResources().getColor(R.color.a58));
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.shengqingshouhou_null));
                    holder.tvItemorderfThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            qvXiaoClick.onclick(position);
                        }
                    });
                    holder.tvItemorderfTwo.setText("Cancel");
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.tongyifahuo_grey));
                    holder.tvItemorderfTwo.setVisibility(View.GONE);
                    break;
                case "3":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Wait receiving");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setText("Affirm goods");
                    holder.tvItemorderfTwo.setTextColor(mContext.getResources().getColor(R.color.white));
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.querenwuliu));
                    holder.tvItemorderfTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("buyer_id", MyUtils.getUser().getUser_id());
                            map.put("order_id", mList.get(position).getOrder_id());
                            map.put("seller_id", String.valueOf(mList.get(position).getSeller_id()));
                            map.put("trade_name", mList.get(position).getGoods_name());
                            map.put("order_type", "1");
//                        map.put("source_id",mList.get(position).getca);
                            OkHttpUtils.post().url(NetConfig.querenshouhuo_url)
                                    .params(map)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("aaa", "----确认收货返回--error-->" + e.getMessage() + e.getCause());
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e("aaa", "----确认收货返回---->" + response);
                                        }
                                    });
                        }
                    });
                    holder.tvItemorderfThree.setText("Logistics information");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    holder.tvItemorderfThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mContext.startActivity(new Intent(mContext, LogisticsInfoActivity.class));
                        }
                    });
                    break;
                case "4":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Completed");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setVisibility(View.GONE);
                    holder.tvItemorderfThree.setText("Logistics information");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    break;
                case "5":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "仅退款  退款金额：¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("退款中");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfThree.setText("取消申请");
                    holder.tvItemorderfThree.setTextColor(mContext.getResources().getColor(R.color.a58));
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.shengqingshouhou_null));
                    holder.tvItemorderfTwo.setText("立即付款");
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    holder.tvItemorderfTwo.setVisibility(View.GONE);
                    break;
                default:

                    break;
            }
        }
        holder.tvItemorderfGoodsname.setText(mList.get(position).getGoods_name());
        holder.tvItemorderfOrderid.setText(mList.get(position).getOrder_id() != null ? mList.get(position).getOrder_id() : "");
        holder.tvItemorderfDate.setText(mList.get(position).getPlace_time() != null ? mList.get(position).getPlace_time() : "");
        String picture = mList.get(position).getProduct_picture();
        if (!TextUtils.isEmpty(picture)) {
            Glide.with(mContext).load(NetConfig.baseurl + picture).into(holder.ivItemorderfLogo);
        } else {
            holder.ivItemorderfLogo.setImageResource(R.drawable.goodsone);
        }
        //三方检测
        holder.llItemorderfSanfangjiance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ThreeDetectionActivity.class)
                        .putExtra("title","Test report")
                        .putExtra("order_id",mList.get(position).getOrder_id());
                mContext.startActivity(intent);
            }
        });
        //免费监理
        holder.llItemorderfMianfeijianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ThreeDetectionActivity.class);
                intent.putExtra("title","Supervisor");
                intent.putExtra("order_id",mList.get(position).getOrder_id());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }


    public interface qvXiaoClick {
        void onclick(int position);
    }

    static class ViewHolder {
        @BindView(R.id.tv_itemorderf_orderid)
        TextView tvItemorderfOrderid;
        @BindView(R.id.tv_itemorderf_date)
        TextView tvItemorderfDate;
        @BindView(R.id.tv_itemorderf_orderstate)
        TextView tvItemorderfOrderstate;
        @BindView(R.id.iv_itemorderf_logo)
        ImageView ivItemorderfLogo;
        @BindView(R.id.tv_itemorderf_goodsname)
        TextView tvItemorderfGoodsname;
        @BindView(R.id.ll_itemorderf_sanfangjiance)
        LinearLayout llItemorderfSanfangjiance;
        @BindView(R.id.ll_itemorderf_mianfeijianli)
        LinearLayout llItemorderfMianfeijianli;
        @BindView(R.id.ll_itemorderf_sanfang)
        LinearLayout llItemorderfSanfang;
        @BindView(R.id.tv_itemorderf_price)
        TextView tvItemorderfPrice;
        @BindView(R.id.tv_itemorderf_chakanhetong)
        TextView tvItemorderfChakanhetong;
        @BindView(R.id.tv_itemorderf_one)
        TextView tvItemorderfOne;
        @BindView(R.id.tv_itemorderf_two)
        TextView tvItemorderfTwo;
        @BindView(R.id.tv_itemorderf_three)
        TextView tvItemorderfThree;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
