package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class CanYuJingJiaActivity extends BaseActivity {


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
    @BindView(R.id.ll_baseinfo)
    LinearLayout llBaseinfo;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.ll_qualitydetail)
    LinearLayout llQualitydetail;
    @BindView(R.id.ll_metalpercent)
    LinearLayout llMetalpercent;
    @BindView(R.id.ll_testreport)
    LinearLayout llTestreport;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.tv_state)
    EditText tvState;
    @BindView(R.id.tv_canyuujingjia_commit)
    TextView tvCanyuujingjiaCommit;
    private String sponsor_id;
    private String cargo_id;
    private String spon_serl;
    private String cargo_id2;
    private static int CARGORETURN = 6;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_yu_jing_jia);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("参与竞价");
        if ("4".equals(MyApplication.getNtype())) {
            sponsor_id = getIntent().getStringExtra("seller_id");
            spon_serl = getIntent().getStringExtra("spon_serl");
            cargo_id = getIntent().getStringExtra("cargo_id");
        }
        if ("5".equals(MyApplication.getNtype())) {
            cargo_id = getIntent().getStringExtra("cargo_id");
            p = getIntent().getIntExtra("position", 0);
            spon_serl = getIntent().getStringExtra("spon_serl");
        }
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_baseinfo, R.id.ll_qualitydetail, R.id.ll_metalpercent, R.id.ll_testreport, R.id.ll_other, R.id.tv_canyuujingjia_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.ll_baseinfo:
//                startActivityForResult(new Intent(this, GoodsBaseInfoActivity.class), 0);
                break;
            case R.id.ll_qualitydetail:
//                if (cargo_id == null) {
//                } else {
//                    startActivity(new Intent(this, QualityDetailActivity.class).putExtra("cargo_id", cargo_id)
//                            .putExtra("bid_id", cargo_id));
//                }
                break;
            case R.id.ll_metalpercent:
//                startActivity(new Intent(this, JinShuHanLiangActivity.class).putExtra("cargo_id", cargo_id)
//                        .putExtra("bid_id", cargo_id));
                break;
            case R.id.ll_testreport:
//                startActivity(new Intent(this, JianCeBaoGaoActivity.class).putExtra("cargo_id", cargo_id)
//                        .putExtra("bid_id", cargo_id));
                break;
            case R.id.ll_other:
                break;
            case R.id.tv_canyuujingjia_commit:
//                commiteData();
                break;
        }
    }

    private void commiteData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("spon_serl", spon_serl);
            jsonObject.put("buyer_id", sponsor_id);
            jsonObject.put("prtp_state", "1");
            jsonObject.put("cargo_id", cargo_id);
            jsonObject.put("cargo_name", cargo_id2);
            jsonObject.put("prtp_type", "1");
            jsonObject.put("seller_id", MyUtils.getUser().getUser_id());
            Log.e("aaa", "====canyucaigou====" + jsonObject.toString());
            if ("5".equals(MyApplication.getNtype())) {
                jsonObject.put("one_key", "1");
                setResult(CARGORETURN, new Intent().putExtra("jsonreturn", jsonObject.toString())
                        .putExtra("position", p));
                finish();
            } else {
                jsonObject.put("one_key", "0");
                final ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(jsonObject.toString());
                OkHttpUtils.post()
                        .url(NetConfig.canyucaigou)
                        .addParams("jsonStr", arrayList.toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaa", "====canyucaigou====" + e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                if (response.contains("success")) {
                                    Log.e("CanYuJingJiaActivity", "onResponse" + response);
                                    Log.e("CanYuJingJiaActivity", "onResponse" + arrayList.toString());
                                    finish();
                                }
                            }
                        });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 21) {
            cargo_id2 = data.getStringExtra("cargo_id");
            etName.setText(data.getStringExtra("name"));
        }
    }
}
