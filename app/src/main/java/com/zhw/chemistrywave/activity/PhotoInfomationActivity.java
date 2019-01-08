package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Axehome_Mr.Z on 2019/1/7
 */
public class PhotoInfomationActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.iv_img1)
    ImageView ivImg1;
    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.iv_img2)
    ImageView ivImg2;
    @BindView(R.id.tv_text2)
    TextView tvText2;
    @BindView(R.id.ll_img1)
    LinearLayout llImg1;
    @BindView(R.id.ll_img2)
    LinearLayout llImg2;
    private String flag;
    private String url, url2, url3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_infomation);
        ButterKnife.bind(this);

        flag = getIntent().getStringExtra("flag");
        url = getIntent().getStringExtra("url");

        tvTitlebarCenter.setText("Details");

        if (flag.equals("idCard")) {
            url2 = getIntent().getStringExtra("url2");
            url3 = getIntent().getStringExtra("url3");

            Glide.with(this).load(NetConfig.baseurl + url2).apply(MyApplication.options).into(ivImg1);
            Glide.with(this).load(NetConfig.baseurl + url3).apply(MyApplication.options).into(ivImg2);
            tvText1.setText("Negative Id card");
            tvText2.setText("The front of your handheld id card");
        } else {
            llImg1.setVisibility(View.INVISIBLE);
            llImg2.setVisibility(View.INVISIBLE);
        }

        switch (flag) {
            case "business":
                tvText.setText("Business license");
                break;
            case "attachment":
                tvText.setText("Other");
                break;
            case "idCard":
                tvText.setText("Positive Id card");
                break;
        }

        Glide.with(this).load(NetConfig.baseurl + url).apply(MyApplication.options).into(ivImg);

    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {

        finish();
    }
}
