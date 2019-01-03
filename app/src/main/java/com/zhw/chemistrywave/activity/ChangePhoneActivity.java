package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_old_phone)
    TextView tvOldPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    private String user_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        user_phone = MyUtils.getUser().getUser_phone();
        initView();
    }

    private void initView() {
        tvTitlebarCenter.setText("Change phone number");
        tvOldPhone.setText(user_phone);
    }

    @OnClick({R.id.rl_titlebar_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.btn_commit:
                commitPassword();
                break;
        }
    }

    private void commitPassword() {
        String user_password = MyUtils.getUser().getUser_password();
        String trim = etPassword.getText().toString().trim();
        if (trim.equals(user_password)){
            // TODO: 2018/5/10 验证成功跳转到下一界面
            startActivity(new Intent(this,SetPhoneActivity.class));
        }else {
            Toast.makeText(this, "the passwords is error，please try again！", Toast.LENGTH_SHORT).show();
        }

    }
}
