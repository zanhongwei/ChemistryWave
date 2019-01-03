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
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.CanYuJingJIaDetailLvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.CanYuJingJiaBean;
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

public class CanYuJingJiaDetailActivity extends BaseActivity {


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
    @BindView(R.id.tv_detail_dingdanbianhao)
    TextView tvDetailDingdanbianhao;
    @BindView(R.id.lv_detail)
    ListView lvDetail;
    @BindView(R.id.tv_detail_chakanyijianjingbiaozhe)
    TextView tvDetailChakanyijianjingbiaozhe;
    private int p;
    private String str;
    ArrayList<CanYuJingJiaBean.DataBean.ListBean> mlist = new ArrayList<>();
    ArrayList<CanYuJingJiaBean.DataBean.ListBean.ParticipationBeansBean> pBeans = new ArrayList<>();
    private CanYuJingJIaDetailLvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canyujingjiadetail);
        ButterKnife.bind(this);
        initData();
        str = getIntent().getStringExtra("str");
        p = getIntent().getIntExtra("position", 0);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        requestInternet(str);
        tvTitlebarCenter.setText("详情");
        mAdapter = new CanYuJingJIaDetailLvAdapter(CanYuJingJiaDetailActivity.this, pBeans);
        lvDetail.setAdapter(mAdapter);

    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_detail_chakanyijianjingbiaozhe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.tv_detail_chakanyijianjingbiaozhe:
                startActivity(new Intent(CanYuJingJiaDetailActivity.this, YiJianJingBiaoZheActivity.class));
                break;
        }
    }

    private void requestInternet(String str) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("seller_id", MyUtils.getUser().getUser_id() + "");
            jsonObject.put("prtp_state", str);
            jsonObject.put("prtp_type", "1");
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
            OkHttpUtils.post()
                    .url(NetConfig.myInvokebid)
                    .addParams("jsonStr", jsonObject.toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("aaa", "WoCanYuDeJingJiaActivity" + e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mlist.clear();
                            Log.e("aaa", "WoCanYuDeJingJiaActivity" + response);
                            Gson gson = new Gson();
                            mlist.addAll(gson.fromJson(response, CanYuJingJiaBean.class).getData().getList());
                            tvDetailDingdanbianhao.setText("订单编号：" + mlist.get(p).getSpon_serl());
                            pBeans.clear();
                            pBeans.addAll(mlist.get(p).getParticipationBeans());
                            mAdapter.notifyDataSetChanged();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
