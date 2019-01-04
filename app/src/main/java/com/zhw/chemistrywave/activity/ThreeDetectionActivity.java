package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.ThreeDetectionAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.ThreeDection;
import com.zhw.chemistrywave.bean.ThreeDectionS;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ThreeDetectionActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.lv_detection)
    GridView lvDetection;
    private ThreeDetectionAdapter mAdapter;
    private List<ThreeDectionS.DataBean> mList;
    private String order_id;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_detection);
        ButterKnife.bind(this);
        order_id = getIntent().getStringExtra("order_id");
        title = getIntent().getStringExtra("title");
        initView();
    }

    private void initView() {

        tvTitlebarCenter.setText(title);
        mList = new ArrayList<>();
        mAdapter = new ThreeDetectionAdapter(ThreeDetectionActivity.this, mList);
        getData();
        lvDetection.setAdapter(mAdapter);
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("order_id", order_id);
        Log.e("aaa", "(ThreeDetectionActivity.java:67)<--order_id-->" + order_id);
        String url = "";
        if (title.equals("Supervisor")) url = NetConfig.mianfeijianliorder;
        else url = NetConfig.sanfangjiancelist_url;
        OkHttpUtils.post().url(url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---我的三方检测返回---error-->" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---我的三方检测返回----->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    if (title.equals("Supervisor")) {
                                        ThreeDectionS threeDection = gson.fromJson(response, ThreeDectionS.class);
                                        List<ThreeDectionS.DataBean> list = threeDection.getData();
                                        mList.addAll(list);
                                        Log.e("aaa", "----mList.addAll(list);------->" + mList.toString());
                                        mAdapter.notifyDataSetChanged();
                                    } else {
                                        ThreeDection threeDection = gson.fromJson(response, ThreeDection.class);
                                        String check_url = threeDection.getData().getCheck_url();
                                        ThreeDectionS threeDectionS = new ThreeDectionS();
                                        threeDectionS.getData().get(0).setSuper_url(check_url);
                                        mList.add(threeDectionS.getData().get(0));
                                        Log.e("aaa", "----mList.addAll(list);------->" + mList.toString());
                                        mAdapter.notifyDataSetChanged();
                                    }
//

                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
