package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.MyServiceAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.MyService;
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

public class MyServiceActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.lv_my_service)
    ListView lvMyService;

    private MyServiceAdapter mAdapter;
    private List<MyService.DataBean.ListBean> mList = new ArrayList<>();
    private String name,flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {
        mList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page","1");
            jsonObject.put("limit","1000");
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("five_type", flag);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils
                .post()
                .url(NetConfig.MYSERVICE)
                .addParams("jsonStr",jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyServiceActivity.java:70)<--我申请的服务 error-->"+e.getMessage());
                        Toast.makeText(MyServiceActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyServiceActivity.java:77)<--我申请的服务 success-->"+response);

                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(MyServiceActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            MyService myService = new Gson().fromJson(response, MyService.class);
                            mList.addAll(myService.getData().getList());
                            mAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

    private void initView() {

        name = getIntent().getStringExtra("name");
        tvTitlebarCenter.setText(name);
        switch (name){
            case "Platform guarantee":
                flag = "ser2";
                break;
            case "Harlan eye":
                flag = "har";
                break;
            case "Supply chain finance":
                flag = "ser3";
                break;
            case "Price express":
                flag = "ser1";
                break;
        }

        mAdapter = new MyServiceAdapter(this,mList);
        lvMyService.setAdapter(mAdapter);

    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
