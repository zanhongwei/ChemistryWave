package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.RegisterBuyerGvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.PingShiGouMai;
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

public class RegisterBuyerActivity extends BaseActivity {

    @BindView(R.id.rl_registersbuyer_back)
    RelativeLayout rlRegistersbuyerBack;
    @BindView(R.id.gv_registerbuyer)
    GridView gvRegisterbuyer;
    @BindView(R.id.tv_registerbuyer_huanyipi)
    TextView tvRegisterbuyerHuanyipi;
    @BindView(R.id.tv_registersbuyer_haveaccount)
    TextView tvRegistersbuyerHaveaccount;
    @BindView(R.id.tv_registersbuyer_register)
    TextView tvRegistersbuyerRegister;
    private List<PingShiGouMai.DataBean> mList;
    private RegisterBuyerGvAdapter mAdapter;
    /**
     * 用了存放点击的gridview的item的pos和这个pos的选中状态
     */
    private Map<Integer, Boolean> gvChooseMap = new HashMap<Integer, Boolean>();
    private String user_type;
    private String user_phone;
    private String user_code;
    private String user_password;
    private String trade_tendency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_buyer);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        user_type = intent.getStringExtra("user_type");
        user_phone = intent.getStringExtra("user_phone");
        user_code = intent.getStringExtra("user_code");
        user_password = intent.getStringExtra("user_password");
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new RegisterBuyerGvAdapter(this, mList);
        getData();
        gvRegisterbuyer.setAdapter(mAdapter);
        gvRegisterbuyer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                if (view.isActivated()) {
                    view.setActivated(false);
                    gvChooseMap.put(pos, false);
                } else {
                    view.setActivated(true);
                    gvChooseMap.put(pos, true);
                }
            }
        });
    }

    /**
     * 查询平时购买的商品
     */
    private void getData() {
        OkHttpUtils.post().url(NetConfig.pingshigoumaisp)
                .addParams("domestic", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "----查询平时购物的商品--->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----查询平时购物的商品返回--->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    PingShiGouMai pingShiGouMai = gson.fromJson(response, PingShiGouMai.class);
                                    List<PingShiGouMai.DataBean> list = pingShiGouMai.getData();
                                    mList.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });
    }

    @OnClick({R.id.tv_registerbuyer_huanyipi, R.id.rl_registersbuyer_back, R.id.tv_registersbuyer_haveaccount, R.id.tv_registersbuyer_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.rl_registersbuyer_back:
                onBackPressed();
                break;
            case R.id.tv_registerbuyer_huanyipi:
                if (mList != null) {
                    mList.clear();
                }
                getData();
                break;
            //已有账户
            case R.id.tv_registersbuyer_haveaccount:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            //注册
            case R.id.tv_registersbuyer_register:

                Map<Integer, Boolean> deleteMap = mAdapter.getDeleteMap();
                if (deleteMap.size() == 0)//如果map为0或者，map里面的全是false表示一个也没有选中。
                {
                    Toast.makeText(this, "请选择您平时购买的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb = new StringBuffer();
                //遍历map
                for (Map.Entry<Integer, Boolean> entry : deleteMap.entrySet()) {

                    int strkey = entry.getKey();
                    boolean flag = entry.getValue();
                    if (flag == true) {
                        sb.append(mList.get(strkey).getShopIntent_id() + ",");
                    }
                }
                if (sb.length() > 0) {
                    //说明有爱好的内容。否则就提示选择爱好
                    //作用是去掉最后一个， 因为这个数据是从网上获取的，所以需要解码
                    String ss = sb.substring(0, sb.length() - 1);
                    Log.e("aaa", "--您平时购买的商品---->" + ss);
                    trade_tendency = ss;
                    doRegister();

                } else {
                    Toast.makeText(this, "请选择您平时购买的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
//
                break;
        }
    }

    private void doRegister() {
        HashMap<String, String> params = new HashMap<>();
        params.put("user_type", user_type);
        params.put("user_phone", user_phone);
        params.put("user_code", user_code);
        params.put("user_password", user_password);
        params.put("trade_tendency", trade_tendency);
        params.put("domestic", "1");
        Log.e("aaa",
                "(RegisterBuyerActivity.java:146)" + params.toString());
        OkHttpUtils.post()
                .url(NetConfig.register)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(RegisterBuyerActivity.java:153)" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(RegisterBuyerActivity.java:158)" + response);
                        if (response.contains("success")) {
                            Toast.makeText(RegisterBuyerActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterBuyerActivity.this, LoginActivity.class));
                        }
                    }
                });
    }


}
