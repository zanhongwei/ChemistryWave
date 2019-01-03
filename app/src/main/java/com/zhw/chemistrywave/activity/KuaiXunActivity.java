package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.KuaiXunLvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Kuaixun;
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

public class KuaiXunActivity extends BaseActivity implements AdapterView.OnItemClickListener {


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
    @BindView(R.id.lv_kuaixun)
    ListView lvKuaixun;
    private List<Kuaixun.DataBean.ListBean> mList;
    private KuaiXunLvAdapter mAdaoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuai_xun);
        ButterKnife.bind(this);
        initData();

    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("News");
        mList = new ArrayList<>();
        mAdaoter = new KuaiXunLvAdapter(KuaiXunActivity.this, mList);
        getData();
        lvKuaixun.setAdapter(mAdaoter);
        lvKuaixun.setOnItemClickListener(this);
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("news_type", "2");
        map.put("page", "1");
        map.put("limit", "1000000");
        OkHttpUtils.post().url(NetConfig.gonggao_url)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--快讯返回-error->" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--快讯返回-->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Kuaixun kuaixun = gson.fromJson(response, Kuaixun.class);
                                    List<Kuaixun.DataBean.ListBean> list = kuaixun.getData().getList();
                                    mList.addAll(list);
                                    mAdaoter.notifyDataSetChanged();
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
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int news_id = mList.get(i).getNews_id();
        startActivity(new Intent(this,NewsDetailActivity.class).putExtra("news_id",news_id+""));
    }
}
