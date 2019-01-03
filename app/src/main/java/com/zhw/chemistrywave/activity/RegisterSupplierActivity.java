package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterSupplierActivity extends BaseActivity {


    @BindView(R.id.rl_registersupplier_back)
    RelativeLayout rlRegistersupplierBack;
    @BindView(R.id.et_registersupplier_companyname)
    EditText etRegistersupplierCompanyname;
    @BindView(R.id.et_registersupplier_companyaddress)
    EditText etRegistersupplierCompanyaddress;
    @BindView(R.id.et_registersupplier_companyphone)
    EditText etRegistersupplierCompanyphone;
    @BindView(R.id.tv_registersupplier_haveaccount)
    TextView tvRegistersupplierHaveaccount;
    @BindView(R.id.tv_registersupplier_register)
    TextView tvRegistersupplierRegister;
    private String user_type;
    private String user_phone;
    private String user_code;
    private String user_password;
    private String company_name;
    private String company_address;
    private String company_landline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_supplier);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        user_type = intent.getStringExtra("user_type");
        user_phone = intent.getStringExtra("user_phone");
        user_code = intent.getStringExtra("user_code");
        user_password = intent.getStringExtra("user_password");
    }

    @OnClick({R.id.rl_registersupplier_back, R.id.tv_registersupplier_haveaccount, R.id.tv_registersupplier_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_registersupplier_back:
                onBackPressed();
                break;
            //已有账户
            case R.id.tv_registersupplier_haveaccount:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            //注册
            case R.id.tv_registersupplier_register:
                company_name = etRegistersupplierCompanyname.getText().toString();
                company_address = etRegistersupplierCompanyaddress.getText().toString();
                company_landline = etRegistersupplierCompanyphone.getText().toString();
                if (TextUtils.isEmpty(company_name)) {
                    Toast.makeText(this, "Please fill in company name", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(company_address)) {
                    Toast.makeText(this, "Please fill in company address", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(company_landline)) {
                    Toast.makeText(this, "Please fill in company phone", Toast.LENGTH_SHORT).show();
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("user_type", user_type);
                params.put("user_phone", user_phone);
                params.put("user_code", user_code);
                params.put("user_password", user_password);
                params.put("company_name", company_name);
                params.put("company_address", company_address);
                params.put("company_landline", company_landline);
                OkHttpUtils.post()
                        .url(NetConfig.register)
                        .params(params)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {

                                Log.e("aaa",
                                        "(RegisterSupplierActivity.java:93)" + response);
                                if (response.contains("success")) {
                                    Toast.makeText(RegisterSupplierActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterSupplierActivity.this, LoginActivity.class));
                                }

                            }
                        });

                break;
        }
    }
}
