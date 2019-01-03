package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.User;
import com.zhw.chemistrywave.utils.CountDownTimerUtils;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.VertifyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ChangePhoneSActivity extends BaseActivity {


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
    @BindView(R.id.tv_new_phone)
    EditText tvNewPhone;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    private CountDownTimerUtils countDownTimerUtils;
    private String newPhone;
    private User user;
    private String codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_s);
        ButterKnife.bind(this);
        countDownTimerUtils = new CountDownTimerUtils(tvGetcode, 59000, 1000);
        user = MyUtils.Touser((String) SPUtils.get("user", ""));
        initView();
    }

    private void initView() {
        tvTitlebarCenter.setText("更换手机号");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_getcode, R.id.tv_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_getcode:
                newPhone = tvNewPhone.getText().toString().trim();
                if (VertifyUtils.isMobileNO(newPhone)) {
                    Toast.makeText(this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                } else {

                    OkHttpUtils.post()
                            .url(NetConfig.changephonecode)
                            .addParams("user_id", user.getUser_id())
                            .addParams("user_phone", newPhone)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Log.e("aaa",
                                            "(ChangePhoneSActivity.java:74)" + e.toString());
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.e("aaa",
                                            "(ChangePhoneSActivity.java:78)" + response);
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        if ("0".equals(jsonObject.getString("code"))) {
                                            codes = jsonObject.getString("data");
                                            countDownTimerUtils.start();
                                            Toast.makeText(ChangePhoneSActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                }
                break;
            case R.id.tv_complete:
                String code = tvCode.getText().toString().trim();
                if (TextUtils.isEmpty(code)) {
                    Toast.makeText(this, "请输入验证码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!code.equals(codes)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post()
                        .url(NetConfig.changephone)
                        .addParams("user_id", user.getUser_id())
                        .addParams("user_phone", newPhone)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa",
                                        "(ChangePhoneSActivity.java:124)" + response);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if ("0".equals(jsonObject.getString("code"))) {
                                        Toast.makeText(ChangePhoneSActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                break;
        }
    }
}
