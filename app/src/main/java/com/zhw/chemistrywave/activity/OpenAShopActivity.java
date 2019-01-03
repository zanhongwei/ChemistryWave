package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpenAShopActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.cb_select)
    CheckBox cbSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_ashop);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Apply A Shop");
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 化浪的开店协议
     * @param view
     */
    public void hlAgreement(View view){
        Toast.makeText(this, "HarLan Open Shop Agreement", Toast.LENGTH_SHORT).show();
    }

    /**
     * 我要开店
     * @param view
     */
    public void openShop(View view){
        startActivity(new Intent(this,AddOpenShopInfoActivity.class));
        finish();
    }
}
