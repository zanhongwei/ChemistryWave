package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.JingJiaItemAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Cargo;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class JingJiaDetailActivity extends BaseActivity {

    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rl_titlebar_search)
    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.tv_ordernum)
    TextView tvOrdernum;
    @BindView(R.id.lv_jingjiaitem)
    ListView lvJingjiaitem;
    @BindView(R.id.tv_onekeyjingbiao)
    TextView tvOnekeyjingbiao;
    private String spon_id;
    ArrayList<Cargo.DataBean.CargoBeansBean> cargoBeansList = new ArrayList<>();
    private JingJiaItemAdapter jingJiaItemAdapter;
    private String spon_serl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jing_jia_detail);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("详情");
        spon_id = getIntent().getStringExtra("spon_id");
        spon_serl = getIntent().getStringExtra("spon_serl");
        jingJiaItemAdapter = new JingJiaItemAdapter(this, cargoBeansList);
        lvJingjiaitem.setAdapter(jingJiaItemAdapter);
        initData();
    }

    private void initData() {
        OkHttpUtils.post()
                .url(NetConfig.shidancaifou_appointorder)
                .addParams("spon_id", spon_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(JingJiaDetailActivity.java:60)" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(JingJiaDetailActivity.java:56)" + response);
                        Gson gson = new Gson();
                        Cargo cargo = gson.fromJson(response, Cargo.class);
                        tvOrdernum.setText("订单编号：" + spon_serl);
                        cargoBeansList.clear();
                        cargoBeansList.addAll(cargo.getData().getCargoBeans());
                        jingJiaItemAdapter.setSponsor_id(cargo.getData().getSponsor_id() + "");
                        jingJiaItemAdapter.setSpon_serl(cargo.getData().getSpon_serl() + "");
                        jingJiaItemAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_onekeyjingbiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_onekeyjingbiao:
                MyApplication.setNtype("5");
                startActivity(new Intent(this, OneKeyJingBiaoActivity.class).putExtra("spon_id", spon_id));
                break;
        }
    }
}
