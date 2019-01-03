package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.zhw.chemistrywave.MainActivity;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.rl_register_back)
    RelativeLayout rlRegisterBack;
    @BindView(R.id.tv_register_type)
    TextView tvRegisterType;
    @BindView(R.id.iv_register_type)
    ImageView ivRegisterType;
    @BindView(R.id.ll_register_type)
    LinearLayout llRegisterType;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_yzcode)
    EditText etRegisterYzcode;
    @BindView(R.id.tv_register_getcode)
    TextView tvRegisterGetcode;
    @BindView(R.id.et_register_newpwd)
    EditText etRegisterNewpwd;
    @BindView(R.id.et_register_surenewpwd)
    EditText etRegisterSurenewpwd;
    @BindView(R.id.rb_register_buyer)
    RadioButton rbRegisterBuyer;
    @BindView(R.id.rb_register_supplier)
    RadioButton rbRegisterSupplier;
    @BindView(R.id.rg_register)
    RadioGroup rgRegister;
    @BindView(R.id.tv_register_haveaccount)
    TextView tvRegisterHaveaccount;
    @BindView(R.id.tv_register_next)
    TextView tvRegisterNext;
    private PopupWindow popWnd;
    private ArrayList<String> listFour;
    private TimeCount time;
    private String code = "";
    private String user_password;
    private String user_phone;
    private Intent intent;
    private LoadingDailog dialog;
    private SharedPreferences log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        time = new TimeCount(60000, 1000);
        initData();

        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();

        log = getSharedPreferences("Log", MODE_PRIVATE);

    }

    /**
     * 初始化数据源
     */
    private void initData() {
        rgRegister.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.rb_register_supplier) {
                    tvRegisterNext.setText("Next");
                } else {
                    tvRegisterNext.setText("Register");
                }
            }
        });
    }

    @OnClick({R.id.rl_register_back, R.id.ll_register_type, R.id.tv_register_getcode, R.id.tv_register_haveaccount, R.id.tv_register_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_register_back:
                onBackPressed();
                break;
            //获取验证码
            case R.id.tv_register_getcode:

                String phone = etRegisterPhone.getText().toString();
                if (!TextUtils.isEmpty(phone)) {
                    dialog.show();
                    OkHttpUtils.post()
                            .url(NetConfig.sendcode)
                            .addParams("user_phone", phone)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("aaa",
                                            "(RegisterActivity.java:114)" + e.toString());
                                    dialog.dismiss();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("aaa",
                                            "(RegisterActivity.java:118)" + response);
                                    dialog.dismiss();
                                    try {

                                        if (response.contains("registered")) {
                                            Toast.makeText(RegisterActivity.this, "This phone had been registered", Toast.LENGTH_SHORT).show();
                                        } else {
                                            time.start();
                                            JSONObject json = new JSONObject(response);
                                            code = json.getString("data");
                                            Toast.makeText(RegisterActivity.this, "Send successfully", Toast.LENGTH_SHORT).show();
//                                            etRegisterYzcode.setText(code);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(this, "The phone number can't be empty", Toast.LENGTH_SHORT).show();
                }
                break;
            //已有账户
            case R.id.tv_register_haveaccount:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            //下一步
            case R.id.tv_register_next:
                user_phone = etRegisterPhone.getText().toString();
                user_password = etRegisterNewpwd.getText().toString();
                String pwdconfig = etRegisterSurenewpwd.getText().toString();
                if (code.equals(etRegisterYzcode.getText().toString())) {
                    if ((!TextUtils.isEmpty(user_password)) && user_password.equals(pwdconfig)) {
//
                        if (rbRegisterBuyer.isChecked()) {
                            registerBuyer();//采购商的注册
                        } else {
                            registerSupplier();
                        }
                    } else {
                        Toast.makeText(this, "The passwords entered are inconsistent", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Enter your correct phone code", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void registerSupplier() {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_phone", user_phone);
        params.put("user_password", user_password);
        params.put("user_code", code);
        params.put("biz_type", "seller_en");
        params.put("user_state", "inactive");
//        user_phone
//                user_password
//        user_code
//                biz_type
//        user_state


        OkHttpUtils.post()
                .url(NetConfig.register)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(RegisterActivity.java:221)<---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(RegisterActivity.java:227)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(RegisterActivity.this,"NetWork error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    String phone1 = log.getString("phone", "");
                                    Log.e("aaa",
                                            "(LoginActivity.java:119)<--phone1-->"+phone1);
                                    String data = jsonObject.getString("data");
                                    SPUtils.put("user", data);
                                    String biz_type = MyUtils.getUser().getBiz_type();
                                    if ("buyer_en".equals(biz_type)){
                                        SPUtils.put(RegisterActivity.this,"user_state","0");
                                    }else {
                                        if ("com".equals(MyUtils.getUser().getUser_type())){
                                            SPUtils.put(RegisterActivity.this,"user_state","2");
                                        }else {
                                            SPUtils.put(RegisterActivity.this,"user_state","1");
                                        }
                                    }
                                    JSONObject data1 = jsonObject.getJSONObject("data");
                                    String user_id = data1.getString("user_id");
                                    intent = new Intent(RegisterActivity.this, ChooseTypeActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("user_id", user_id);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
//
                    }
                });

    }

    private void registerBuyer() {

        HashMap<String, String> params = new HashMap<>();
        params.put("user_phone", user_phone);
        params.put("user_password", user_password);
        params.put("user_code", code);
        params.put("biz_type", "buyer_en");
        params.put("user_state", "normal");
//        user_phone
//                user_password
//        user_code
//                biz_type
//        user_state


        OkHttpUtils.post()
                .url(NetConfig.register)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(RegisterActivity.java:261)<---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(RegisterActivity.java:267)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(RegisterActivity.this, "Service Error！", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                                if (code==0){
                                    log.edit().putString("phone",user_phone).commit();
                                    log.edit().putString("pwd",user_password).commit();

                                    String phone1 = log.getString("phone", "");
                                    Log.e("aaa",
                                            "(LoginActivity.java:119)<--phone1-->"+phone1);
                                    String data = jsonObject.getString("data");
                                    SPUtils.put("user", data);
                                    String biz_type = MyUtils.getUser().getBiz_type();
                                    if ("buyer_en".equals(biz_type)){
                                        SPUtils.put(RegisterActivity.this,"user_state","0");
                                    }else {
                                        if ("com".equals(MyUtils.getUser().getUser_type())){
                                            SPUtils.put(RegisterActivity.this,"user_state","2");
                                        }else {
                                            SPUtils.put(RegisterActivity.this,"user_state","1");
                                        }
                                    }
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class).putExtra("index",0));
                                    finish();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    /**
     * 倒计时类
     */
    class TimeCount extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // mBtnGetcode.setBackgroundColor(Color.parseColor("#B6B6D8"));
            tvRegisterGetcode.setClickable(false);
            tvRegisterGetcode.setText("" + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            tvRegisterGetcode.setText("Send code");
            tvRegisterGetcode.setClickable(true);
            //  mBtnGetcode.setBackgroundColor(Color.parseColor("#4EB84A"));
        }
    }
}
