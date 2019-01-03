package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.MainActivity;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zhw.chemistrywave.utils.NetConfig.login;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.rl_login_back)
    RelativeLayout rlLoginBack;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.tv_login_forgetpwd)
    TextView tvLoginForgetpwd;
    @BindView(R.id.tv_login_login)
    TextView tvLoginLogin;
    @BindView(R.id.iv_login_qq)
    ImageView ivLoginQq;
    @BindView(R.id.iv_login_wechat)
    ImageView ivLoginWechat;
    @BindView(R.id.iv_login_weibo)
    ImageView ivLoginWeibo;
    @BindView(R.id.rb_register_supplier)
    RadioButton radioButtonSupplier;
    @BindView(R.id.rb_register_buyer)
    RadioButton radioButtonBuyer;
    @BindView(R.id.rg_register)
    RadioGroup radioGroup;
    private SharedPreferences log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）

        Log.e("aaa",
                "(LoginActivity.java:73)<--screenHeight-->"+screenHeight+"      screenWidth == "+ screenWidth);

        log = getSharedPreferences("Log", MODE_PRIVATE);
        if (null!= log.getString("phone","")){
            String phone = log.getString("phone","");
            String pwd = log.getString("pwd", "");
            login(phone,pwd);
        }

    }

    private void login(final String phone, final String pwd) {

        Log.e("aaa",
                "(LoginActivity.java:90)<--phone-->"+phone);
        Log.e("aaa",
                "(LoginActivity.java:92)<--pwd-->"+pwd);
        HashMap<String, String> params = new HashMap<>();
        params.put("user_phone",phone);
        params.put("user_password",pwd);
        OkHttpUtils.post()
                .url(login)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(LoginActivity.java:88)" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(LoginActivity.java:92)" + response);
                        try {
                            JSONObject json = new JSONObject(response);
                            if (response.contains("success")) {
                                log.edit().putString("phone",phone).commit();
                                log.edit().putString("pwd",pwd).commit();
                                String phone1 = log.getString("phone", "");
                                Log.e("aaa",
                                        "(LoginActivity.java:119)<--phone1-->"+phone1);
                                String data = json.getString("data");
                                SPUtils.put("user", data);
                                String biz_type = MyUtils.getUser().getBiz_type();
                                if ("buyer_en".equals(biz_type)){
                                    SPUtils.put(LoginActivity.this,"user_state","0");
                                }else {
                                    if ("com".equals(MyUtils.getUser().getUser_type())){
                                        SPUtils.put(LoginActivity.this,"user_state","2");
                                    }else {
                                        SPUtils.put(LoginActivity.this,"user_state","1");
                                    }
                                }
                                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("index",0));
                                finish();
                            } else {
                                String msg = json.getString("msg");
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {


                        }

                    }
                });
    }

    @OnClick({R.id.rl_login_back, R.id.tv_login_register, R.id.tv_login_forgetpwd, R.id.tv_login_login, R.id.iv_login_qq, R.id.iv_login_wechat, R.id.iv_login_weibo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_login_back:
                onBackPressed();
                break;
            //注册
            case R.id.tv_login_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            //忘记密码
            case R.id.tv_login_forgetpwd:
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
                break;
            //登录
            case R.id.tv_login_login:

                final String user_phone = etLoginPhone.getText().toString();
                final String user_password = etLoginPwd.getText().toString();
//                if (TextUtils.isEmpty(user_phone) || user_phone.length() != 11) {
//                    Toast.makeText(this, "请填写正确的手机号码", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(user_password)) {
                    Toast.makeText(this, "Password can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, String> params = new HashMap<>();
                params.put("user_phone",user_phone);
                params.put("user_password",user_password);
                OkHttpUtils.post()
                        .url(login)
                        .params(params)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaa",
                                        "(LoginActivity.java:88)" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa",
                                        "(LoginActivity.java:92)" + response);
                                try {
                                    JSONObject json = new JSONObject(response);
                                    if (response.contains("success")) {
                                        log.edit().putString("phone",user_phone).commit();
                                        log.edit().putString("pwd",user_password).commit();
                                        String data = json.getString("data");
                                        SPUtils.put("user", data);
                                        String biz_type = MyUtils.getUser().getBiz_type();
                                        if ("buyer_en".equals(biz_type)){
                                            SPUtils.put(LoginActivity.this,"user_state","0");
                                        }else {
                                            if ("com".equals(MyUtils.getUser().getUser_type())){
                                                SPUtils.put(LoginActivity.this,"user_state","2");
                                            }else {
                                                SPUtils.put(LoginActivity.this,"user_state","1");
                                            }
                                        }
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("index",0));
                                    } else {
                                        String msg = json.getString("msg");
                                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {


                                }

                            }
                        });
                break;
        }
    }

}
