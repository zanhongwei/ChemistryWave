package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class AddXunPanActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_telephone)
    EditText etTelephone;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    private String goods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xun_pan);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        tvTitlebarCenter.setText("Inquiry");
        goods_id = getIntent().getStringExtra("goods_id");
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }

    public void submit(View view) {
        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String telePhone = etTelephone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        final String remark = etRemark.getText().toString().trim();
        
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "The name is null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(address)){
            Toast.makeText(this, "The address is null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(telePhone)){
            Toast.makeText(this, "The contact person is null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "The phone number is null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "The email is null", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("buyer_id", MyUtils.getUser().getUser_id());
        params.put("goods_id",goods_id );
        params.put("com_name", name);
        params.put("com_addr", address);
        params.put("com_contacts",telePhone );
        params.put("com_number",phone);
        params.put("com_mailbox",email );
        params.put("postscript", remark+"");

        OkHttpUtils.post()
                .url(NetConfig.tianjiagongyingshangxunpan)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddXunPanActivity.java:93)<--添加供应商询盘   error-->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddXunPanActivity.java:100)<--添加供应商的询盘  success-->"+response);

                        if (TextUtils.isEmpty(response)){

                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    Toast.makeText(AddXunPanActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(AddXunPanActivity.this,"Fail",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
}
