package com.zhw.chemistrywave.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.XTXPOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreeSearchGoodsDetailActivity extends BaseActivity {


    @BindView(R.id.iv_freedetection_back)
    ImageView ivFreedetectionBack;
    @BindView(R.id.tv_freedetection_title)
    TextView tvFreedetectionTitle;
    @BindView(R.id.tv_freesearchgoodsdetail_one)
    TextView tvFreesearchgoodsdetailOne;
    @BindView(R.id.tv_freesearchgoodsdetail_two)
    TextView tvFreesearchgoodsdetailTwo;
    @BindView(R.id.tv_freesearchgoodsdetail_bianhao)
    TextView tvFreesearchgoodsdetailBianhao;
    @BindView(R.id.tv_freesearchgoodsdetail_date)
    TextView tvFreesearchgoodsdetailDate;
    @BindView(R.id.tv_freesearchgoodsdetail_lainxikefu)
    TextView tvFreesearchgoodsdetailLainxikefu;
    @BindView(R.id.tv_freesearchgoodsdetail_goodsname)
    TextView tvFreesearchgoodsdetailGoodsname;
    @BindView(R.id.tv_freesearchgoodsdetail_chundu)
    TextView tvFreesearchgoodsdetailChundu;
    @BindView(R.id.tv_freesearchgoodsdetail_huoqi)
    TextView tvFreesearchgoodsdetailHuoqi;
    @BindView(R.id.tv_freesearchgoodsdetail_shuliang)
    TextView tvFreesearchgoodsdetailShuliang;
    @BindView(R.id.tv_freesearchgoodsdetail_baozhuang)
    TextView tvFreesearchgoodsdetailBaozhuang;
    @BindView(R.id.tv_freesearchgoodsdetail_yunshu)
    TextView tvFreesearchgoodsdetailYunshu;
    @BindView(R.id.tv_freesearchgoodsdetail_fendingjiage)
    TextView tvFreesearchgoodsdetailFendingjiage;
    @BindView(R.id.tv_freesearchgoodsdetail_payway)
    TextView tvFreesearchgoodsdetailPayway;
    @BindView(R.id.tv_freesearchgoodsdetail_other)
    TextView tvFreesearchgoodsdetailOther;
    @BindView(R.id.tv_freesearchgoodsdetail_chakandetail)
    TextView tvFreesearchgoodsdetailChakandetail;
    private String mState;
    private int goods_id;
    private XTXPOrder.DataBean.ListBean list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_search_goods_detail);
        ButterKnife.bind(this);
        list = (XTXPOrder.DataBean.ListBean) getIntent().getSerializableExtra("list");
        mState = list.getGoods_state();
        goods_id = list.getGoods_id();

        initView();
        initData();
    }

    private void initView() {
        tvFreesearchgoodsdetailBianhao.setText("Number：" + list.getGoods_id());
        tvFreesearchgoodsdetailDate.setText("generated time：" + list.getCreate_time());
        tvFreesearchgoodsdetailGoodsname.setText(list.getGoods_name());
        tvFreesearchgoodsdetailChundu.setText(list.getGoods_purity());
        tvFreesearchgoodsdetailHuoqi.setText(list.getGoods_deliver());
        tvFreesearchgoodsdetailShuliang.setText(list.getGoods_num());
        tvFreesearchgoodsdetailBaozhuang.setText(list.getPackage_opt());
        tvFreesearchgoodsdetailYunshu.setText(list.getTransport_opt());
        tvFreesearchgoodsdetailFendingjiage.setText(list.getCurrent_price());
        tvFreesearchgoodsdetailPayway.setText(list.getPayment_opt());
        tvFreesearchgoodsdetailOther.setText("");
    }

    /**
     * 初始化数据源
     */
    @SuppressLint("NewApi")
    private void initData() {
        tvFreedetectionTitle.setText("Platform search result");
        switch (mState) {
            case "1":
                tvFreesearchgoodsdetailOne.setText("To accept");
                tvFreesearchgoodsdetailTwo.setText("The product is waiting in line for acceptance...");
                break;
            case "2":
                tvFreesearchgoodsdetailOne.setText(" looking for the goods");
                tvFreesearchgoodsdetailTwo.setText("The product is trying to find the goods...");
                break;

        }
    }

    @OnClick({R.id.iv_freedetection_back, R.id.tv_freesearchgoodsdetail_one, R.id.tv_freesearchgoodsdetail_lainxikefu, R.id.tv_freesearchgoodsdetail_chakandetail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_freedetection_back:
                onBackPressed();
                break;
            case R.id.tv_freesearchgoodsdetail_one:
                startActivity(new Intent(FreeSearchGoodsDetailActivity.this, GoodsDetailActivity.class).putExtra("mer_id", goods_id));
                break;
            case R.id.tv_freesearchgoodsdetail_lainxikefu:
                break;
            //查看详情
            case R.id.tv_freesearchgoodsdetail_chakandetail:
                startActivity(new Intent(FreeSearchGoodsDetailActivity.this, FreeSearchGoodsDetailErJiActivity.class).putExtra("list", list));
                break;
        }
    }
}
