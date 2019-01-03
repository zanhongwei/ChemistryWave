package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.CanYuJingJiaDetailBean;
import com.zhw.chemistrywave.bean.SDJJBean;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class JingBiaoZheDetailActivity extends BaseActivity {


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
    @BindView(R.id.tv_jingbiaozhedetail_goodsname)
    TextView tvJingbiaozhedetailGoodsname;
    @BindView(R.id.tv_jingbiaozhedetail_guige)
    TextView tvJingbiaozhedetailGuige;
    @BindView(R.id.tv_jingbiaozhedetail_chundu)
    TextView tvJingbiaozhedetailChundu;
    @BindView(R.id.tv_jingbiaozhedetail_huoqi)
    TextView tvJingbiaozhedetailHuoqi;
    @BindView(R.id.tv_jingbiaozhedetail_chanpingshuliang)
    TextView tvJingbiaozhedetailChanpingshuliang;
    @BindView(R.id.tv_jingbiaozhedetail_zhichibaozhuang)
    TextView tvJingbiaozhedetailZhichibaozhuang;
    @BindView(R.id.tv_jingbiaozhedetail_fukuangfangshi)
    TextView tvJingbiaozhedetailFukuangfangshi;
    @BindView(R.id.tv_jingbiaozhedetail_yunshufangshi)
    TextView tvJingbiaozhedetailYunshufangshi;
    @BindView(R.id.tv_jingbiaozhedetail_zuidijiage)
    TextView tvJingbiaozhedetailZuidijiage;
    @BindView(R.id.tv_jingbiaozhedetail_yingyongfangan)
    TextView tvJingbiaozhedetailYingyongfangan;
    @BindView(R.id.tv_jingbiaozhedetail_shengchanzhuangtai)
    TextView tvJingbiaozhedetailShengchanzhuangtai;
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
    @BindView(R.id.tv_jingbiaozhedetail_xuanzeta)
    TextView tvJingbiaozhedetailXuanzeta;
    private String mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_biao_zhe_detail);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("竞标者详情");
        mType = getIntent().getStringExtra("type");
        switch (mType) {
            case "chakan":
                tvJingbiaozhedetailXuanzeta.setVisibility(View.VISIBLE);
                OkHttpUtils.post()
                        .url(NetConfig.bidderDetail)
                        .addParams("cargo_id", getIntent().getStringExtra("cargo_id"))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa", "JingBiaoZheDetailActivity" + response);
                                Gson gson = new Gson();
                                SDJJBean.DataBean.ListBean.CargoBeansBean cargoBean = gson.fromJson(response, SDJJBean.DataBean.ListBean.CargoBeansBean.class);
                                //设置参与竞价的详情

                            }
                        });

                break;
            case "wotijiaodexiangqing":
                String cargo_id = getIntent().getStringExtra("cargo_id");
                tvJingbiaozhedetailXuanzeta.setVisibility(View.GONE);
                OkHttpUtils.post()
                        .url(NetConfig.biddetailInvoke)
                        .addParams("cargo_id", cargo_id)
                        .addParams("seller_id", MyUtils.getUser().getUser_id())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa", "JingBiaoZheDetailActivity" + response);
                                Gson gson = new Gson();
                                CanYuJingJiaDetailBean canyujingjiaBean = gson.fromJson(response, CanYuJingJiaDetailBean.class);
                                tvJingbiaozhedetailGoodsname.setText(canyujingjiaBean.getData().getCargo_name() + "");
                                tvJingbiaozhedetailGuige.setText(canyujingjiaBean.getData().getSpecifications() + "");
                                tvJingbiaozhedetailChundu.setText(canyujingjiaBean.getData().getCargo_purity() + "");
                                tvJingbiaozhedetailHuoqi.setText(canyujingjiaBean.getData().getDelivery_time() + "");
                                tvJingbiaozhedetailChanpingshuliang.setText(canyujingjiaBean.getData().getCargo_num() + "");
                                tvJingbiaozhedetailZhichibaozhuang.setText(canyujingjiaBean.getData().getCargo_package() + "");
                                String payway = canyujingjiaBean.getData().getPayment_way();//付款要求（offline线下 alipay支付宝 wechat微信 unionpay银联）
                                if (payway != null) {
                                    switch (payway) {
                                        case "offline":
                                            tvJingbiaozhedetailFukuangfangshi.setText("线下支付");
                                            break;
                                        case "alipay":
                                            tvJingbiaozhedetailFukuangfangshi.setText("支付宝支付");
                                            break;
                                        case "wechat":
                                            tvJingbiaozhedetailFukuangfangshi.setText("微信支付");
                                            break;
                                        case "unionpay":
                                            tvJingbiaozhedetailFukuangfangshi.setText("银联支付");
                                            break;
                                    }

                                }
                                tvJingbiaozhedetailYunshufangshi.setText(canyujingjiaBean.getData().getTransport_way());
                                tvJingbiaozhedetailZuidijiage.setText(canyujingjiaBean.getData().getFloor_price());
                                /**
                                 * 品质参数
                                 */
                                CanYuJingJiaDetailBean.DataBean.QualityBeanBean qualityBeanBean = canyujingjiaBean.getData().getQualityBean();
                                if (qualityBeanBean != null) {

                                    tvWofaqijingjiadetailQiangdu.setText(qualityBeanBean.getIntensity() != null ? "强度(色力)：" + qualityBeanBean.getIntensity() : "强度(色力)：" + "");
                                    tvWofaqijingjiadetailSeguang.setText(qualityBeanBean.getColoured_light() != null ? "色光：" + qualityBeanBean.getColoured_light() : "色光：" + "");
                                    tvWofaqijingjiadetailWaiguang.setText(qualityBeanBean.getAppearance() != null ? "外观：" + qualityBeanBean.getAppearance() : "外观：" + "");
                                    tvWofaqijingjiadetailShuifen.setText(qualityBeanBean.getMoisture() != null ? "水分：" + qualityBeanBean.getMoisture() : "水分：" + "");
                                    tvWofaqijingjiadetailBurongwu.setText(qualityBeanBean.getInsoluble_substance() != null ? "不溶物：" + qualityBeanBean.getInsoluble_substance() : "不溶物：" + "");
                                    tvWofaqijingjiadetailRongjiedu.setText(qualityBeanBean.getSolubility() != null ? "溶解度：" + qualityBeanBean.getSolubility() : "溶解度：" + "");
                                    tvWofaqijingjiadetailLvlizi.setText(qualityBeanBean.getChloride_content() != null ? "氯离子含量：" + qualityBeanBean.getChloride_content() : "氯离子含量：" + "");
                                    tvWofaqijingjiadetailYanfen.setText(qualityBeanBean.getSalinity() != null ? "盐分：" + qualityBeanBean.getSalinity() : "盐分：" + "");
                                    tvWofaqijingjiadetailDiandaolv.setText(qualityBeanBean.getConductivity() != null ? "电导率：" + qualityBeanBean.getConductivity() : "电导率：" + "");
                                    tvWofaqijingjiadetailMishitong.setText(qualityBeanBean.getMichler_ketone() != null ? "米氏酮：" + qualityBeanBean.getMichler_ketone() : "米氏酮：" + "");
                                    tvWofaqijingjiadetailGuhanliang.setText(qualityBeanBean.getSolid_content() != null ? "固含量：" + qualityBeanBean.getSolid_content() : "固含量：" + "");

                                }
                                CanYuJingJiaDetailBean.DataBean.MetalBeanBean metalBeanBean = canyujingjiaBean.getData().getMetalBean();
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
                break;
            case "yijianjingjiazhelvlvadapter":
                tvJingbiaozhedetailXuanzeta.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_jingbiaozhedetail_xuanzeta})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.tv_jingbiaozhedetail_xuanzeta:
                break;
        }
    }
}
