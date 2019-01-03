package com.zhw.chemistrywave.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.bean.HotSuppliers;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.view.ShapeImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhw on 2018/7/30.
 */

public class HotSupplierAdapter extends BaseAdapter {

    private Context context;
    private List<HotSuppliers.DataBean> mList;

    public HotSupplierAdapter(Context context, List<HotSuppliers.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getCount() {

        if (mList != null) {

            if (mList.size()>2)
                return 3;
            else
                return mList.size();
        }
        return 0;
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
            view = View.inflate(context, R.layout.item_hot_supplier_lv, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Glide.with(context).load(NetConfig.baseurl+mList.get(i).getCom_logo()).apply(MyApplication.options).into(holder.sivCompanyLogo1);
        holder.tvCompanyName1.setText(mList.get(i).getCom_name());
        holder.tvSalesNum1.setText("Total Sales："+mList.get(i).getTotal_volumes()+" piece");
        holder.tvSupplierGrade1.setText(mList.get(i).getRank()+"Grade");

        List<HotSuppliers.DataBean.MerchandiseBeansBean> merchandiseBeans = mList.get(i).getMerchandiseBeans();
        if (merchandiseBeans!=null){
            if (merchandiseBeans.size()>1){
                String product_picture = merchandiseBeans.get(0).getProduct_picture();//商品图片1
                String product_picture1 = merchandiseBeans.get(1).getProduct_picture();//商品图片2
                String goods_name = merchandiseBeans.get(0).getGoods_name();
                String goods_name1 = merchandiseBeans.get(1).getGoods_name();

                Glide.with(context).load(NetConfig.baseurl + product_picture).apply(MyApplication.options).into(holder.ivImag11);
                Glide.with(context).load(NetConfig.baseurl + product_picture1).apply(MyApplication.options).into(holder.ivImag12);
                holder.tvSupplierContent11.setText(goods_name);
                holder.tvSupplierContent12.setText(goods_name1);

            }else if (merchandiseBeans.size()==1){
                String product_picture = merchandiseBeans.get(0).getProduct_picture();//商品图片1
                String goods_name = merchandiseBeans.get(0).getGoods_name();
                Glide.with(context).load(NetConfig.baseurl + product_picture).apply(MyApplication.options).into(holder.ivImag11);
                holder.tvSupplierContent11.setText(goods_name);
            }else {

            }
        }
//
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic11).apply(MyApplication.options).into(ivImag11);
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic12).apply(MyApplication.options).into(ivImag12);
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic21).apply(MyApplication.options).into(ivImag21);
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic22).apply(MyApplication.options).into(ivImag22);
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic31).apply(MyApplication.options).into(ivImag31);
//                                Glide.with(getActivity()).load(NetConfig.baseurl + goodsPic32).apply(MyApplication.options).into(ivImag32);
//
//                                tvSupplierContent11.setText(goodsName11);
//                                tvSupplierContent12.setText(goodsName12);
//                                tvSupplierContent21.setText(goodsName21);
//                                tvSupplierContent22.setText(goodsName22);
//                                tvSupplierContent31.setText(goodsName31);
//                                tvSupplierContent32.setText(goodsName32);
//
//                                String userId1 = data.getJSONObject(0).has("user_id") ? data.getJSONObject(0).getString("user_id") : "";
//                                String userId2 = data.getJSONObject(1).has("user_id") ? data.getJSONObject(1).getString("user_id") : "";
//                                String userId3 = data.getJSONObject(2).has("user_id") ? data.getJSONObject(2).getString("user_id") : "";
//
//                                hotSupplierUserIdList.add(userId1);
//                                hotSupplierUserIdList.add(userId2);
//                                hotSupplierUserIdList.add(userId3);
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.siv_company_logo1)
        ShapeImageView sivCompanyLogo1;
        @BindView(R.id.tv_sales_num1)
        TextView tvSalesNum1;
        @BindView(R.id.tv_supplier_grade1)
        TextView tvSupplierGrade1;
        @BindView(R.id.tv_company_name1)
        TextView tvCompanyName1;
        @BindView(R.id.iv_imag11)
        ImageView ivImag11;
        @BindView(R.id.iv_imag12)
        ImageView ivImag12;
        @BindView(R.id.ll_imgs1)
        LinearLayout llImgs1;
        @BindView(R.id.tv_supplier_content11)
        TextView tvSupplierContent11;
        @BindView(R.id.tv_supplier_content12)
        TextView tvSupplierContent12;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
