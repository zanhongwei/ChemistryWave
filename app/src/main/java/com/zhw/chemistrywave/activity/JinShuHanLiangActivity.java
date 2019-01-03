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
import com.zhw.chemistrywave.bean.Metal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JinShuHanLiangActivity extends BaseActivity {

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
    private String type;
    private String mBid_id;
    private String metal_id;
    private String mType;
    private String cargo_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jin_shu_han_liang);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Metal content");


        if (null!=getIntent().getSerializableExtra("metal")){

            Metal metal = (Metal) getIntent().getSerializableExtra("metal");
//
            etIncludejinshuhanliangTong.setText(metal.getMetal_cu());
            etIncludejinshuhanliangQian.setText(metal.getMetal_pb());
            etIncludejinshuhanliangXin.setText(metal.getMetal_zn());
            etIncludejinshuhanliangXi.setText(metal.getMetal_sn());
            etIncludejinshuhanliangNie.setText(metal.getMetal_ni());
            etIncludejinshuhanliangGu.setText(metal.getMetal_co());
            etIncludejinshuhanliangTi.setText(metal.getMetal_sb());
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

        if (TextUtils.isEmpty(tong)||TextUtils.isEmpty(qian)||TextUtils.isEmpty(xin)||TextUtils.isEmpty(xi)
                ||TextUtils.isEmpty(nie)||TextUtils.isEmpty(gu)||TextUtils.isEmpty(ti)||TextUtils.isEmpty(gun)
                ||TextUtils.isEmpty(ge)||TextUtils.isEmpty(bi)){
            Toast.makeText(this, "Please fill in the complete information", Toast.LENGTH_SHORT).show();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("Cu", tong);//铜离子
        bundle.putString("Zn", xin);//锌离子
        bundle.putString("Ni", nie);//镍离子
        bundle.putString("Sb", ti);//锑离子
        bundle.putString("Cd", ge);//镉离子
        bundle.putString("Pb", qian);//铅离子
        bundle.putString("Sn", xi);//锡离子
        bundle.putString("Co", gu);//钴离子
        bundle.putString("Hg", gun);//汞离子
        bundle.putString("Bi", bi);//铋离子
        bundle.putString("others", "");//铋离子

        setResult(33,new Intent().putExtras(bundle));
        finish();
    }

}
