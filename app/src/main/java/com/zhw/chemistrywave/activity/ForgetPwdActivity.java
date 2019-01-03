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
import com.zhw.chemistrywave.utils.CountDownTimerUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ForgetPwdActivity extends BaseActivity {


    @BindView(R.id.rl_forgetpwd_back)
    RelativeLayout rlForgetpwdBack;
    @BindView(R.id.et_forgetpwd_phone)
    EditText etForgetpwdPhone;
    @BindView(R.id.et_forgetpwd_yzcode)
    EditText etForgetpwdYzcode;
    @BindView(R.id.tv_forgetpwd_getcode)
    TextView tvForgetpwdGetcode;
    @BindView(R.id.et_forgetpwd_newpwd)
    EditText etForgetpwdNewpwd;
    @BindView(R.id.et_forgetpwd_surenewpwd)
    EditText etForgetpwdSurenewpwd;
    @BindView(R.id.tv_forgetpwd_login)
    TextView tvForgetpwdLogin;
    @BindView(R.id.tv_forgetpwd_register)
    TextView tvForgetpwdRegister;
    @BindView(R.id.tv_forgetpwd_resetpwd)
    TextView tvForgetpwdResetpwd;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        countDownTimerUtils = new CountDownTimerUtils(tvForgetpwdGetcode, 59000, 1000);
    }

    @OnClick({R.id.rl_forgetpwd_back, R.id.tv_forgetpwd_getcode, R.id.tv_forgetpwd_login, R.id.tv_forgetpwd_register, R.id.tv_forgetpwd_resetpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_forgetpwd_back:
                onBackPressed();
                break;
            //获取验证码
            case R.id.tv_forgetpwd_getcode:
                String user_phone = etForgetpwdPhone.getText().toString().trim();
                if (TextUtils.isEmpty(user_phone)) {
                    Toast.makeText(this, "Mobile number is null", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post()
                        .url(NetConfig.forpwdcode)
                        .addParams("user_phone", user_phone)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaa", "ForgetPwdActivity" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa", "(ForgetPwdActivity.java:85)<---->" + response);
                                try {
                                    JSONObject job = new JSONObject(response);
                                    if ("0".equals(job.getString("code"))) {
                                        countDownTimerUtils.start();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
            //登录
            case R.id.tv_forgetpwd_login:
                startActivity(new Intent(ForgetPwdActivity.this, LoginActivity.class));
                break;
            //注册
            case R.id.tv_forgetpwd_register:
                startActivity(new Intent(ForgetPwdActivity.this, RegisterActivity.class));
                break;
            //重置密码
            case R.id.tv_forgetpwd_resetpwd:
                String user_code = etForgetpwdYzcode.getText().toString().trim();
                String user_password = etForgetpwdNewpwd.getText().toString().trim();
                if (TextUtils.isEmpty(user_code) || TextUtils.isEmpty(user_password)) {
                    Toast.makeText(this, "Please complete the information.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!user_password.equals(etForgetpwdSurenewpwd.getText().toString().trim())) {
                    Toast.makeText(this, "The passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post()
                        .url(NetConfig.resetpwd)
                        .addParams("user_phone", etForgetpwdPhone.getText().toString().trim())
                        .addParams("user_code", user_code)
                        .addParams("user_password", user_password)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa",
                                        "(ForgetPwdActivity.java:131)" + response);
                                try {
                                    JSONObject json = new JSONObject(response);
                                    String msg = json.getString("msg");
                                    Toast.makeText(ForgetPwdActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (response.contains("success")) {
                                    finish();
                                }
                            }
                        });
                break;
        }
    }
}
