package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Goods;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.PermissionHelper;
import com.zhw.chemistrywave.utils.PermissionInterface;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhw.chemistrywave.view.GoodsParamsPopupWindow;
import com.zhw.chemistrywave.view.MyGridView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zhw.chemistrywave.R.id.iv_guanbi;

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener, PermissionInterface {

    @BindView(R.id.iv_goods_img)
    ImageView ivGoodsImg;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_add_shopcar)
    ImageView ivAddShopcar;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_goods_ori_price)
    TextView tvGoodsOriPrice;
    @BindView(R.id.tv_goods_original_price)
    TextView tvGoodsOriginalPrice;
    @BindView(R.id.tv_ci_no)
    TextView tvCiNo;
    @BindView(R.id.tv_cas)
    TextView tvCas;
    @BindView(R.id.tv_specification)
    TextView tvSpecification;
    @BindView(R.id.tv_purity)
    TextView tvPurity;
    @BindView(R.id.tv_goods_time)
    TextView tvGoodsTime;
    @BindView(R.id.tv_goods_params)
    TextView tvGoodsParams;
    @BindView(R.id.ll_choose_params)
    LinearLayout llChooseParams;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_choose_address)
    LinearLayout llChooseAddress;
    @BindView(R.id.ll_scheme)
    LinearLayout llScheme;
    @BindView(R.id.ll_production_state)
    LinearLayout llProductionState;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.convenientBanner)
    ConvenientBanner convenientBanner;
    @BindView(R.id.tv_detection_kinds_name)
    TextView tvDetectionKindsName;
    @BindView(R.id.tv_detection_num)
    TextView tvDetectionNum;
    @BindView(R.id.tv_detection_num1)
    TextView tvDetectionNum1;
    @BindView(R.id.tv_detection_num2)
    TextView tvDetectionNum2;
    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_goods_chinese_name)
    TextView tvGoodsChineseName;
    @BindView(R.id.tv_goods_English_name)
    TextView tvGoodsEnglishName;
    @BindView(R.id.tv_ci_no_s)
    TextView tvCiNoS;
    @BindView(R.id.tv_cas_s)
    TextView tvCasS;
    @BindView(R.id.tv_purity_s)
    TextView tvPurityS;
    @BindView(R.id.tv_specification_s)
    TextView tvSpecificationS;
    @BindView(R.id.tv_molecule_num)
    TextView tvMoleculeNum;
    @BindView(R.id.tv_psa)
    TextView tvPsa;
    @BindView(R.id.tv_excat_quality)
    TextView tvExcatQuality;
    @BindView(R.id.tv_qiangdu)
    TextView tvQiangdu;
    @BindView(R.id.tv_seguang)
    TextView tvSeguang;
    @BindView(R.id.tv_waiguan)
    TextView tvWaiguan;
    @BindView(R.id.tv_shuifen)
    TextView tvShuifen;
    @BindView(R.id.tv_burongwu)
    TextView tvBurongwu;
    @BindView(R.id.tv_rongjiedu)
    TextView tvRongjiedu;
    @BindView(R.id.tv_lvlizihanliang)
    TextView tvLvlizihanliang;
    @BindView(R.id.tv_yanfen)
    TextView tvYanfen;
    @BindView(R.id.tv_diandaolv)
    TextView tvDiandaolv;
    @BindView(R.id.tv_mishitong)
    TextView tvMishitong;
    @BindView(R.id.tv_guhanliang)
    TextView tvGuhanliang;
    @BindView(R.id.tv_tulizi)
    TextView tvTulizi;
    @BindView(R.id.tv_qianlizi)
    TextView tvQianlizi;
    @BindView(R.id.tv_xinlizi)
    TextView tvXinlizi;
    @BindView(R.id.tv_xilizi)
    TextView tvXilizi;
    @BindView(R.id.tv_nielizi)
    TextView tvNielizi;
    @BindView(R.id.tv_gulizi)
    TextView tvGulizi;
    @BindView(R.id.tv_tilizi)
    TextView tvTilizi;
    @BindView(R.id.tv_gonglizi)
    TextView tvGonglizi;
    @BindView(R.id.tv_gelizi)
    TextView tvGelizi;
    @BindView(R.id.tv_bilizi)
    TextView tvBilizi;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.tv_add_shopcar)
    TextView tvAddShopcar;
    @BindView(R.id.tv_pay)
    TextView tvPay;

    private int mer_id;
    private List<String> ListBanner;
    private int position = 0;
    private String picture;
    private String cargo_name;
    private String cargo_purity;
    private String market_price;
    private String user_id;
    private String goods_id;
    private String current_price;
    private String color_power;
    private String color_light;
    private String package_opt;
    private boolean isBuyer = false;

    private Bundle bundle = new Bundle();//确认订单的bundle
    private int num = 1;//货物所选数量
    private GoodsParamsPopupWindow popupWindow;
    private String productPicture;
    private String productState;
    private View view;
    private ImageView ivProductImg;
    private TextView tvPrice;
    private TextView tvProductParams;
    private TextView tvNum;
    private TextView tvColorPower;
    private TextView tvColorLight;
    private TextView tvPackage;
    private PermissionHelper mPermissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        mer_id = getIntent().getIntExtra("mer_id", 0);
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case iv_guanbi:
                popupWindow.dismiss();
                break;
            case R.id.iv_subtract:
                String trim = tvNum.getText().toString().trim();
                int num = Integer.parseInt(trim);
                if (num < 2) {
                } else {
                    num--;
                    tvNum.setText(num + "");
                }

                break;
            case R.id.iv_add:
                String trim1 = tvNum.getText().toString().trim();
                int num1 = Integer.parseInt(trim1);
                num1++;
                tvNum.setText(num1 + "");
                break;
            case R.id.tv_cart:
                if (isBuyer) {
                    addShopCart();
                    popupWindow.dismiss();
                } else {
                    ToastUtil.showToastShort(this, "Please switch the buyer to add");
                }

                break;
            case R.id.tv_buy:
                if (isBuyer) {
                    gotoOrder();
                    popupWindow.dismiss();
                } else {
                    ToastUtil.showToastShort(this, "Please switch buyer for buying");
                }
                break;
        }
    }

    private void addShopCart() {
        String goods_num = "1";
        if (tvNum != null)
            goods_num = tvNum.getText().toString().trim();

        if (TextUtils.isEmpty(color_light) || TextUtils.isEmpty(color_power) || TextUtils.isEmpty(package_opt)) {
            Toast.makeText(this, "Please fill in the complete parameters", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("goods_name", cargo_name);
        params.put("goods_num", goods_num);
        params.put("goods_id", mer_id + "");
        params.put("goods_price", current_price);
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("color_power", color_power);
        params.put("color_light", color_light);
        params.put("package_opt", package_opt);

        OkHttpUtils.post()
                .url(NetConfig.tianjiagouwuche)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(GoodsDetailActivity.java:435)<---->" + e.getMessage());
                        Toast.makeText(GoodsDetailActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(GoodsDetailActivity.java:442)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(GoodsDetailActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(GoodsDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    //product params

    private void gotoOrder() {
        String goods_num = "1";
        if (tvNum != null) {
            goods_num = tvNum.getText().toString().trim();
        }
        if (TextUtils.isEmpty(color_light) || TextUtils.isEmpty(color_power) || TextUtils.isEmpty(package_opt)) {
            Toast.makeText(this, "Please fill in the complete parameters", Toast.LENGTH_SHORT).show();
            return;
        }
        bundle.putString("shopId", user_id);//店铺id
        bundle.putString("goods_id", goods_id);//店铺id
        bundle.putString("goods_name", cargo_name);//货物名称
        bundle.putString("goods_photo", picture);//货物名称
        bundle.putString("color_power", color_power);//货物所选参数
        bundle.putString("color_light", color_light);//货物所选参数
        bundle.putString("package_opt", package_opt);//货物所选参数
        bundle.putDouble("goods_price", Double.parseDouble(current_price));//货物价格
        bundle.putInt("goods_num", Integer.parseInt(goods_num));//货物数量

        startActivity(new Intent(GoodsDetailActivity.this, VertifyOrderActivity.class)
                .putExtra("type", "GooodsDetailActivity")
                .putExtras(bundle));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)) {
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return 10000;
    }

    @Override
    public String[] getPermissions() {

        return new String[]{
                Manifest.permission.CALL_PHONE
        };

    }

    @Override
    public void requestPermissionsSuccess() {
        initViews();
    }

    private void initViews() {
        initView();
        getData();
        String user_state = "";
        try {
            user_state = (String) SPUtils.get(this, "user_state", "");
        } catch (Exception e) {

        }
        if (null != user_state && !user_state.isEmpty() && "0".equals(user_state)) {
            isBuyer = true;
        } else {
            isBuyer = false;
            ivAddShopcar.setVisibility(View.GONE);
        }
    }

    private void initView() {
        tvGoodsOriPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        tvGoodsOriginalPrice.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG | Paint.STRIKE_THRU_TEXT_FLAG);
        tvDetectionNum.setText(position + "");
        convenientBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
                Log.e("aaa", "--position---->" + pos);
                tvDetectionNum.setText((pos + 1) + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void getData() {
        Log.e("aaa", "--商品详情参数--mer_id-->" + mer_id);
        OkHttpUtils.post().url(NetConfig.goodsdetail_url)
                .addParams("goods_id", String.valueOf(mer_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--商品详情返回--error->" + e.getMessage());
                        Toast.makeText(GoodsDetailActivity.this, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--商品详情返回--->" + response);
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Goods shangpxiangqing = gson.fromJson(response, Goods.class);
                                    picture = shangpxiangqing.getData().getProduct_picture();
                                    if (!TextUtils.isEmpty(picture)) {
                                        Glide.with(GoodsDetailActivity.this).load(NetConfig.baseurl + picture).into(ivGoodsImg);
                                    } else {
                                        ivGoodsImg.setImageResource(R.drawable.goodsone);
                                    }
                                    user_id = shangpxiangqing.getData().getUser_id() + "";
                                    Log.e("aaa",
                                            "(GoodsDetailActivity.java:266)<---->" + user_id);
                                    goods_id = shangpxiangqing.getData().getGoods_id() + "";
                                    cargo_name = shangpxiangqing.getData().getGoods_name();
                                    cargo_purity = shangpxiangqing.getData().getGoods_purity();
                                    market_price = shangpxiangqing.getData().getMarket_price();
                                    current_price = shangpxiangqing.getData().getCurrent_price();
                                    color_power = shangpxiangqing.getData().getColor_power() == null ? "" : shangpxiangqing.getData().getColor_power();
                                    color_light = shangpxiangqing.getData().getColor_light() == null ? "" : shangpxiangqing.getData().getColor_light();
                                    package_opt = shangpxiangqing.getData().getPackage_opt() == null ? "" : shangpxiangqing.getData().getPackage_opt();
                                    tvGoodsName.setText(shangpxiangqing.getData().getGoods_name_en() != null ? shangpxiangqing.getData().getGoods_name_en() : "");
                                    tvGoodsPrice.setText(shangpxiangqing.getData().getCurrent_price() != null ? "¥" + shangpxiangqing.getData().getCurrent_price() : "");
                                    tvGoodsOriginalPrice.setText(shangpxiangqing.getData().getMarket_price() != null ? "¥" + shangpxiangqing.getData().getMarket_price() : "");
                                    tvCiNo.setText(shangpxiangqing.getData().getCino() != null ? shangpxiangqing.getData().getCino() : "");
                                    tvCiNoS.setText(shangpxiangqing.getData().getCino() != null ? shangpxiangqing.getData().getCino() : "");
                                    tvCas.setText(shangpxiangqing.getData().getCas() != null ? shangpxiangqing.getData().getCas() : "");
                                    tvCasS.setText(shangpxiangqing.getData().getCas() != null ? shangpxiangqing.getData().getCas() : "");
                                    tvSpecification.setText(shangpxiangqing.getData().getSpecification() != null ? shangpxiangqing.getData().getSpecification() : "");
                                    tvSpecificationS.setText(shangpxiangqing.getData().getSpecification() != null ? shangpxiangqing.getData().getSpecification() : "");
                                    tvPurity.setText(shangpxiangqing.getData().getGoods_purity() != null ? shangpxiangqing.getData().getGoods_purity() : "");
                                    tvPurityS.setText(shangpxiangqing.getData().getGoods_purity() != null ? shangpxiangqing.getData().getGoods_purity() : "");
                                    tvGoodsTime.setText(shangpxiangqing.getData().getGoods_deliver() != null ? shangpxiangqing.getData().getGoods_deliver() : "");
                                    tvGoodsChineseName.setText(shangpxiangqing.getData().getGoods_name() != null ? shangpxiangqing.getData().getGoods_name() : "");
                                    tvGoodsEnglishName.setText(shangpxiangqing.getData().getGoods_name_en() != null ? shangpxiangqing.getData().getGoods_name_en() : "");
                                    tvMoleculeNum.setText(shangpxiangqing.getData().getMolecular_weight() != null ? shangpxiangqing.getData().getMolecular_weight() : "");
                                    tvPsa.setText(shangpxiangqing.getData().getPas() != null ? shangpxiangqing.getData().getPas() : "");
                                    tvExcatQuality.setText(shangpxiangqing.getData().getPrecise_quality() != null ? shangpxiangqing.getData().getPrecise_quality() : "");
                                    tvQiangdu.setText(shangpxiangqing.getData().getColor_power() != null ? shangpxiangqing.getData().getColor_power() : "");
                                    tvSeguang.setText(shangpxiangqing.getData().getColor_light() != null ? shangpxiangqing.getData().getColor_light() : "");
                                    tvWaiguan.setText(shangpxiangqing.getData().getQuality_aspect() != null ? shangpxiangqing.getData().getQuality_aspect() : "");
                                    tvShuifen.setText(shangpxiangqing.getData().getQuality_moisture() != null ? shangpxiangqing.getData().getQuality_moisture() : "");
                                    tvBurongwu.setText(shangpxiangqing.getData().getQuality_insoluble() != null ? shangpxiangqing.getData().getQuality_insoluble() : "");
                                    tvRongjiedu.setText(shangpxiangqing.getData().getQuality_solubility() != null ? shangpxiangqing.getData().getQuality_solubility() : "");
                                    tvLvlizihanliang.setText(shangpxiangqing.getData().getQuality_clContent() != null ? shangpxiangqing.getData().getQuality_clContent() : "");
                                    tvYanfen.setText(shangpxiangqing.getData().getQuality_salinity() != null ? shangpxiangqing.getData().getQuality_salinity() : "");
                                    tvDiandaolv.setText(shangpxiangqing.getData().getQuality_conductivity() != null ? shangpxiangqing.getData().getQuality_conductivity() : "");
                                    tvMishitong.setText(shangpxiangqing.getData().getQuality_michlerKetone() != null ? shangpxiangqing.getData().getQuality_michlerKetone() : "");
                                    tvGuhanliang.setText(shangpxiangqing.getData().getQuality_solidContent() != null ? shangpxiangqing.getData().getQuality_solidContent() : "");
                                    tvTulizi.setText(shangpxiangqing.getData().getMetal_cu() != null ? shangpxiangqing.getData().getMetal_cu() : "");
                                    tvQianlizi.setText(shangpxiangqing.getData().getMetal_pb() != null ? shangpxiangqing.getData().getMetal_pb() : "");
                                    tvXinlizi.setText(shangpxiangqing.getData().getMetal_zn() != null ? shangpxiangqing.getData().getMetal_zn() : "");
                                    tvXilizi.setText(shangpxiangqing.getData().getMetal_sn() != null ? shangpxiangqing.getData().getMetal_sn() : "");
                                    tvNielizi.setText(shangpxiangqing.getData().getMetal_ni() != null ? shangpxiangqing.getData().getMetal_ni() : "");
                                    tvGulizi.setText(shangpxiangqing.getData().getMetal_co() != null ? shangpxiangqing.getData().getMetal_co() : "");
                                    tvTilizi.setText(shangpxiangqing.getData().getMetal_sb() != null ? shangpxiangqing.getData().getMetal_sb() : "");
                                    tvGonglizi.setText(shangpxiangqing.getData().getMetal_hg() != null ? shangpxiangqing.getData().getMetal_hg() : "");
                                    tvGelizi.setText(shangpxiangqing.getData().getMetal_cd() != null ? shangpxiangqing.getData().getMetal_cd() : "");
                                    tvBilizi.setText(shangpxiangqing.getData().getMetal_bi() != null ? shangpxiangqing.getData().getMetal_bi() : "");
                                    tvGoodsParams.setText("“" + color_power + "”," + "“" + color_light + "”," + "“" + package_opt + "”");
                                    ListBanner = new ArrayList<String>();
                                    productPicture = shangpxiangqing.getData().getProduct_picture();
                                    productState = shangpxiangqing.getData().getProduction_state();
                                    String application_scheme = shangpxiangqing.getData().getApplication_scheme();

                                    if (!TextUtils.isEmpty(productPicture)) {
                                        ListBanner.add(NetConfig.baseurl + productPicture);
                                    }
                                    if (!TextUtils.isEmpty(productState)) {
                                        ListBanner.add(NetConfig.baseurl + productState);
                                    }
                                    tvDetectionNum2.setText(ListBanner.size() + "");
                                    setConvenientBanner(ListBanner);
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(GoodsDetailActivity.this, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setConvenientBanner(List<String> bannerList) {

        Log.e("aaa", "---ffff----->" + bannerList.toString());
        convenientBanner.setPages(
                new CBViewHolderCreator<NetWorkImageHolderView>() {
                    @Override
                    public NetWorkImageHolderView createHolder() {
                        return new NetWorkImageHolderView();
                    }
                }, bannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.circler_null, R.drawable.circler})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
//                .startTurning(3000);
    }

    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        finish();
    }

    @OnClick({R.id.iv_back, R.id.iv_add_shopcar, R.id.ll_choose_params, R.id.ll_choose_address, R.id.ll_scheme, R.id.ll_production_state,
            R.id.ll_service, R.id.ll_store, R.id.ll_collect, R.id.tv_add_shopcar, R.id.tv_pay})
    public void onViewClick1ed(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回键
                finish();
                break;
            case R.id.iv_add_shopcar://跳转购物车
                if (isBuyer) {
                    startActivity(new Intent(GoodsDetailActivity.this, ShopCarActivity.class));
                } else {
                    ToastUtil.showToastShort(this, "Please switch the buyer to add");
                }
                break;
            case R.id.ll_choose_params://选择商品参数
                addGoodsParams();
                break;
            case R.id.ll_choose_address://选择送货地址
//                startActivity(new Intent(GoodsDetailActivity.this, NewAddressManagerActivity.class));
                break;
            case R.id.ll_scheme://应用方案
                startActivity(new Intent(GoodsDetailActivity.this, UseSchemeActivity.class).putExtra("name", "Application Scheme"));
                break;
            case R.id.ll_production_state://监测报告
                startActivity(new Intent(GoodsDetailActivity.this, UseSchemeActivity.class).putExtra("name", "Test report"));
//                startActivity(new Intent(GoodsDetailActivity.this, ExaminingReportActivity.class));
                break;
            case R.id.ll_service:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:02223789275"));
                startActivity(intent);
                break;
            case R.id.ll_store:
                Log.e("aaa",
                        "(GoodsDetailActivity.java:382)<---->" + user_id);
                startActivity(new Intent(this, ShopDetailActivity.class).putExtra("user_id", user_id));
                break;
            //添加对供应商的询盘
            case R.id.ll_collect:
                if (isBuyer) {
                    startActivity(new Intent(this, AddXunPanActivity.class).putExtra("goods_id", goods_id));
                } else {
                    ToastUtil.showToastShort(this, "Please switch the buyer to inquiry");
                }
                break;
            case R.id.tv_add_shopcar:
                if (isBuyer) {
                    addShopCart();
                } else {
                    ToastUtil.showToastShort(this, "Please switch the buyer to add");
                }
                break;
            case R.id.tv_pay:
                if (isBuyer) {
                    gotoOrder();
                } else {
                    ToastUtil.showToastShort(this, "Please switch buyer for buying");
                }
                break;
        }
    }

    private void addGoodsParams() {
        view = View.inflate(GoodsDetailActivity.this, R.layout.layout_goods_params, null);
        ivProductImg = (ImageView) view.findViewById(R.id.iv_product_img);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        tvProductParams = (TextView) view.findViewById(R.id.tv_product_params);
        tvNum = (TextView) view.findViewById(R.id.tv_num);
        tvColorPower = (TextView) view.findViewById(R.id.tv_color_power);
        tvColorLight = (TextView) view.findViewById(R.id.tv_color_light);
        tvPackage = (TextView) view.findViewById(R.id.tv_package);
        MyGridView gvColor;
        MyGridView gvColour;
        MyGridView gvPackage;


        ImageView ivClose = (ImageView) view.findViewById(iv_guanbi);
        popupWindow = new GoodsParamsPopupWindow(GoodsDetailActivity.this, view);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        ivClose.setOnClickListener(this);
        view.findViewById(R.id.iv_subtract).setOnClickListener(this);
        view.findViewById(R.id.iv_add).setOnClickListener(this);
        view.findViewById(R.id.tv_cart).setOnClickListener(this);
        view.findViewById(R.id.tv_buy).setOnClickListener(this);

        Glide.with(GoodsDetailActivity.this).load(NetConfig.baseurl + productPicture).apply(MyApplication.options).into(ivProductImg);
        tvProductParams.setText("Selected: “" + color_power + "”,“" + color_light + "”,“" + package_opt + "”");
        tvColorPower.setText(color_power);
        tvColorLight.setText(color_light);
        tvPackage.setText(package_opt);
        tvPrice.setText(current_price);

    }

    class NetWorkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
//            imageView.setImageResource(data);
            if (data != null) {
                Log.e("aaa", "---data-->" + data);
                Glide.with(GoodsDetailActivity.this).load(data).into(imageView);
                //ImageLoader.getInstance().displayImage(data, imageView, options);
            } else {
                imageView.setImageResource(R.drawable.goodsone);
            }
        }


    }

    class MyColorPowerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
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
            if (view == null) {
                view = View.inflate(GoodsDetailActivity.this, R.layout.item_color_power_gv, null);
            }
            return null;
        }
    }

}
