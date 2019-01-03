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

public class ChangePwdActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_nickname)
    EditText etNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Login Password");

    }

    @OnClick({R.id.rl_titlebar_back, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    private void next() {

        String pwd = etNickname.getText().toString().trim();
        String user_password = MyUtils.getUser().getUser_password();
        if (pwd.equals(user_password)){
            startActivity(new Intent(ChangePwdActivity.this,ChangePwdsActivity.class));
            finish();
        }else {
            Toast.makeText(this, "the old password is not correct", Toast.LENGTH_SHORT).show();
        }
    }
}
