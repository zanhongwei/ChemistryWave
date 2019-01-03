package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseTypeActivity extends BaseActivity {

    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        extras = intent.getExtras();

    }

    @OnClick({R.id.rl_back, R.id.ll_choose_per, R.id.ll_choose_com})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_choose_per:
                startActivity(new Intent(this,AddInformationActivity.class).putExtras(extras));
                finish();
                break;
            case R.id.ll_choose_com:
                startActivity(new Intent(this,AddInformationSActivity.class).putExtras(extras));
                finish();
                break;
        }
    }
}
