package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.EditGoodsActivity;
import com.zhw.chemistrywave.bean.GoodsInfoBean;
import com.zhw.chemistrywave.event.ShangjiaEvent;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by axehome on 2017/11/17.
 */

public class MyGoodsLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsInfoBean.DataBean.ListBean> mList;
    private String tag = "up";

    public MyGoodsLvAdapter(Context mContext, List<GoodsInfoBean.DataBean.ListBean> mList) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_mygoods, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
            AutoUtils.autoSize(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String picture = mList.get(position).getProduct_picture();
        if (TextUtils.isEmpty(picture)) {
            holder.ivItemmygoodsLogo.setImageResource(R.drawable.goodsone);
        } else {
            Glide.with(mContext).load(NetConfig.baseurl.concat(picture)).apply(MyApplication.options).into(holder.ivItemmygoodsLogo);
        }
        holder.tvItemmygoodsGoodsname.setText(mList.get(position).getGoods_name_en() != null ? mList.get(position).getGoods_name_en() : "");
        holder.tvItemmygoodsNongdu.setText(mList.get(position).getGoods_purity() != null ? "purity：" + mList.get(position).getGoods_purity() : "");
        holder.tvItemmygoodsKucun.setText(mList.get(position).getGoods_num() != null ? "stock：" + mList.get(position).getGoods_num() : "");
//        String state = mList.get(position).getGoods_state();
        if (!TextUtils.isEmpty(tag)) {
            switch (tag) {
                case "up":
                    holder.tvItemmygoodsOne.setText("Edit");
                    holder.tvItemmygoodsTwo.setText("Sold out");
                    holder.tvItemmygoodsOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("aaa",
                                    "(MyGoodsLvAdapter.java:102)<---->"+mList.get(position).getGoods_name());

                            mContext.startActivity(new Intent(mContext, EditGoodsActivity.class)
                                    .putExtra("mer_id", String.valueOf(mList.get(position).getGoods_id()))
                                    .putExtra("name", mList.get(position).getGoods_name()));
                        }
                    });
                    holder.tvItemmygoodsTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OkHttpUtils.post().url(NetConfig.mygoodsxiajia_url)
                                    .addParams("goods_id", String.valueOf(mList.get(position).getGoods_id()))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("aaa", "--下架返回-error->" + e.toString());
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e("aaa", "--下架返回-->" + response);
                                            //--下架返回-->{"msg":"success","code":0}
                                            if (!TextUtils.isEmpty(response)) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    int code = jsonObject.getInt("code");
                                                    if (code == 0) {
                                                        mList.remove(position);
                                                        notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
                        }
                    });
                    break;
                case "down":
                    holder.tvItemmygoodsOne.setText("Put away");
                    holder.tvItemmygoodsTwo.setText("Delete");
                    holder.tvItemmygoodsOne.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OkHttpUtils.post().url(NetConfig.mygoodsshangjia_url)
                                    .addParams("goods_id", String.valueOf(mList.get(position).getGoods_id()))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("aaa", "--上架返回-error->" + e.toString());
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e("aaa", "--上架返回-->" + response);
                                            //--下架返回-->{"msg":"success","code":0}
                                            if (!TextUtils.isEmpty(response)) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    int code = jsonObject.getInt("code");
                                                    if (code == 0) {
                                                        EventBus.getDefault().post(new ShangjiaEvent("上架成功"));
                                                        notifyDataSetChanged();
                                                    }else {
                                                        String msg = jsonObject.getString("msg") != null ? jsonObject.getString("msg") : "network error";
                                                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }else {
                                                Toast.makeText(mContext, "network error", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                    holder.tvItemmygoodsTwo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OkHttpUtils.post().url(NetConfig.mygoodsdelete_url)
                                    .addParams("goods_id", String.valueOf(mList.get(position).getGoods_id()))
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            Log.e("aaa", "--删除返回-error->" + e.toString());
                                            Toast.makeText(mContext, "network error", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Log.e("aaa", "--删除返回-->" + response);
                                            //--下架返回-->{"msg":"success","code":0}
                                            if (!TextUtils.isEmpty(response)) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    int code = jsonObject.getInt("code");
                                                    if (code == 0) {
                                                        mList.remove(position);
                                                        notifyDataSetChanged();
                                                    }
                                                    String msg = jsonObject.getString("msg") != null ? jsonObject.getString("msg") : "network error";
                                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }else {
                                                Toast.makeText(mContext, "network error", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    });
                    break;
            }
        }

        holder.tvItemmygoodsBianhaotwo.setText("No.：" + mList.get(position).getGoods_id() + "" + MyUtils.getNum(111111111, 999999999));
        return convertView;
    }

    public void setTag(String tag){
        this.tag = tag;
        notifyDataSetChanged();
    }



    class ViewHolder {
        @BindView(R.id.tv_itemmygoods_bianhaotwo)
        TextView tvItemmygoodsBianhaotwo;
        @BindView(R.id.tv_itemmygoods_bianhao)
        TextView tvItemmygoodsBianhao;
        @BindView(R.id.iv_itemmygoods_logo)
        ImageView ivItemmygoodsLogo;
        @BindView(R.id.tv_itemmygoods_goodsname)
        TextView tvItemmygoodsGoodsname;
        @BindView(R.id.tv_itemmygoods_nongdu)
        TextView tvItemmygoodsNongdu;
        @BindView(R.id.tv_itemmygoods_kucun)
        TextView tvItemmygoodsKucun;
        @BindView(R.id.tv_itemmygoods_edit)
        TextView tvItemmygoodsEdit;
        @BindView(R.id.tv_itemmygoods_xiajia)
        TextView tvItemmygoodsXiajia;
        @BindView(R.id.tv_itemmygoods_zhekouyouhui)
        TextView tvItemmygoodsZhekouyouhui;
        @BindView(R.id.tv_itemmygoods_xianshimiaosha)
        TextView tvItemmygoodsXianshimiaosha;
        @BindView(R.id.tv_itemmygoods_one)
        TextView tvItemmygoodsOne;
        @BindView(R.id.tv_itemmygoods_two)
        TextView tvItemmygoodsTwo;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
