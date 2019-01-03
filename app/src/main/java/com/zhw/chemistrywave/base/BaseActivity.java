package com.zhw.chemistrywave.base;

import android.os.Bundle;
import android.view.WindowManager;

import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        MyApplication.getInstance().addActivity(this);
        setContentView(R.layout.activity_base);
    }
}
