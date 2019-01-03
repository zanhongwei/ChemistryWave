package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.AddGoodsBaseInfo;
import com.zhw.chemistrywave.bean.Metal;
import com.zhw.chemistrywave.bean.QualityDetail;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class TianJiaShangPingActivity extends BaseActivity {


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
    @BindView(R.id.ll_baseinfo)
    LinearLayout llBaseinfo;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.ll_qualitydetail)
    LinearLayout llQualitydetail;
    @BindView(R.id.ll_metalpercent)
    LinearLayout llMetalpercent;
    @BindView(R.id.ll_jiancebaogao)
    LinearLayout llJiancebaogao;
    @BindView(R.id.ll_zhekou)
    LinearLayout llZhekou;
    @BindView(R.id.tv_tianjiashangpin_save)
    TextView tvTianjiashangpinSave;

    //货物的基本信息
    private String name, one_type, two_type, guige, chudu, huoqi, shuliang, goods_unit, baozhuang, yunshufangshi, zuidingjiage, fukuangfangshi, yingyongfangan, chanpintupian, shengchanzhuangtai;
    private String cino;
    private String cas;
    private String molecular_weight;
    private String pas;
    private String precise_quality;
    //品质详情 start----------------------
    private String intensity;
    private String coloured_light;
    private String appearance;
    private String moisture;
    private String insoluble_substance;
    private String solubility;
    private String chloride_content;
    private String solid_content;
    private String salinity;
    private String conductivity;
    private String michler_ketone;
    //品质详情 end-------------------------
    //金属含量start-------------------
    private String mer_cu;
    private String mer_zn;
    private String mer_ni;
    private String mer_sb;
    private String mer_cd;
    private String mer_pb;
    private String mer_sn;
    private String mer_co;
    private String mer_hg;
    private String mer_bi;
    //金属含量end-------------------

    //监测报告  start-----------------
    private String uvDataUp;
    private String ceseyiDataUp;
    private String yanPinUp;
    private String jiancebaogaoUp;
    private String jianceshipUp;
    private String daHuoTuPianUp;
    private AddGoodsBaseInfo addGoodsBaseInfo;
    private QualityDetail qualityDetail;
    private Metal metal;
    private Bundle quality;
    //监测报告  end-----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_jia_shang_ping);
        ButterKnife.bind(this);
        llZhekou.setVisibility(View.GONE);
//        mer_id = getIntent().getStringExtra("mer_id");
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("Add gooods");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_baseinfo, R.id.ll_qualitydetail, R.id.ll_metalpercent, R.id.ll_jiancebaogao, R.id.ll_zhekou, R.id.tv_tianjiashangpin_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //货物基本信息
            case R.id.ll_baseinfo:
                Intent intent = new Intent(TianJiaShangPingActivity.this, AddGoodsBaseInfoActivity.class);
