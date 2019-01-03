package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.CountDownTimerUtils;
import com.zhw.chemistrywave.utils.VertifyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindMailActivity extends BaseActivity {


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
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_mail_address)
    EditText etMailAddress;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.tv_getcode)
    TextView tvGetcode;
    @BindView(R.id.tv_bind)
    TextView tvBind;
    private CountDownTimerUtils countDownTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_mail);
        ButterKnife.bind(this);
        countDownTimerUtils = new CountDownTimerUtils(tvGetcode, 59000, 1000);
        initView();
    }

    private void initView() {
        tvTitlebarCenter.setText("绑定邮箱账号");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_getcode, R.id.tv_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_getcode:
                String emailAddress = etMailAddress.getText().toString().trim();
                if (VertifyUtils.isEmail(emailAddress)) {
                    countDownTimerUtils.start();
                } else {
                    Toast.makeText(this, "请输入正确的邮箱地址！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_bind:

                break;
        }
    }
}
