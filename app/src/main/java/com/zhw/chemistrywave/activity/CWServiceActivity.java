package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class CWServiceActivity extends BaseActivity {

    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_title_s)
    TextView tvTitleS;
    @BindView(R.id.tv_title_text)
    TextView tvTitleText;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_company_email)
    EditText etCompanyEmail;
    @BindView(R.id.et_remark)
    EditText etRemark;
    private String titleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cwservice);
        ButterKnife.bind(this);

        titleName = getIntent().getStringExtra("name");
        tvTitlebarCenter.setText(titleName);
        tvTitleS.setText("About " + titleName);

        switch (titleName) {
            case "Harlan eye"://化浪之眼
                tvTitleText.setText(getResources().getString(R.string.app_name));
                break;
            case "Price express"://找代理
                tvTitleText.setText("");
                break;
            case "Platform guarantee"://平台监测
                tvTitleText.setText("");
                break;
            case "Supply chain finance"://投诉纠纷
                tvTitleText.setText("");
                break;
        }
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_commit:
//                Toast.makeText(this, "提交", Toast.LENGTH_SHORT).show();
                commitInfomation();
                break;
        }
    }

    private void commitInfomation() {

        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String telPhone = etTelephone.getText().toString().trim();
        String phone = etPhoneNumber.getText().toString().trim();
        String email = etCompanyEmail.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address) || TextUtils.isEmpty(telPhone)
                || TextUtils.isEmpty(phone) || TextUtils.isEmpty(email) || TextUtils.isEmpty(remark)) {
            Toast.makeText(this, "Please enter the complete information！", Toast.LENGTH_SHORT).show();
            return;
        }

        String five_type = "";

        switch (titleName) {
            case "Harlan eye"://化浪之眼
                five_type = "ser5";
                break;
            case "Price express"://找代理
                five_type = "ser1";
                break;
            case "Platform guarantee"://平台监测
                five_type = "ser2";
                break;
            case "Supply chain finance"://投诉纠纷
                five_type = "ser3";
                break;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("com_name", name);
        params.put("com_addr", address);
        params.put("contact", telPhone);//联系人
        params.put("contact_phone", phone);
        params.put("mailbox", email);
        params.put("postscript", remark);
        params.put("five_type", five_type);

        Log.e("aaa",
                "(CWServiceActivity.java:113)<--五大服务的参数-->" + params.toString());
        Log.e("aaa",
                "(CWServiceActivity.java:115)<--五大服务的url-->" + NetConfig.FIVE_SERVICE);
        OkHttpUtils.post()
                .url(NetConfig.FIVE_SERVICE)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(CWServiceActivity.java:119)<--五大服务提交失败返回-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(CWServiceActivity.java:126)<--五大服务提交成功返回-->" + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                Toast.makeText(CWServiceActivity.this, "Commit successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(CWServiceActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
