package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyResultActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_result);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Apply A Store");

    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
