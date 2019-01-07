package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class SetPhoneActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code)
    EditText etCode;

    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_phone);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Change phone number");
        countDownTimerUtils = new CountDownTimerUtils(tvSendCode,59000,1000);
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_send_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_send_code:
                String phone = etPhoneNumber.getText().toString().trim();

                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "Please fill in phone！", Toast.LENGTH_SHORT).show();
                }else {
                    sendCode(phone);
                }
                break;
            case R.id.btn_commit:
                String trim = etCode.getText().toString().trim();
                if (TextUtils.isEmpty(trim)){
                    Toast.makeText(this, "Please fill in code", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this,"Commit",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void sendCode(String phone) {

        countDownTimerUtils.start();
        OkHttpUtils.post()
                .url(NetConfig.sendcode)
                .addParams("user_phone",phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(SetPhoneActivity.java:74)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(SetPhoneActivity.java:81)<---->"+response);
                        if (TextUtils.isEmpty(response)){
                            
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    Toast.makeText(SetPhoneActivity.this, "Send successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(SetPhoneActivity.this, "Send unsuccessfully", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
