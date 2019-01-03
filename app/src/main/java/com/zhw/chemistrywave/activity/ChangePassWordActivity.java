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
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class ChangePassWordActivity extends BaseActivity {


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
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.et_verifys)
    EditText etVerifys;
    @BindView(R.id.tv_change)
    TextView tvChange;
    private User user;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);
        ButterKnife.bind(this);
        user = MyUtils.Touser((String) SPUtils.get("user", ""));
        user_id = user.getUser_id();
        initView();

        etVerify.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String newPassword = etVerify.getText().toString().trim();
                    if (newPassword.length() >= 6 && newPassword.length() <= 20) {
                    } else {
                        Toast.makeText(ChangePassWordActivity.this, "密码格式不正确！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initView() {
        tvTitlebarCenter.setText("修改密码");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_change:
                String old_pass = etOldPassword.getText().toString().trim();
                String new_pass = etVerify.getText().toString().trim();
                String vertify = etVerifys.getText().toString().trim();

                if (TextUtils.isEmpty(old_pass)) {
                    Toast.makeText(this, "原密码输入不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (new_pass.length() >= 6 && new_pass.length() <= 20) {
                if (vertify.equals(new_pass)) {
                    toChange(old_pass, new_pass, vertify);
                } else {
                    Toast.makeText(this, "两次输入的密码不一致！请重新输入", Toast.LENGTH_SHORT).show();
                    etVerifys.setText("");
                    return;
                }
//                } else {
//                    Toast.makeText(this, "密码输入长度有误！", Toast.LENGTH_SHORT).show();
//                }

                break;
        }
    }

    private void toChange(String old_pass, String new_pass, String vertify) {
        OkHttpUtils.post()
                .url(NetConfig.changepwd)
                .addParams("user_id", user_id)
                .addParams("old_pass", old_pass)
                .addParams("new_pass", new_pass)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(ChangePassWordActivity.java:110)" + response);
                        if (response.contains("success")) {
                            Toast.makeText(ChangePassWordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}
