package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.HasHandleSearchAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.HasHandleSearchInfo;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class HasHandleActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.lv_has_handle_search)
    ListView lvHasHandleSearch;

    private HasHandleSearchAdapter mAdapter;
    private List<HasHandleSearchInfo> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_has_handle);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {
        mList.clear();
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("page","1");
        params.put("limit","1000");
        params.put("tar_status","1");

        OkHttpUtils
                .post()
                .url(NetConfig.UNHANDLE_XUNPAN)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HasHandleActivity.java:63)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HasHandleActivity.java:70)<---->"+response);
                    }
                });
    }

    private void initView() {

        tvTitlebarCenter.setText("handled inquiry");
        mAdapter = new HasHandleSearchAdapter(mList,this);
        lvHasHandleSearch.setAdapter(mAdapter);

    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
