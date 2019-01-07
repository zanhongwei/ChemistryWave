package com.zhw.chemistrywave.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.Order;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by axehome on 2017/11/17.
 */

public class GonghuoOrderfLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<Order.DataBean.ListBean> mList;

    public GonghuoOrderfLvAdapter(Context mContext, List<Order.DataBean.ListBean> mList) {
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
        String state = mList.get(position).getStatus();
        if (!TextUtils.isEmpty(state)) {
            switch (state) {
                case "2":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Pending delivery");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setText("Cancel order");
                    holder.tvItemorderfTwo.setTextColor(mContext.getResources().getColor(R.color.a58));
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.shengqingshouhou_null));
                    holder.tvItemorderfThree.setText("deliver goods");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.tongyifahuo_grey));
                    holder.tvItemorderfThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            mContext.startActivity(new Intent(mContext, TianxiewuliuxinxiActivity.class));
                        }
                    });
                    break;
                case "3":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Pending received");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setVisibility(View.GONE);

                    holder.tvItemorderfThree.setText("Logistics information");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    final ViewHolder finalHolder1 = holder;
                    holder.tvItemorderfThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopUpWindow(finalHolder1.tvItemorderfThree,mList.get(position).getTransport_corporation(),mList.get(position).getTransport_number());
                        }
                    });
                    break;
                case "4":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("Complete");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setVisibility(View.GONE);

                    holder.tvItemorderfThree.setText("Logistics information");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    final ViewHolder finalHolder = holder;
                    holder.tvItemorderfThree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopUpWindow(finalHolder.tvItemorderfThree,mList.get(position).getTransport_corporation(),mList.get(position).getTransport_number());
                        }
                    });
                    break;
                case "5":
                    holder.tvItemorderfPrice.setText(mList.get(position).getGoods_price() != null ? "仅退款  退款金额：¥" + mList.get(position).getGoods_price() : "");
                    holder.tvItemorderfOrderstate.setText("退款中");
                    holder.tvItemorderfOne.setVisibility(View.GONE);
                    holder.tvItemorderfTwo.setText("取消订单");
                    holder.tvItemorderfTwo.setTextColor(mContext.getResources().getColor(R.color.a58));
                    holder.tvItemorderfTwo.setBackground(mContext.getResources().getDrawable(R.drawable.shengqingshouhou_null));
                    holder.tvItemorderfThree.setText("同意退款");
                    holder.tvItemorderfThree.setBackground(mContext.getResources().getDrawable(R.drawable.chakanwuliu));
                    break;

            }
        }
        //三方检测
        holder.llItemorderfSanfangjiance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mContext, ThreeDetectionActivity.class);
//                mContext.startActivity(intent);
            }
        });
        //免费监理
        holder.llItemorderfMianfeijianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(mContext, FreeSuperVisorActivity.class);
//                mContext.startActivity(intent);
            }
        });
        String picture = mList.get(position).getProduct_picture();
        if (!TextUtils.isEmpty(picture)) {
            Glide.with(mContext).load(NetConfig.baseurl + picture).into(holder.ivItemorderfLogo);
        } else {
            holder.ivItemorderfLogo.setImageResource(R.drawable.goodsone);
        }
        holder.tvItemorderfGoodsname.setText(mList.get(position).getGoods_name() != null ? mList.get(position).getGoods_name() : "");
        holder.tvItemorderfOrderid.setText(mList.get(position).getOrder_id() != null ? mList.get(position).getOrder_id() : "");
        holder.tvItemorderfDate.setText(mList.get(position).getPlace_time() != null ? mList.get(position).getPlace_time() : "");

        return convertView;
    }


    private void showPopUpWindow(TextView tvItemorderfThree, String gs, String dh) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_pop_wuliu, null);
        final PopupWindow popWnd = new PopupWindow(mContext);

        popWnd.setContentView(contentView);
//    popWnd.setWidth(263);
//    popWnd.setHeight(320);
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View contentView1 = popWnd.getContentView();
        TextView mTvClose = (TextView) contentView1.findViewById(R.id.tv_itempopwuliu_close);
        TextView mTvGs = (TextView) contentView1.findViewById(R.id.tv_itempopwuliu_gs);
        TextView mTvDanHao = (TextView) contentView1.findViewById(R.id.tv_itempopwuliu_danhao);

        mTvGs.setText(gs);
        mTvDanHao.setText(dh);
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWnd.dismiss();
            }
        });

        popWnd.setTouchable(true);
        popWnd.setFocusable(true);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
//        backgroundAlpha(0.6f);
        EventBus.getDefault().post("背景");
        //添加pop窗口关闭事件
        popWnd.setOnDismissListener(new poponDismissListener());

        popWnd.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popWnd.dismiss();
                    return true;
                }
                return false;
            }
        });

        //popWnd.showAsDropDown(mTvLine, 200, 0);
        popWnd.showAtLocation(tvItemorderfThree,
                Gravity.CENTER, 0, 0);

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

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
//            backgroundAlpha(1f);
            EventBus.getDefault().post("背景1");
        }

    }
}
