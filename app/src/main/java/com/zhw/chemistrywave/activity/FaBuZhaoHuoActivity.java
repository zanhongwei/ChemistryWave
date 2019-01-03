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
import com.zhw.chemistrywave.bean.BaseInfomation;
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

public class FaBuZhaoHuoActivity extends BaseActivity {


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
    TextView etName;
    @BindView(R.id.ll_qualitydetail)
    LinearLayout llQualitydetail;
    @BindView(R.id.ll_metalpercent)
    LinearLayout llMetalpercent;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.tv_state)
    EditText tvState;
    @BindView(R.id.tv_fabuzhaohuo_publish)
    TextView tvFabuzhaohuoPublish;
    private String spon_id;
    private String cargo_id;
    private String name;
    private String goods_name_en;
    private String goods_name;
    private String goods_purity;
    private String goods_deliver;
    private String goods_num;
    private String package_opt;
    private String transport_opt;
    private String current_price;
    private String payment_opt;
    private String specification;
    private String color_power;
    private String color_light;
    private String quality_aspect;
    private String quality_moisture;
    private String quality_insoluble;
    private String quality_solubility;
    private String quality_clContent;
    private String quality_solidContent;
    private String quality_salinity;
    private String quality_conductivity;
    private String quality_michlerKetone;
    private String metal_cu;
    private String metal_zn;
    private String metal_ni;
    private String metal_sb;
    private String metal_cd;
    private String metal_pb;
    private String metal_sn;
    private String metal_co;
    private String metal_hg;
    private String metal_bi;
    private BaseInfomation baseInfomation;
    private QualityDetail qualityDetail;
    private Metal metal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_zhao_huo);
        ButterKnife.bind(this);
