package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.wb_news_detail)
    WebView wbNewsDetail;
    private String news_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        news_id = getIntent().getStringExtra("news_id");

        tvTitlebarCenter.setText("Contract");
        setwebModeChoose();
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }


    private void setwebModeChoose() {
        wbNewsDetail.getSettings().setJavaScriptEnabled(true);
        //设置可以访问文件
        wbNewsDetail.getSettings().setAllowFileAccess(true);
        //设置支持缩放
        wbNewsDetail.getSettings().setBuiltInZoomControls(true);
        //自适应屏幕
//        webModeChoose.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wbNewsDetail.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wbNewsDetail.getSettings().setLoadWithOverviewMode(true);
        wbNewsDetail.getSettings().setSupportZoom(true);
        wbNewsDetail.getSettings().setDomStorageEnabled(true);
        wbNewsDetail.getSettings().setUseWideViewPort(true);
        wbNewsDetail.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wbNewsDetail.getSettings().setBlockNetworkImage(false);
        //扩大比例的缩放
        wbNewsDetail.getSettings().setUseWideViewPort(true);
        //去掉放大缩小按钮
        wbNewsDetail.getSettings().setDisplayZoomControls(false);
        wbNewsDetail.loadUrl(NetConfig.baseurl+"news_h5.html?news_id="+news_id);
//        wbNewsDetail.loadUrl("http://157.10.1.105:8020/Vehicle_cleaning/add_car_type.html");
        //设置Web视图
        wbNewsDetail.setWebViewClient(new webViewClient());
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
