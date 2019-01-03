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
import com.zhw.chemistrywave.adapters.AddressAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.AddressDetailBean;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class NewAddressManagerActivity extends BaseActivity {

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
    @BindView(R.id.lv_newaddressmanager)
    ListView lvNewaddressmanager;
    @BindView(R.id.tv_newaddressmanager_add)
    TextView tvNewaddressmanagerAdd;
    private List<AddressDetailBean.DataBean.ListBean> mList;
    private AddressAdapter mAdapter;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address_manager);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Address manager");
        lvNewaddressmanager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(NewAddressManagerActivity.this, "aaaa", Toast.LENGTH_SHORT).show();
                Intent data = new Intent();
                data.putExtra("addr_id", mList.get(position).getAddr_id());
                data.putExtra("consignee", mList.get(position).getConsignee());
                data.putExtra("province", mList.get(position).getProvince());
                data.putExtra("city", mList.get(position).getCity());
                data.putExtra("area", mList.get(position).getArea());
                data.putExtra("detail", mList.get(position).getDetail());
                data.putExtra("mobile", mList.get(position).getMobile());
                data.putExtra("landline", mList.get(position).getLandline());
                setResult(222, data);
                finish();
            }
        });
        mList = new ArrayList<>();
        mAdapter = new AddressAdapter(this, mList);
        lvNewaddressmanager.setAdapter(mAdapter);
        user_id = MyUtils.getUser().getUser_id();

        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        JSONObject json = new JSONObject();
        try {
            json.put("user_id", user_id);
            json.put("page", "1");
            json.put("limit", "100");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(NetConfig.addresslist)
                .addParams("jsonStr", json.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(NewAddressManagerActivity.java:85)" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(NewAddressManagerActivity.java:79)" + response);
                        mList.clear();
                        Gson gson = new Gson();
                        mList.addAll(gson.fromJson(response, AddressDetailBean.class).getData().getList());
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_newaddressmanager_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.tv_newaddressmanager_add:
                startActivity(new Intent(this, AddressManagerActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
