package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PingTaiChaZhaoJieGuoActivity extends BaseActivity {


    @BindView(R.id.iv_freedetection_back)
    ImageView ivFreedetectionBack;
    @BindView(R.id.tv_freedetection_title)
    TextView tvFreedetectionTitle;
    @BindView(R.id.tv_pingtaichazhaojieguo_goodsname)
    TextView tvPingtaichazhaojieguoGoodsname;
    @BindView(R.id.tv_pingtaichazhaojieguo_guige)
    TextView tvPingtaichazhaojieguoGuige;
    @BindView(R.id.tv_pingtaichazhaojieguo_chundu)
    TextView tvPingtaichazhaojieguoChundu;
    @BindView(R.id.tv_pingtaichazhaojieguo_huoqi)
    TextView tvPingtaichazhaojieguoHuoqi;
    @BindView(R.id.tv_pingtaichazhaojieguo_chanpingshuliang)
    TextView tvPingtaichazhaojieguoChanpingshuliang;
    @BindView(R.id.tv_pingtaichazhaojieguo_zhichibaozhuang)
    TextView tvPingtaichazhaojieguoZhichibaozhuang;
    @BindView(R.id.tv_pingtaichazhaojieguo_fukuangfangshi)
    TextView tvPingtaichazhaojieguoFukuangfangshi;
    @BindView(R.id.tv_pingtaichazhaojieguo_yunshufangshi)
    TextView tvPingtaichazhaojieguoYunshufangshi;
    @BindView(R.id.tv_pingtaichazhaojieguo_zuidijiage)
    TextView tvPingtaichazhaojieguoZuidijiage;
    @BindView(R.id.tv_pingtaichazhaojieguo_yingyongfangan)
    TextView tvPingtaichazhaojieguoYingyongfangan;
    @BindView(R.id.ll_pingtaichazhaojieguo_yingyongfanan)
    LinearLayout llPingtaichazhaojieguoYingyongfanan;
    @BindView(R.id.tv_pingtaichazhaojieguo_shengchanzhuangtai)
    TextView tvPingtaichazhaojieguoShengchanzhuangtai;
    @BindView(R.id.ll_pingtaichazhaojieguo_jiancebaogao)
    LinearLayout llPingtaichazhaojieguoJiancebaogao;
    @BindView(R.id.tv_wofaqijingjiadetail_qiangdu)
    TextView tvWofaqijingjiadetailQiangdu;
    @BindView(R.id.tv_wofaqijingjiadetail_seguang)
    TextView tvWofaqijingjiadetailSeguang;
    @BindView(R.id.tv_wofaqijingjiadetail_waiguang)
    TextView tvWofaqijingjiadetailWaiguang;
    @BindView(R.id.tv_wofaqijingjiadetail_shuifen)
    TextView tvWofaqijingjiadetailShuifen;
    @BindView(R.id.tv_wofaqijingjiadetail_burongwu)
    TextView tvWofaqijingjiadetailBurongwu;
    @BindView(R.id.tv_wofaqijingjiadetail_rongjiedu)
    TextView tvWofaqijingjiadetailRongjiedu;
    @BindView(R.id.tv_wofaqijingjiadetail_lvlizi)
    TextView tvWofaqijingjiadetailLvlizi;
    @BindView(R.id.tv_wofaqijingjiadetail_yanfen)
    TextView tvWofaqijingjiadetailYanfen;
    @BindView(R.id.tv_wofaqijingjiadetail_diandaolv)
    TextView tvWofaqijingjiadetailDiandaolv;
    @BindView(R.id.tv_wofaqijingjiadetail_mishitong)
    TextView tvWofaqijingjiadetailMishitong;
    @BindView(R.id.tv_wofaqijingjiadetail_guhanliang)
    TextView tvWofaqijingjiadetailGuhanliang;
    @BindView(R.id.tv_wofaqijingjiadetail_tonglizi)
    TextView tvWofaqijingjiadetailTonglizi;
    @BindView(R.id.tv_wofaqijingjiadetail_qianlizi)
    TextView tvWofaqijingjiadetailQianlizi;
    @BindView(R.id.tv_wofaqijingjiadetail_xinlizi)
    TextView tvWofaqijingjiadetailXinlizi;
    @BindView(R.id.tv_wofaqijingjiadetail_xilizi)
    TextView tvWofaqijingjiadetailXilizi;
    @BindView(R.id.tv_wofaqijingjiadetail_nielizi)
    TextView tvWofaqijingjiadetailNielizi;
    @BindView(R.id.tv_wofaqijingjiadetail_gulizi)
    TextView tvWofaqijingjiadetailGulizi;
    @BindView(R.id.tv_wofaqijingjiadetail_tilizi)
    TextView tvWofaqijingjiadetailTilizi;
    @BindView(R.id.tv_wofaqijingjiadetail_gonglizi)
    TextView tvWofaqijingjiadetailGonglizi;
    @BindView(R.id.tv_wofaqijingjiadetail_gelizi)
    TextView tvWofaqijingjiadetailGelizi;
    @BindView(R.id.tv_wofaqijingjiadetail_bilizi)
    TextView tvWofaqijingjiadetailBilizi;
    @BindView(R.id.tv_pingtaichazhaojieguo_lijigoumai)
    TextView tvPingtaichazhaojieguoLijigoumai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_tai_cha_zhao_jie_guo);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvFreedetectionTitle.setText("平台查找结果");
    }

    @OnClick({R.id.iv_freedetection_back, R.id.ll_pingtaichazhaojieguo_yingyongfanan, R.id.ll_pingtaichazhaojieguo_jiancebaogao, R.id.tv_pingtaichazhaojieguo_lijigoumai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_freedetection_back:
                onBackPressed();
                break;
            //应用方案
            case R.id.ll_pingtaichazhaojieguo_yingyongfanan:
                startActivity(new Intent(PingTaiChaZhaoJieGuoActivity.this, UseSchemeActivity.class));
                break;
            //检测报告
            case R.id.ll_pingtaichazhaojieguo_jiancebaogao:
//                startActivity(new Intent(PingTaiChaZhaoJieGuoActivity.this,JianCeBaoGaoListActivity.class));
                break;
            //立即购买
            case R.id.tv_pingtaichazhaojieguo_lijigoumai:

                break;
        }
    }
}
