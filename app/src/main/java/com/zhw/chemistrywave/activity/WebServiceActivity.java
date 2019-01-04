package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebServiceActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.wb_harlan_eye)
    WebView wbHarlanEye;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Harlan eye");
        url = getIntent().getStringExtra("url");
        String name = getIntent().getStringExtra("name");
        tvTitlebarCenter.setText(name);
        Log.e("aaa", "(WebServiceActivity.java:33)<--url-->" + url);

        setWebView();
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }

    private void setWebView() {
        wbHarlanEye.getSettings().setJavaScriptEnabled(true);
        //设置可以访问文件
        wbHarlanEye.getSettings().setAllowFileAccess(true);
        //设置支持缩放
        wbHarlanEye.getSettings().setBuiltInZoomControls(true);
        //自适应屏幕
//        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wbHarlanEye.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wbHarlanEye.getSettings().setLoadWithOverviewMode(true);
        wbHarlanEye.getSettings().setSupportZoom(true);
        wbHarlanEye.getSettings().setDomStorageEnabled(true);
        wbHarlanEye.getSettings().setUseWideViewPort(true);
        wbHarlanEye.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbHarlanEye.getSettings().setBlockNetworkImage(false);
        //扩大比例的缩放
        wbHarlanEye.getSettings().setUseWideViewPort(true);
        //去掉放大缩小按钮
        wbHarlanEye.getSettings().setDisplayZoomControls(false);
        wbHarlanEye.loadUrl(NetConfig.baseurl + url);
        //设置Web视图
        wbHarlanEye.setWebViewClient(new webViewClient());
        //clearWebViewCache();
    }


    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
