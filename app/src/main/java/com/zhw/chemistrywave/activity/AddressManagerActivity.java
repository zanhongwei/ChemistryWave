package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.AddressManagerAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.AddressDetailBean;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.view.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;

public class AddressManagerActivity extends BaseActivity implements AddressManagerAdapter.OnMakeAddressListener {


    //    @BindView(R.id.rl_titlebar_back)
//    RelativeLayout rlTitlebarBack;
//    @BindView(R.id.iv_titlebar_line)
//    ImageView ivTitlebarLine;
//    @BindView(R.id.tv_titlebar_center)
//    TextView tvTitlebarCenter;
//    @BindView(R.id.tv_titlebar_right)
//    TextView tvTitlebarRight;
//    @BindView(R.id.rl_titlebar_search)
//    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_address_detail)
    EditText tvAddressDetail;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_telephone)
    EditText tvTelephone;
    @BindView(R.id.rb_select)
    CheckBox rbSelect;
    @BindView(R.id.tv_add_address)
    TextView tvAddAddress;
    @BindView(R.id.lv_address_list)
    MyListView lvAddressList;
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
    private boolean flag = false;//默认地址是否选择
    private AddressManagerAdapter mAdapter;
    private List<AddressDetailBean> mList;
    private String p;
    private String c;
    private String a;
    private String is_default;
    private String user_id;
    private String consignee;
    private String detail;
    private String mobile;
    private String landline;
    private String addr_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);
        user_id = MyUtils.getUser().getUser_id();
        Intent intent = getIntent();
        addr_id = intent.getStringExtra("addr_id");
        initView();
        if (TextUtils.isEmpty(addr_id)) {

        } else {
            tvName.setText(intent.getStringExtra("consignee"));
            p = intent.getStringExtra("province");
            c = intent.getStringExtra("city");
            a = intent.getStringExtra("area");
            tvAddressDetail.setText(intent.getStringExtra("detail"));
            tvPhone.setText(intent.getStringExtra("mobile"));
            tvTelephone.setText(intent.getStringExtra("landline"));
            if ("1".equals(intent.getStringExtra("is_default"))) {
                rbSelect.setChecked(true);
            } else {
                rbSelect.setChecked(false);
            }
        }
    }

    private void initView() {
        tvTitlebarCenter.setText("Address Manager");
        mList = new ArrayList<>();
        mAdapter = new AddressManagerAdapter(this, mList);
        lvAddressList.setAdapter(mAdapter);
        mAdapter.setOnMakeAddressListener(this);
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_province, R.id.tv_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.tv_province:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(AddressManagerActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            Toast.makeText(AddressManagerActivity.this, province.getAreaName() + city.getAreaName(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddressManagerActivity.this, province.getAreaName() + city.getAreaName() + county.getAreaName(), Toast.LENGTH_SHORT).show();
                            p = province.getAreaName();
                            c = city.getAreaName();
                            a = county.getAreaName();
                        }
                    }
                });
                task.execute("贵州", "毕节", "纳雍");
                break;
            case R.id.tv_add_address:
                Log.e("aaa",
                        "(AddressManagerActivity.java:157)<--aaaaaaa-->");
                consignee = tvName.getText().toString();//收货人姓名
                detail = tvAddressDetail.getText().toString();
                mobile = tvPhone.getText().toString();
                if (rbSelect.isChecked()) {
                    is_default = "1";
                } else {
                    is_default = "0";
                }
                if (TextUtils.isEmpty(consignee) || TextUtils.isEmpty(detail) || TextUtils.isEmpty(mobile)) {
                    Toast.makeText(this, "Please fill in the complete information", Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(addr_id)) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("consignee", consignee);
                        params.put("province", "");
                        params.put("city", "");
                        params.put("area", "");
                        params.put("detail", detail);
                        params.put("mobile", mobile);
                        params.put("landline", "");
                        params.put("is_default", is_default);
                        params.put("user_id", user_id);
                        OkHttpUtils.post()
                                .url(NetConfig.addaddress)
                                .params(params)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.e("aaa",
                                                "(AddressManagerActivity.java:198)<--添加地址失败返回-->" + e.getMessage());
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.e("aaa",
                                                "(AddressManagerActivity.java:154)<--添加地址成功返回-->" + response);
                                        if (response.contains("success")) {
                                            finish();
                                        }
                                    }
                                });
                    } else {

                        HashMap<String, String> params = new HashMap<>();
                        params.put("consignee", consignee);
                        params.put("province", p);
                        params.put("city", c);
                        params.put("area", a);
                        params.put("detail", detail);
                        params.put("mobile", mobile);
                        params.put("landline", landline);
                        params.put("is_default", is_default);
                        params.put("addr_id", addr_id);
                        OkHttpUtils.post()
                                .url(NetConfig.changeaddress)
                                .params(params)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.e("aaa",
                                                "(AddressManagerActivity.java:154)" + response);
                                        if (response.contains("success")) {
                                            finish();
                                        }
                                    }
                                });
                    }
                }
                break;
        }
    }

    @Override
    public void onDelect(int position) {
        Toast.makeText(this, "删除按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEdit(int position) {
        Toast.makeText(this, "编辑按钮", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDefault(int position) {
        Toast.makeText(this, "设置默认按钮", Toast.LENGTH_SHORT).show();
    }
}
