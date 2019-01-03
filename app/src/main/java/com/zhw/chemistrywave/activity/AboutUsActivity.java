package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Axehome_Mr.Z on 2018/12/20
 */
public class AboutUsActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("About Us");
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
