package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.SearchSupplier;
import com.zhw.chemistrywave.utils.NetConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/17.
 */

public class SearchResultSlvAdapter extends BaseAdapter {


    private Context context;
    private List<SearchSupplier.DataBean> mList;
    private int size = 0;

    public SearchResultSlvAdapter(Context context, List<SearchSupplier.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {

        if (mList!=null){
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
            view = View.inflate(context, R.layout.item_search_supplier_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Glide.with(context).load(NetConfig.baseurl+mList.get(i).getCom_logo()).apply(MyApplication.options).into(holder.civShopLogo);
        holder.tvShopName.setText(mList.get(i).getCom_name());
        holder.tvShopGrade.setText("0 Grade");//等级暂时接口未返回
        holder.tvSaleNum.setText("Total sales："+(mList.get(i).getTotal_volumes()==null?"0":mList.get(i).getTotal_volumes()));
        if (null!=mList.get(i).getMerchandiseBeans()){
            List<SearchSupplier.DataBean.MerchandiseBeansBean> merchandiseBeans = mList.get(i).getMerchandiseBeans();
            switch (merchandiseBeans.size()){
                case 0:
                    break;
                case 1:
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(0).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg1);
                    break;
                case 2:
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(0).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg1);
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(1).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg2);
                    break;
                default:
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(0).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg1);
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(1).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg2);
                    Glide.with(context).load(NetConfig.baseurl+merchandiseBeans.get(2).getProduct_picture()).apply(MyApplication.options).into(holder.ivShopImg3);
                    break;
            }
        }


        return view;
    }

    static class ViewHolder {
        @BindView(R.id.civ_shop_logo)
        CircleImageView civShopLogo;
        @BindView(R.id.tv_shop_name)
        TextView tvShopName;
        @BindView(R.id.tv_shop_grade)
        TextView tvShopGrade;
        @BindView(R.id.tv_sale_num)
        TextView tvSaleNum;
        @BindView(R.id.tv_goto_shop)
        TextView tvGotoShop;
        @BindView(R.id.iv_shop_img1)
        ImageView ivShopImg1;
        @BindView(R.id.iv_shop_img2)
        ImageView ivShopImg2;
        @BindView(R.id.iv_shop_img3)
        ImageView ivShopImg3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
