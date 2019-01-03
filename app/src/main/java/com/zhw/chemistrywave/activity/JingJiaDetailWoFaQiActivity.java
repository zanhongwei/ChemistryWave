package com.zhw.chemistrywave.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.CargoDetailBean;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class JingJiaDetailWoFaQiActivity extends BaseActivity {

    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rl_titlebar_search)
    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.tv_wofaqijingjiadetail_goodsname)
    TextView tvWofaqijingjiadetailGoodsname;
    @BindView(R.id.tv_wofaqijingjiadetail_chundu)
    TextView tvWofaqijingjiadetailChundu;
    @BindView(R.id.tv_wofaqijingjiadetail_huoqi)
    TextView tvWofaqijingjiadetailHuoqi;
    @BindView(R.id.tv_wofaqijingjiadetail_shuliang)
    TextView tvWofaqijingjiadetailShuliang;
    @BindView(R.id.tv_wofaqijingjiadetail_baozhuang)
    TextView tvWofaqijingjiadetailBaozhuang;
    @BindView(R.id.tv_wofaqijingjiadetail_price)
    TextView tvWofaqijingjiadetailPrice;
    @BindView(R.id.tv_wofaqijingjiadetail_fukuangyaoqiu)
    TextView tvWofaqijingjiadetailFukuangyaoqiu;
    @BindView(R.id.rb_wofaqijingjiadetail_xianxia)
    RadioButton rbWofaqijingjiadetailXianxia;
    @BindView(R.id.rb_wofaqijingjiadetail_alipay)
    RadioButton rbWofaqijingjiadetailAlipay;
    @BindView(R.id.rb_wofaqijingjiadetail_wechatpay)
    RadioButton rbWofaqijingjiadetailWechatpay;
    @BindView(R.id.rb_wofaqijingjiadetail_yinlianpay)
    RadioButton rbWofaqijingjiadetailYinlianpay;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rg_wofaqijingjiadetail_pay)
    RadioGroup rgWofaqijingjiadetailPay;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_wofaqijingjiadetail_yunshu)
    TextView tvWofaqijingjiadetailYunshu;
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
    @BindView(R.id.tv_wofaqijingjiadetail_qvxiaojingjia)
    TextView tvWofaqijingjiadetailQvxiaojingjia;
    @BindView(R.id.tv_wofaqijingjiadetail_chakanjingbiaozhe)
    TextView tvWofaqijingjiadetailChakanjingbiaozhe;
    @BindView(R.id.ll_yijingjia)
    LinearLayout llYijingjia;
    @BindView(R.id.tv_wofaqijingjiadetail_canyujingjia)
    TextView tvWofaqijingjiadetailCanyujingjia;
    @BindView(R.id.ll_canyujingjia)
    LinearLayout llCanyujingjia;
    private String mType;
    private String cargo_id;
    private String sponsor_id;
    private String spon_serl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_jia_detail_wo_fa_qi);
        ButterKnife.bind(this);
        cargo_id = getIntent().getStringExtra("cargo_id");
        initData();
    }

    /**
     * 初始化数据源
     */
    @SuppressLint("NewApi")
    private void initData() {
        tvTitlebarCenter.setText("竞价详情");
        mType = getIntent().getStringExtra("type");
        switch (mType) {
            case "canyu":
                tvWofaqijingjiadetailQvxiaojingjia.setText("已参与");
                tvWofaqijingjiadetailQvxiaojingjia.setBackground(getResources().getDrawable(R.drawable.bj_grey));
                tvWofaqijingjiadetailChakanjingbiaozhe.setText("我提交的详情");
                initCanYuJingJia();
                break;
            case "faqi":
                tvWofaqijingjiadetailQvxiaojingjia.setText("取消竞价");
                tvWofaqijingjiadetailQvxiaojingjia.setBackground(getResources().getDrawable(R.drawable.qvxiaojingjia));
                tvWofaqijingjiadetailChakanjingbiaozhe.setText("查看竞标者");
                sponsor_id = getIntent().getStringExtra("seller_id");
                spon_serl = getIntent().getStringExtra("spon_serl");
                initCanYuJingJia();
                break;
            case "canyujingjia":
                sponsor_id = getIntent().getStringExtra("seller_id");
                spon_serl = getIntent().getStringExtra("spon_serl");
                llYijingjia.setVisibility(View.GONE);
                llCanyujingjia.setVisibility(View.VISIBLE);
                initCanYuJingJia();
                llCanyujingjia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(JingJiaDetailWoFaQiActivity.this, CanYuJingJiaActivity.class).putExtra("seller_id", sponsor_id)
//                                .putExtra("cargo_id", cargo_id).putExtra("spon_serl", spon_serl));
                    }
                });
                break;
        }
    }

    private void initCanYuJingJia() {
        OkHttpUtils.post()
                .url(NetConfig.purchasedetail)
                .addParams("cargo_id", cargo_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(JingJiaDetailWoFaQiActivity.java:167)" + response);
                        Gson gson = new Gson();
                        CargoDetailBean.DataBean cargo = gson.fromJson(response, CargoDetailBean.class).getData();
                        tvWofaqijingjiadetailGoodsname.setText(cargo.getCargo_name() + "");
                        tvWofaqijingjiadetailChundu.setText(cargo.getCargo_purity() + "");
                        tvWofaqijingjiadetailHuoqi.setText(cargo.getDelivery_time() + "");
                        tvWofaqijingjiadetailShuliang.setText(cargo.getCargo_num() + "");
                        tvWofaqijingjiadetailBaozhuang.setText(cargo.getCargo_package() + "");
                        tvWofaqijingjiadetailPrice.setText(cargo.getCeiling_price() + "");
                        String payway = cargo.getPayment_way() + "";//付款要求（offline线下 alipay支付宝 wechat微信 unionpay银联）
                        if (payway != null) {

                            switch (payway) {
                                case "offline":
                                    rbWofaqijingjiadetailXianxia.setChecked(true);
                                    break;
                                case "alipay":
                                    rbWofaqijingjiadetailAlipay.setChecked(true);
                                    break;
                                case "wechat":
                                    rbWofaqijingjiadetailWechatpay.setChecked(true);
                                    break;
                                case "unionpay":
                                    rbWofaqijingjiadetailYinlianpay.setChecked(true);
                                    break;
                                case "finance":
                                    rb5.setChecked(true);
                                    break;
                            }
                        }
                        CargoDetailBean.DataBean.QualityBeanBean qualityBean = cargo.getQualityBean();
                        if (qualityBean != null) {
                            tvWofaqijingjiadetailYunshu.setText(cargo.getTransport_way() + "");
                            tvWofaqijingjiadetailQiangdu.setText(qualityBean.getIntensity() != null ? "强度(色力)：" + qualityBean.getIntensity() : "强度(色力)：" + "");
                            tvWofaqijingjiadetailSeguang.setText(qualityBean.getColoured_light() != null ? "色光：" + qualityBean.getColoured_light() : "色光：" + "");
                            tvWofaqijingjiadetailWaiguang.setText(qualityBean.getAppearance() != null ? "外观：" + qualityBean.getAppearance() : "外观：" + "");
                            tvWofaqijingjiadetailShuifen.setText(qualityBean.getMoisture() != null ? "水分：" + qualityBean.getMoisture() : "水分：" + "");
                            tvWofaqijingjiadetailBurongwu.setText(qualityBean.getInsoluble_substance() != null ? "不溶物：" + qualityBean.getInsoluble_substance() : "不溶物：" + "");
                            tvWofaqijingjiadetailRongjiedu.setText(qualityBean.getSolubility() != null ? "溶解度：" + qualityBean.getSolubility() : "溶解度：" + "");
                            tvWofaqijingjiadetailLvlizi.setText(qualityBean.getChloride_content() != null ? "氯离子含量：" + qualityBean.getChloride_content() : "氯离子含量：" + "");
                            tvWofaqijingjiadetailYanfen.setText(qualityBean.getSalinity() != null ? "盐分：" + qualityBean.getSalinity() : "盐分：" + "");
                            tvWofaqijingjiadetailDiandaolv.setText(qualityBean.getConductivity() != null ? "电导率：" + qualityBean.getConductivity() : "电导率：" + "");
                            tvWofaqijingjiadetailMishitong.setText(qualityBean.getMichler_ketone() != null ? "米氏酮：" + qualityBean.getMichler_ketone() : "米氏酮：" + "");
                            tvWofaqijingjiadetailGuhanliang.setText(qualityBean.getSolid_content() != null ? "固含量：" + qualityBean.getSolid_content() : "固含量：" + "");
                        }
                        CargoDetailBean.DataBean.MetalBeanBean metalBeanBean = cargo.getMetalBean();
                        /**
                         * 金属含量
                         */
                        if (metalBeanBean != null) {

                            tvWofaqijingjiadetailTonglizi.setText(metalBeanBean.getCu() != null ? "铜离子：" + metalBeanBean.getCu() : "铜离子：" + "");
                            tvWofaqijingjiadetailQianlizi.setText(metalBeanBean.getZn() != null ? "铅离子：" + metalBeanBean.getZn() : "铅离子：" + "");
                            tvWofaqijingjiadetailXinlizi.setText(metalBeanBean.getNi() != null ? "锌离子：" + metalBeanBean.getNi() : "锌离子：" + "");
                            tvWofaqijingjiadetailXilizi.setText(metalBeanBean.getSn() != null ? "锡离子：" + metalBeanBean.getSn() : "锡离子：" + "");
                            tvWofaqijingjiadetailNielizi.setText(metalBeanBean.getNi() != null ? "镍离子：" + metalBeanBean.getNi() : "镍离子：" + "");
                            tvWofaqijingjiadetailGulizi.setText(metalBeanBean.getCo() != null ? "钴离子：" + metalBeanBean.getCo() : "钴离子：" + "");
                            tvWofaqijingjiadetailTilizi.setText(metalBeanBean.getSb() != null ? "锑离子：" + metalBeanBean.getSb() : "锑离子：" + "");
                            tvWofaqijingjiadetailGonglizi.setText(metalBeanBean.getHg() != null ? "汞离子：" + metalBeanBean.getHg() : "汞离子：" + "");
                            tvWofaqijingjiadetailGelizi.setText(metalBeanBean.getCd() != null ? "镉离子：" + metalBeanBean.getCd() : "镉离子：" + "");
                            tvWofaqijingjiadetailBilizi.setText(metalBeanBean.getBi() != null ? "铋离子：" + metalBeanBean.getBi() : "铋离子：" + "");
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_wofaqijingjiadetail_qvxiaojingjia, R.id.tv_wofaqijingjiadetail_chakanjingbiaozhe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //取消竞价
            case R.id.tv_wofaqijingjiadetail_qvxiaojingjia:
                break;
            //查看竞标者
            case R.id.tv_wofaqijingjiadetail_chakanjingbiaozhe:
                String ssss = tvWofaqijingjiadetailChakanjingbiaozhe.getText().toString();
                switch (ssss) {
                    case "我提交的详情":
//                        startActivity(new Intent(this, JingBiaoZheDetailActivity.class).putExtra("type", "wotijiaodexiangqing").putExtra("cargo_id", cargo_id));
                        break;
                    case "查看竞标者":
//                        startActivity(new Intent(this, ChaKanJingBiaoZheActivity.class));
                        break;
                }
                break;
        }
    }
}
