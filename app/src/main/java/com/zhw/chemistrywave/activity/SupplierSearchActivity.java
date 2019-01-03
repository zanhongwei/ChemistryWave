package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.SupplierSearchAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.SupplierSearchInfo;
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

public class SupplierSearchActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.lv_supplier_search)
    ListView lvSupplierSearch;

    private List<SupplierSearchInfo.DataBean> mList = new ArrayList<>();
    private SupplierSearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_search);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {

        mList.clear();

        HashMap<String, String> params = new HashMap<>();
        params.put("page","1");
        params.put("limit","1000");
        params.put("buyer_id", MyUtils.getUser().getUser_id());

        OkHttpUtils.post()
                .url(NetConfig.gongyingshangxunpan)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(SupplierSearchActivity.java:63)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(SupplierSearchActivity.java:70)<---->"+response);

                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(SupplierSearchActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                        }else {
                            SupplierSearchInfo supplierSearchBean = new Gson().fromJson(response, SupplierSearchInfo.class);
                            mList.addAll(supplierSearchBean.getData());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {

        tvTitlebarCenter.setText("Supplier inquiry");
        mAdapter = new SupplierSearchAdapter(mList,this);
        lvSupplierSearch.setAdapter(mAdapter);
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {

        finish();
    }
}
