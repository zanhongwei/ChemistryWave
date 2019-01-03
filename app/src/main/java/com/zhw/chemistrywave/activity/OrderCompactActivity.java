package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderCompactActivity extends BaseActivity {


    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.wb_contract)
    WebView wbContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_compact);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Contract");
        setwebModeChoose();
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setwebModeChoose() {
        wbContract.getSettings().setJavaScriptEnabled(true);
        //设置可以访问文件
        wbContract.getSettings().setAllowFileAccess(true);
        //设置支持缩放
        wbContract.getSettings().setBuiltInZoomControls(true);
        //自适应屏幕
//        webModeChoose.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wbContract.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wbContract.getSettings().setLoadWithOverviewMode(true);
        wbContract.getSettings().setSupportZoom(true);
        wbContract.getSettings().setDomStorageEnabled(true);
        wbContract.getSettings().setUseWideViewPort(true);
        wbContract.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbContract.getSettings().setBlockNetworkImage(false);
        //扩大比例的缩放
        wbContract.getSettings().setUseWideViewPort(true);
        //去掉放大缩小按钮
        wbContract.getSettings().setDisplayZoomControls(false);
        wbContract.loadUrl("http://docs.google.com/gviewembedded=true&url="
                + NetConfig.baseurl+"upload/0/InternationalHarLan.doc");
//        wbContract.loadUrl("http://157.10.1.105:8020/Vehicle_cleaning/add_car_type.html");
        //设置Web视图
        wbContract.setWebViewClient(new webViewClient());
        //clearwebModeChooseCache();
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            Log.e("aaa",
                    "(webViewClient.java:76)<---->" + url);
            if (url.contains("hr_PersonalCen")) {
                finish();
                return true;
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }
}