//        spon_id = getIntent().getStringExtra("spon_id");
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("Publish");
        tvTitlebarRight.setVisibility(View.GONE);
        tvTitlebarRight.setText("save to draft");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_baseinfo, R.id.ll_qualitydetail, R.id.ll_metalpercent, R.id.ll_other, R.id.tv_fabuzhaohuo_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //货物基本信息
            case R.id.ll_baseinfo:

                Intent intent = new Intent(FaBuZhaoHuoActivity.this, FQJJBaseInfoActivity.class);
                if (baseInfomation!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("baseinfo",baseInfomation);
                    intent.putExtras(bundle);
                }
                startActivityForResult(intent, 1);
                break;
            //品质详情
            case R.id.ll_qualitydetail:
                Intent intent1 = new Intent(FaBuZhaoHuoActivity.this, QualityDetailActivity.class);
                if (qualityDetail!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("quality",qualityDetail);
                    intent1.putExtras(bundle);
                }
                startActivityForResult(intent1,2);
                break;
            //金属含量
            case R.id.ll_metalpercent:
                Intent intent2 = new Intent(FaBuZhaoHuoActivity.this, JinShuHanLiangActivity.class);
                if (metal!=null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("metal",metal);
                    intent2.putExtras(bundle);
                }
                startActivityForResult(intent2,3);
                break;
            case R.id.ll_other:
                break;
            case R.id.tv_fabuzhaohuo_publish:
                findGoods();
                break;
        }
    }

    private void findGoods() {
        
        if (TextUtils.isEmpty(goods_name_en)){
            Toast.makeText(this, "Please fill in the complete information", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("goods_name_en",goods_name_en);//
        params.put("goods_name",goods_name);//
        params.put("goods_purity",goods_purity);//
        params.put("goods_deliver",goods_deliver);//
        params.put("goods_num",goods_num);//
        params.put("package_opt",package_opt);//
        params.put("current_price",current_price);//
        params.put("payment_opt",payment_opt);//
        params.put("specification",specification);//
        params.put("color_power",color_power);//
        params.put("color_light",color_light);//
        params.put("quality_aspect",quality_aspect);//
        params.put("quality_moisture",quality_moisture);//
        params.put("quality_insoluble",quality_insoluble);//
        params.put("quality_solubility",quality_solubility);//
        params.put("quality_clContent",quality_clContent);//
        params.put("quality_solidContent",quality_solidContent);//
        params.put("quality_salinity",quality_salinity);//
        params.put("quality_conductivity",quality_conductivity);//
        params.put("quality_michlerKetone",quality_michlerKetone);//
        params.put("metal_cu",metal_cu);//
        params.put("metal_zn",metal_zn);//
        params.put("metal_ni",metal_ni);//
        params.put("metal_sb",metal_sb);//
        params.put("metal_cd",metal_cd);//
        params.put("metal_pb",metal_pb);//
        params.put("metal_sn",metal_sn);//
        params.put("metal_co",metal_co);//
        params.put("metal_hg",metal_hg);//
        params.put("metal_bi",metal_bi);//
        params.put("goods_type","enquiry");//
        params.put("user_id", MyUtils.getUser().getUser_id());//

        OkHttpUtils.post()
                .url(NetConfig.FIND)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(FaBuZhaoHuoActivity.java:186)<--系统询盘接口返回  error-->"+e.getMessage());
                        Toast.makeText(FaBuZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(FaBuZhaoHuoActivity.java:192)<--系统询盘接口返回  success-->"+response);

                        if (TextUtils.isEmpty(response)||"null".equals(response)){
                            Toast.makeText(FaBuZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    finish();
                                }
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(FaBuZhaoHuoActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && resultCode == 11) {
                Bundle bundle = data.getExtras();
                //货名英文
                goods_name_en = bundle.getString("cargo_name");
                //货名英文
                goods_name = bundle.getString("cargo_name");
                //纯度
                goods_purity = bundle.getString("cargo_purity");
                //货期
                goods_deliver = bundle.getString("delivery_time");
                //数量
                goods_num = bundle.getString("cargo_num");
                //包装方式
                package_opt = bundle.getString("cargo_package");
                //运输要求
                transport_opt = bundle.getString("transport_way");
                current_price = bundle.getString("ceiling_price");
                payment_opt = bundle.getString("payment_way");
                specification = bundle.getString("specifications");

                etName.setText(goods_name_en);
                baseInfomation = new BaseInfomation(goods_name_en, goods_purity, goods_deliver, goods_num
                        , package_opt, transport_opt, current_price, payment_opt, specification);

            }else if (requestCode ==2 && resultCode ==22){
                Bundle bundle = data.getExtras();
                //强度
                color_power = bundle.getString("intensity");
                //色光
                color_light = bundle.getString("coloured_light");
                //外观
                quality_aspect = bundle.getString("appearance");
                //水分
                quality_moisture = bundle.getString("moisture");
                //不溶物
                quality_insoluble = bundle.getString("insoluble_substance");
                //溶解度
                quality_solubility = bundle.getString("solubility");
                //氯离子含量
                quality_clContent = bundle.getString("chloride_content");
                //固含量
                quality_solidContent = bundle.getString("solid_content");
                //盐分
                quality_salinity = bundle.getString("salinity");
                //电导率
                quality_conductivity = bundle.getString("conductivity");
                //米氏酮
                quality_michlerKetone = bundle.getString("michler_ketone");

                qualityDetail = new QualityDetail(color_power,color_light,quality_aspect,quality_moisture,quality_insoluble,
                        quality_solubility,quality_clContent,quality_solidContent,quality_salinity,quality_conductivity,quality_michlerKetone);
            }else if (requestCode==3&&resultCode==33){
                Bundle bundle = data.getExtras();
                //铜离子
                metal_cu = bundle.getString("Cu");
                //锌离子
                metal_zn = bundle.getString("Zn");
                //镍离子
                metal_ni = bundle.getString("Ni");
                //锑离子
                metal_sb = bundle.getString("Sb");
                //镉离子
                metal_cd = bundle.getString("Cd");
                //铅离子
                metal_pb = bundle.getString("Pb");
                //锡离子
                metal_sn = bundle.getString("Sn");
                //钴离子
                metal_co = bundle.getString("Co");
                //汞离子
                metal_hg = bundle.getString("Hg");
                //铋离子
                metal_bi = bundle.getString("Bi");

                metal = new Metal(metal_cu, metal_zn, metal_ni, metal_sb, metal_cd, metal_pb, metal_sn, metal_co, metal_hg, metal_bi);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        deleteGoods();

    }

    /**
     * 保存此商品
     */
    private void saveGoods() {
        OkHttpUtils.post().url(NetConfig.fabucaigou)
                .addParams("spon_id", spon_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---保存此商品返回--error---->" + e.getMessage());
                        Toast.makeText(FaBuZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---保存此商品返回------>" + response);
                        if (!TextUtils.isEmpty(response)) {
                            JSONObject jo1 = null;
                            try {
                                jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    Toast.makeText(FaBuZhaoHuoActivity.this, "Publish successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(FaBuZhaoHuoActivity.this, "Publish unsuccessfully", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(FaBuZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
