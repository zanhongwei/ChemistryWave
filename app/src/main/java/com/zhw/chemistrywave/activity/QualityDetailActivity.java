package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.QualityDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QualityDetailActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_detail);
        ButterKnife.bind(this);
        //-------------
        //-------------
        tvTitlebarCenter.setText("Quality detail");

        if (getIntent().getSerializableExtra("quality")!=null){
            QualityDetail quality = (QualityDetail) getIntent().getSerializableExtra("quality");
//
//            bundle.putString("intensity", qingdu);//强度
//            bundle.putString("coloured_light", seguan);//色光
//            bundle.putString("appearance", waiguan);//外观
//            bundle.putString("moisture", shuifen);//水分
//            bundle.putString("insoluble_substance", burongwu);//不溶物
//            bundle.putString("solubility", rongjiedu);//溶解度
//            bundle.putString("chloride_content", lvlizihanliang);//氯离子含量
//            bundle.putString("solid_content", guhanliang);//固含量
//            bundle.putString("salinity", yanfen);//盐分
//            bundle.putString("conductivity", diandaolv);//电导率
//            bundle.putString("michler_ketone", mishitong);//米氏酮
            etIncludequalitydetailQiangdu.setText(quality.getIntensity());
            etIncludequalitydetailSeguang.setText(quality.getColoured_light());
            etIncludequalitydetailWaiguan.setText(quality.getAppearance());
            etIncludequalitydetailShuifen.setText(quality.getMoisture());
            etIncludequalitydetailBurongwu.setText(quality.getInsoluble_substance());
            etIncludequalitydetailRongjiedu.setText(quality.getSolubility());
            etIncludequalitydetailLvlizihanliang.setText(quality.getChloride_content());
            etIncludequalitydetailYanfen.setText(quality.getChloride_content());
            etIncludequalitydetailDiandaolv.setText(quality.getSalinity());
            etIncludequalitydetailMishitong.setText(quality.getConductivity());
            etIncludequalitydetailGuhanliang.setText(quality.getMichler_ketone());


        }
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

        if (TextUtils.isEmpty(qingdu)||TextUtils.isEmpty(seguan)||TextUtils.isEmpty(waiguan)||TextUtils.isEmpty(shuifen)
                ||TextUtils.isEmpty(burongwu)||TextUtils.isEmpty(rongjiedu)||TextUtils.isEmpty(lvlizihanliang)
                ||TextUtils.isEmpty(yanfen) ||TextUtils.isEmpty(diandaolv)||TextUtils.isEmpty(mishitong)
                ||TextUtils.isEmpty(guhanliang)){
            Toast.makeText(this, "Please fill in the complete information", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (TextUtils.isEmpty(seguan)){
//            Toast.makeText(this, "色光为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(waiguan)){
//            Toast.makeText(this, "外观为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(shuifen)){
//            Toast.makeText(this, "水分为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(burongwu)){
//            Toast.makeText(this, "不溶物为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(rongjiedu)){
//            Toast.makeText(this, "溶解率为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(lvlizihanliang)){
//            Toast.makeText(this, "氯离子含量为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(yanfen)){
//            Toast.makeText(this, "盐分为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(diandaolv)){
//            Toast.makeText(this, "电导率为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(mishitong)){
//            Toast.makeText(this, "米氏酮为空", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(guhanliang)){
//            Toast.makeText(this, "固含量为空", Toast.LENGTH_SHORT).show();
//            return;
//        }

        Bundle bundle = new Bundle();
        bundle.putString("intensity", qingdu);//强度
        bundle.putString("coloured_light", seguan);//色光
        bundle.putString("appearance", waiguan);//外观
        bundle.putString("moisture", shuifen);//水分
        bundle.putString("insoluble_substance", burongwu);//不溶物
        bundle.putString("solubility", rongjiedu);//溶解度
        bundle.putString("chloride_content", lvlizihanliang);//氯离子含量
        bundle.putString("solid_content", guhanliang);//固含量
        bundle.putString("salinity", yanfen);//盐分
        bundle.putString("conductivity", diandaolv);//电导率
        bundle.putString("michler_ketone", mishitong);//米氏酮

        setResult(22,new Intent().putExtras(bundle));
        finish();
    }

}
