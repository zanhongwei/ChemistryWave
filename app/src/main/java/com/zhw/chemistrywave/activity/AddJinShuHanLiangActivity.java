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
import com.zhw.chemistrywave.bean.Metal;
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

public class AddJinShuHanLiangActivity extends BaseActivity {


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
    @BindView(R.id.et_includejinshuhanliang_tong)
    EditText etIncludejinshuhanliangTong;
    @BindView(R.id.et_includejinshuhanliang_qian)
    EditText etIncludejinshuhanliangQian;
    @BindView(R.id.et_includejinshuhanliang_xin)
    EditText etIncludejinshuhanliangXin;
    @BindView(R.id.et_includejinshuhanliang_xi)
    EditText etIncludejinshuhanliangXi;
    @BindView(R.id.et_includejinshuhanliang_nie)
    EditText etIncludejinshuhanliangNie;
    @BindView(R.id.et_includejinshuhanliang_gu)
    EditText etIncludejinshuhanliangGu;
    @BindView(R.id.et_includejinshuhanliang_ti)
    EditText etIncludejinshuhanliangTi;
    @BindView(R.id.et_includejinshuhanliang_gun)
    EditText etIncludejinshuhanliangGun;
    @BindView(R.id.et_includejinshuhanliang_ge)
    EditText etIncludejinshuhanliangGe;
    @BindView(R.id.et_includejinshuhanliang_bi)
    EditText etIncludejinshuhanliangBi;
    @BindView(R.id.tv_jinshuhanliang_save)
    TextView tvJinshuhanliangSave;
    private String metal_id;
    private String mer_id;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_shu_han_liang);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Metal content");
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "EditGoodsActivity":
                mer_id = getIntent().getStringExtra("mer_id");
                getData();
                break;
            case "TianJiaShangPingActivity":

                break;
        }

        if (getIntent().getSerializableExtra("metal")!=null){
            Metal metal = (Metal) getIntent().getSerializableExtra("metal");
            etIncludejinshuhanliangTong.setText(metal.getMetal_cu());
            etIncludejinshuhanliangQian.setText(metal.getMetal_sb());
            etIncludejinshuhanliangXin.setText(metal.getMetal_zn());
            etIncludejinshuhanliangXi.setText(metal.getMetal_sn());
            etIncludejinshuhanliangNie.setText(metal.getMetal_ni());
            etIncludejinshuhanliangGu.setText(metal.getMetal_co());
            etIncludejinshuhanliangTi.setText(metal.getMetal_pb());
            etIncludejinshuhanliangGun.setText(metal.getMetal_hg());
            etIncludejinshuhanliangGe.setText(metal.getMetal_cd());
            etIncludejinshuhanliangBi.setText(metal.getMetal_bi());

        }
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_jinshuhanliang_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_jinshuhanliang_save:
                saveShiDanCaiGou();
                break;
        }
    }

    /**
     * 得到商品基本信息详情
     */
    private void getData() {
        Log.e("aaa", "--商品详情参数--mer_id-->" + mer_id);
        OkHttpUtils.post().url(NetConfig.goodsdetail_url)
                .addParams("goods_id", String.valueOf(mer_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--商品详情返回--error->" + e.getMessage());
                        Toast.makeText(AddJinShuHanLiangActivity.this, "network error", Toast.LENGTH_SHORT).show();
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

                                    etIncludejinshuhanliangTong.setText(shangpxiangqing.getData().getMetal_cu() != null ? shangpxiangqing.getData().getMetal_cu() : "");
                                    etIncludejinshuhanliangQian.setText(shangpxiangqing.getData().getMetal_pb() != null ? shangpxiangqing.getData().getMetal_pb() : "");
                                    etIncludejinshuhanliangXin.setText(shangpxiangqing.getData().getMetal_zn() != null ? shangpxiangqing.getData().getMetal_zn() : "");
                                    etIncludejinshuhanliangXi.setText(shangpxiangqing.getData().getMetal_sn() != null ? shangpxiangqing.getData().getMetal_sn() : "");
                                    etIncludejinshuhanliangNie.setText(shangpxiangqing.getData().getMetal_ni() != null ? shangpxiangqing.getData().getMetal_ni() : "");
                                    etIncludejinshuhanliangGu.setText(shangpxiangqing.getData().getMetal_co() != null ? shangpxiangqing.getData().getMetal_co() : "");
                                    etIncludejinshuhanliangTi.setText(shangpxiangqing.getData().getMetal_sb() != null ? shangpxiangqing.getData().getMetal_sb() : "");
                                    etIncludejinshuhanliangGun.setText(shangpxiangqing.getData().getMetal_hg() != null ? shangpxiangqing.getData().getMetal_hg() : "");
                                    etIncludejinshuhanliangGe.setText(shangpxiangqing.getData().getMetal_cd() != null ? shangpxiangqing.getData().getMetal_cd() : "");
                                    etIncludejinshuhanliangBi.setText(shangpxiangqing.getData().getMetal_bi() != null ? shangpxiangqing.getData().getMetal_bi() : "");

                                } else {
                                    Toast.makeText(AddJinShuHanLiangActivity.this, "network error", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddJinShuHanLiangActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveShiDanCaiGou() {
        String tong = etIncludejinshuhanliangTong.getText().toString().trim();
        String qian = etIncludejinshuhanliangQian.getText().toString().trim();
        String xin = etIncludejinshuhanliangXin.getText().toString().trim();
        String xi = etIncludejinshuhanliangXi.getText().toString().trim();
        String nie = etIncludejinshuhanliangNie.getText().toString().trim();
        String gu = etIncludejinshuhanliangGu.getText().toString().trim();
        String ti = etIncludejinshuhanliangTi.getText().toString().trim();
        String gun = etIncludejinshuhanliangGun.getText().toString().trim();
        String ge = etIncludejinshuhanliangGe.getText().toString().trim();
        String bi = etIncludejinshuhanliangBi.getText().toString().trim();
        if (TextUtils.isEmpty(tong)){
            Toast.makeText(this, "Please fill in cupric ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(qian)){
            Toast.makeText(this, "Please fill in lead ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(xin)){
            Toast.makeText(this, "Please fill in zinc ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(xi)){
            Toast.makeText(this, "Please fill in tine ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nie)){
            Toast.makeText(this, "Please fill in nickel ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gu)){
            Toast.makeText(this, "Please fill in cobalt ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ti)){
            Toast.makeText(this, "Please fill in Antimony ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(gun)){
            Toast.makeText(this, "Please fill in mercury ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ge)){
            Toast.makeText(this, "Please fill in cadmium ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bi)){
            Toast.makeText(this, "Please fill in bismuth ion or null", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("mer_cu", tong);//铜离子
        bundle.putString("mer_zn", xin);//锌离子
        bundle.putString("mer_ni", nie);//镍离子
        bundle.putString("mer_sb", ti);//锑离子
        bundle.putString("mer_cd", ge);//镉离子
        bundle.putString("mer_pb", qian);//铅离子
        bundle.putString("mer_sn", xi);//锡离子
        bundle.putString("mer_co", gu);//钴离子
        bundle.putString("mer_hg", gun);//汞离子
        bundle.putString("mer_bi", bi);//铋离子

        setResult(3333,new Intent().putExtras(bundle));
        finish();
    }


}