//                intent.putExtra("mer_id", mer_id);
                intent.putExtra("type", "TianJiaShangPingActivity");
                if (addGoodsBaseInfo != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("baseInfo", addGoodsBaseInfo);
                    intent.putExtras(bundle);
                }
                startActivityForResult(intent, 111);
                break;

            //品质详情
            case R.id.ll_qualitydetail:
                Intent intent3 = new Intent(TianJiaShangPingActivity.this, AddQualityDetailActivity.class);
                intent3.putExtra("type", "TianJiaShangPingActivity");
                if (qualityDetail != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("quality", qualityDetail);
                    intent3.putExtras(bundle);
                }
                startActivityForResult(intent3, 222);
                break;
            //金属含量
            case R.id.ll_metalpercent:
                Intent intent1 = new Intent(TianJiaShangPingActivity.this, AddJinShuHanLiangActivity.class);
                intent1.putExtra("type", "TianJiaShangPingActivity");
                if (metal != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("metal", metal);
                    intent1.putExtras(bundle);
                }
                startActivityForResult(intent1, 333);
                break;
            //检测报告
            case R.id.ll_jiancebaogao:

                Intent intent2 = new Intent(TianJiaShangPingActivity.this, AddJianCeBaoGaoActivity.class);
                intent2.putExtra("type", "TianJiaShangPingActivity");
                startActivityForResult(intent2, 444);
                break;
            //折扣
            case R.id.ll_zhekou:
                startActivity(new Intent(TianJiaShangPingActivity.this, DisCountActivity.class)
//                        .putExtra("mer_id", mer_id)
                        .putExtra("type", "TianJiaShangPingActivity"));
                break;
            //保存
            case R.id.tv_tianjiashangpin_save:
                save();
                break;
        }
    }

    private void save() {

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please fill in goods base information", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(one_type)){
//            Toast.makeText(this, "请填写产品分类", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(two_type)){
//            Toast.makeText(this, "请填写产品分类", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(cino)){
//            Toast.makeText(this, "请填写产品的CINO", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(cas)){
//            Toast.makeText(this, "请填写产品的CAS", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(molecular_weight)){
//            Toast.makeText(this, "请填写产品的分子量", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(pas)){
//            Toast.makeText(this, "请填写产品的PAS", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(precise_quality)){
//            Toast.makeText(this, "请填写产品的精确质量", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(guige)){
//            Toast.makeText(this, "请填写产品规格", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(chudu)){
//            Toast.makeText(this, "请填写产品纯度", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(huoqi)){
//            Toast.makeText(this, "请填写货期", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(shuliang)){
//            Toast.makeText(this, "请填写产品数量", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(baozhuang)){
//            Toast.makeText(this, "请填写产品包装", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(yunshufangshi)){
//            Toast.makeText(this, "请选择产品运输方式", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(zuidingjiage)){
//            Toast.makeText(this, "请填写产品最低价格", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(fukuangfangshi)){
//            Toast.makeText(this, "请选择产品付款要求", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(yingyongfangan)){
//            Toast.makeText(this, "请上传产品应用方案", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(chanpintupian)){
//            Toast.makeText(this, "请上传产品图片", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(shengchanzhuangtai)){
//            Toast.makeText(this, "请上传生产状态", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(name)){
//            Toast.makeText(this, "请填写产品名称", Toast.LENGTH_SHORT).show();
//            return;
//        }
        if (TextUtils.isEmpty(intensity)) {
            Toast.makeText(this, "Please fill in goods quality detail", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(coloured_light)){
//
//            return;
//        }
//        if (TextUtils.isEmpty(appearance)){
//            Toast.makeText(this, "请填写外观，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(moisture)){
//            Toast.makeText(this, "请填写水分，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(insoluble_substance)){
//            Toast.makeText(this, "请填写不溶物，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(solubility)){
//            Toast.makeText(this, "请填写溶解度，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(chloride_content)){
//            Toast.makeText(this, "请填写氯离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(salinity)){
//            Toast.makeText(this, "请填写盐分，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(conductivity)){
//            Toast.makeText(this, "请填写电导率，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(michler_ketone)){
//            Toast.makeText(this, "请填写米氏酮，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(solid_content)){
//            Toast.makeText(this, "请填写固含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        params.put("mer_cu", mer_cu);//铜离子
//        params.put("mer_zn",mer_zn);//锌离子
//        params.put("mer_ni", mer_ni);//镍离子
//        params.put("mer_sb", mer_sb);//锑离子
//        params.put("mer_cd",mer_cd );//镉离子
//        params.put("mer_pb", mer_pb);//铅离子
//        params.put("mer_sn", mer_sn);//锡离子
//        params.put("mer_co", mer_co);//钴离子
//        params.put("mer_hg", mer_hg);//汞离子
//        params.put("mer_bi", mer_bi);//铋离子

        if (TextUtils.isEmpty(mer_cu)) {
            Toast.makeText(this, "Please fill in goods metal content", Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "请填写铜离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(mer_zn)){
//            Toast.makeText(this, "请填写锌离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_ni)){
//            Toast.makeText(this, "请填写镍离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_sb)){
//            Toast.makeText(this, "请填写锑离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_cd)){
//            Toast.makeText(this, "请填写镉离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_pb)){
//            Toast.makeText(this, "请填写铅含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_co)){
//            Toast.makeText(this, "请填写钴离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_sn)){
//            Toast.makeText(this, "请填写锡离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_hg)){
//            Toast.makeText(this, "请填写汞离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mer_bi)){
//            Toast.makeText(this, "请填写铋离子含量，若没有请填无", Toast.LENGTH_SHORT).show();
//            return;
//        }

        if (TextUtils.isEmpty(uvDataUp)) {
            Toast.makeText(this, "Please fill in Examining report", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(ceseyiDataUp)){
//            Toast.makeText(this, "请上传测色仪数据", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(yanPinUp)){
//            Toast.makeText(this, "请上传样品图片数据", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(jiancebaogaoUp)){
//            Toast.makeText(this, "请上传监测报告", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(jianceshipUp)){
//            Toast.makeText(this, "请上传检测视频", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(daHuoTuPianUp)){
//            Toast.makeText(this, "请上传大货图片", Toast.LENGTH_SHORT).show();
//            return;
//        }

        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("goods_type", "others_normal");
        params.put("goods_name", name);
        params.put("goods_name_en", name);
        params.put("one_type", one_type);
        params.put("two_type", two_type);
        params.put("cino", cino);
        params.put("cas", cas);
        params.put("molecular_weight", molecular_weight);
        params.put("pas", pas);
        params.put("precise_quality", precise_quality);
        params.put("specification", guige);
        params.put("goods_purity", chudu);
        params.put("goods_deliver", huoqi);
        params.put("goods_num", shuliang);
        params.put("goods_unit", goods_unit);
        params.put("package_opt", baozhuang);
        params.put("current_price", zuidingjiage);
        params.put("market_price", zuidingjiage);
        params.put("payment_opt", fukuangfangshi);
        params.put("transport_opt", yunshufangshi);
        params.put("application_scheme", yingyongfangan);//应用方案
        params.put("product_picture", chanpintupian);
        params.put("production_state", shengchanzhuangtai);

        params.put("color_power", intensity);//强度
        params.put("color_light", coloured_light);//色光
        params.put("quality_aspect", appearance);//外观
        params.put("quality_moisture", moisture);//水分
        params.put("quality_insoluble", insoluble_substance);//不溶物
        params.put("quality_solubility", solubility);//溶解度
        params.put("quality_clContent", chloride_content);//氯离子含量
        params.put("quality_solidContent", solid_content);//固含量
        params.put("quality_salinity", salinity);//盐分
        params.put("quality_conductivity", conductivity);//电导率
        params.put("quality_michlerKetone", michler_ketone);//米氏酮

        params.put("metal_cu", mer_cu);//铜离子
        params.put("metal_zn", mer_zn);//锌离子
        params.put("metal_ni", mer_ni);//镍离子
        params.put("metal_sb", mer_sb);//锑离子
        params.put("metal_cd", mer_cd);//镉离子
        params.put("metal_pb", mer_pb);//铅离子
        params.put("metal_sn", mer_sn);//锡离子
        params.put("metal_co", mer_co);//钴离子
        params.put("metal_hg", mer_hg);//汞离子
        params.put("metal_bi", mer_bi);//铋离子

        params.put("detect_uv", uvDataUp);
        params.put("detect_colourimeter", ceseyiDataUp);
        params.put("detect_sample_imgs", yanPinUp);
        params.put("detect_report", jiancebaogaoUp);
        params.put("detect_video", jianceshipUp);
        params.put("detect_bulk_imgs", daHuoTuPianUp);


        OkHttpUtils.post()
                .url(NetConfig.ADD_GOODS)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(TianJiaShangPingActivity.java:384)<---->" + e.getMessage());
                        Toast.makeText(TianJiaShangPingActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(TianJiaShangPingActivity.java:437)<---->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    finish();
                                }
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(TianJiaShangPingActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(TianJiaShangPingActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 111 && resultCode == 1111) {
                Bundle extras = data.getExtras();
                name = extras.getString("goods_name");
                if (TextUtils.isEmpty(name)) {
                    etName.setText("");
                } else {
                    etName.setText(name);
                }
//                //商品分类一级分类
//                //商品分类二级分类
//                //货物纯度
//                //货物规格
//                //货物数量
//                //货物单位
//                //货物包装
//                bundle.putString("cargo_package", baozhuang);
//                //货物运输方式
//                bundle.putString("transport_way", yunshufangshi);
//                //货物最低价格
//                bundle.putString("current_price", zuidijiage);
//                //货物货期
//                bundle.putString("cargo_huoqi", huoqi);
//                //货物付款要求
//                bundle.putString("payment_way", fukuangyaoqiul);
//                //货物应用方案
//                bundle.putString("application_scheme", yingyongfanan);
//                //货物产品图案
//                bundle.putString("product_picture", chanpintupian);
//                //生产状态
//                bundle.putString("production_state", shengchanzhuangtai);
//                bundle.putString("cino", needCINO);
//                bundle.putString("cas", needCAS);
//                bundle.putString("molecular_weight", needFZL);
//                bundle.putString("pas", needPSA);
//                bundle.putString("precise_quality", needJQZL);
                guige = extras.getString("cargo_specification");
                one_type = extras.getString("one_type");
                two_type = extras.getString("two_type");
                cino = extras.getString("cino");
                cas = extras.getString("cas");
                molecular_weight = extras.getString("molecular_weight");
                pas = extras.getString("pas");
                precise_quality = extras.getString("precise_quality");
                chudu = extras.getString("cargo_purity");
                shuliang = extras.getString("cargo_num");
                goods_unit = extras.getString("goods_unit");
                baozhuang = extras.getString("cargo_package");
                yunshufangshi = extras.getString("transport_way");
                zuidingjiage = extras.getString("current_price");
                huoqi = extras.getString("cargo_huoqi");
                fukuangfangshi = extras.getString("payment_way");
                yingyongfangan = extras.getString("application_scheme");
                chanpintupian = extras.getString("product_picture");
                shengchanzhuangtai = extras.getString("production_state");


                addGoodsBaseInfo = new AddGoodsBaseInfo(name, guige, one_type, two_type, cino, cas, molecular_weight, pas,
                        precise_quality, chudu, shuliang, goods_unit, baozhuang, yunshufangshi, zuidingjiage,
                        huoqi, fukuangfangshi, yingyongfangan, chanpintupian, shengchanzhuangtai);

            } else if (requestCode == 222 && resultCode == 2222) {

                quality = data.getExtras();
                //强度
                intensity = quality.getString("intensity");
                //色光
                coloured_light = quality.getString("coloured_light");
                //外观
                appearance = quality.getString("appearance");
                //水分
                moisture = quality.getString("moisture");
                //不溶物
                insoluble_substance = quality.getString("insoluble_substance");
                //溶解度
                solubility = quality.getString("solubility");
                //氯离子含量
                chloride_content = quality.getString("chloride_content");
                //固含量
                solid_content = quality.getString("solid_content");
                //盐分
                salinity = quality.getString("salinity");
                //电导率
                conductivity = quality.getString("conductivity");
                //米氏酮
                michler_ketone = quality.getString("michler_ketone");

                qualityDetail = new QualityDetail(intensity, coloured_light, appearance, moisture, insoluble_substance, solubility,
                        chloride_content, solid_content, salinity, conductivity, michler_ketone);
            } else if (resultCode == 3333 && requestCode == 333) {
                Bundle bundle = data.getExtras();
                //铜离子
                mer_cu = bundle.getString("mer_cu");
                //锌离子
                mer_zn = bundle.getString("mer_zn");
                //镍离子
                mer_ni = bundle.getString("mer_ni");
                //锑离子
                mer_sb = bundle.getString("mer_sb");
                //镉离子
                mer_cd = bundle.getString("mer_cd");
                //铅离子
                mer_pb = bundle.getString("mer_pb");
                //锡离子
                mer_sn = bundle.getString("mer_sn");
                //钴离子
                mer_co = bundle.getString("mer_co");
                //汞离子
                mer_hg = bundle.getString("mer_hg");
                //铋离子
                mer_bi = bundle.getString("mer_bi");

                metal = new Metal(mer_cu, mer_zn, mer_ni, mer_sb, mer_cd, mer_pb, mer_sn, mer_co, mer_hg, mer_bi);
            } else if (requestCode == 444 && resultCode == 4444) {

                Bundle bundle = data.getExtras();
                uvDataUp = bundle.getString("uv_data");
                ceseyiDataUp = bundle.getString("colourimeter_data");
                yanPinUp = bundle.getString("sample_images");
                jiancebaogaoUp = bundle.getString("detect_report");
                jianceshipUp = bundle.getString("detect_video");
                daHuoTuPianUp = bundle.getString("big_photos");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
