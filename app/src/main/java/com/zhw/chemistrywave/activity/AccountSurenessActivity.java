package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSurenessActivity extends BaseActivity {


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
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.tv_pg_grade)
    TextView tvPgGrade;
    @BindView(R.id.tv_password_s)
    TextView tvPasswordS;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.tv_phone_s)
    TextView tvPhoneS;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_mail_s)
    TextView tvMailS;
    @BindView(R.id.tv_mail)
    TextView tvMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sureness);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        tvTitlebarCenter.setText("账户安全");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_password, R.id.tv_phone, R.id.tv_mail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_password:
                startActivity(new Intent(this, ChangePassWordActivity.class));
                break;
            case R.id.tv_phone:
                startActivity(new Intent(this, ChangePhoneSActivity.class));
                break;
            case R.id.tv_mail:
                startActivity(new Intent(this, BindMailActivity.class));
                break;
        }
    }
}
