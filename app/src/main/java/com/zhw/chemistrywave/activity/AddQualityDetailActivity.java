package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Shangpxiangqing;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddQualityDetailActivity extends BaseActivity {

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
    @BindView(R.id.et_includequalitydetail_qiangdu)
    EditText etIncludequalitydetailQiangdu;
    @BindView(R.id.et_includequalitydetail_seguang)
    EditText etIncludequalitydetailSeguang;
    @BindView(R.id.et_includequalitydetail_waiguan)
    EditText etIncludequalitydetailWaiguan;
    @BindView(R.id.et_includequalitydetail_shuifen)
    EditText etIncludequalitydetailShuifen;
    @BindView(R.id.et_includequalitydetail_burongwu)
    EditText etIncludequalitydetailBurongwu;
    @BindView(R.id.et_includequalitydetail_rongjiedu)
    EditText etIncludequalitydetailRongjiedu;
    @BindView(R.id.et_includequalitydetail_lvlizihanliang)
    EditText etIncludequalitydetailLvlizihanliang;
    @BindView(R.id.et_includequalitydetail_yanfen)
    EditText etIncludequalitydetailYanfen;
    @BindView(R.id.et_includequalitydetail_diandaolv)
    EditText etIncludequalitydetailDiandaolv;
    @BindView(R.id.et_includequalitydetail_mishitong)
    EditText etIncludequalitydetailMishitong;
    @BindView(R.id.et_includequalitydetail_guhanliang)
    EditText etIncludequalitydetailGuhanliang;
    @BindView(R.id.tv_qualitydetail_save)
    TextView tvQualitydetailSave;
    private String mer_id;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_detail);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Quality detail");

        type = getIntent().getStringExtra("type");
        switch (type) {
            case "EditGoodsActivity":
                mer_id = getIntent().getStringExtra("mer_id");
                getData();
                break;
            case "TianJiaShangPingActivity":

                break;
        }
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
                        Toast.makeText(AddQualityDetailActivity.this, "network error", Toast.LENGTH_SHORT).show();
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
                                    Shangpxiangqing shangpxiangqing = gson.fromJson(response, Shangpxiangqing.class);

                                    etIncludequalitydetailQiangdu.setText(shangpxiangqing.getData().getColor_power() != null ? shangpxiangqing.getData().getColor_power() : "");
                                    etIncludequalitydetailSeguang.setText(shangpxiangqing.getData().getColor_light() != null ? shangpxiangqing.getData().getColor_light() : "");
                                    etIncludequalitydetailWaiguan.setText(shangpxiangqing.getData().getSpecification() != null ? shangpxiangqing.getData().getSpecification() : "");
                                    etIncludequalitydetailShuifen.setText(shangpxiangqing.getData().getQuality_moisture() != null ? shangpxiangqing.getData().getQuality_moisture() : "");
                                    etIncludequalitydetailBurongwu.setText(shangpxiangqing.getData().getQuality_insoluble() != null ? shangpxiangqing.getData().getQuality_insoluble() : "");
                                    etIncludequalitydetailRongjiedu.setText(shangpxiangqing.getData().getQuality_solubility() != null ? shangpxiangqing.getData().getQuality_solubility() : "");
                                    etIncludequalitydetailLvlizihanliang.setText(shangpxiangqing.getData().getQuality_clContent() != null ? shangpxiangqing.getData().getQuality_clContent() : "");
                                    etIncludequalitydetailYanfen.setText(shangpxiangqing.getData().getQuality_salinity() != null ? shangpxiangqing.getData().getQuality_salinity() : "");
                                    etIncludequalitydetailDiandaolv.setText(shangpxiangqing.getData().getQuality_conductivity() != null ? shangpxiangqing.getData().getQuality_conductivity() : "");
                                    etIncludequalitydetailMishitong.setText(shangpxiangqing.getData().getQuality_michlerKetone() != null ? shangpxiangqing.getData().getQuality_michlerKetone() : "");
                                    etIncludequalitydetailGuhanliang.setText(shangpxiangqing.getData().getQuality_solidContent() != null ? shangpxiangqing.getData().getQuality_solidContent() : "");

                                } else {
                                    Toast.makeText(AddQualityDetailActivity.this, "network error", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddQualityDetailActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_qualitydetail_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_qualitydetail_save:
                saveShiDanCaiGou();
                break;
        }
    }

    /**
     *
     */
    private void saveShiDanCaiGou() {
        String qingdu = etIncludequalitydetailQiangdu.getText().toString().trim();
        String seguan = etIncludequalitydetailSeguang.getText().toString().trim();
        String waiguan = etIncludequalitydetailWaiguan.getText().toString().trim();
        String shuifen = etIncludequalitydetailShuifen.getText().toString().trim();
        String burongwu = etIncludequalitydetailBurongwu.getText().toString().trim();
        String rongjiedu = etIncludequalitydetailRongjiedu.getText().toString().trim();
        String lvlizihanliang = etIncludequalitydetailLvlizihanliang.getText().toString().trim();
        String yanfen = etIncludequalitydetailYanfen.getText().toString().trim();
        String diandaolv = etIncludequalitydetailDiandaolv.getText().toString().trim();
        String mishitong = etIncludequalitydetailMishitong.getText().toString().trim();
        String guhanliang = etIncludequalitydetailGuhanliang.getText().toString().trim();

        if (TextUtils.isEmpty(qingdu)){
            Toast.makeText(this, "Please fill in intension or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(seguan)){
            Toast.makeText(this, "Please fill in coloured light or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(waiguan)){
            Toast.makeText(this, "Please fill in appearance or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(shuifen)){
            Toast.makeText(this, "Please fill in moistures or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(burongwu)){
            Toast.makeText(this, "Please fill in insolubles or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rongjiedu)){
            Toast.makeText(this, "Please fill in solubility or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lvlizihanliang)){
            Toast.makeText(this, "Please fill in Cl- content or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(yanfen)){
            Toast.makeText(this, "Please fill in salinity or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(diandaolv)){
            Toast.makeText(this, "Please fill in conductivity or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mishitong)){
            Toast.makeText(this, "Please fill in michler's ketone or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(guhanliang)){
            Toast.makeText(this, "Please fill in solid content or null", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle map = new Bundle();
        map.putString("intensity", qingdu);//强度
        map.putString("coloured_light", seguan);//色光
        map.putString("appearance", waiguan);//外观
        map.putString("moisture", shuifen);//水分
        map.putString("insoluble_substance", burongwu);//不溶物
        map.putString("solubility", rongjiedu);//溶解度
        map.putString("chloride_content", lvlizihanliang);//氯离子含量
        map.putString("solid_content", guhanliang);//固含量
        map.putString("salinity", yanfen);//盐分
        map.putString("conductivity", diandaolv);//电导率
        map.putString("michler_ketone", mishitong);//米氏酮

        setResult(2222,new Intent().putExtras(map));
        finish();
    }

}
