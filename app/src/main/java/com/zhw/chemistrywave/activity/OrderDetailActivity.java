package com.zhw.chemistrywave.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Order;
import com.zhw.chemistrywave.utils.NetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.iv_goods_picture)
    ImageView ivGoodsPicture;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rl_titlebar_search)
    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.iv_orderdetail_state)
    ImageView ivOrderdetailState;
    @BindView(R.id.tv_orderdetail_state)
    TextView tvOrderdetailState;
    @BindView(R.id.tv_orderdetail_one)
    TextView tvOrderdetailOne;
    @BindView(R.id.tv_orderdetail_two)
    TextView tvOrderdetailTwo;
    @BindView(R.id.tv_orderdetail_shouhuoren)
    TextView tvOrderdetailShouhuoren;
    @BindView(R.id.tv_orderdetail_phone)
    TextView tvOrderdetailPhone;
    @BindView(R.id.tv_orderdetail_address)
    TextView tvOrderdetailAddress;
    @BindView(R.id.tv_orderdetail_goodsname)
    TextView tvOrderdetailGoodsname;
    @BindView(R.id.tv_orderdetail_guige)
    TextView tvOrderdetailGuige;
    @BindView(R.id.tv_orderdetail_num)
    TextView tvOrderdetailNum;
    @BindView(R.id.tv_orderdetail_sanfangjiance)
    TextView tvOrderdetailSanfangjiance;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.tv_orderdetail_mianfeijianli)
    TextView tvOrderdetailMianfeijianli;
    @BindView(R.id.tv_orderdetail_yunfei)
    TextView tvOrderdetailYunfei;
    @BindView(R.id.tv_orderdetail_shangpingzongjia)
    TextView tvOrderdetailShangpingzongjia;
    @BindView(R.id.tv_orderdetail_xufukuang)
    TextView tvOrderdetailXufukuang;
    @BindView(R.id.ll_orderdetail_lianximaijia)
    LinearLayout llOrderdetailLianximaijia;
    @BindView(R.id.ll_orderdetail_bodadianhua)
    LinearLayout llOrderdetailBodadianhua;
    @BindView(R.id.tv_orderdetail_orderid)
    TextView tvOrderdetailOrderid;
    @BindView(R.id.tv_orderdetail_copy)
    TextView tvOrderdetailCopy;
    @BindView(R.id.tv_orderdetail_zhifubaojiaoyihao)
    TextView tvOrderdetailZhifubaojiaoyihao;
    @BindView(R.id.tv_orderdetail_chuanjianshijian)
    TextView tvOrderdetailChuanjianshijian;
    @BindView(R.id.tv_orderdetail_btnthree)
    TextView tvOrderdetailBtnthree;
    @BindView(R.id.tv_orderdetail_btnone)
    TextView tvOrderdetailBtnone;
    @BindView(R.id.tv_orderdetail_btntwo)
    TextView tvOrderdetailBtntwo;
    @BindView(R.id.ll_goods_info)
    LinearLayout llGoodsInfo;
    private String mType;
    private Order.DataBean.ListBean goods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    @SuppressLint("NewApi")
    private void initData() {
        tvTitlebarCenter.setText("Order Detail");
        Bundle bundle = getIntent().getExtras();
        goods = (Order.DataBean.ListBean) bundle.getSerializable("goods");
        mType = goods.getStatus();
        if (!TextUtils.isEmpty("mType")) {
            switch (mType) {
                //待付款
                case "1":
                    ivOrderdetailState.setImageResource(R.drawable.bj_daifukuang);
                    tvOrderdetailState.setText("Wait pay");
                    tvOrderdetailOne.setText("The remaining 2 days and 23 hours automatically shut down");
//                    tvOrderdetailTwo.setVisibility(View.GONE);
                    tvOrderdetailBtnone.setText("Cancel");
                    tvOrderdetailBtntwo.setText("Pay");
                    tvOrderdetailBtnthree.setVisibility(View.GONE);
                    break;
                //待发货
                case "2":
                    ivOrderdetailState.setImageResource(R.drawable.bj_orderdetail_top);
                    tvOrderdetailState.setText("Wait receiving");
                    tvOrderdetailOne.setText("The order will be shipped within 24 hours");
                    tvOrderdetailTwo.setText("Please be patient");
                    tvOrderdetailBtnone.setText("Wait receiving");
                    tvOrderdetailBtntwo.setVisibility(View.GONE);
                    tvOrderdetailBtnthree.setVisibility(View.GONE);
                    break;
                //待收货
                case "3":
                    ivOrderdetailState.setImageResource(R.drawable.bj_daishouhuo);
                    tvOrderdetailState.setVisibility(View.GONE);
                    tvOrderdetailOne.setText("Waiting for the buyer to collect the goods");
                    tvOrderdetailOne.setVisibility(View.GONE);
                    tvOrderdetailTwo.setVisibility(View.GONE);
                    tvOrderdetailBtnone.setText("Confirmation of logistics");
                    tvOrderdetailBtnone.setTextColor(getResources().getColor(R.color.white));
                    tvOrderdetailBtnone.setBackground(getResources().getDrawable(R.drawable.tongyifahuo_grey));
                    tvOrderdetailBtntwo.setText("Look at the logistics");
                    tvOrderdetailBtnone.setVisibility(View.GONE);
                    tvOrderdetailBtntwo.setVisibility(View.GONE);
                    tvOrderdetailBtnthree.setText("Waiting delivery");
                    break;
                //已完成
                case "4":
                    ivOrderdetailState.setImageResource(R.drawable.bj_jiaoyichenggong);
                    tvOrderdetailState.setVisibility(View.GONE);
                    tvOrderdetailOne.setText("Complete");
                    tvOrderdetailTwo.setVisibility(View.GONE);
                    tvOrderdetailBtnone.setVisibility(View.GONE);
                    tvOrderdetailBtntwo.setVisibility(View.GONE);
                    tvOrderdetailBtnthree.setText("Completed");
                    break;
                //申请售后
                case "5":
                    ivOrderdetailState.setImageResource(R.drawable.bj_shenqingshouhou);
                    tvOrderdetailState.setVisibility(View.GONE);
                    tvOrderdetailOne.setText("Apply service");
                    tvOrderdetailTwo.setVisibility(View.GONE);
                    tvOrderdetailBtnone.setVisibility(View.GONE);
                    tvOrderdetailBtntwo.setVisibility(View.GONE);
                    tvOrderdetailBtnthree.setText("Completed");
                    break;
            }
        }

        if (goods !=null){
            Glide.with(this).load(NetConfig.baseurl+ goods.getProduct_picture()).apply(MyApplication.options).into(ivGoodsPicture);
            tvOrderdetailGoodsname.setText(goods.getGoods_name());
            tvOrderdetailGuige.setText("“"+ goods.getColor_power()+"”，“"+ goods.getColor_light()+"”，“"+ goods.getPackage_opt()+"”");
            tvOrderdetailNum.setText("X"+ goods.getGoods_num());

            tvOrderdetailShangpingzongjia.setText("$"+ goods.getGoods_price());
            tvOrderdetailXufukuang.setText("$"+ goods.getTotal_money());

            tvOrderdetailOrderid.setText(goods.getOrder_id());
            tvOrderdetailChuanjianshijian.setText(goods.getPlace_time());
            tvOrderdetailShouhuoren.setText(goods.getConsignee());
        }


    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_orderdetail_lianximaijia, R.id.ll_orderdetail_bodadianhua, R.id.tv_orderdetail_copy, R.id.tv_orderdetail_btnone, R.id.tv_orderdetail_btntwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //联系卖家
            case R.id.ll_orderdetail_lianximaijia:
                break;
            //拨打电话
            case R.id.ll_orderdetail_bodadianhua:
                break;
            //复制
            case R.id.tv_orderdetail_copy:
                break;
            //底部按钮1
            case R.id.tv_orderdetail_btnone:
                break;
            //底部按钮2
            case R.id.tv_orderdetail_btntwo:
                break;
            case R.id.ll_goods_info:
                startActivity(new Intent(this,GoodsDetailActivity.class).putExtra("mer_id",goods.getGoods_id()));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
