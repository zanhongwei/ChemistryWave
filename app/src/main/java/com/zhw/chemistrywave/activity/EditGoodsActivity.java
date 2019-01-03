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

public class EditGoodsActivity extends BaseActivity {


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
    @BindView(R.id.tv_editgoods_save)
    TextView tvEditgoodsSave;
    private String mer_id;
    private String name;
    private Bundle baseInfo;
    private Bundle qualitity;
    private Bundle metal;
    private Bundle baogao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goods);
        ButterKnife.bind(this);
        mer_id = getIntent().getStringExtra("mer_id");
        name = getIntent().getStringExtra("name");
        etName.setText(name + "");
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("Edit Goods");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_baseinfo, R.id.et_name, R.id.ll_qualitydetail, R.id.ll_metalpercent, R.id.ll_jiancebaogao, R.id.ll_zhekou, R.id.tv_editgoods_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //货物基本信息
            case R.id.ll_baseinfo:
                Intent intent = new Intent(EditGoodsActivity.this, AddGoodsBaseInfoActivity.class);
                intent.putExtra("mer_id", mer_id);
                intent.putExtra("type", "EditGoodsActivity");
                startActivityForResult(intent, 1);
                break;

            //品质详情
            case R.id.ll_qualitydetail:
                startActivityForResult(new Intent(EditGoodsActivity.this, AddQualityDetailActivity.class)
                        .putExtra("mer_id", mer_id).putExtra("type", "EditGoodsActivity"),2);
                break;
            //金属含量
            case R.id.ll_metalpercent:
                startActivityForResult(new Intent(EditGoodsActivity.this, AddJinShuHanLiangActivity.class)
                        .putExtra("mer_id", mer_id).putExtra("type", "EditGoodsActivity")
                ,3);
                break;
            //检测报告
            case R.id.ll_jiancebaogao:
                startActivityForResult(new Intent(EditGoodsActivity.this, AddJianCeBaoGaoActivity.class)
                        .putExtra("mer_id", mer_id).putExtra("type", "EditGoodsActivity"),4);
                break;
            //折扣
            case R.id.ll_zhekou:
                startActivity(new Intent(EditGoodsActivity.this, DisCountActivity.class)
                        .putExtra("mer_id", mer_id).putExtra("type", "EditGoodsActivity"));
                break;
            //保存
            case R.id.tv_editgoods_save:
                save();
                break;
        }
    }

    private void save() {

        HashMap<String, String> params = new HashMap<>();

        if (null!=baseInfo){

            //货物名称
            String goods_name = baseInfo.getString("goods_name");
            String goods_name_en = baseInfo.getString("goods_name");
            //商品分类一级分类
            String one_type = baseInfo.getString("one_type");
            //商品分类二级分类
            String two_type = baseInfo.getString("two_type");
            //货物纯度
            String goods_purity = baseInfo.getString("cargo_purity");
            //货物规格
            String specification = baseInfo.getString("cargo_specification");
            //货物数量
            String goods_num = baseInfo.getString("cargo_num");
            //货物单位
            String goods_unit = baseInfo.getString("goods_unit");
            //货物包装
            String package_opt = baseInfo.getString("cargo_package");
            //货物运输方式
            String transport_opt = baseInfo.getString("transport_way");
            //货物最低价格
            String current_price = baseInfo.getString("current_price");
            String market_price = baseInfo.getString("current_price");
            //货物货期
            String goods_deliver = baseInfo.getString("cargo_huoqi");
            //货物付款要求
            String payment_opt = baseInfo.getString("payment_way");
            //货物应用方案
            String application_scheme = baseInfo.getString("application_scheme");
            //货物产品图案
            String product_picture = baseInfo.getString("product_picture");
            //生产状态
            String production_state = baseInfo.getString("production_state");
            String cino = baseInfo.getString("cino");
            String cas = baseInfo.getString("cas");
            String molecular_weight = baseInfo.getString("molecular_weight");
            String pas = baseInfo.getString("pas");
            String precise_quality = baseInfo.getString("precise_quality");

            params.put("goods_name", goods_name);
            params.put("goods_name_en", goods_name_en);
            params.put("one_type", one_type);
            params.put("two_type", two_type);
            params.put("cino", cino);
            params.put("cas", cas);
            params.put("molecular_weight", molecular_weight);
            params.put("pas", pas);
            params.put("precise_quality", precise_quality);
            params.put("specification", specification);
            params.put("goods_purity", goods_purity);
            params.put("goods_deliver", goods_deliver);
            params.put("goods_num", goods_num);
            params.put("goods_unit", goods_unit);
            params.put("package_opt", package_opt);
            params.put("current_price", current_price);
            params.put("market_price", market_price);
            params.put("payment_opt", payment_opt);
            params.put("transport_opt", transport_opt);
            params.put("application_scheme", application_scheme);//应用方案
            params.put("product_picture", product_picture);
            params.put("production_state", production_state);
        }else {

        }

        if (qualitity!=null){

            String color_power = qualitity.getString("intensity");
            String color_light = qualitity.getString("coloured_light");
            String quality_aspect = qualitity.getString("appearance");
            String quality_moisture = qualitity.getString("moisture");
            String quality_insoluble = qualitity.getString("insoluble_substance");
            String quality_solubility = qualitity.getString("solubility");
            String quality_clContent = qualitity.getString("chloride_content");
            String quality_solidContent = qualitity.getString("solid_content");
            String quality_salinity = qualitity.getString("salinity");
            String quality_conductivity = qualitity.getString("conductivity");
            String quality_michlerKetone = qualitity.getString("michler_ketone");

            params.put("color_power", color_power);//强度
            params.put("color_light", color_light);//色光
            params.put("quality_aspect", quality_aspect);//外观
            params.put("quality_moisture", quality_moisture);//水分
            params.put("quality_insoluble", quality_insoluble);//不溶物
            params.put("quality_solubility", quality_solubility);//溶解度
            params.put("quality_clContent", quality_clContent);//氯离子含量
            params.put("quality_solidContent", quality_solidContent);//固含量
            params.put("quality_salinity", quality_salinity);//盐分
            params.put("quality_conductivity", quality_conductivity);//电导率
            params.put("quality_michlerKetone", quality_michlerKetone);//米氏酮
        }else {

        }

        if (metal!=null){
            String metal_cu = metal.getString("mer_cu");
            String metal_zn = metal.getString("mer_zn");
            String metal_ni = metal.getString("mer_ni");
            String metal_sb = metal.getString("mer_sb");
            String metal_cd = metal.getString("mer_cd");
            String metal_pb = metal.getString("mer_pb");
            String metal_sn = metal.getString("mer_sn");
            String metal_co = metal.getString("mer_co");
            String metal_hg = metal.getString("mer_hg");
            String metal_bi = metal.getString("mer_bi");

            params.put("metal_cu", metal_cu);//铜离子
            params.put("metal_zn", metal_zn);//锌离子
            params.put("metal_ni", metal_ni);//镍离子
            params.put("metal_sb", metal_sb);//锑离子
            params.put("metal_cd", metal_cd);//镉离子
            params.put("metal_pb", metal_pb);//铅离子
            params.put("metal_sn", metal_sn);//锡离子
            params.put("metal_co", metal_co);//钴离子
            params.put("metal_hg", metal_hg);//汞离子
            params.put("metal_bi", metal_bi);//铋离子
        }else {

        }

        if (baogao!=null){

            String detect_uv = baogao.getString("uv_data");
            String detect_colourimeter = baogao.getString("colourimeter_data");
            String detect_sample_imgs = baogao.getString("sample_images");
            String detect_report = baogao.getString("detect_report");
            String detect_video = baogao.getString("detect_video");
            String detect_bulk_imgs = baogao.getString("big_photos");

            params.put("detect_uv", detect_uv);
            params.put("detect_colourimeter", detect_colourimeter);
            params.put("detect_sample_imgs", detect_sample_imgs);
            params.put("detect_report", detect_report);
            params.put("detect_video", detect_video);
            params.put("detect_bulk_imgs", detect_bulk_imgs);
        }else {

        }
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("goods_type", "others_normal");
        params.put("goods_id",mer_id);
//        params.put("goods_name", name);
//        params.put("goods_name_en", name);
//        params.put("one_type", one_type);
//        params.put("two_type", two_type);
//        params.put("cino", cino);
//        params.put("cas", cas);
//        params.put("molecular_weight", molecular_weight);
//        params.put("pas", pas);
//        params.put("precise_quality", precise_quality);
//        params.put("specification", guige);
//        params.put("goods_purity", chudu);
//        params.put("goods_deliver", huoqi);
//        params.put("goods_num", shuliang);
//        params.put("goods_unit", goods_unit);
//        params.put("package_opt", baozhuang);
//        params.put("current_price", zuidingjiage);
//        params.put("market_price", zuidingjiage);
//        params.put("payment_opt", fukuangfangshi);
//        params.put("transport_opt", yunshufangshi);
//        params.put("application_scheme", yingyongfangan);//应用方案
//        params.put("product_picture", chanpintupian);
//        params.put("production_state", shengchanzhuangtai);

        OkHttpUtils.post()
                .url(NetConfig.EDIT_GOODS)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(TianJiaShangPingActivity.java:384)<---->" + e.getMessage());
                        Toast.makeText(EditGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(EditGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(EditGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && resultCode ==1111) {
                baseInfo = data.getExtras();
                name = baseInfo.getString("goods_name");
                if (TextUtils.isEmpty(this.name)) {
                    etName.setText("");
                } else {
                    etName.setText(this.name);
                }
            }else if (requestCode==2&&resultCode==2222){
                qualitity = data.getExtras();
            }else if (requestCode==3&&resultCode==3333){
                metal = data.getExtras();
            }else if (requestCode==4&&resultCode==4444){
                baogao = data.getExtras();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
