package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.OneKeyJingBiaoAdapter;
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

public class OneKeyJingBiaoActivity extends BaseActivity {


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
    @BindView(R.id.tv_lv_onekey_number)
    TextView tvLvOnekeyNumber;
    @BindView(R.id.lv_onekeyjingbiao)
    ListView lvOnekeyjingbiao;
    @BindView(R.id.tv_onekeyjingbiao)
    TextView tvOnekeyjingbiao;
    private String spon_id;
    ArrayList<Cargo.DataBean.CargoBeansBean> cargoBeanList = new ArrayList<>();
    private OneKeyJingBiaoAdapter oneKeyJingBiaoAdapter;
    private String[] cargos;
    private String spon_serl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_key_jing_biao);
        ButterKnife.bind(this);
        spon_id = getIntent().getStringExtra("spon_id");
        oneKeyJingBiaoAdapter = new OneKeyJingBiaoAdapter(this, cargoBeanList);
        lvOnekeyjingbiao.setAdapter(oneKeyJingBiaoAdapter);
        lvOnekeyjingbiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForResult(new Intent(OneKeyJingBiaoActivity.this, CanYuJingJiaActivity.class).putExtra("position", position)
                        .putExtra("cargo_id", cargoBeanList.get(position).getCargo_id() + "").putExtra("spon_serl", spon_serl), 0);
            }
        });
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

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "========" + response);
                        cargoBeanList.clear();
                        Gson gson = new Gson();
                        Cargo cargo = gson.fromJson(response, Cargo.class);
                        spon_serl = cargo.getData().getSpon_serl();
                        tvLvOnekeyNumber.setText("订单编号：" + cargo.getData().getSpon_serl());
                        cargoBeanList.addAll(cargo.getData().getCargoBeans());
                        cargos = new String[cargoBeanList.size()];
                        oneKeyJingBiaoAdapter.notifyDataSetChanged();
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
                ArrayList<String> strs = new ArrayList<>();
                for (String str : cargos
                        ) {
                    strs.add(str);
                }
                Log.e("aaa", "========" + strs);
                OkHttpUtils.post()
                        .url(NetConfig.canyucaigou)
                        .addParams("jsonStr", strs.toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaa", "====canyucaigou====" + e.toString());

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa", "====" + "canyucaigou" + "====" + response);
                                if (response.contains("success")) {
                                    finish();
                                }
                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 6) {
            int p = data.getIntExtra("position", 0);
            cargos[p] = data.getStringExtra("jsonreturn");
        }
    }
}
