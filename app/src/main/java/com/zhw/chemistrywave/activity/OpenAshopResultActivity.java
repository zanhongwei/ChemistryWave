package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenAshopResultActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ashop_result);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Application Result");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_commit:
                startActivity(new Intent(this,AddOpenShopInfoActivity.class).putExtra("flag","edit"));
                break;
        }
    }
}
