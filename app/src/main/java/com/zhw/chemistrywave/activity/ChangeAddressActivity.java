package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ChangeAddressActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_contact_person)
    EditText etContactPerson;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_select_state)
    ImageView ivSelectState;

    private boolean isDefault = true;

    private String tag = "0";//0 新增状态 1 编辑状态
    private String addr_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Address");

        if (null!=getIntent().getExtras()){
            tag = "1";
            Bundle bundle = getIntent().getExtras();
            addr_id = bundle.getString("addr_id");
            String name = bundle.getString("name");
            String mobile = bundle.getString("mobile");
            String detail = bundle.getString("detail");
            boolean is_default = bundle.getBoolean("is_default");
            this.isDefault = is_default;
            initView(addr_id,name,mobile,detail,is_default);
        }
    }

    private void initView(String addr_id,String name,String mobile,String detail,boolean isDefault) {
        etContactPhone.setText(mobile);
        etContactPerson.setText(name);
        etAddress.setText(detail);
        if (isDefault){
            ivSelectState.setImageResource(R.drawable.address_moren_selected);
        }else {
            ivSelectState.setImageResource(R.drawable.address_moren_unselected);
        }
    }

    @OnClick({R.id.rl_titlebar_back,R.id.iv_select_state,R.id.tv_select_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.iv_select_state:
            case R.id.tv_select_state:
                if (isDefault){
                    isDefault=false;
                    ivSelectState.setImageResource(R.drawable.address_moren_unselected);
                } else{
                    isDefault = true;
                    ivSelectState.setImageResource(R.drawable.address_moren_selected);
                }
                break;
        }
    }

    public void addAddress(View view){
        String address = etAddress.getText().toString().trim();
        String contactPer = etContactPerson.getText().toString().trim();
        String contactPhone = etContactPhone.getText().toString().trim();

        if (TextUtils.isEmpty(address)){
            Toast.makeText(this, "please fill in receiving address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contactPer)){
            Toast.makeText(this,"please fill in contact person",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contactPhone)){
            Toast.makeText(this, "please fill in contact number", Toast.LENGTH_SHORT).show();
            return;
        }

        String state = "";
        if (isDefault){
            state = "1";
        }else{
            state ="0";
        }

        if (tag.equals("0")){
            HashMap<String, String> params = new HashMap<>();
            params.put("consignee",contactPer);
            params.put("detail",address);
            params.put("mobile",contactPhone);
            params.put("consignee",contactPer);
            params.put("is_default",state);
            params.put("user_id", MyUtils.getUser().getUser_id());

            OkHttpUtils
                    .post()
                    .url(NetConfig.addaddress)
                    .params(params)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("aaa",
                                    "(ChangeAddressActivity.java:110)<---->"+e.getMessage());
                            Toast.makeText(ChangeAddressActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("aaa",
                                    "(ChangeAddressActivity.java:117)<---->"+response);
                            if (TextUtils.isEmpty(response)){
                                Toast.makeText(ChangeAddressActivity.this, "network error", Toast.LENGTH_SHORT).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int code = jsonObject.getInt("code");
                                    if (code==0){
                                        finish();
                                    }
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ChangeAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
        }else {

            HashMap<String, String> params = new HashMap<>();
            params.put("addr_id",addr_id);
            params.put("consignee",contactPer);
            params.put("detail",address);
            params.put("mobile",contactPhone);
            params.put("consignee",contactPer);
            params.put("is_default",state);
            params.put("user_id", MyUtils.getUser().getUser_id());

            OkHttpUtils.post()
                    .url(NetConfig.changeaddress)
                    .params(params)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("aaa",
                                    "(ChangeAddressActivity.java:185)<---->"+e.getMessage());
                            Toast.makeText(ChangeAddressActivity.this, "network error", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("aaa",
                                    "(ChangeAddressActivity.java:191)<---->"+response);
                            if (TextUtils.isEmpty(response)){
                                Toast.makeText(ChangeAddressActivity.this, "network error", Toast.LENGTH_SHORT).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int code = jsonObject.getInt("code");
                                    if (code==0){
                                        finish();
                                    }
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ChangeAddressActivity.this, msg, Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

        }


    }

}
