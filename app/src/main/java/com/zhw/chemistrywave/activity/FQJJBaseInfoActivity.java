package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.BaseInfomation;
import com.zhw.chemistrywave.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FQJJBaseInfoActivity extends BaseActivity {


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
    @BindView(R.id.et_need_name)
    EditText etNeedName;
    @BindView(R.id.et_need_guige)
    EditText etNeedGuige;
    @BindView(R.id.et_need_chundu)
    EditText etNeedChundu;
    @BindView(R.id.et_need_timequest)
    EditText etNeedTimequest;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.et_need_numquest)
    EditText etNeedNumquest;
    @BindView(R.id.tv_danwei)
    TextView tvDanwei;
    @BindView(R.id.et_need_packagerequest)
    EditText etNeedPackagerequest;
    @BindView(R.id.et_need_transportrequest)
    EditText etNeedTransportrequest;
    @BindView(R.id.et_need_money)
    EditText etNeedMoney;
    @BindView(R.id.ll_need_fkyq)
    LinearLayout llNeedFkyq;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.ll_need_fkyqjt)
    LinearLayout llNeedFkyqjt;
    @BindView(R.id.tv_fqjjbaseinfo_save)
    TextView tvFqjjbaseinfoSave;
    private String user_id;
    private String payment_way;
    private String cargo_name;
//    private String spon_id;
    private String cargo_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fqjjbase_info);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Goods base information");
        user_id = MyUtils.getUser().getUser_id();


        if (null!=getIntent().getSerializableExtra("baseinfo")){
            BaseInfomation baseinfo = (BaseInfomation) getIntent().getSerializableExtra("baseinfo");
//            cargo_name = etNeedName.getText().toString();
//            String cargo_purity = etNeedChundu.getText().toString();
//            String delivery_time = etNeedTimequest.getText().toString();
//            final String cargo_num = etNeedNumquest.getText().toString();
//            String cargo_package = etNeedPackagerequest.getText().toString();
//            String transport_way = etNeedTransportrequest.getText().toString();
//            String ceiling_price = etNeedMoney.getText().toString();
//            String specifications = etNeedGuige.getText().toString().trim();

            etNeedName.setText(baseinfo.getCargo_name());
            etNeedTimequest.setText(baseinfo.getDelivery_time());
            etNeedChundu.setText(baseinfo.getCargo_purity());
            etNeedNumquest.setText(baseinfo.getCargo_num());
            etNeedPackagerequest.setText(baseinfo.getCargo_package());
            etNeedTransportrequest.setText(baseinfo.getTransport_way());
            etNeedMoney.setText(baseinfo.getCeiling_price());
            etNeedGuige.setText(baseinfo.getSpecifications());
            String payment_way = baseinfo.getPayment_way();
            switch (payment_way){
                case "offline":
                    rb1.setChecked(true);
                    break;
                case "alipay":
                    rb2.setChecked(true);
                    break;
                case "wechat":
                    rb3.setChecked(true);
                    break;
                case "unionpay":
                    rb4.setChecked(true);
                    break;
            }
        }

    }


    @OnClick({R.id.rl_titlebar_back, R.id.tv_fqjjbaseinfo_save, R.id.rb1, R.id.rb2, R.id.rb3, R.id.rb4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.tv_fqjjbaseinfo_save:
                saveSDJJBaseInfo();
                break;
            case R.id.rb1:
                payment_way = "offline";
                break;
            case R.id.rb2:
                payment_way = "alipay";
                break;
            case R.id.rb3:
                payment_way = "wechat";
                break;
            case R.id.rb4:
                payment_way = "unionpay";
                break;
        }
    }

    private void saveSDJJBaseInfo() {
        cargo_name = etNeedName.getText().toString();
        String cargo_purity = etNeedChundu.getText().toString();
        String delivery_time = etNeedTimequest.getText().toString();
        final String cargo_num = etNeedNumquest.getText().toString();
        String cargo_package = etNeedPackagerequest.getText().toString();
        String transport_way = etNeedTransportrequest.getText().toString();
        String ceiling_price = etNeedMoney.getText().toString();
        String specifications = etNeedGuige.getText().toString().trim();
        if (TextUtils.isEmpty(cargo_name) || TextUtils.isEmpty(cargo_purity) || TextUtils.isEmpty(delivery_time) ||
                TextUtils.isEmpty(cargo_num) || TextUtils.isEmpty(cargo_package) || TextUtils.isEmpty(transport_way) ||
                TextUtils.isEmpty(ceiling_price) || TextUtils.isEmpty(payment_way) || TextUtils.isEmpty(specifications)) {
            Toast.makeText(this, "Please fill in the complete information", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("cargo_name", cargo_name);//货物名称
        bundle.putString("cargo_purity", cargo_purity);//货物纯度
        bundle.putString("delivery_time", delivery_time);//货期要求
        bundle.putString("cargo_num", cargo_num);//数量要求
        bundle.putString("cargo_package", cargo_package);//包装方式
        bundle.putString("transport_way", transport_way);//运输方式
        bundle.putString("ceiling_price", ceiling_price);//封顶价格
        bundle.putString("payment_way", payment_way);//付款方式
        bundle.putString("specifications", specifications);//货物规格

        setResult(11,new Intent().putExtras(bundle));
        finish();

    }
}
